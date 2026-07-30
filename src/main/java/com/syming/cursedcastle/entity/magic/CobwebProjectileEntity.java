package com.syming.cursedcastle.entity.magic;

import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.mob.arachne.ArachneEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class CobwebProjectileEntity extends MagicAttackEntity {
    public CobwebProjectileEntity(EntityType<? extends MagicAttackEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public CobwebProjectileEntity(Level pLevel) {
        super(CCEntity.COBWEB_PROJECTILE.get(), pLevel);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.level().addParticle(ParticleTypes.CLOUD, getX(), getY(), getZ(), 0.0D, 0.0D, 0.0D);
        }


        setPos(getX() + getDeltaMovement().x, getY() + getDeltaMovement().y, getZ() + getDeltaMovement().z);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);

        if (!this.level().isClientSide()) {
            Entity hitEntity = pResult.getEntity();
            if (hitEntity != null && hitEntity != this.getOwner()) {
                if (hitEntity.isAlive() && hitEntity instanceof LivingEntity livingEntity) {
                    hitEntity.hurt(this.damageSources().magic(), 2.0F);
                    this.level().setBlock(hitEntity.blockPosition(), Blocks.COBWEB.defaultBlockState(), 3);
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2));
                    discard();
                }
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);

        BlockPos hitPos = pResult.getBlockPos();

        if (!this.level().isClientSide()) {

            BlockPos placePos = pResult.getBlockPos().relative(pResult.getDirection());

            if (this.getOwner() instanceof ArachneEntity) {
                if (this.level().isEmptyBlock(placePos)) {
                    this.level().setBlock(placePos, Blocks.COBWEB.defaultBlockState(), 3);
                }
                discard();
            }
        }
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }
}
