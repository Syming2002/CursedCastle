package com.syming.cursedcastle.entity.monster.shade;

import com.syming.cursedcastle.entity.magic.ThunderBreathEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ThunderShadeEntity extends ShadeEntity {

    public static final byte LIGHTNING_BOLT_ATTACK_ANIMATION = 2;
    public static final float LIGHTNING_BOLT_ATTACK_ANIMATION_LENGTH_IN_SECONDS = 5.0F;
    public static final int LIGHTNING_BOLT_ATTACK_ANIMATION_LENGTH_IN_TICKS = (int) (LIGHTNING_BOLT_ATTACK_ANIMATION_LENGTH_IN_SECONDS * 20);
    private static final int LIGHTNING_BOLT_ATTACK_COOLDOWN = 300;
    private int lightningBoltAttackCooldown = LIGHTNING_BOLT_ATTACK_COOLDOWN;

    public final AnimationState lightningBoltAttackAnimationState = new AnimationState();

    private int lightningBoltAttackAnimationTick = 0;

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
    public List<Component> getWeaknessDescription() {
        return List.of();
    }

    @Override
    public List<Component> getStrengthDescription() {
        return List.of();
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
        if (this.level().isClientSide()) {
            this.lightningBoltAttackAnimationState.animateWhen(getAnimation() == LIGHTNING_BOLT_ATTACK_ANIMATION, this.tickCount);
        }

        super.tick();

        LivingEntity target = getTarget();

        if (this.level().isClientSide()) {
            spawnElectricSparkParticles();
        }

        if (target != null) {
            lookAt(target,30.0F,30.0F);

            if (this.lightningBoltAttackCooldown > 0) this.lightningBoltAttackCooldown--;

            if (distanceToSqr(target) > 6.0F && getAnimation() == IDLE_ANIMATION && this.lightningBoltAttackCooldown <= 0 && isAlive()) {
                setAnimation(LIGHTNING_BOLT_ATTACK_ANIMATION);
                this.lightningBoltAttackCooldown = LIGHTNING_BOLT_ATTACK_COOLDOWN;
            }

            if (getAnimation() == ShadeEntity.REPULSE_ATTACK_ANIMATION) {
                if (this.repulseAttackAnimationTick == 95) {
                    if (!this.level().isClientSide()) {
                        Vec3 headPos = this.position().add(0.0D, getEyeHeight(), 0.0D);
                        Vec3 lookVec = getLookAngle().scale(0.5D);
                        headPos = headPos.add(lookVec);

                        this.thunderBreath = new ThunderBreathEntity(this.level());
                        this.thunderBreath.setOwner(this);
                        this.thunderBreath.setPos(headPos.x, headPos.y, headPos.z);
                        this.level().addFreshEntity(this.thunderBreath);
                    }
                }
            } else if (getAnimation() == ThunderShadeEntity.LIGHTNING_BOLT_ATTACK_ANIMATION) {
                this.lightningBoltAttackAnimationTick++;
                if (this.lightningBoltAttackAnimationTick == 1) {
                    this.storedTargetPos = target.position();
                }
                if (this.lightningBoltAttackAnimationTick == 50) {
                    if (!this.level().isClientSide()) {
                        LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, this.level());
                        lightningBolt.setPos(this.storedTargetPos.x, this.storedTargetPos.y, this.storedTargetPos.z);
                        this.level().addFreshEntity(lightningBolt);
                    }
                }

                if (this.lightningBoltAttackAnimationTick > LIGHTNING_BOLT_ATTACK_ANIMATION_LENGTH_IN_TICKS) {
                    setAnimation(IDLE_ANIMATION);
                    this.lightningBoltAttackAnimationTick = 0;
                    this.lightningBoltAttackCooldown = LIGHTNING_BOLT_ATTACK_COOLDOWN;
                }
            }
            if (getAnimation() != ThunderShadeEntity.LIGHTNING_BOLT_ATTACK_ANIMATION) {
                this.storedTargetPos = null;
            }
        } else {
            setAnimation(IDLE_ANIMATION);
            this.lightningBoltAttackCooldown = LIGHTNING_BOLT_ATTACK_COOLDOWN;
            this.lightningBoltAttackAnimationTick = 0;
        }

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
}
