package com.syming.cursedcastle.entity.magic;

import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.mob.shade.HeatShadeEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class FireBreathEntity extends MagicAttackEntity {


    public FireBreathEntity(EntityType<? extends FireBreathEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        refreshDimensions();
    }

    public FireBreathEntity(Level pLevel) {
        super(CCEntity.FIRE_BREATH.get(), pLevel);
        refreshDimensions();
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            spawnFlameParticles();
        }

        if (getOwner() instanceof HeatShadeEntity heatShade) {

            if (heatShade != null && !heatShade.isAlive()) {
                discard();
            }

            if (heatShade != null) {
                if (heatShade.getTarget() != null) {
                    Vec3 headPos = heatShade.position().add(0, heatShade.getEyeHeight(), 0);

                    Vec3 lookVec = heatShade.getLookAngle().scale(0.5D);

                    headPos = headPos.add(lookVec);

                    setPosRaw(headPos.x, headPos.y, headPos.z);

                }

                List<LivingEntity> entitiesHit = this.level().getEntitiesOfClass(LivingEntity.class,
                        this.getBoundingBox().inflate(RANGE, heatShade.getEyeHeight(), RANGE), e -> e != heatShade && e.isAlive() && distanceTo(e) <= 5 + e.getBbWidth() / 2.0F && e.getY() <= getY() + heatShade.getEyeHeight());

                for (LivingEntity entityHit : entitiesHit) {
                    if (isYawCheck(entityHit)) {
                        entityHit.hurt(entityHit.damageSources().magic(), 1.0F);
                        entityHit.setSecondsOnFire(4);
                        System.out.println("Target is Damaged and Burned");
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
    private void spawnFlameParticles() {
        if (getOwner() instanceof HeatShadeEntity heatShade) {
            float yaw = (float) Math.toRadians(-heatShade.getYHeadRot());
            float pitch = (float) Math.toRadians(-heatShade.getXRot());
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
                    this.level().addParticle(ParticleTypes.FLAME, getX(), getY(), getZ(), xSpeed, ySpeed, zSpeed);
                }
            }
        }
    }
}
