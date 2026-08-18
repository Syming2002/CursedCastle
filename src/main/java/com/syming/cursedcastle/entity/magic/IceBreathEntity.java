package com.syming.cursedcastle.entity.magic;

import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.monster.shade.FrozenShadeEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class IceBreathEntity extends MagicAttackEntity {

    public IceBreathEntity(EntityType<? extends IceBreathEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        refreshDimensions();
    }

    public IceBreathEntity(Level pLevel) {
        super(CCEntity.ICE_BREATH.get(), pLevel);
        refreshDimensions();
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            spawnSnowflakesParticles();
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
                    }
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void spawnSnowflakesParticles() {
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
}
