package com.syming.cursedcastle.entity.mob;

import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.syming.cursedcastle.item.whip.vampirekiller.VampireKillerWhipItem;
import com.syming.cursedcastle.entity.CCEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class BloodSkeletonEntity extends CCMonster implements IAnimatedEntity {

    private Animation animation;
    public static final Animation DEATH_ANIMATION = Animation.create(55);
    private static final Animation[] ANIMATIONS = {DEATH_ANIMATION};
    private int animationTick;
    private Item lastHitItem;

    private float fallSideRotation = 0;
    private boolean initializedDeathAnimation = false;

    public BloodSkeletonEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        refreshDimensions();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<Player>(this, Player.class, true));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, true));
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.ATTACK_SPEED, 1.2D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D);
    }

    @Override
    public void tickDeath() {
        this.deathTime++;

        AnimationHandler.INSTANCE.updateAnimations(this);

        if (this.deathTime == 1) {
            setAnimation(DEATH_ANIMATION);
        }
        if (this.deathTime == 55 && !this.level().isClientSide() && !(this.lastHitItem instanceof VampireKillerWhipItem)) {
            BloodSkeletonEntity bloodSkeleton = new BloodSkeletonEntity(CCEntity.BLOOD_SKELETON.get(), this.level());
            bloodSkeleton.copyPosition(this);
            bloodSkeleton.setHealth(10.0F);
            this.level().addFreshEntity(bloodSkeleton);
            discard();
        }
        if (this.deathTime >= 20 && this.lastHitItem instanceof VampireKillerWhipItem) {
            this.level().broadcastEntityEvent(this, (byte)60);
            this.remove(Entity.RemovalReason.KILLED);
        }
    }

    @Override
    public void die(DamageSource pDamageSource) {
        super.die(pDamageSource);
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        return EntityDimensions.scalable(0.6F, 1.99F);
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (pSource.getDirectEntity() instanceof Player attackingPlayer) {
            ItemStack heldItem = attackingPlayer.getMainHandItem();
            if (!heldItem.isEmpty()) {
                this.lastHitItem = heldItem.getItem();
            } else {
                this.lastHitItem = null;
            }
        }
        return super.hurt(pSource, pAmount);
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

    @Override
    public int getAnimationTick() {
        return this.animationTick;
    }

    @Override
    public void setAnimationTick(int tick) {
        this.animationTick = tick;
    }

    @Override
    public Animation getAnimation() {
        return this.animation;
    }

    @Override
    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    @Override
    public Animation[] getAnimations() {
        return ANIMATIONS;
    }

    @Override
    public boolean hasWeakness(DamageSource source, float damageAmount) {
        return false;
    }

    @Override
    public boolean hasStrength(DamageSource source, float damageAmount) {
        return false;
    }

    public void initDeathAnimationRandoms() {
        if (!this.initializedDeathAnimation) {
            this.fallSideRotation = (this.random.nextFloat() - 0.5F) * 30.0F;
            this.initializedDeathAnimation = true;
        }
    }

    public float getFallSideRotation() {
        return this.fallSideRotation;
    }

}
