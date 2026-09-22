package com.syming.cursedcastle.entity.projectile;

import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.monster.skeleton.ClearBoneSkeletonEntity;
import com.syming.cursedcastle.entity.monster.skeleton.ThrowingBoneSkeletonEntity;
import com.syming.cursedcastle.item.CCItem;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class ThrowingBoneEntity extends CCThrowableWeapon {

    public ThrowingBoneEntity(Level pLevel) {
        super(CCEntity.THROWING_BONE.get(), pLevel);
    }

    public ThrowingBoneEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ThrowingBoneEntity(double pX, double pY, double pZ, Level pLevel) {
        super(CCEntity.THROWING_BONE.get(), pX, pY, pZ, pLevel);
    }

    public ThrowingBoneEntity(LivingEntity pShooter, Level pLevel) {
        super(CCEntity.THROWING_BONE.get(), pShooter, pLevel);
    }

    @Override
    public float getImpactDamage() {
        return getOwner() instanceof ClearBoneSkeletonEntity ? 16.0F : 4.0F;
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(CCItem.THROWING_BONE.get());
    }

    @Override
    protected Item getDefaultItem() {
        return CCItem.THROWING_BONE.get();
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        double length = Mth.sqrt((float) (x * x + y * y + z * z));
        x /= length;
        y /= length;
        z /= length;

        x += this.random.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        y += this.random.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        z += this.random.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;

        x *= velocity;
        y *= velocity;
        z *= velocity;

        double time = 0.0;
        double amplitude = 0.5;
        double frequency = 1.0;

        double xOffset = amplitude * Math.sin(frequency * time);
        double yOffset = amplitude * Math.cos(frequency * time);

        x += xOffset;
        y += yOffset;

        setDeltaMovement(x, y, z);

        float f1 = Mth.sqrt((float) (x * x + z * z));
        setYRot((float) (Mth.atan2(x, z) * (180D / Math.PI)));
        setXRot((float) (Mth.atan2(y, (double)f1) * (180D / Math.PI)));
    }

    @Override
    public void shootFromRotation(Entity pShooter, float pX, float pY, float pZ, float pVelocity, float pInaccuracy) {
        super.shootFromRotation(pShooter, pX, pY, pZ, pVelocity, pInaccuracy);

        Vec3 motion = getDeltaMovement();

        motion = motion.multiply(0.6D, 1.0D, 0.6D);

        motion = motion.add(0, 0.4D, 0);

        setDeltaMovement(motion);
    }

    @Override
    public double getDefaultGravity() {
        return 0.1F;
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);

        Entity target = pResult.getEntity();

        if (!this.level().isClientSide() && getOwner() != null) {
            if (target instanceof LivingEntity livingEntity && (getOwner() instanceof ThrowingBoneSkeletonEntity || getOwner() instanceof ClearBoneSkeletonEntity)) {
                livingEntity.hurt(this.damageSources().mobProjectile(this, (LivingEntity) getOwner()), getImpactDamage());
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);

        if (!this.level().isClientSide() && getOwner() != null) {
            discard();
        }
    }
}
