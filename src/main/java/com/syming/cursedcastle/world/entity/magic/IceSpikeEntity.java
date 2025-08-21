package com.syming.cursedcastle.world.entity.magic;

import com.syming.cursedcastle.world.entity.CCEntity;
import com.syming.cursedcastle.world.entity.mob.shade.FrozenShadeEntity;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class IceSpikeEntity extends AbstractHurtingProjectile {
    protected FrozenShadeEntity frozenShade;

    public IceSpikeEntity(EntityType<? extends IceSpikeEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public IceSpikeEntity(double pX, double pY, double pZ, double pOffsetX, double pOffsetY, double pOffsetZ, Level pLevel) {
        super(CCEntity.ICE_SPIKE.get(), pX, pY, pZ, pOffsetX, pOffsetY, pOffsetZ, pLevel);
    }

    public IceSpikeEntity(LivingEntity pShooter, double pOffsetX, double pOffsetY, double pOffsetZ, Level pLevel) {
        super(CCEntity.ICE_SPIKE.get(), pShooter, pOffsetX, pOffsetY, pOffsetZ, pLevel);

        if (pShooter instanceof FrozenShadeEntity frozenShade) {
            this.frozenShade = frozenShade;
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pEntityHitResult) {
        super.onHitEntity(pEntityHitResult);

        if (!this.level().isClientSide()) {
            if (pEntityHitResult != null) {
                if (pEntityHitResult.getEntity() != this.frozenShade) {
                    if (this.frozenShade != null) {
                        this.frozenShade.heal(5.0F);
                    }

                    if (pEntityHitResult.getEntity() instanceof LivingEntity living) {
                        living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2));
                    }
                } else {
                    return;
                }
            }
            discard();
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.level().addParticle(ParticleTypes.SNOWFLAKE, getX(), getY(), getZ(), 0.0D, 0.0D, 0.0D);
        }

        if (this.frozenShade.lockedTargetPos != null) {
            lookAt(EntityAnchorArgument.Anchor.EYES, this.frozenShade.lockedTargetPos);
        }

    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        discard();
        return true;
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        return EntityDimensions.scalable(1.3125F, 0.0625F);
    }
}
