package com.syming.cursedcastle.entity.magic;

import com.syming.cursedcastle.effect.CCEffect;
import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.mob.ArachneEntity;
import com.syming.cursedcastle.entity.mob.shade.FrozenShadeEntity;
import com.syming.cursedcastle.entity.projectile.subweapon.IceHolyCrossEntity;
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

        System.out.println("Cobweb Projectile tick...");

        if (this.level().isClientSide()) {
            this.level().addParticle(ParticleTypes.CLOUD, getX(), getY(), getZ(), 0.0D, 0.0D, 0.0D);
        }


        setPos(getX() + getDeltaMovement().x, getY() + getDeltaMovement().y, getZ() + getDeltaMovement().z);

        if (getOwner() != null) {
            if (getOwner() instanceof ArachneEntity arachne) {
                Vec3 headPos = arachne.position().add(0.0D, arachne.getEyeHeight() * 0.5, 0.0D);
                Vec3 lookVec = arachne.getLookAngle();

                headPos = headPos.add(lookVec);

                setPos(headPos.x, headPos.y, headPos.z);
            }
        }
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
            if (hitPos != null) {
                if (this.getOwner() instanceof ArachneEntity) {
                    this.level().setBlock(hitPos.above(), Blocks.COBWEB.defaultBlockState(), 3);
                    discard();
                }
            }
        }
    }
}
