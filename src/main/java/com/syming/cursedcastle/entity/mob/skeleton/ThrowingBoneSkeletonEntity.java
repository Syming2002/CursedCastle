package com.syming.cursedcastle.entity.mob.skeleton;

import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.syming.cursedcastle.api.IElementalWeapon;
import com.syming.cursedcastle.damagesource.CCDamageSource;
import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.projectile.ThrowingBoneEntity;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ThrowingBoneSkeletonEntity extends CCSkeletonEntity implements RangedAttackMob {

    public static final Animation THROWING_BONE_ANIMATION = Animation.create(40);
    private int throwingBoneCooldown = 0;
    public static final int THROWING_BONE_COOLDOWN = 60;

    private static final Animation[] ANIMATIONS = {THROWING_BONE_ANIMATION, DEATH_ANIMATION};

    public ThrowingBoneSkeletonEntity(EntityType<? extends CCSkeletonEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ThrowingBoneSkeletonEntity(Level pLevel) {
        super(CCEntity.THROWING_BONE_SKELETON.get(), pLevel);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<Player>(this, Player.class, true));
        this.goalSelector.addGoal(2, new RestrictSunGoal(this));
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundEvents.SKELETON_STEP;
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    public void aiStep() {
        super.aiStep();
    }

    @Override
    public boolean hasWeakness(DamageSource source, float damageAmount) {
        LivingEntity attacker = getLastAttacker();
        LivingEntity target = getTarget();
        if (attacker != null && target != null) {
            ItemStack stack = attacker.getMainHandItem();
            if (stack != null && stack.getItem() instanceof IElementalWeapon elementalWeapon) {
                if (elementalWeapon != null) {
                    source = elementalWeapon.getElementalDamageSource(stack, target, attacker);
                    return source.is(CCDamageSource.FIRE) || source.is(CCDamageSource.HOLY) || source == target.damageSources().onFire() || source == target.damageSources().inFire();
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasStrength(DamageSource source, float damageAmount) {
        LivingEntity attacker = getLastAttacker();
        LivingEntity target = getTarget();
        if (attacker != null && target != null) {
            ItemStack stack = attacker.getMainHandItem();
            if (stack != null && stack.getItem() instanceof IElementalWeapon elementalWeapon) {
                if (elementalWeapon != null) {
                    source = elementalWeapon.getElementalDamageSource(stack, target, attacker);
                    return source.is(CCDamageSource.DARK);
                }
            }
        }
        return false;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getTarget() != null) {
            if (getAnimation() == NO_ANIMATION && getRandom().nextFloat() < 0.5F && this.throwingBoneCooldown <= 0) {
                setAnimation(THROWING_BONE_ANIMATION);
                this.throwingBoneCooldown = THROWING_BONE_COOLDOWN - this.random.nextInt(16);
            }

            if (getAnimation() == THROWING_BONE_ANIMATION) {
                if (getAnimationTick() == 32) {
                    if (!this.level().isClientSide()) {
                        ThrowingBoneEntity throwingBone = new ThrowingBoneEntity(this, this.level());
                        throwingBone.shootFromRotation(this, this.getXRot(), this.getYRot(), 0.0F, 0.5F, 0.2F);
                        playSound(SoundEvents.SNOWBALL_THROW, 1.0F, 0.5F);
                        this.level().addFreshEntity(throwingBone);
                    }
                }
            }
        }

        AnimationHandler.INSTANCE.updateAnimations(this);

        if (this.throwingBoneCooldown > 0 && getAnimation() == NO_ANIMATION) {
            this.throwingBoneCooldown--;
        }
    }

    @Override
    public void tickDeath() {
        super.tickDeath();

        if (this.deathTime >= 20) {
            this.level().broadcastEntityEvent(this, (byte)60);
            this.remove(Entity.RemovalReason.KILLED);
        }
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {

    }

    @Override
    public Animation getAnimation() {
        return super.getAnimation();
    }

    @Override
    public void setAnimation(Animation animation) {
        super.setAnimation(animation);
    }

    @Override
    public int getAnimationTick() {
        return super.getAnimationTick();
    }

    @Override
    public void setAnimationTick(int tick) {
        super.setAnimationTick(tick);
    }

    @Override
    public Animation[] getAnimations() {
        return ANIMATIONS;
    }
}
