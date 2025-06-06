package com.syming.castlevaniacraft.entity.projectiles.subweapons;

import com.syming.castlevaniacraft.entity.projectiles.CCThrowableWeapon;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityHolyCross extends CCThrowableWeapon {

    public static final DataParameter<Float> THROWER_X = EntityDataManager.createKey(EntityHolyCross.class,
            DataSerializers.FLOAT);
    public static final DataParameter<Float> THROWER_Y = EntityDataManager.createKey(EntityHolyCross.class,
            DataSerializers.FLOAT);
    public static final DataParameter<Float> THROWER_Z = EntityDataManager.createKey(EntityHolyCross.class,
            DataSerializers.FLOAT);
    public static final DataParameter<Float> YAW = EntityDataManager.createKey(EntityHolyCross.class,
            DataSerializers.FLOAT);
    public static final DataParameter<ItemStack> STACK = EntityDataManager.createKey(EntityHolyCross.class,
            DataSerializers.ITEM_STACK);

    private Item.ToolMaterial material;

    public EntityHolyCross(World worldIn) {
        super(worldIn);
    }

    public EntityHolyCross(World worldIn, EntityLivingBase thrower, ItemStack stack, Item.ToolMaterial material) {
        super(worldIn, thrower);
        this.material = material;
        if (!stack.isEmpty() && stack != null) {
            setItemStack(stack);
        }
        setCoordinates(thrower.posX, thrower.posY + thrower.getEyeHeight(), thrower.posZ);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(YAW, 0F);
        this.dataManager.register(THROWER_X, 0F);
        this.dataManager.register(THROWER_Y, 0F);
        this.dataManager.register(THROWER_Z, 0F);
        this.rotationYaw = dataManager.get(YAW);

        this.dataManager.register(STACK, ItemStack.EMPTY);

    }

    public double getThrowerX() {
        return dataManager.get(THROWER_X);
    }

    public double getThrowerY() {
        return dataManager.get(THROWER_Y);
    }

    public double getThrowerZ() {
        return dataManager.get(THROWER_Z);
    }

    public void setCoordinates(double playerX, double playerY, double playerZ) {
        dataManager.set(THROWER_X, (float) playerX);
        dataManager.set(THROWER_Y, (float) playerY);
        dataManager.set(THROWER_Z, (float) playerZ);
    }

    @Override
    protected float getGravityVelocity() {
        return 0F;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        Entity hitEntity = result.entityHit;
        ItemStack stack = getItemStack();
        if (!this.world.isRemote && getThrower() != null) {
            if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
                if (!stack.isEmpty()) {
                    entityDropItem(stack, 0.5F);
                    stack.attemptDamageItem(1, this.rand, getThrower() instanceof EntityPlayerMP ? (EntityPlayerMP) getThrower() : null);
                    setDead();
                    System.out.println("Holy Cross has hit a block");

                } else {
                    System.out.println("Stack is Empty");
                }
            } else if (result.typeOfHit == RayTraceResult.Type.ENTITY) {
                if (hitEntity != null) {
                    if (hitEntity == getThrower()) {
                        if (getThrower() instanceof EntityPlayer player) {
                            if (stack.getMaxDamage() - stack.getItemDamage() > 0) {
                                player.inventory.addItemStackToInventory(stack);
                                this.world.playSound(player, posX, posY, posZ, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.MASTER, 0.5F, 1.0F);
                                setDead();
                                System.out.println("Holy Cross is back in inventory");
                            }
                        }
                    } else if (hitEntity instanceof EntityLiving target && hitEntity != getThrower()) {
                        applyEntityHitEnchantments(target, stack);
                        if (!stack.isItemEnchanted()) {
                            target.attackEntityFrom(DamageSource.causeIndirectDamage(this, getThrower()), getImpactDamage());
                            System.out.println("the entity isn't enchanted");
                        }
                        System.out.println("the entity is enchanted");
                        stack.attemptDamageItem(1, this.rand, (EntityPlayerMP) getThrower());
                    }
                }
            }
        }
    }

    @Override
    protected float getImpactDamage() {
        if (this.material != null) {
            return material.getAttackDamage() + 2.0F;
        } else {
            return 0.0F;
        }
    }

    public ItemStack getItemStack() {
        return this.dataManager.get(STACK);
    }

    public void setItemStack(ItemStack stack) {
        if (!stack.isEmpty() || stack.hasTagCompound()) {
            this.dataManager.set(STACK, stack);
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        ItemStack stack = getItemStack();
        if (getThrower() != null) {
            setCoordinates(getThrower().posX, getThrower().posY + getThrower().getEyeHeight(), getThrower().posZ);

            double dx = this.posX - getThrowerX();
            double dy = this.posY - getThrowerY();
            double dz = this.posZ - getThrowerZ();

            double d = Math.sqrt(dx * dx + dy * dy + dz * dz);

            dx /= d;
            dy /= d;
            dz /= d;

            motionX -= (0.05D * dx);
            motionY -= (0.05D * dy);
            motionZ -= (0.05D * dz);

            if (isInWater() && !this.world.isRemote) {
                if (stack.getMaxDamage() - stack.getItemDamage() > 0) {
                    entityDropItem(stack, 0.5f);
                }
                setDead();
            }

            if (ticksExisted >= 100) {
                if (!this.world.isRemote) {
                    if (stack.getMaxDamage() - stack.getItemDamage() > 0) {
                        entityDropItem(stack, 0.5f);
                    }
                    setDead();
                }
            }
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        super.writeEntityToNBT(tagCompound);

        tagCompound.setDouble("playerX", getThrowerX());
        tagCompound.setDouble("playerY", getThrowerY());
        tagCompound.setDouble("playerZ", getThrowerZ());
        tagCompound.setDouble("yawHolyCross", dataManager.get(YAW));

        if (!getItemStack().isEmpty()) {
            tagCompound.setTag("HolyCross", getItemStack().writeToNBT(new NBTTagCompound()));
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund) {
        super.readEntityFromNBT(tagCompund);
        setCoordinates(tagCompund.getDouble("playerX"), tagCompund.getDouble("playerY"), tagCompund.getDouble("playerZ"));
        dataManager.set(YAW, tagCompund.getFloat("yawHolyCross"));
        setItemStack(new ItemStack(tagCompund.getCompoundTag("HolyCross")));
    }
}
