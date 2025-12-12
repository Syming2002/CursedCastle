package com.syming.cursedcastle.entity.mob.skeleton;

import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.syming.cursedcastle.api.IElementalWeapon;
import com.syming.cursedcastle.api.ILivingEntityStrengthWeakness;
import com.syming.cursedcastle.datagen.CCDatapackBuiltinEntriesProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public abstract class CCSkeletonEntity extends AbstractSkeleton implements IAnimatedEntity, ILivingEntityStrengthWeakness {

    protected Animation animation;
    public static final Animation DEATH_ANIMATION = Animation.create(95);

    public static final Animation THROWING_BONE_ANIMATION = Animation.create(40);
    protected int throwingBoneCooldown = 0;
    public static final int THROWING_BONE_COOLDOWN = 60;

    protected static final Animation[] ANIMATIONS = {DEATH_ANIMATION, THROWING_BONE_ANIMATION};
    protected int animationTick;


    protected float fallSideRotation = 0;
    protected boolean initializedDeathAnimation = false;

    public CCSkeletonEntity(EntityType<? extends AbstractSkeleton> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        refreshDimensions();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<Player>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<Villager>(this, Villager.class, true));
    }

    @Override
    public void tick() {
        super.tick();

        if (getTarget() != null) {
            if (getAnimation() == NO_ANIMATION && getRandom().nextFloat() < 0.5F && this.throwingBoneCooldown <= 0) {
                setAnimation(THROWING_BONE_ANIMATION);
                this.throwingBoneCooldown = THROWING_BONE_COOLDOWN - this.random.nextInt(16);
            }
        }

        AnimationHandler.INSTANCE.updateAnimations(this);

        if (this.throwingBoneCooldown > 0 && getAnimation() == NO_ANIMATION) {
            this.throwingBoneCooldown--;
        }
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        return EntityDimensions.scalable(0.6F, 1.99F);
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
