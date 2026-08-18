package com.syming.cursedcastle.entity.magic;

import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.mob.shade.ThunderShadeEntity;
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

public class ThunderBreathEntity extends MagicAttackEntity {

    public ThunderBreathEntity(EntityType<? extends MagicAttackEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ThunderBreathEntity(Level pLevel) {
        super(CCEntity.THUNDER_BREATH.get(), pLevel);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            spawnElectricSparkParticles();
        }

        if (getOwner() instanceof ThunderShadeEntity thunderShade) {

            if (thunderShade != null && !thunderShade.isAlive()) {
                discard();
            }

            if (thunderShade != null) {
                if (thunderShade.getTarget() != null) {
                    Vec3 headPos = thunderShade.position().add(0, thunderShade.getEyeHeight(), 0);

                    Vec3 lookVec = thunderShade.getLookAngle().scale(0.5D);

                    headPos = headPos.add(lookVec);

                    setPos(headPos.x, headPos.y, headPos.z);

                }

                List<LivingEntity> entitiesHit = this.level().getEntitiesOfClass(LivingEntity.class,
                        this.getBoundingBox().inflate(5, thunderShade.getEyeHeight(), 5), e -> e != thunderShade && e.isAlive() && distanceTo(e) <= 2.5F + e.getBbWidth() / 2.0F && e.getY() <= getY() + thunderShade.getEyeHeight());

                for (LivingEntity entityHit : entitiesHit) {
                    if (isYawCheck(entityHit)) {
                        entityHit.hurt(entityHit.damageSources().thorns(this), 5.0F);
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
    public void spawnElectricSparkParticles() {
        if (getOwner() instanceof ThunderShadeEntity thunderShade) {
            float yaw = (float) Math.toRadians(-thunderShade.getYHeadRot());
            float pitch = (float) Math.toRadians(-thunderShade.getXRot());
            float spread = 0.75F;
            float speed = 0.5F;
            float xComp = (float) (Math.sin(yaw) * Math.cos(pitch));
            float yComp = (float) (Math.sin(pitch));
            float zComp = (float) (Math.cos(yaw) * Math.cos(pitch));
            if (this.level().isClientSide()) {
                for (int i = 0; i < 5; i++) {
                    double xSpeed = speed * xComp + (spread * 0.7 * (this.random.nextFloat() * 2 - 1) * (Math.sqrt(1 - xComp * xComp)));
                    double ySpeed = speed * yComp + (spread * 0.7 * (this.random.nextFloat() * 2 - 1) * (Math.sqrt(1 - yComp * yComp)));
                    double zSpeed = speed * zComp + (spread * 0.7 * (this.random.nextFloat() * 2 - 1) * (Math.sqrt(1 - zComp * zComp)));
                    this.level().addParticle(ParticleTypes.CRIT, getX(), getY(), getZ(), xSpeed, ySpeed, zSpeed);
                }
            }
        }
    }
}
