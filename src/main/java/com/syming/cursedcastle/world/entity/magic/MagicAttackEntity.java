package com.syming.cursedcastle.world.entity.magic;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public abstract class MagicAttackEntity extends Projectile {
    public double accelX;
    public double accelY;
    public double accelZ;

    public MagicAttackEntity(EntityType<? extends MagicAttackEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public MagicAttackEntity(EntityType<? extends MagicAttackEntity> pEntityType, Level pLevel, LivingEntity pShooter, double accelX, double accelY, double accelZ) {
        super(pEntityType, pLevel);
//        if (getOwner() != null) {
//            pShooter = (LivingEntity) getOwner();
//
//            setPosRaw(pShooter.getX(), pShooter.getY(), pShooter.getZ());
//
//            setDeltaMovement(0.0D, 0.0D, 0.0D);
//
//            accelX = accelX + this.random.nextGaussian() * 0.4D;
//            accelY = accelY + this.random.nextGaussian() * 0.4D;
//            accelZ = accelZ + this.random.nextGaussian() * 0.4D;
//
//            double distance = Mth.sqrt((float) (accelX * accelX + accelY * accelY + accelZ * accelZ));
//
//            this.accelX = accelX / distance * 0.1D;
//            this.accelY = accelY / distance * 0.1D;
//            this.accelZ = accelZ / distance * 0.1D;
//        }
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        return EntityDimensions.scalable(1.0F, 1.0F);
    }

//    @Override
//    protected void addAdditionalSaveData(CompoundTag pCompound) {
//        super.addAdditionalSaveData(pCompound);
//
//        pCompound.putDouble("acceleration");
//    }
//
//    protected void readAdditionalSaveData(CompoundTag pCompound) {
//
//    }

    public <T extends Entity> List<T> getNearbyEntities(Class<T> entityClazz, double distanceX, double distanceY, double distanceZ, double radius) {
        AABB box = getBoundingBox().inflate(distanceX, distanceY, distanceZ);

        return this.level().getEntitiesOfClass(entityClazz, box, entity -> entity != this && distanceTo(entity) <= radius + (entity.getBbWidth() / 2.0F) && entity.getY() <= getY() + distanceY);
    }
}
