package com.syming.minevania.entity.projectiles.subweapons;

import com.syming.minevania.api.CCThrowable;
import com.syming.minevania.api.CompoundTags;
import com.syming.minevania.item.projectiles.subweapons.holycross.ItemHolyCross;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public class EntityHolyCross extends CCThrowable {

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
        setItemStack(stack);
        setCoords(thrower.posX, thrower.posY + thrower.getEyeHeight(), thrower.posZ);
        setInited();
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

    public ItemStack getItemStack() {
        return dataManager.get(STACK);
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

    public void setCoords(double playerX, double playerY, double playerZ) {
        dataManager.set(THROWER_X, (float) playerX);
        dataManager.set(THROWER_Y, (float) playerY);
        dataManager.set(THROWER_Z, (float) playerZ);
    }

    @Override
    protected float getGravityVelocity() {
        return 0F;
    }

    @Override
    protected float getImpactDamage(Entity target) {
        return material.getAttackDamage() + 1F;
    }

    public void setItemStack(ItemStack stack) {
        if (stack == null || stack.isEmpty() || !(stack.getItem() instanceof ItemHolyCross)) {
            throw new IllegalArgumentException("Invalid Itemstack!");
        }
        dataManager.set(STACK, stack);
    }

    @Override
    protected boolean onBlockHit(BlockPos blockPos) {
        if (!world.isRemote) {
            if (getItemStack().getMaxDamage() - getItemStack().getItemDamage() > 0 && !creativeUse()) {
                entityDropItem(getItemStack(), 0.5f);
            }
        }
        return true;
    }

    @Override
    protected boolean onEntityHit(Entity hitEntity) {
        ItemStack stack = getItemStack();
        if (hitEntity == getThrower()) {
            // It's the thrower himself
            EntityPlayer p = (EntityPlayer) hitEntity;
            if (stack.getMaxDamage() - stack.getItemDamage() > 0 && !creativeUse()) {
                p.inventory.addItemStackToInventory(stack);
                world.playSound(null, posX, posY, posZ, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.MASTER, 0.5F,
                        0.7F);
            }
            return true;
        } else {
            // It's an hit entity
            hitEntity.attackEntityFrom(DamageSource.causeIndirectDamage(hitEntity, getThrower()), getImpactDamage(hitEntity));

            Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
            for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                Enchantment enchantment = entry.getKey();
                int level = entry.getValue();

                applyEnchantmentEffect(hitEntity, enchantment, level);
            }
            if (stack.getItem().isDamageable() && stack.attemptDamageItem(1, rand, null)) {
                return true;
            } else {
                setItemStack(stack);
            }
        }
        return false;
    }

    @Override
    public void onUpdate() {

        if (getThrower() != null && CompoundTags.giveCompound(getItemStack()).getBoolean(CompoundTags.ENCHANTED)) {
            setCoords(getThrower().posX, getThrower().posY + getThrower().getEyeHeight(), getThrower().posZ);
        }

        double dx = this.posX - getPosX();
        double dy = this.posY - getPosY();
        double dz = this.posZ - getPosZ();

        double d = Math.sqrt(dx * dx + dy * dy + dz * dz);

        dx /= d;
        dy /= d;
        dz /= d;

        motionX -= (0.05D * dx);
        motionY -= (0.05D * dy);
        motionZ -= (0.05D * dz);

        if (isInWater() && !world.isRemote) {
            if (getItemStack().getMaxDamage() - getItemStack().getItemDamage() > 0 && !creativeUse()) {
                entityDropItem(getItemStack(), 0.5f);
            }
            setDead();
        }

        if (ticksExisted >= 100) {
            if (CompoundTags.giveCompound(getItemStack()).getBoolean(CompoundTags.ENCHANTED)) {
                if (onEntityHit(getThrower()))
                    setDead();
            } else if (!world.isRemote) {
                if (getItemStack().getMaxDamage() - getItemStack().getItemDamage() > 0 && !creativeUse()) {
                    entityDropItem(getItemStack(), 0.5f);
                }
                setDead();
            }
        }
        rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
        rotationYaw += 20;
        setRotation(rotationYaw, rotationPitch);

    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound) {

        super.writeEntityToNBT(tagCompound);

        tagCompound.setDouble("playerX", getPosX());
        tagCompound.setDouble("playerY", getPosY());
        tagCompound.setDouble("playerZ", getPosZ());
        tagCompound.setDouble("yawholycross", dataManager.get(YAW));

        if (getItemStack() != null && !getItemStack().isEmpty()) {
            tagCompound.setTag("item_holy_cross", getItemStack().writeToNBT(new NBTTagCompound()));
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund) {
        super.readEntityFromNBT(tagCompund);
        setItemStack(new ItemStack(tagCompund.getCompoundTag("item_holy_cross")));
        setCoords(tagCompund.getDouble("playerX"), tagCompund.getDouble("playerY"), tagCompund.getDouble("playerZ"));
        dataManager.set(YAW, tagCompund.getFloat("yawholycross"));
    }

    /**
     * Applies effects for weapon enchantments to the hit entity.
     */
    private void applyEnchantmentEffect(Entity hitEntity, Enchantment enchantment, int level) {
        if (enchantment == Enchantments.FIRE_ASPECT) {
            // Fire Aspect: sets the target on fire
            hitEntity.setFire(4 * level);
        } else if (enchantment == Enchantments.KNOCKBACK) {
            // Knockback: pushes the target away
            double knockbackStrength = 0.5D + level * 0.5D;
            hitEntity.addVelocity(-MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F) * knockbackStrength, 0.1D,
                    MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F) * knockbackStrength);
        } else if (enchantment == Enchantments.SHARPNESS) {
            // Sharpness: adds extra damage
            float extraDamage = 1.0F + 0.5F * (level - 1);
            hitEntity.attackEntityFrom(DamageSource.causeIndirectDamage(this, getThrower()), extraDamage);
        } else if (enchantment == Enchantments.SMITE && hitEntity instanceof EntityLivingBase) {
            // Smite: extra damage to undead
            EntityLivingBase livingTarget = (EntityLivingBase) hitEntity;
            if (livingTarget.isEntityUndead()) {
                float extraDamage = 2.5F * level;
                hitEntity.attackEntityFrom(DamageSource.causeIndirectDamage(this, getThrower()), extraDamage);
            }
        } else if (enchantment == Enchantments.BANE_OF_ARTHROPODS && hitEntity instanceof EntityLivingBase) {
            // Bane of Arthropods: extra damage to arthropods and slowness
            EntityLivingBase livingTarget = (EntityLivingBase) hitEntity;
            if (livingTarget.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD) {
                float extraDamage = 2.5F * level;
                hitEntity.attackEntityFrom(DamageSource.causeIndirectDamage(this, getThrower()), extraDamage);
                // Apply slowness effect for 1 second per level
                livingTarget.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20 * level, 1));
            }
        } else if (enchantment == Enchantments.SWEEPING) {
            // Sweeping Edge: increases damage of sweeping attacks
            if (hitEntity instanceof EntityLivingBase) {
                List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class,
                        hitEntity.getEntityBoundingBox().grow(1.0D, 0.25D, 1.0D));
                for (EntityLivingBase entity : entities) {
                    if (entity != getThrower() && entity != hitEntity) {
                        entity.attackEntityFrom(DamageSource.causeIndirectDamage(this, getThrower()), level);
                    }
                }
            }
        } else if (enchantment == Enchantments.LOOTING) {
            // Looting: increases drops, handled by Minecraft's existing system on entity death
            // Can be left empty unless you have custom loot logic
        }
    }
}
