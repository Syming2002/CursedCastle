package com.syming.cursedcastle.entity.mob.skeleton;

import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.api.IElementalDamage;
import com.syming.cursedcastle.datagen.CCDatapackBuiltinEntriesProvider;
import com.syming.cursedcastle.enchantment.CCEnchantment;
import com.syming.cursedcastle.entity.projectile.ThrowingBloodBoneEntity;
import com.syming.cursedcastle.item.whip.UndeadKillerWhipItem;
import com.syming.cursedcastle.entity.CCEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class BloodSkeletonEntity extends CCSkeletonEntity {

    private ItemStack lastHitItem;

    public BloodSkeletonEntity(EntityType<? extends AbstractSkeleton> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public BloodSkeletonEntity(Level pLevel) {
        super(CCEntity.BLOOD_SKELETON.get(), pLevel);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.ATTACK_SPEED, 1.2D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D);
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
                    if (!this.level().isClientSide() && this instanceof BloodSkeletonEntity) {
                        ThrowingBloodBoneEntity throwingBloodBone = new ThrowingBloodBoneEntity(this, this.level());
                        throwingBloodBone.shootFromRotation(this, this.getXRot(), this.getYRot(), 0.0F, 0.5F, 0.2F);
                        playSound(SoundEvents.SNOWBALL_THROW, 1.0F, 0.5F);
                        this.level().addFreshEntity(throwingBloodBone);
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

        if (this.deathTime == 95 && !this.level().isClientSide()) {
            BloodSkeletonEntity newBloodSkeleton = new BloodSkeletonEntity(this.level());
            newBloodSkeleton.copyPosition(this);
            newBloodSkeleton.setHealth(10.0F);
            this.level().addFreshEntity(newBloodSkeleton);
            discard();
        }
        if (this.lastHitItem != null) {
            if (this.deathTime == 20 && (this.lastHitItem.getItem() instanceof UndeadKillerWhipItem || this.lastHitItem.getEnchantmentLevel(CCEnchantment.BLOOD_SKELETON_KILLER.get()) == 1)) {
                this.level().broadcastEntityEvent(this, (byte) 60);
                this.remove(Entity.RemovalReason.KILLED);
            }
        }
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (pSource.getDirectEntity() instanceof Player attackingPlayer) {
            ItemStack heldItem = attackingPlayer.getMainHandItem();
            if (!heldItem.isEmpty()) {
                this.lastHitItem = heldItem;
            } else {
                this.lastHitItem = null;
            }
        }
        return super.hurt(pSource, pAmount);
    }

    @Override
    protected ResourceLocation getDefaultLootTable() {
        return new ResourceLocation(CursedCastleMod.MOD_ID, "entities/blood_skeleton");
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
    protected SoundEvent getStepSound() {
        return SoundEvents.SKELETON_STEP;
    }

    @Override
    public boolean hasWeakness(DamageSource source, float damageAmount) {
        LivingEntity attacker = getLastAttacker();
        LivingEntity target = getTarget();
        if (attacker != null && target != null) {
            ItemStack stack = attacker.getMainHandItem();
            if (stack != null && stack.getItem() instanceof IElementalDamage elementalWeapon) {
                if (elementalWeapon != null) {
                    source = elementalWeapon.getElementalDamageSource(stack, target, attacker);
                    return source.is(CCDatapackBuiltinEntriesProvider.FIRE) || source.is(CCDatapackBuiltinEntriesProvider.HOLY) || source == target.damageSources().onFire() || source == target.damageSources().inFire();
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
            if (stack != null && stack.getItem() instanceof IElementalDamage elementalWeapon) {
                if (elementalWeapon != null) {
                    source = elementalWeapon.getElementalDamageSource(stack, target, attacker);
                    return source.is(CCDatapackBuiltinEntriesProvider.DARK) || source.is(CCDatapackBuiltinEntriesProvider.ICE);
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
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {

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
    public Animation getAnimation() {
        return super.getAnimation();
    }

    @Override
    public void setAnimation(Animation animation) {
        super.setAnimation(animation);
    }

    @Override
    public Animation[] getAnimations() {
        return super.getAnimations();
    }
}
