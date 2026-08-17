package com.syming.cursedcastle.entity.mob.skeleton;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.api.IElementalDamage;
import com.syming.cursedcastle.damagetype.CCDamageType;
import com.syming.cursedcastle.enchantment.CCEnchantment;
import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.item.whip.UndeadKillerWhipItem;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class BloodSkeletonEntity extends CCSkeletonEntity {

    private ItemStack lastHitItem;

    public BloodSkeletonEntity(EntityType<? extends CCSkeletonEntity> pEntityType, Level pLevel) {
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
    }

    @Override
    public void tickDeath() {
        super.tickDeath();

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

//    @Override
//    protected SoundEvent getStepSound() {
//        return SoundEvents.SKELETON_STEP;
//    }

    @Override
    public boolean hasWeakness(DamageSource source, float damageAmount) {
        LivingEntity attacker = getLastAttacker();
        LivingEntity target = getTarget();
        if (attacker != null && target != null) {
            ItemStack stack = attacker.getMainHandItem();
            if (stack != null && stack.getItem() instanceof IElementalDamage elementalWeapon) {
                if (elementalWeapon != null) {
                    source = elementalWeapon.getElementalDamageSource(stack, target, attacker);
                    return source.is(CCDamageType.FIRE) || source.is(CCDamageType.HOLY) || source == target.damageSources().onFire() || source == target.damageSources().inFire();
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
                    return source.is(CCDamageType.DARK) || source.is(CCDamageType.ICE);
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
}