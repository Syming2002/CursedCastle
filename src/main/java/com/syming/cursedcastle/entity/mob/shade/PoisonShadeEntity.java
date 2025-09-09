package com.syming.cursedcastle.entity.mob.shade;

import com.syming.cursedcastle.entity.magic.PoisonBreathEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class PoisonShadeEntity extends ShadeEntity {

    private PoisonBreathEntity poisonBreath;

    public PoisonShadeEntity(EntityType<? extends ShadeEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public boolean hasWeakness(DamageSource source, float damageAmount) {
        return false;
    }

    @Override
    public boolean hasStrength(DamageSource source, float damageAmount) {
        return false;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }

    @Override
    public void setTarget(@Nullable LivingEntity living) {
        super.setTarget(living);
    }

    @Override
    @Nullable
    public LivingEntity getTarget() {
        return super.getTarget();
    }

    @Override
    public float getEyeHeight(Pose pPose) {
        return super.getEyeHeight(pPose);
    }

    @Override
    public void tick() {
        super.tick();

        LivingEntity target = getTarget();

        if (this.level().isClientSide()) {
            spawnLeavesParticles();
        }

        if (target != null) {
            lookAt(target,30.0F,30.0F);
            if (getAnimation() == ShadeEntity.REPULSE_ATTACK_ANIMATION) {
                if (getAnimationTick() == 95) {
                    if (!this.level().isClientSide()) {
                        Vec3 headPos = this.position().add(0.0D, getEyeHeight(), 0.0D);
                        Vec3 lookVec = getLookAngle().scale(0.5D);
                        headPos = headPos.add(lookVec);

                        this.poisonBreath = new PoisonBreathEntity(this.level());
                        this.poisonBreath.setOwner(this);
                        this.poisonBreath.setPos(headPos.x, headPos.y, headPos.z);
                        this.level().addFreshEntity(this.poisonBreath);
                        System.out.println("Poison Breath is Launching at " + this.poisonBreath.getX() + " " + this.poisonBreath.getY() + " " + this.poisonBreath.getZ());
                    }
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    private void spawnLeavesParticles() {
        double minX = this.getX() - this.getBbWidth() * 0.5;
        double minY = this.getY();
        double minZ = this.getZ() - this.getBbWidth() * 0.5;

        double maxX = this.getX() + this.getBbWidth() * 0.5;
        double maxY = this.getY() + this.getBbHeight();
        double maxZ = this.getZ() + this.getBbWidth() * 0.5;

        for (int i = 0; i < 1; i++) {
            double posX = minX + this.random.nextDouble() * (maxX - minX);
            double posY = minY + this.random.nextDouble() * (maxY - minY);
            double posZ = minZ + this.random.nextDouble() * (maxZ - minZ);

            double speedX = (this.random.nextDouble() - 0.5) * 0.02;
            double speedY = this.random.nextDouble() * 0.02;
            double speedZ = (this.random.nextDouble() - 0.5) * 0.02;

            this.level().addParticle(
                    ParticleTypes.CHERRY_LEAVES,
                    posX, posY, posZ,
                    speedX, speedY, speedZ
            );
        }
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {

    }
}
