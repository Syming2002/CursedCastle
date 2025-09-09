package com.syming.cursedcastle.entity.magic;

import com.syming.cursedcastle.entity.mob.shade.ShadeEntity;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public abstract class MagicAttackEntity extends Projectile {
    protected final int RANGE = 10;

    public MagicAttackEntity(EntityType<? extends MagicAttackEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        return EntityDimensions.scalable(0.0F, 0.0F);
    }

    @Override
    public void tick() {
        super.tick();

        HitResult hitResult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);

        if (hitResult.getType() != HitResult.Type.MISS) {
            onHit(hitResult);
        }

        if (this.tickCount > 65) {
            discard();
        }
    }

    protected boolean isYawCheck(LivingEntity entityHit) {
        if (getOwner() instanceof ShadeEntity shade) {
            float entityHitYaw = (float) ((Math.atan2(entityHit.getZ() - shade.getZ(), entityHit.getX() - shade.getX()) * (180 / Math.PI) - 90) % 360);
            float entityAttackingYaw = shade.getYRot() % 360;
            if (entityHitYaw < 0) {
                entityHitYaw += 360;
            }
            if (entityAttackingYaw < 0) {
                entityAttackingYaw += 360;
            }
            float entityRelativeYaw = entityHitYaw - entityAttackingYaw;

            boolean yawCheck = entityRelativeYaw >= -45 / 2f && entityRelativeYaw <= 45 / 2f;
            return yawCheck;
        }
        return false;
    }

}
