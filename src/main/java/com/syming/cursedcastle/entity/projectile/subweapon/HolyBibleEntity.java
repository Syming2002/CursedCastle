package com.syming.cursedcastle.entity.projectile.subweapon;

import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.projectile.CCThrowableWeapon;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class HolyBibleEntity extends CCThrowableWeapon {


    private int spiralTicks = 0;

    public HolyBibleEntity(double pX, double pY, double pZ, Level pLevel) {
        super(CCEntity.HOLY_BIBLE.get(), pX, pY, pZ, pLevel);
    }

    public HolyBibleEntity(Level pLevel) {
        super(CCEntity.HOLY_BIBLE.get(), pLevel);
    }

    public HolyBibleEntity(LivingEntity pShooter, Level pLevel, ItemStack pStack) {
        super(CCEntity.HOLY_BIBLE.get(), pShooter, pLevel);

        setCoordinates(pShooter.getX(), pShooter.getY() + pShooter.getEyeHeight() / 2, pShooter.getZ());
        setItem(pStack);
    }

    public HolyBibleEntity(EntityType<HolyBibleEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {

    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        ItemStack stack = getItem();
        Entity target = pResult.getEntity();

        if (!this.level().isClientSide() && getOwner() != null) {
            if (!stack.isEmpty()) {
                 if (target instanceof Monster monster && getOwner() instanceof LivingEntity shooter) {
                    applyEntityHitEnchantments(monster);
                    if (!stack.isEnchanted()) {
                        monster.hurt(this.damageSources().mobProjectile(this, shooter), getImpactDamage());
                    }
                }
            }
        }
    }

    @Override
    public float getImpactDamage() {
        return 12.5F;
    }

    @Override
    protected Item getDefaultItem() {
        return CCItem.HOLY_BIBLE.get();
    }

    @Override
    public void tick() {
        super.tick();

        if (getOwner() != null) {
            double throwerX = getOwner().getX();
            double throwerY = getOwner().getY() + getOwner().getEyeHeight() / 2;
            double throwerZ = getOwner().getZ();

            setCoordinates(throwerX,  throwerY, throwerZ);

            double angle = this.random.nextDouble() * Math.PI * 2;
            double distance = this.spiralTicks * 0.0078125;
            double speed = 0.125;

            double dirX = Mth.cos((float) angle);
            double dirZ = Mth.sin((float) angle);
            double dirY = (this.random.nextDouble() - 0.5) * 2;

            double randomX = throwerX + dirX * distance;
            double randomY = throwerY + dirY * distance;
            double randomZ = throwerZ + dirZ * distance;

            double randomMotionX = dirX * (0.1 + this.random.nextDouble() * 0.1) * speed;
            double randomMotionY = (this.random.nextDouble() - 0.5) * 0.2 * speed;
            double randomMotionZ = dirZ * (0.1 + this.random.nextDouble() * 0.1) * speed;

            setPos(randomX, randomY, randomZ);

            setDeltaMovement(randomMotionX, randomMotionY, randomMotionZ);

            if (this.tickCount >= 150) {
                discard();
            }
            this.spiralTicks += 2;

            if (!getOwner().isAlive()) {
                discard();
            }
        }
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }
}
