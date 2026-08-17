package com.syming.cursedcastle.entity.magic;

import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.mob.HarpyEntity;
import com.syming.cursedcastle.entity.mob.shade.FrozenShadeEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class HarpyFeatherEntity extends Projectile {
    private int lifeTicks = 0;
    private static final int MAX_LIFETIME = 150;

    public HarpyFeatherEntity(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public HarpyFeatherEntity(Level pLevel) {
        super(CCEntity.HARPY_FEATHER.get(), pLevel);
    }

    public HarpyFeatherEntity(Level pLevel, HarpyEntity pHarpy) {
        super(CCEntity.HARPY_FEATHER.get(), pLevel);
        setOwner(pHarpy);
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
        onHit(hitResult);
        setPos(getX() + getDeltaMovement().x, getY() + getDeltaMovement().y, getZ() + getDeltaMovement().z);

        if (getOwner() instanceof HarpyEntity harpy) {
            if (harpy != null) {
                if (harpy.getTarget() != null) {

                    float targetYaw = (float)(Mth.atan2(getDeltaMovement().z, getDeltaMovement().x) * (180F / Math.PI)) - 90F;
                    float targetPitch = (float)(-(Mth.atan2(getDeltaMovement().y, Math.sqrt(getDeltaMovement().x * getDeltaMovement().x + getDeltaMovement().z * getDeltaMovement().z)) * (180F / Math.PI)));

                    this.setYRot(targetYaw);
                    this.setXRot(targetPitch);

                }
                if (!harpy.isAlive()) {
                    discard();
                }
            }
        }
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
    }

    @Override
    protected boolean canHitEntity(Entity pEntity) {
        return pEntity.isPickable() && pEntity.isAlive() && pEntity != getOwner() && pEntity != this;
    }

    @Override
    protected void onHitEntity(EntityHitResult pEntityHitResult) {
        super.onHitEntity(pEntityHitResult);

        if (!this.level().isClientSide()) {
            Entity entity = getOwner();
            if (entity instanceof HarpyEntity) {
                pEntityHitResult.getEntity().hurt(this.damageSources().thorns(pEntityHitResult.getEntity()), 5.0F);
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
}
