package com.syming.minevania.entity.projectiles.subweapons;

import com.syming.minevania.api.CCThrowable;
import com.syming.minevania.item.projectiles.subweapons.throwingaxe.ItemThrowingAxe;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public class EntityThrowingAxe extends CCThrowable {

    public static final DataParameter<ItemStack> STACK = EntityDataManager.createKey(EntityThrowingAxe.class,
            DataSerializers.ITEM_STACK);
    private Item.ToolMaterial material;

    public EntityThrowingAxe(World worldIn) {
        super(worldIn);
    }

    public EntityThrowingAxe(World world, EntityLivingBase thrower, ItemStack stack, Item.ToolMaterial material) {
        super(world, thrower);
        this.material = material;
        setInited();
        setItemStack(stack);
        shoot(this.motionX, this.motionY, this.motionZ, 1.0F, 0.5F);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(STACK, ItemStack.EMPTY);
    }

    @Override
    protected float getGravityVelocity() {
        return 0.10F;
    }

    @Override
    protected float getImpactDamage(Entity target) {

        return this.material.getAttackDamage() + 10F;
    }

    public ItemStack getItemStack() {
        return dataManager.get(STACK);
    }

    @Override
    protected boolean onBlockHit(BlockPos blockPos) {
        ItemStack stack = getItemStack();
        if (!stack.attemptDamageItem(1, rand, null)) {
            setItemStack(stack);
        }
        if (!world.isRemote) {
            if (getItemStack().getMaxDamage() - getItemStack().getItemDamage() > 0) {
                if (!creativeUse()) {
                    entityDropItem(getItemStack(), 0.5f);
                }
            }
        }
        return true;
    }

    @Override
    protected boolean onEntityHit(Entity hitEntity) {
        ItemStack stack = getItemStack();
        hitEntity.attackEntityFrom(DamageSource.causeIndirectDamage(hitEntity, getThrower()), getImpactDamage(hitEntity));
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
            Enchantment enchantment = entry.getKey();
            int level = entry.getValue();

            applyEnchantmentEffect(hitEntity, enchantment, level);
        }
        if (!stack.attemptDamageItem(1, rand, null)) {
            setItemStack(stack);
        }
        if (getItemStack().getMaxDamage() - getItemStack().getItemDamage() > 0) {
            if (!creativeUse()) {
                entityDropItem(getItemStack(), 0.5f);
            }
        }
        return true;
    }

    @Override
    public void onUpdate() {
        if (!isDead)
            super.onUpdate();
        if (ticksExisted > 100) {
            setDead();
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund) {

        super.readEntityFromNBT(tagCompund);
        setItemStack(new ItemStack(tagCompund.getCompoundTag("item_throwing_axe")));
    }

    public void setItemStack(ItemStack stack) {
        if (stack == null || stack.isEmpty() || !(stack.getItem() instanceof ItemThrowingAxe)) {
            throw new IllegalArgumentException("Invalid Itemstack!");
        }
        dataManager.set(STACK, stack);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        super.writeEntityToNBT(tagCompound);

        if (getItemStack() != null && !getItemStack().isEmpty()) {
            tagCompound.setTag("item_throwing_axe", getItemStack().writeToNBT(new NBTTagCompound()));
        }
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy)
    {
        // Normalize the direction vector
        double length = MathHelper.sqrt(x * x + y * y + z * z);
        x /= length;
        y /= length;
        z /= length;

        // Add some randomness to the direction
        x += this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        y += this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        z += this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;

        // Apply velocity to the direction
        x *= velocity;
        y *= velocity;
        z *= velocity;

        // Apply bell trajectory
        double time = 0.0; // You may need to adjust this based on your requirements
        double amplitude = 0.5; // Adjust the amplitude as needed
        double frequency = 1.0; // Adjust the frequency as needed

        double xOffset = amplitude * Math.sin(frequency * time); // Bell curve in x direction
        double yOffset = amplitude * Math.cos(frequency * time); // Bell curve in y direction

        x += xOffset;
        y += yOffset;

        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;

        // Set rotation based on the modified motion
        float f1 = MathHelper.sqrt(x * x + z * z);
        this.rotationYaw = (float)(MathHelper.atan2(x, z) * (180D / Math.PI));
        this.rotationPitch = (float)(MathHelper.atan2(y, (double)f1) * (180D / Math.PI));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
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
