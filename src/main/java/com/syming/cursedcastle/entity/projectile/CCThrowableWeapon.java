package com.syming.cursedcastle.entity.projectile;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Map;

public abstract class CCThrowableWeapon extends ThrowableItemProjectile {

    public CCThrowableWeapon(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public CCThrowableWeapon(EntityType<? extends ThrowableItemProjectile> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        super(pEntityType, pX, pY, pZ, pLevel);
    }

    public CCThrowableWeapon(EntityType<? extends ThrowableItemProjectile> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
        if (pShooter != null) {
            this.setPos(pShooter.getX(), pShooter.getY() + pShooter.getEyeHeight(), pShooter.getZ());
            this.setRot(pShooter.getYRot(), pShooter.getXRot());

            double offsetX = -Math.cos(this.getYRot() / 180.0F * (float) Math.PI) * 0.25;
            double offsetZ = -Math.sin(this.getYRot() / 180.0F * (float) Math.PI) * 0.25;
            double offsetY = -0.10000000149011612D;

            this.setPos(this.getX() + offsetX, this.getY() + offsetY, this.getZ() + offsetZ);

            Vec3 motion = new Vec3(
                    -Math.sin(this.getYRot() / 180.0F * (float) Math.PI) * Math.cos(this.getXRot() / 180.0F * (float) Math.PI),
                    -Math.sin(this.getXRot() / 180.0F * (float) Math.PI),
                    Math.cos(this.getYRot() / 180.0F * (float) Math.PI) * Math.cos(this.getXRot() / 180.0F * (float) Math.PI)
            );

            this.setDeltaMovement(motion.scale(1.5F));
            this.shoot(motion.x, motion.y, motion.z, 1.5F, 1.0F);
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
    }

    @Override
    public void onHit(HitResult pResult) {
        super.onHit(pResult);
    }

    public abstract float getImpactDamage();

    public void applyEntityHitEnchantments(Entity pTarget) {
        if (getOwner() instanceof LivingEntity shooter && getOwner() != null && pTarget instanceof LivingEntity target) {
            int fireAspect = EnchantmentHelper.getFireAspect(shooter);
            if (fireAspect > 0) {
                target.setSecondsOnFire(fireAspect);
            }

            int knockback = EnchantmentHelper.getKnockbackBonus(shooter);
            if (knockback > 0) {
                target.knockback(knockback, -Mth.sin(getYRot() * 0.017453292F), -Mth.cos(getYRot() * 0.017453292F));
            }

            int mobLooting = EnchantmentHelper.getMobLooting(shooter);
            if (mobLooting > 0) {
                target.hurt(target.damageSources().mobProjectile(this, shooter), getImpactDamage());
            }



            float damage = getImpactDamage();
            float damageBonus = EnchantmentHelper.getDamageBonus(getItemRaw(), target.getMobType());
            float totalDamage = damage + damageBonus;

            int sharpnessLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, shooter);
            if (sharpnessLevel > 0) {
                target.hurt(target.damageSources().mobProjectile(this, shooter), totalDamage);
            }

            int smiteLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.SMITE, shooter);
            if (smiteLevel > 0 && target.getMobType() == MobType.UNDEAD) {
                target.hurt(target.damageSources().mobProjectile(this, shooter), totalDamage);
            }

            int arthropodsLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.BANE_OF_ARTHROPODS, shooter);
            if (arthropodsLevel > 0 && target.getMobType() == MobType.ARTHROPOD) {
                target.hurt(target.damageSources().mobProjectile(this, shooter), totalDamage);
            }
        }
    }
}
