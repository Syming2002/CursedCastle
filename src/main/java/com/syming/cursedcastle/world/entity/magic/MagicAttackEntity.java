package com.syming.cursedcastle.world.entity.magic;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public abstract class MagicAttackEntity extends AbstractHurtingProjectile {

    public MagicAttackEntity(EntityType<? extends MagicAttackEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public MagicAttackEntity(EntityType<? extends MagicAttackEntity> pEntityType, double pX, double pY, double pZ, double pOffsetX, double pOffsetY, double pOffsetZ, Level pLevel) {
        super(pEntityType, pX, pY, pZ, pOffsetX, pOffsetY, pOffsetZ, pLevel);
    }

    public MagicAttackEntity(EntityType<? extends MagicAttackEntity> pEntityType, LivingEntity pShooter, double pOffsetX, double pOffsetY, double pOffsetZ, Level pLevel) {
        super(pEntityType, pShooter, pOffsetX, pOffsetY, pOffsetZ, pLevel);
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        return EntityDimensions.scalable(1.0F, 1.0F);
    }

    public <T extends Entity> List<T> getNearbyEntities(Class<T> entityClazz, double distanceX, double distanceY, double distanceZ, double radius) {
        AABB box = getBoundingBox().inflate(distanceX, distanceY, distanceZ);

        return this.level().getEntitiesOfClass(entityClazz, box, entity -> entity != this && distanceTo(entity) <= radius + (entity.getBbWidth() / 2.0F) && entity.getY() <= getY() + distanceY);
    }
}
