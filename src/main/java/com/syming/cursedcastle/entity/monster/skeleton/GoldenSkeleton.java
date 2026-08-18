package com.syming.cursedcastle.entity.monster.skeleton;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.api.IElementalDamage;
import com.syming.cursedcastle.damagetype.CCDamageType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
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

//    @Override
//    protected SoundEvent getStepSound() {
//        return SoundEvents.SKELETON_STEP;
//    }

    @Override
    public void aiStep() {
        super.aiStep();
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public boolean hasStrength(DamageSource pSource, float pDamageAmount) {
        LivingEntity attacker = getLastAttacker();
        LivingEntity target = getTarget();
        if (attacker != null && target != null) {
            ItemStack stack = attacker.getMainHandItem();
            if (stack != null && stack.getItem() instanceof IElementalDamage elementalWeapon) {
                if (elementalWeapon != null) {
                    pSource = elementalWeapon.getElementalDamageSource(stack, target, attacker);
                    return pSource.is(CCDamageType.DARK);
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
            if (stack != null && stack.getItem() instanceof IElementalDamage elementalWeapon) {
                if (elementalWeapon != null) {
                    pSource = elementalWeapon.getElementalDamageSource(stack, target, attacker);
                    return pSource.is(CCDamageType.FIRE)
                            || pSource == target.damageSources().onFire()
                            || pSource == target.damageSources().inFire()
                            || pSource.is(CCDamageType.HOLY);
                }
            }
        }
        return false;
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {

    }

    @Override
    protected ResourceLocation getDefaultLootTable() {
        return new ResourceLocation(CursedCastleMod.MOD_ID, "entities/golden_skeleton");
    }
}
