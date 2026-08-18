package com.syming.cursedcastle.entity.magic;

import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.mob.shade.PoisonShadeEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class PoisonBreathEntity extends MagicAttackEntity {
    public PoisonBreathEntity(EntityType<? extends MagicAttackEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public PoisonBreathEntity(Level pLevel) {
        super(CCEntity.POISON_BREATH.get(), pLevel);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            spawnPoisonParticles();
        }

        if (getOwner() instanceof PoisonShadeEntity poisonShade) {

            if (poisonShade != null && !poisonShade.isAlive()) {
                discard();
            }

            if (poisonShade != null) {
                if (poisonShade.getTarget() != null) {
                    Vec3 headPos = poisonShade.position().add(0, poisonShade.getEyeHeight(), 0);

                    Vec3 lookVec = poisonShade.getLookAngle().scale(0.5D);

                    headPos = headPos.add(lookVec);

                    setPos(headPos.x, headPos.y, headPos.z);

                }

                List<LivingEntity> entitiesHit = this.level().getEntitiesOfClass(LivingEntity.class,
                        this.getBoundingBox().inflate(RANGE, poisonShade.getEyeHeight(), RANGE), e -> e != poisonShade && e.isAlive() && distanceTo(e) <= 5 + e.getBbWidth() / 2.0F && e.getY() <= getY() + poisonShade.getEyeHeight());

                for (LivingEntity entityHit : entitiesHit) {
                    if (isYawCheck(entityHit)) {
                        entityHit.hurt(entityHit.damageSources().magic(), 1.0F);
                        entityHit.addEffect(new MobEffectInstance(MobEffects.POISON, 10, 1));
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
    public void spawnPoisonParticles() {
        if (getOwner() instanceof PoisonShadeEntity poisonShade) {
            float yaw = (float) Math.toRadians(-poisonShade.getYHeadRot());
            float pitch = (float) Math.toRadians(-poisonShade.getXRot());
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
                    this.level().addParticle(ParticleTypes.DRAGON_BREATH, getX(), getY(), getZ(), xSpeed, ySpeed, zSpeed);
                }
            }
        }
    }
}
