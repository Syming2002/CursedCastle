package com.syming.cursedcastle.entity.monster.shade;

import com.syming.cursedcastle.api.IElementalDamage;
import com.syming.cursedcastle.damagetype.CCDamageType;
import com.syming.cursedcastle.entity.magic.FireBreathEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HeatShadeEntity extends ShadeEntity {

    private FireBreathEntity fireBreath;

    public HeatShadeEntity(EntityType<? extends ShadeEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
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
                    return source.is(CCDamageType.ICE);
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
                    return source.is(CCDamageType.FIRE) || source == target.damageSources().onFire() || source == target.damageSources().inFire();
                }
            }
        }
        return false;
    }

    @Override
    public List<Component> getWeaknessDescription() {
        return List.of(Component.translatable("entity.cursedcastle.ice_weakness_description").setStyle(Style.EMPTY.withColor(0xB9E8EA)));
    }

    @Override
    public List<Component> getStrengthDescription() {
        return List.of(Component.translatable("entity.cursedcastle.fire_strength_description").setStyle(Style.EMPTY.withColor(0xEB0306)));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }

    @Override
    public void setTarget(@Nullable LivingEntity living) {
        super.setTarget(living);
    }

    @Override
    @Nullable
    public LivingEntity getTarget() {
        return super.getTarget();
    }

    @Override
    public void tick() {
        super.tick();

        LivingEntity target = getTarget();

        if (this.level().isClientSide()) {
            spawnFlameParticles();
        }

        if (target != null) {
            lookAt(target,30.0F,30.0F);
            if (getAnimation() == ShadeEntity.REPULSE_ATTACK_ANIMATION) {
                if (this.repulseAttackAnimationTick == 95) {
                    if (!this.level().isClientSide()) {
                        Vec3 headPos = this.position().add(0.0D, getEyeHeight(), 0.0D);
                        Vec3 lookVec = getLookAngle().scale(0.5D);
                        headPos = headPos.add(lookVec);

                        this.fireBreath = new FireBreathEntity(this.level());
                        this.fireBreath.setOwner(this);
                        this.fireBreath.setPos(headPos.x, headPos.y, headPos.z);
                        this.level().addFreshEntity(this.fireBreath);
                    }
                }
            }
        }
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (hasStrength(pSource, pAmount)) {
            return super.hurt(pSource, pAmount / pAmount);
        }
        if (hasWeakness(pSource, pAmount)) {
            return super.hurt(pSource, pAmount * pAmount);
        }
        return super.hurt(pSource, pAmount);
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    private void spawnFlameParticles() {
        double minX = this.getX() - this.getBbWidth() * 0.5;
        double minY = this.getY();
        double minZ = this.getZ() - this.getBbWidth() * 0.5;

        double maxX = this.getX() + this.getBbWidth() * 0.5;
        double maxY = this.getY() + this.getBbHeight();
        double maxZ = this.getZ() + this.getBbWidth() * 0.5;

        for (int i = 0; i < 1; i++) {
            double posX = minX + this.random.nextDouble() * (maxX - minX);
            double posY = minY + this.random.nextDouble() * (maxY - minY);
            double posZ = minZ + this.random.nextDouble() * (maxZ - minZ);

            double speedX = (this.random.nextDouble() - 0.5) * 0.02;
            double speedY = this.random.nextDouble() * 0.02;
            double speedZ = (this.random.nextDouble() - 0.5) * 0.02;

            this.level().addParticle(
                    ParticleTypes.FLAME,
                    posX, posY, posZ,
                    speedX, speedY, speedZ
            );
        }
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {

    }
}
