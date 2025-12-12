package com.syming.cursedcastle.entity.mob.skeleton;

import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.api.IElementalWeapon;
import com.syming.cursedcastle.datagen.CCDatapackBuiltinEntriesProvider;
import com.syming.cursedcastle.entity.projectile.ThrowingGoldenBoneEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class GoldenSkeleton extends CCSkeletonEntity {
    public GoldenSkeleton(EntityType<? extends CCSkeletonEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_SPEED, 1.2D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D);
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundEvents.SKELETON_STEP;
    }

    @Override
    public void aiStep() {
        super.aiStep();
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getTarget() != null) {
            if (getAnimation() == NO_ANIMATION && getRandom().nextFloat() < 0.5F && this.throwingBoneCooldown <= 0) {
                AnimationHandler.INSTANCE.sendAnimationMessage(this, THROWING_BONE_ANIMATION);
                this.throwingBoneCooldown = THROWING_BONE_COOLDOWN + this.random.nextInt(16);
            }

            if (getAnimation() == THROWING_BONE_ANIMATION) {
                if (getAnimationTick() == 32) {
                    if (!this.level().isClientSide() && this instanceof GoldenSkeleton) {
                        ThrowingGoldenBoneEntity throwingGoldenBone = new ThrowingGoldenBoneEntity(this, this.level());
                        throwingGoldenBone.shootFromRotation(this, this.getXRot(), this.getYRot(), 0.0F, 0.5F, 0.2F);
                        playSound(SoundEvents.SNOWBALL_THROW, 1.0F, 0.5F);
                        this.level().addFreshEntity(throwingGoldenBone);
                    }
                }
            }
        }

        if (this.throwingBoneCooldown > 0 && getAnimation() == NO_ANIMATION) {
            this.throwingBoneCooldown--;
        }
    }

    @Override
    public void tickDeath() {
        ++this.deathTime;

        if (this.deathTime == 1) {
            AnimationHandler.INSTANCE.sendAnimationMessage(this, DEATH_ANIMATION);
        }

        if (this.deathTime >= 20 && !this.level().isClientSide() && !isRemoved()) {
            this.level().broadcastEntityEvent(this, (byte)60);
            this.remove(Entity.RemovalReason.KILLED);
        }
    }

    @Override
    public boolean hasStrength(DamageSource pSource, float pDamageAmount) {
        LivingEntity attacker = getLastAttacker();
        LivingEntity target = getTarget();
        if (attacker != null && target != null) {
            ItemStack stack = attacker.getMainHandItem();
            if (stack != null && stack.getItem() instanceof IElementalWeapon elementalWeapon) {
                if (elementalWeapon != null) {
                    pSource = elementalWeapon.getElementalDamageSource(stack, target, attacker);
                    return pSource.is(CCDatapackBuiltinEntriesProvider.DARK);
                }
            }
        }
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
    public boolean hasWeakness(DamageSource pSource, float pDamageAmount) {
        LivingEntity attacker = getLastAttacker();
        LivingEntity target = getTarget();
        if (attacker != null && target != null) {
            ItemStack stack = attacker.getMainHandItem();
            if (stack != null && stack.getItem() instanceof IElementalWeapon elementalWeapon) {
                if (elementalWeapon != null) {
                    pSource = elementalWeapon.getElementalDamageSource(stack, target, attacker);
                    return pSource.is(CCDatapackBuiltinEntriesProvider.FIRE)
                            || pSource == target.damageSources().onFire()
                            || pSource == target.damageSources().inFire()
                            || pSource.is(CCDatapackBuiltinEntriesProvider.HOLY);
                }
            }
        }
        return false;
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
        return super.getAnimations();
    }

    @Override
    protected ResourceLocation getDefaultLootTable() {
        return new ResourceLocation(CursedCastleMod.MOD_ID, "entities/golden_skeleton");
    }
}
