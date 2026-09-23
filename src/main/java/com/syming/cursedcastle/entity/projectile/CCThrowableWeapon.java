package com.syming.cursedcastle.entity.projectile;

import com.syming.cursedcastle.enchantment.CCEnchantment;
import com.syming.cursedcastle.entity.monster.skeleton.BloodSkeletonEntity;
import com.syming.cursedcastle.entity.projectile.subweapon.HolyBibleEntity;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Map;

import static net.minecraft.tags.EntityTypeTags.*;

public abstract class CCThrowableWeapon extends ThrowableItemProjectile {

    private static final EntityDataAccessor<Float> THROWER_X = SynchedEntityData.defineId(CCThrowableWeapon.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> THROWER_Y = SynchedEntityData.defineId(CCThrowableWeapon.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> THROWER_Z = SynchedEntityData.defineId(CCThrowableWeapon.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> THROWER_YAW = SynchedEntityData.defineId(CCThrowableWeapon.class, EntityDataSerializers.FLOAT);

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
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);

        builder.define(THROWER_X, 0.0F);
        builder.define(THROWER_Y, 0.0F);
        builder.define(THROWER_Z, 0.0F);
        builder.define(THROWER_YAW, 0.0F);
    }

    @Override
    public ItemStack getItem() {
        return super.getItem();
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

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);

        tag.putDouble("throwerX", getThrowerPosX());
        tag.putDouble("throwerY", getThrowerPosY());
        tag.putDouble("throwerZ", getThrowerPosZ());

        tag.putFloat("throwerYaw", this.entityData.get(THROWER_YAW));
    }


    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setCoordinates(tag.getDouble("throwerX"), tag.getDouble("throwerY"), tag.getDouble("throwerZ"));
        this.entityData.set(THROWER_YAW, tag.getFloat("throwerYaw"));
    }

    public abstract float getImpactDamage();

    public void applyEntityHitEnchantments(Entity pTarget) {
        Level level = pTarget.level();

        Registry<Enchantment> enchantmentRegistryLookup = level.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
        Holder<Enchantment> sharpnessHolder = level.holderOrThrow(Enchantments.SHARPNESS);
        Holder<Enchantment> smiteHolder = level.holderOrThrow(Enchantments.SMITE);
        Holder<Enchantment> arthropodsHolder = level.holderOrThrow(Enchantments.BANE_OF_ARTHROPODS);
        Holder<Enchantment> knockbackHolder = level.holderOrThrow(Enchantments.KNOCKBACK);
        Holder<Enchantment> fireAspectHolder = level.holderOrThrow(Enchantments.FIRE_ASPECT);
        Holder<Enchantment> lootingHolder = level.holderOrThrow(Enchantments.LOOTING);

        if (getOwner() instanceof LivingEntity shooter && getOwner() != null && pTarget instanceof LivingEntity target) {
            int fireAspect = EnchantmentHelper.getEnchantmentLevel(fireAspectHolder, shooter);
            if (fireAspect > 0) {
                target.setRemainingFireTicks(fireAspect);
            }

            int knockback = EnchantmentHelper.getEnchantmentLevel(knockbackHolder, shooter);
            if (knockback > 0) {
                target.knockback(knockback, -Mth.sin(getYRot() * 0.017453292F), -Mth.cos(getYRot() * 0.017453292F));
            }

            int mobLooting = EnchantmentHelper.getEnchantmentLevel(lootingHolder, shooter);
            if (mobLooting > 0) {
                target.hurt(target.damageSources().mobProjectile(this, shooter), getImpactDamage());
            }

            int sharpnessLevel = EnchantmentHelper.getEnchantmentLevel(sharpnessHolder, shooter);
            int smiteLevel = EnchantmentHelper.getEnchantmentLevel(smiteHolder, shooter);
            int arthropodsLevel = EnchantmentHelper.getEnchantmentLevel(arthropodsHolder, shooter);

            float damage = getImpactDamage();

            float sharpnessDamageBonus = 0.5F * sharpnessLevel + 0.5F;
            float smiteDamageBonus = 0.5F * smiteLevel + 0.5F;
            float arthropodsDamageBonus = 0.5F * arthropodsLevel + 0.5F;

            float totalSharpnessDamage = damage + sharpnessDamageBonus;
            float totalSmiteDamage = damage + smiteDamageBonus;
            float totalArthropodsDamage = damage + arthropodsDamageBonus;


            if (sharpnessLevel > 0) {
                target.hurt(target.damageSources().mobProjectile(this, shooter), totalSharpnessDamage);
            }


            if (smiteLevel > 0 && target.getType().is(SENSITIVE_TO_SMITE)) {
                target.hurt(target.damageSources().mobProjectile(this, shooter), totalSmiteDamage);
            }


            if (arthropodsLevel > 0 && target.getType().is(SENSITIVE_TO_BANE_OF_ARTHROPODS)) {
                target.hurt(target.damageSources().mobProjectile(this, shooter), totalArthropodsDamage);
            }
        }
    }

    public double getThrowerPosX() {
        return this.entityData.get(THROWER_X);
    }

    public double getThrowerPosY() {
        return this.entityData.get(THROWER_Y);
    }

    public double getThrowerPosZ() {
        return this.entityData.get(THROWER_Z);
    }

    public void setCoordinates(double playerX, double playerY, double playerZ) {
        this.entityData.set(THROWER_X, (float) playerX);
        this.entityData.set(THROWER_Y, (float) playerY);
        this.entityData.set(THROWER_Z, (float) playerZ);
    }
}
