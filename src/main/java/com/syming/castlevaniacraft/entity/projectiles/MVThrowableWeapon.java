package com.syming.castlevaniacraft.entity.projectiles;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public abstract class MVThrowableWeapon extends EntityThrowable {

    public MVThrowableWeapon(World worldIn, EntityLivingBase thrower) {
        super(worldIn, thrower);
        this.ignoreEntity = thrower;
        setLocationAndAngles(thrower.posX, thrower.posY + thrower.getEyeHeight(), thrower.posZ, thrower.rotationYaw,
                thrower.rotationPitch);
        posX -= MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * 0.25;
        posY -= 0.10000000149011612D;
        posZ -= MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * 0.25;
        setPosition(posX, posY, posZ);
        motionX = -MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI);
        motionZ = MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI);
        motionY = -MathHelper.sin(rotationPitch / 180.0F * (float) Math.PI);
        shoot(motionX, motionY, motionZ, 1.5F, 1.0F);
    }

    public MVThrowableWeapon(World worldIn) {
        super(worldIn);
    }

    protected abstract float getImpactDamage();

    public void applyEntityHitEnchantments(Entity entity, ItemStack stack) {
        if (getThrower() != null && entity instanceof EntityLiving target && stack.isItemEnchanted()) {

            int fireAspect = EnchantmentHelper.getEnchantmentLevel(Enchantments.FIRE_ASPECT, stack);
            if (fireAspect > 0) {
                target.setFire(fireAspect);
            }


            int knockback = EnchantmentHelper.getEnchantmentLevel(Enchantments.KNOCKBACK, stack);
            if (knockback > 0) {
                target.knockBack(target, knockback, -MathHelper.sin(this.rotationYaw * 0.017453292F), -MathHelper.cos(this.rotationYaw * 0.017453292F));
            }

            float sharpnessDamageBonus = EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, stack);
            if (sharpnessDamageBonus > 0) {
                target.attackEntityFrom(DamageSource.causeIndirectDamage(this, getThrower()), getImpactDamage() + sharpnessDamageBonus);
            }

            if (target.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) {
                float smiteDamageBonus = EnchantmentHelper.getEnchantmentLevel(Enchantments.SMITE, stack);
                if (smiteDamageBonus > 0) {
                    target.attackEntityFrom(DamageSource.causeIndirectDamage(this, getThrower()), getImpactDamage() + smiteDamageBonus);
                }
            }

            if (target.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD) {
                float arthropodDamageBonus = EnchantmentHelper.getEnchantmentLevel(Enchantments.BANE_OF_ARTHROPODS, stack);
                if (arthropodDamageBonus > 0) {
                    target.attackEntityFrom(DamageSource.causeIndirectDamage(this, getThrower()), getImpactDamage() + arthropodDamageBonus);
                }
            }

            int looting = EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, stack);
            if (looting > 0) {
                ForgeHooks.getLootingLevel(target, DamageSource.causeIndirectDamage(this, getThrower()), looting);
            }
        }
    }
}
