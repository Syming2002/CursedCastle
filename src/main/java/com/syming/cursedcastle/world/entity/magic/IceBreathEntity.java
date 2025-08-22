package com.syming.cursedcastle.world.entity.magic;

import com.syming.cursedcastle.world.entity.CCEntity;
import com.syming.cursedcastle.world.entity.mob.shade.FrozenShadeEntity;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class IceBreathEntity extends MagicAttackEntity {
    private final int RANGE = 10;

    public IceBreathEntity(EntityType<? extends IceBreathEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        refreshDimensions();
    }

    public IceBreathEntity(Level pLevel) {
        super(CCEntity.ICE_BREATH.get(), pLevel);
        refreshDimensions();
    }

    @Override
    protected void defineSynchedData() {
    }

    @Override
    public void tick() {
        super.tick();

        HitResult hitResult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);

        if (hitResult.getType() != HitResult.Type.MISS) {
            onHit(hitResult);
        }

        if (getOwner() instanceof FrozenShadeEntity frozenShade) {

            if (frozenShade != null && !frozenShade.isAlive()) {
                discard();
            }

            if (frozenShade != null) {
                if (frozenShade.getTarget() != null) {
                    Vec3 headPos = frozenShade.position().add(0, frozenShade.getEyeHeight(), 0);

                    Vec3 lookVec = frozenShade.getLookAngle().scale(0.5D);

                    headPos = headPos.add(lookVec);

                    setPos(headPos.x, headPos.y, headPos.z);

                }

                List<LivingEntity> entitiesHit = this.level().getEntitiesOfClass(LivingEntity.class,
                        this.getBoundingBox().inflate(RANGE, frozenShade.getEyeHeight(), RANGE), e -> e != frozenShade && e.isAlive() && distanceTo(e) <= 5 + e.getBbWidth() / 2.0F && e.getY() <= getY() + frozenShade.getEyeHeight());

                for (LivingEntity entityHit : entitiesHit) {
                    if (isYawCheck(entityHit)) {
                        entityHit.hurt(entityHit.damageSources().magic(), 1.0F);
                        entityHit.setDeltaMovement(entityHit.getDeltaMovement().multiply(0.125, 1.0, 0.125));
                        System.out.println("Target is Damaged and Slowed Down");
                    }
                }
            }
        }

        if (this.level().isClientSide()) {
            spawnSnowParticles();
        }

        if (this.tickCount > 65) {
            discard();
        }
    }

//    @Override
//    protected boolean canHitEntity(Entity pEntity) {
//        return pEntity.isPickable() && pEntity.isAlive() && pEntity != getOwner();
//    }

//    @Override
//    protected void onHitEntity(EntityHitResult pResult) {
//        super.onHitEntity(pResult);
//
//        if (getOwner() instanceof FrozenShadeEntity frozenShade) {
//            List<LivingEntity> entitiesHit = getNearbyEntities(LivingEntity.class, RANGE, frozenShade.getEyeHeight(), RANGE, 5);
//
//            float damage = 1.0F;
//            for (LivingEntity entityHit : entitiesHit) {
//                if (entityHit != frozenShade) {
//                    boolean yawCheck = isYawCheck(entityHit);
//
//                    double dx = entityHit.getX() - getX();
//                    double dy = entityHit.getY() - getY();
//                    double dz = entityHit.getZ() - getZ();
//                    double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
//
//                    if (yawCheck) {
//
//                        boolean inRange = distance <= RANGE;
//
//                        if (inRange) {
//                            entityHit.hurt(this.level().damageSources().magic(), damage);
//                            entityHit.setDeltaMovement(entityHit.getDeltaMovement().x * 0.125F, getDeltaMovement().y, getDeltaMovement().z * 0.125F);
//                        }
//                    }
//                }
//            }
//        }
//    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        return EntityDimensions.scalable(0.0F, 0.0F);
    }

    @OnlyIn(Dist.CLIENT)
    public void spawnSnowParticles() {
        if (getOwner() instanceof FrozenShadeEntity frozenShade) {
            float yaw = (float) Math.toRadians(-frozenShade.getYHeadRot());
            float pitch = (float) Math.toRadians(-frozenShade.getXRot());
            float spread = 0.25F;
            float speed = 0.5F;
            float xComp = (float) (Math.sin(yaw) * Math.cos(pitch));
            float yComp = (float) (Math.sin(pitch));
            float zComp = (float) (Math.cos(yaw) * Math.cos(pitch));
            if (this.level().isClientSide()) {
                for (int i = 0; i < 5; i++) {
                    double xSpeed = speed * xComp + (spread * 0.7 * (this.random.nextFloat() * 2 - 1) * (Math.sqrt(1 - xComp * xComp)));
                    double ySpeed = speed * yComp + (spread * 0.7 * (this.random.nextFloat() * 2 - 1) * (Math.sqrt(1 - yComp * yComp)));
                    double zSpeed = speed * zComp + (spread * 0.7 * (this.random.nextFloat() * 2 - 1) * (Math.sqrt(1 - zComp * zComp)));
                    this.level().addParticle(ParticleTypes.SNOWFLAKE, getX(), getY(), getZ(), xSpeed, ySpeed, zSpeed);
                }
            }
        }
    }

    private boolean isYawCheck(LivingEntity entityHit) {
        if (getOwner() instanceof FrozenShadeEntity frozenShade) {
            float entityHitYaw = (float) ((Math.atan2(entityHit.getZ() - frozenShade.getZ(), entityHit.getX() - frozenShade.getX()) * (180 / Math.PI) - 90) % 360);
            float entityAttackingYaw = frozenShade.getYRot() % 360;
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
