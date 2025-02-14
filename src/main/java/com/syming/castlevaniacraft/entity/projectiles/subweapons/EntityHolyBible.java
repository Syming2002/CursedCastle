package com.syming.castlevaniacraft.entity.projectiles.subweapons;

import com.syming.castlevaniacraft.entity.projectiles.MVThrowableWeapon;
import com.syming.castlevaniacraft.item.MVItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityHolyBible extends MVThrowableWeapon {

    private int spiralTicks = 0;
    public static final DataParameter<Float> THROWER_X = EntityDataManager.createKey(EntityHolyBible.class,
            DataSerializers.FLOAT);
    public static final DataParameter<Float> THROWER_Y = EntityDataManager.createKey(EntityHolyBible.class,
            DataSerializers.FLOAT);
    public static final DataParameter<Float> THROWER_Z = EntityDataManager.createKey(EntityHolyBible.class,
            DataSerializers.FLOAT);
    public static final DataParameter<Float> YAW = EntityDataManager.createKey(EntityHolyBible.class,
            DataSerializers.FLOAT);
    public static final DataParameter<ItemStack> STACK = EntityDataManager.createKey(EntityHolyBible.class,
            DataSerializers.ITEM_STACK);

    public EntityHolyBible(World world) {
        super(world);
    }

    public EntityHolyBible(World world, EntityLivingBase thrower, ItemStack stack) {
        super(world, thrower);

        setCoordinates(thrower.posX, thrower.posY + thrower.getEyeHeight() / 2, thrower.posZ);
        setItemStack(stack);
    }
    @Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(STACK, ItemStack.EMPTY);

        dataManager.register(YAW, 0F);
        dataManager.register(THROWER_X, 0F);
        dataManager.register(THROWER_Y, 0F);
        dataManager.register(THROWER_Z, 0F);

        rotationYaw = dataManager.get(YAW);
    }

    @Override
    protected float getGravityVelocity() {
        return 0f;
    }

    public double getPosX() {
        return dataManager.get(THROWER_X);
    }

    public double getPosY() {
        return dataManager.get(THROWER_Y);
    }

    public double getPosZ() {
        return dataManager.get(THROWER_Z);
    }

    @Override
    protected float getImpactDamage() {
        return 20;
    }

    public void setItemStack(ItemStack stack) {
        if (!stack.isEmpty()) {
            this.dataManager.set(STACK, stack);
        }
    }

    public ItemStack getItemStack() {
        return this.dataManager.get(STACK);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        ItemStack stack = getItemStack();
        Entity hitEntity = result.entityHit;

        if (!this.world.isRemote && getThrower() != null) {
            if (!stack.isEmpty()) {
                applyEntityHitEnchantments(hitEntity, stack);
                if (!stack.isItemEnchanted()) {
                    hitEntity.attackEntityFrom(DamageSource.causeIndirectDamage(hitEntity, getThrower()), getImpactDamage());
                }
            }
        }
    }

    @Override
    public void onUpdate() {
        if (getThrower() != null) {

            double throwerX = getThrower().posX;
            double throwerY = getThrower().posY + getThrower().getEyeHeight() / 2;
            double throwerZ = getThrower().posZ;

            setCoordinates(getThrower().posX, getThrower().posY + getThrower().getEyeHeight() / 2, getThrower().posZ);

            double angle = rand.nextDouble() * Math.PI * 2;
            double distance = this.spiralTicks * 0.0078125;
            double speed = 0.125;

            double dirX = MathHelper.cos((float) angle);
            double dirZ = MathHelper.sin((float) angle);
            double dirY = (rand.nextDouble() - 0.5) * 2;

            double randomX = throwerX + dirX * distance;
            double randomY = throwerY + dirY * distance;
            double randomZ = throwerZ + dirZ * distance;

            double randomMotionX = dirX * (0.1 + rand.nextDouble() * 0.1) * speed;
            double randomMotionY = (rand.nextDouble() - 0.5) * 0.2 * speed;
            double randomMotionZ = dirZ * (0.1 + rand.nextDouble() * 0.1) * speed;

            this.posX = randomX;
            this.posY = randomY;
            this.posZ = randomZ;

            this.motionX = randomMotionX;
            this.motionY = randomMotionY;
            this.motionZ = randomMotionZ;


            if (this.ticksExisted >= 120) {
                this.setDead();
            }
            this.spiralTicks += 2;
        }
    }

    public void setCoordinates(double playerX, double playerY, double playerZ) {
        dataManager.set(THROWER_X, (float) playerX);
        dataManager.set(THROWER_Y, (float) playerY);
        dataManager.set(THROWER_Z, (float) playerZ);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund) {

        super.readEntityFromNBT(tagCompund);
        setItemStack(new ItemStack(tagCompund.getCompoundTag("HolyBible")));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        super.writeEntityToNBT(tagCompound);

        if (getItemStack() != null && !getItemStack().isEmpty()) {
            tagCompound.setTag("HolyBible", getItemStack().writeToNBT(new NBTTagCompound()));
        }
    }
}

