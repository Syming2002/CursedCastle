package com.syming.cursedcastle.entity.mob.shade;

import com.github.alexthe666.citadel.animation.Animation;
import com.syming.cursedcastle.entity.magic.ThunderBreathEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class ThunderShadeEntity extends ShadeEntity {
    public static final Animation LIGHTNING_BOLT_ATTACK_ANIMATION = Animation.create(100);
    private int lightningBoltAttackCooldown = 0;
    public static final int LIGHTNING_BOLT_ATTACK_COOLDOWN = 300;

    private static final Animation[] ANIMATIONS = {REPULSE_ATTACK_ANIMATION, LIGHTNING_BOLT_ATTACK_ANIMATION};

    private ThunderBreathEntity thunderBreath;

    private Vec3 storedTargetPos = null;

    public ThunderShadeEntity(EntityType<? extends ShadeEntity> pEntityType, Level pLevel) {
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
            spawnElectricSparkParticles();
        }

        if (target != null) {
            lookAt(target,30.0F,30.0F);
            if (distanceToSqr(target) > 6.0F && getAnimation() == NO_ANIMATION && this.lightningBoltAttackCooldown <= 0 && isAlive()) {
                setAnimation(LIGHTNING_BOLT_ATTACK_ANIMATION);
                this.lightningBoltAttackCooldown = LIGHTNING_BOLT_ATTACK_COOLDOWN;
            }

            if (getAnimation() == ShadeEntity.REPULSE_ATTACK_ANIMATION) {
                if (getAnimationTick() == 95) {
                    if (!this.level().isClientSide()) {
                        Vec3 headPos = this.position().add(0.0D, getEyeHeight(), 0.0D);
                        Vec3 lookVec = getLookAngle().scale(0.5D);
                        headPos = headPos.add(lookVec);

                        this.thunderBreath = new ThunderBreathEntity(this.level());
                        this.thunderBreath.setOwner(this);
                        this.thunderBreath.setPos(headPos.x, headPos.y, headPos.z);
                        this.level().addFreshEntity(this.thunderBreath);
                        System.out.println("Thunder Breath is Launching at " + this.thunderBreath.getX() + " " + this.thunderBreath.getY() + " " + this.thunderBreath.getZ());
                    }
                }
            } else if (getAnimation() == ThunderShadeEntity.LIGHTNING_BOLT_ATTACK_ANIMATION) {
                if (getAnimationTick() == 1) {
                    this.storedTargetPos = target.position();
                }
                if (getAnimationTick() == 50) {
                    if (!this.level().isClientSide()) {
                        LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, this.level());
                        lightningBolt.setPos(this.storedTargetPos.x, this.storedTargetPos.y, this.storedTargetPos.z);
                        this.level().addFreshEntity(lightningBolt);
                        System.out.println("Lightning Bolt is Striking");
                    }
                }
            }
            if (getAnimation() != ThunderShadeEntity.LIGHTNING_BOLT_ATTACK_ANIMATION) {
                this.storedTargetPos = null;
            }
        }

        if (this.lightningBoltAttackCooldown > 0) this.lightningBoltAttackCooldown--;
    }

    @OnlyIn(Dist.CLIENT)
    private void spawnElectricSparkParticles() {
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
                    ParticleTypes.ELECTRIC_SPARK,
                    posX, posY, posZ,
                    speedX, speedY, speedZ
            );
        }
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {

    }

    @Override
    public Animation[] getAnimations() {
        return ANIMATIONS;
    }
}
