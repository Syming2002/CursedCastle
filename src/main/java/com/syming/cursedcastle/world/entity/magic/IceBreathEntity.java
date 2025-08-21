package com.syming.cursedcastle.world.entity.magic;

import com.syming.cursedcastle.world.entity.CCEntity;
import com.syming.cursedcastle.world.entity.mob.shade.FrozenShadeEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class IceBreathEntity extends MagicAttackEntity {
    private final int RANGE = 10;
    public FrozenShadeEntity frozenShade;

    public IceBreathEntity(EntityType<? extends IceBreathEntity> pEntityType, Level pLevel) {
        super(CCEntity.ICE_BREATH.get(), pLevel);
    }

    public IceBreathEntity(LivingEntity pShooter, double pOffsetX, double pOffsetY, double pOffsetZ, Level pLevel) {
        super(CCEntity.ICE_BREATH.get(), pShooter, pOffsetX, pOffsetY, pOffsetZ, pLevel);

        this.frozenShade = (FrozenShadeEntity) pShooter;
    }

    public IceBreathEntity(double pX, double pY, double pZ, double pOffsetX, double pOffsetY, double pOffsetZ, Level pLevel) {
        super(CCEntity.ICE_BREATH.get(), pX, pY, pZ, pOffsetX, pOffsetY, pOffsetZ, pLevel);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.frozenShade == null && !this.frozenShade.isAlive()) {
            discard();
        }

        if (this.frozenShade != null) {
            Vec3 headPos = new Vec3(0.0F, 1.5F, 0.0F);
            headPos = headPos.xRot((float) Math.toRadians(-getXRot()));
            headPos = headPos.yRot((float) Math.toRadians(-getYHeadRot()));
            headPos = headPos.add(new Vec3(0.0F, 0.0F, 0.25F));
            headPos = headPos.add(getX(), getY(), getZ());

            setPos(headPos.x, headPos.y, headPos.z);
            spawnSnowParticles();
        }

        if (this.tickCount > 65) {
            discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);

        if (this.frozenShade != null) {
            List<LivingEntity> entitiesHit = getNearbyEntities(LivingEntity.class, RANGE, this.frozenShade.getEyeHeight(), RANGE, 5);

            float damage = 1.0F;
            for (LivingEntity entityHit : entitiesHit) {
                if (entityHit != this.frozenShade) {
                    boolean yawCheck = isYawCheck(entityHit);

                    if (yawCheck) {
                        float entityHitDistance = (float) Math.sqrt((entityHit.getX() - getX()) * (entityHit.getX() - getX()) + (entityHit.getZ() - getZ()) * (entityHit.getZ() - getZ()) + (entityHit.getY() - getY()) * (entityHit.getY() - getY()));

                        boolean inRange = entityHitDistance <= RANGE;

                        if (inRange) {
                            entityHit.hurt(this.level().damageSources().magic(), damage);
                            setDeltaMovement(new Vec3(0.125D, 1.0D, 0.125D));
                        }
                    }
                }
            }
        }
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        return EntityDimensions.scalable(0.0F, 0.0F);
    }

    @OnlyIn(Dist.CLIENT)
    public void spawnSnowParticles() {
        float yaw = (float) Math.toRadians(-getYHeadRot());
        float pitch = (float) Math.toRadians(-getXRot());
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

    private boolean isYawCheck(LivingEntity entityHit) {
        float entityHitYaw = (float) ((Math.atan2(entityHit.getZ() - getZ(), entityHit.getX() - getX()) * (180 / Math.PI) - 90) % 360);
        float entityAttackingYaw = getYRot() % 360;
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
}
