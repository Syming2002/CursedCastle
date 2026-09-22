package com.syming.cursedcastle.entity.magic;

import com.syming.cursedcastle.effect.CCEffect;
import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.monster.shade.FrozenShadeEntity;
import com.syming.cursedcastle.entity.projectile.subweapon.IceHolyCrossEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class IceSpikeEntity extends Projectile {

    public boolean lockedRotation;

    public IceSpikeEntity(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public IceSpikeEntity(Level pLevel) {
        super(CCEntity.ICE_SPIKE.get(), pLevel);
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
    }

    @Override
    protected boolean canHitEntity(Entity pEntity) {
        return pEntity.isAlive() && pEntity != getOwner();
    }

    @Override
    protected void onHitEntity(EntityHitResult pEntityHitResult) {
        super.onHitEntity(pEntityHitResult);
        if (!this.level().isClientSide()) {
            Entity hitEntity = pEntityHitResult.getEntity();
            if (hitEntity != null && hitEntity != this.getOwner()) {

                if (hitEntity.isAlive()) {
                    if (this.getOwner() instanceof IceHolyCrossEntity) {
                        hitEntity.hurt(this.damageSources().magic(), 8.0F);
                        discard();
                    }
                    if (this.getOwner() instanceof FrozenShadeEntity frozenShade) {
                        hitEntity.hurt(this.damageSources().mobAttack(frozenShade), 8.0F);
                        frozenShade.heal(5.0F);
                        if (hitEntity instanceof LivingEntity livingTarget) {
                            livingTarget.addEffect(new MobEffectInstance(CCEffect.FREEZE, 200, 1));
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        BlockPos hitPos = pResult.getBlockPos();

        if (!this.level().isClientSide()) {
            if (hitPos != null) {
                if (this.getOwner() instanceof IceHolyCrossEntity) {
                    discard();
                }
            }
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

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
            if (frozenShade.getTarget() != null) {
                if (this.getDeltaMovement() == Vec3.ZERO) {
                    Vec3 spikePos = position();
                    Vec3 targetPos = frozenShade.getTarget().getEyePosition();

                    double dx = targetPos.x - spikePos.x;
                    double dy = targetPos.y - spikePos.y;
                    double dz = targetPos.z - spikePos.z;

                    float targetYaw = (float) (Mth.atan2(dz, dx) * (180F / Math.PI)) - 90F;
                    float targetPitch = (float) (-(Mth.atan2(dy, Math.sqrt(dx * dx + dz * dz)) * (180F / Math.PI)));

//                    this.setYRot(Mth.rotLerp(0.2F, getYRot(), targetYaw));
//                    this.setXRot(Mth.rotLerp(0.2F, getXRot(), targetPitch));

                    absRotateTo(targetYaw, targetPitch);

                }

                if (!frozenShade.isAlive()) {
                    discard();
                }
            }
        }
        if (getOwner() instanceof IceHolyCrossEntity) {
            List<Monster> monsters = this.level().getEntitiesOfClass(Monster.class, this.getBoundingBox().inflate(10.0D), LivingEntity::isAlive);
            for (Monster monster : monsters) {
                Vec3 spikePos = position();
                Vec3 targetPos = monster.getEyePosition();

                double dx = targetPos.x - spikePos.x;
                double dy = targetPos.y - spikePos.y;
                double dz = targetPos.z - spikePos.z;

                float targetYaw = (float) (Mth.atan2(dz, dx) * (180F / Math.PI)) - 90F;
                float targetPitch = (float) (-(Mth.atan2(dy, Math.sqrt(dx * dx + dz * dz)) * (180F / Math.PI)));

                this.setYRot(Mth.rotLerp(0.2F, getYRot(), targetYaw));
                this.setXRot(Mth.rotLerp(0.2F, getXRot(), targetPitch));
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
    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity p_entity) {
        return super.getAddEntityPacket(p_entity);
    }

    public boolean isLockedRotation() {
        return this.lockedRotation;
    }
}
