package com.syming.cursedcastle.entity.magic;

import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.mob.shade.FrozenShadeEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class IceSpikeEntity extends Projectile {
    private int lifeTicks = 0;
    private static final int MAX_LIFETIME = 150;
    private boolean lockedRotation = false;

    public IceSpikeEntity(EntityType<? extends IceSpikeEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        refreshDimensions();
    }

    public IceSpikeEntity(Level pLevel) {
        super(CCEntity.ICE_SPIKE.get(), pLevel);

        refreshDimensions();
    }

    @Override
    protected boolean canHitEntity(Entity pEntity) {
        return pEntity.isPickable() && pEntity.isAlive() && pEntity != getOwner();
    }

    @Override
    protected void onHitEntity(EntityHitResult pEntityHitResult) {
        super.onHitEntity(pEntityHitResult);

        if (!this.level().isClientSide) {
            Entity hitEntity = pEntityHitResult.getEntity();

            if (hitEntity != null && hitEntity != this.getOwner()) {
                if (this.getOwner() instanceof FrozenShadeEntity frozenShade) {
                    if (hitEntity.hurt(this.damageSources().mobAttack(frozenShade), 8.0F)) {
                        if (hitEntity.isAlive()) {
                            this.doEnchantDamageEffects(frozenShade, hitEntity); // applies enchantments
                        } else {
                            frozenShade.heal(5.0F);
                        }
                    }
                } else {
                    hitEntity.hurt(this.damageSources().magic(), 5.0F);
                }

                if (hitEntity instanceof LivingEntity living) {
                    living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 2));
                }
            }
            this.discard();
        }
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    public void tick() {
        super.tick();

        this.lifeTicks++;
        if (this.lifeTicks > MAX_LIFETIME) {
            discard();
            return;
        }

        HitResult hitResult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);

        if (hitResult.getType() != HitResult.Type.MISS) {
            onHit(hitResult);
        }

        setPos(getX() + getDeltaMovement().x, getY() + getDeltaMovement().y, getZ() + getDeltaMovement().z);

        if (this.level().isClientSide()) {
            this.level().addParticle(ParticleTypes.SNOWFLAKE, getX(), getY(), getZ(), 0.0D, 0.0D, 0.0D);
        }

        if (!this.lockedRotation && getOwner() instanceof FrozenShadeEntity frozenShade) {
            if (frozenShade != null) {
                if (frozenShade.getTarget() != null) {
                    Vec3 spikePos = position();
                    Vec3 targetPos = frozenShade.getTarget().getEyePosition();

                    double dx = targetPos.x - spikePos.x;
                    double dy = targetPos.y - spikePos.y;
                    double dz = targetPos.z - spikePos.z;

                    float targetYaw = (float)(Mth.atan2(dz, dx) * (180F / Math.PI)) - 90F;
                    float targetPitch = (float)(-(Mth.atan2(dy, Math.sqrt(dx * dx + dz * dz)) * (180F / Math.PI)));

                    this.setYRot(Mth.rotLerp(0.2F, getYRot(), targetYaw));
                    this.setXRot(Mth.rotLerp(0.2F, getXRot(), targetPitch));

                }
                if (!frozenShade.isAlive()) {
                    discard();
                }
            }
        }
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (!this.level().isClientSide() && pSource.getEntity() instanceof Player) {
            discard();
            return true;
        }
        return false;
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        return EntityDimensions.scalable(0.25F, 0.0625F);
    }

    public void lockRotation() {
        this.lockedRotation = true;
    }

    public boolean isLockedRotation() {
        return this.lockedRotation;
    }
}
