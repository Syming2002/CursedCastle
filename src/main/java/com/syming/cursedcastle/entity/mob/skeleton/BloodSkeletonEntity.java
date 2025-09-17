package com.syming.cursedcastle.entity.mob.skeleton;

import com.github.alexthe666.citadel.repack.jaad.aac.syntax.PCE;
import com.syming.cursedcastle.api.IElementalWeapon;
import com.syming.cursedcastle.damagesource.CCDamageSource;
import com.syming.cursedcastle.effect.CCEffect;
import com.syming.cursedcastle.item.whip.UndeadKillerWhipItem;
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
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class BloodSkeletonEntity extends CCSkeletonEntity {

    private Item lastHitItem;

    public BloodSkeletonEntity(EntityType<? extends AbstractSkeleton> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        refreshDimensions();
    }

    public BloodSkeletonEntity(Level pLevel) {
        super(CCEntity.BLOOD_SKELETON.get(), pLevel);

        refreshDimensions();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<Player>(this, Player.class, true));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, true));
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
    public void tickDeath() {
        super.tickDeath();

        if (this.deathTime == 55 && !this.level().isClientSide() && (!(this.lastHitItem instanceof UndeadKillerWhipItem) || !hasEffect(CCEffect.ANTI_BLOOD_SKELETON.get()))) {
            BloodSkeletonEntity bloodSkeleton = new BloodSkeletonEntity(this.level());
            bloodSkeleton.copyPosition(this);
            bloodSkeleton.setHealth(10.0F);
            this.level().addFreshEntity(bloodSkeleton);
            discard();
        }
        if (this.deathTime >= 20 && (this.lastHitItem instanceof UndeadKillerWhipItem || hasEffect(CCEffect.ANTI_BLOOD_SKELETON.get()))) {
            this.level().broadcastEntityEvent(this, (byte) 60);
            this.remove(Entity.RemovalReason.KILLED);
        }
    }

    @Override
    public void die(DamageSource pDamageSource) {
        super.die(pDamageSource);
    }

    @Override
    public void aiStep() {
        super.aiStep();
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
    protected SoundEvent getStepSound() {
        return SoundEvents.SKELETON_STEP;
    }

    @Override
    public boolean hasWeakness(DamageSource source, float damageAmount) {
        return super.hasWeakness(source, damageAmount);
    }

    @Override
    public boolean hasStrength(DamageSource source, float damageAmount) {
        return super.hasStrength(source, damageAmount);
    }
}
