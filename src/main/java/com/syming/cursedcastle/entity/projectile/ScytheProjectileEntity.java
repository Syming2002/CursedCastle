package com.syming.cursedcastle.entity.projectile;

import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.item.CCItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class ScytheProjectileEntity extends CCThrowableWeapon {

    public ScytheProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ScytheProjectileEntity(Level pLevel) {
        super(CCEntity.SCYTHE_PROJECTILE.get(), pLevel);
    }

    public ScytheProjectileEntity(double pX, double pY, double pZ, Level pLevel) {
        super(CCEntity.SCYTHE_PROJECTILE.get(), pX, pY, pZ, pLevel);
    }

    public ScytheProjectileEntity(LivingEntity pShooter, Level pLevel) {
        super(CCEntity.SCYTHE_PROJECTILE.get(), pShooter, pLevel);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.tickCount >= 100) {
            discard();
        }
    }

    @Override
    protected double getDefaultGravity() {
        return 0.0F;
    }

    @Override
    public float getImpactDamage() {
        return 15.0F;
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(CCItem.ANIM_SCYTHE_PROJECTILE_ITEM.get());
    }

    @Override
    protected Item getDefaultItem() {
        return CCItem.SCYTHE_PROJECTILE_ITEM.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);

        Entity target = pResult.getEntity();

        if (!this.level().isClientSide() && getOwner() != null) {
            if (target instanceof LivingEntity livingEntity && getOwner() instanceof LivingEntity shooter) {
                livingEntity.hurt(this.damageSources().mobProjectile(this, shooter), getImpactDamage());
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);

        if (!this.level().isClientSide() && getOwner() != null) {
            discard();
        }
    }
}
