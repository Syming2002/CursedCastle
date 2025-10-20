package com.syming.cursedcastle.entity.projectile;

import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.item.CCItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ScytheProjectileEntity extends CCThrowableWeapon {
    public ScytheProjectileEntity(Level pLevel) {
        super(CCEntity.SCYTHE_PROJECTILE.get(), pLevel);
    }

    public ScytheProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ScytheProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        super(pEntityType, pX, pY, pZ, pLevel);
    }

    public ScytheProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.tickCount >= 100) {
            discard();
        }
    }

    @Override
    protected float getGravity() {
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
}
