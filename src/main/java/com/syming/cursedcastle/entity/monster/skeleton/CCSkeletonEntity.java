package com.syming.cursedcastle.entity.monster.skeleton;

import com.syming.cursedcastle.entity.monster.CCMonster;
import com.syming.cursedcastle.entity.projectile.ThrowingBloodBoneEntity;
import com.syming.cursedcastle.entity.projectile.ThrowingBoneEntity;
import com.syming.cursedcastle.entity.projectile.ThrowingGoldenBoneEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public abstract class CCSkeletonEntity extends CCMonster implements RangedAttackMob {
    private static final EntityDataAccessor<Byte> ANIMATION = SynchedEntityData.defineId(CCSkeletonEntity.class, EntityDataSerializers.BYTE);

    public static final byte IDLE_ANIMATION = 0;

    public static final byte THROWING_BONE_ANIMATION = 1;
    public static final float THROWING_BONE_ATTACK_ANIMATION_LENGTH_IN_SECONDS = 2.0F;
    public static final int THROWING_BONE_ANIMATION_LENGTH_IN_TICKS = (int) (THROWING_BONE_ATTACK_ANIMATION_LENGTH_IN_SECONDS * 20);
    protected int throwingBoneCooldown = 0;
    public static final int THROWING_BONE_COOLDOWN = 60;

    public final AnimationState throwingBoneAnimationState = new AnimationState();

    protected int throwingBoneAnimationTick = 0;


    public static final byte DEATH_ANIMATION = 2;
    public static final float DEATH_ANIMATION_LENGTH_IN_SECONDS = 4.75F;
    public static final int DEATH_ANIMATION_LENGTH_IN_TICKS = (int) (DEATH_ANIMATION_LENGTH_IN_SECONDS * 20);

    public final AnimationState deathAnimationState = new AnimationState();


    protected float fallSideRotation = (this.random.nextFloat() - 0.5F) * 30.0F;

    private boolean strafingBackwards;
    private boolean strafingClockwise;

    public CCSkeletonEntity(EntityType<? extends CCMonster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        this.entityData.define(ANIMATION, IDLE_ANIMATION);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<Player>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<Villager>(this, Villager.class, true));
    }

    @Override
    public void tick() {
        if (this.level().isClientSide()) {
            this.throwingBoneAnimationState.animateWhen(getAnimation() == THROWING_BONE_ANIMATION, this.tickCount);
        }

        super.tick();
        LivingEntity target = this.getTarget();

        if (target != null) {

            if (getAnimation() == IDLE_ANIMATION) {
                if (this.throwingBoneCooldown > 0) {
                    this.throwingBoneCooldown--;
                }
            }

            if (getAnimation() == IDLE_ANIMATION && getRandom().nextFloat() < 0.5F && isAlive() && this.throwingBoneCooldown <= 0) {
                setAnimation(THROWING_BONE_ANIMATION);
            }

            if (getAnimation() == THROWING_BONE_ANIMATION) {
                this.throwingBoneAnimationTick++;

                if (this.getRandom().nextFloat() < 0.3F) {
                    this.strafingBackwards = !this.strafingBackwards;
                }

                if (this.getRandom().nextFloat() < 0.3F) {
                    this.strafingClockwise = !this.strafingClockwise;
                }

                getMoveControl().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);

                if (this.throwingBoneAnimationTick == 32) {

                    if (!this.level().isClientSide() && this instanceof ThrowingBoneSkeletonEntity) {
                        ThrowingBoneEntity throwingBone = new ThrowingBoneEntity(this, this.level());
                        throwingBone.shootFromRotation(this, this.getXRot(), this.getYRot(), 0.0F, 0.5F, 0.2F);
                        playSound(SoundEvents.SNOWBALL_THROW, 1.0F, 0.5F);
                        this.level().addFreshEntity(throwingBone);
                    }

                    if (!this.level().isClientSide() && this instanceof BloodSkeletonEntity) {
                        ThrowingBloodBoneEntity throwingBloodBone = new ThrowingBloodBoneEntity(this, this.level());
                        throwingBloodBone.shootFromRotation(this, this.getXRot(), this.getYRot(), 0.0F, 0.5F, 0.2F);
                        playSound(SoundEvents.SNOWBALL_THROW, 1.0F, 0.5F);
                        this.level().addFreshEntity(throwingBloodBone);
                    }

                    if (!this.level().isClientSide() && this instanceof GoldenSkeletonEntity) {
                        ThrowingGoldenBoneEntity throwingGoldenBone = new ThrowingGoldenBoneEntity(this, this.level());
                        throwingGoldenBone.shootFromRotation(this, this.getXRot(), this.getYRot(), 0.0F, 0.5F, 0.2F);
                        playSound(SoundEvents.SNOWBALL_THROW, 1.0F, 0.5F);
                        this.level().addFreshEntity(throwingGoldenBone);
                    }

                    if (!this.level().isClientSide() && this instanceof ClearBoneSkeletonEntity clearBoneSkeleton) {
                        ThrowingBoneEntity throwingBone = new ThrowingBoneEntity(this, this.level());
                        throwingBone.shootFromRotation(this, this.getXRot(), this.getYRot(), 0.0F, 0.5F, 0.0000001F);
                        playSound(SoundEvents.SNOWBALL_THROW, 1.0F, 0.5F);
                        this.level().addFreshEntity(throwingBone);
                    }
                }

                if (this.throwingBoneAnimationTick > THROWING_BONE_ANIMATION_LENGTH_IN_TICKS) {
                    setAnimation(IDLE_ANIMATION);
                    this.throwingBoneAnimationTick = 0;
                    this.throwingBoneCooldown = THROWING_BONE_COOLDOWN - this.random.nextInt(16);
                }
            }
        } else {
            setAnimation(IDLE_ANIMATION);
            this.throwingBoneAnimationTick = 0;
            this.throwingBoneCooldown = THROWING_BONE_COOLDOWN - this.random.nextInt(16);
        }
    }

    @Override
    public void tickDeath() {
        ++this.deathTime;

        if (!this.deathAnimationState.isStarted()) {
            this.deathAnimationState.start(this.tickCount);
        }

        if (!(this instanceof BloodSkeletonEntity) && this.deathTime >= 20 && !this.level().isClientSide() && !isRemoved()) {
            this.level().broadcastEntityEvent(this, (byte) 60);
            this.remove(Entity.RemovalReason.KILLED);
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_33579_) {
        return SoundEvents.SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SKELETON_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos blockPos, BlockState blockState) {
        playSound(SoundEvents.SKELETON_STEP, 0.15F, 1.0F);
    }

    public byte getAnimation() {
        return this.entityData.get(ANIMATION);
    }

    public void setAnimation(byte pAnimation) {
        this.entityData.set(ANIMATION, pAnimation);
    }

    public float getFallSideRotation() {
        return this.fallSideRotation;
    }

}
