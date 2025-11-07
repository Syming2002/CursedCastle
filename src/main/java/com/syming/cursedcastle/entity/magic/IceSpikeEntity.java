package com.syming.cursedcastle.entity.magic;

import com.syming.cursedcastle.effect.CCEffect;
import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.mob.shade.FrozenShadeEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
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

public class IceSpikeEntity extends Projectile {

    private boolean isLaunched = false;

    public IceSpikeEntity(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        refreshDimensions();
    }

    public IceSpikeEntity(Level pLevel) {
        this(CCEntity.ICE_SPIKE.get(), pLevel);

        refreshDimensions();
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);

        if (pResult.getType() == HitResult.Type.ENTITY) {
            this.onHitEntity((EntityHitResult) pResult);
        }
    }

    @Override
    protected boolean canHitEntity(Entity pEntity) {
        return pEntity.isAlive() && pEntity != getOwner();
    }

    @Override
    protected void onHitEntity(EntityHitResult pEntityHitResult) {
        System.out.println("On Hit Entity");

        if (!this.level().isClientSide()) {
            Entity hitEntity = pEntityHitResult.getEntity();

            if (hitEntity != null && hitEntity != this.getOwner()) {
                System.out.println("Target != Owner");
                if (this.getOwner() instanceof FrozenShadeEntity frozenShade) {
                        System.out.println("Owner == Frozen Shade");
                        if (hitEntity.isAlive()) {
                            System.out.println("Target is Alive");
                            hitEntity.hurt(this.damageSources().mobAttack(frozenShade), 8.0F);
                            frozenShade.heal(5.0F);
                            if (hitEntity instanceof LivingEntity livingTarget) {
                                livingTarget.addEffect(new MobEffectInstance(CCEffect.FREEZE.get(), 200, 1));
                            }
                        }
                }
            }
        }
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        return EntityDimensions.scalable(0.5F, 0.5F);
    }

    @Override
    protected void defineSynchedData() {
    }

    @Override
    public void tick() {
        super.tick();

        if (this.tickCount > 200) {
            discard();
        }

        HitResult hitResult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hitResult.getType() != HitResult.Type.MISS) {
            onHit(hitResult);
        }

        setPos(getX() + getDeltaMovement().x, getY() + getDeltaMovement().y, getZ() + getDeltaMovement().z);

        if (this.level().isClientSide()) {
            this.level().addParticle(ParticleTypes.SNOWFLAKE, getX(), getY(), getZ(), 0.0D, 0.0D, 0.0D);
        }

        if (getOwner() instanceof FrozenShadeEntity frozenShade) {
            if (!frozenShade.isAlive()) {
                discard();
            }

//            LivingEntity target = frozenShade.getTarget();
//            if (target != null && !this.isLaunched) {
//                Vec3 spikePos = this.position();
//                Vec3 targetPos = target.getEyePosition();
//
//                double dx = targetPos.x - spikePos.x;
//                double dy = targetPos.y - spikePos.y;
//                double dz = targetPos.z - spikePos.z;
//
//                float desiredYaw = (float)(Mth.atan2(dz, dx) * (180F / Math.PI)) - 90F;
//                float desiredPitch = (float)(-(Mth.atan2(dy, Math.sqrt(dx * dx + dz * dz)) * (180F / Math.PI)));
//
//                this.setYRot(Mth.rotLerp(0.2F, getYRot(), desiredYaw));
//                this.setXRot(Mth.rotLerp(0.2F, getXRot(), desiredPitch));
//                this.yRotO = this.getYRot();
//                this.xRotO = this.getXRot();
//            }
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
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return super.getAddEntityPacket();
    }

    public void setLaunched(boolean isLaunched) {
        this.isLaunched = isLaunched;
    }
}
