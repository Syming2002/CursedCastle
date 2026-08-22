package com.syming.cursedcastle.entity.monster.skeleton;

import com.syming.cursedcastle.api.IElementalDamage;
import com.syming.cursedcastle.damagetype.CCDamageType;
import com.syming.cursedcastle.entity.CCEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class ClearBoneSkeletonEntity extends CCSkeletonEntity {
    private static final EntityDataAccessor<Boolean> INVULNERABLE = SynchedEntityData.defineId(ClearBoneSkeletonEntity.class, EntityDataSerializers.BOOLEAN);
    public int invulnerableTicker;

    public ClearBoneSkeletonEntity(EntityType<? extends CCSkeletonEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ClearBoneSkeletonEntity(Level pLevel) {
        super(CCEntity.CLEAR_BONE_SKELETON.get(), pLevel);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        this.entityData.define(INVULNERABLE, false);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        super.onSyncedDataUpdated(pKey);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }

    public static AttributeSupplier.Builder createMobAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 36.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.ATTACK_SPEED, 1.2D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D);
    }

    @Override
    public void tick() {
        super.tick();

        LivingEntity target = this.getTarget();

        if (!this.level().isClientSide()) {
            if (target != null && this.random.nextFloat() <= 0.005F && this.invulnerableTicker <= 0 && isAlive() && this.onGround()) {
                setClearBoneInvulnerable(true);
                this.invulnerableTicker = 100;
            }

            if (this.invulnerableTicker > 0) {
                this.invulnerableTicker--;
            }

            if (this.invulnerableTicker <= 0) {
                setClearBoneInvulnerable(false);
            }
        }

        if (isClearBoneInvulnerable()) {
            getNavigation().stop();
            setTarget(null);
            setNoAi(true);
            if (getAnimation() != IDLE_ANIMATION) {
                setAnimation(IDLE_ANIMATION);
            }
        } else {
            setNoAi(false);
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
    public boolean hasWeakness(DamageSource source, float damageAmount) {
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
                    return source.is(CCDamageType.THUNDER);
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
    public boolean hurt(DamageSource pSource, float pAmount) {
        return !isClearBoneInvulnerable() && super.hurt(pSource, pAmount);
    }

    public boolean isClearBoneInvulnerable() {
        return this.entityData.get(INVULNERABLE);
    }

    public void setClearBoneInvulnerable(boolean pClearBoneInvulnerable) {
        this.entityData.set(INVULNERABLE, pClearBoneInvulnerable);
    }
}
