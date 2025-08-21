package com.syming.cursedcastle.world.entity.projectile.subweapon;

import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.world.entity.CCEntity;
import com.syming.cursedcastle.world.entity.projectile.CCThrowableWeapon;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class HolyBibleEntity extends CCThrowableWeapon {
    protected static final EntityDataAccessor<Float> THROWER_X = SynchedEntityData.defineId(HolyBibleEntity.class, EntityDataSerializers.FLOAT);
    protected static final EntityDataAccessor<Float> THROWER_Y = SynchedEntityData.defineId(HolyBibleEntity.class, EntityDataSerializers.FLOAT);
    protected static final EntityDataAccessor<Float> THROWER_Z = SynchedEntityData.defineId(HolyBibleEntity.class, EntityDataSerializers.FLOAT);
    protected static final EntityDataAccessor<Float> THROWER_YAW = SynchedEntityData.defineId(HolyBibleEntity.class, EntityDataSerializers.FLOAT);

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
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(THROWER_X, 0.0F);
        this.entityData.define(THROWER_Y, 0.0F);
        this.entityData.define(THROWER_Z, 0.0F);
        this.entityData.define(THROWER_YAW, 0.0F);

        setYRot(this.entityData.get(THROWER_YAW));
    }

    public double getThrowerPosX() {
        return this.entityData.get(THROWER_X);
    }

    public double getThrowerPosY() {
        return this.entityData.get(THROWER_Y);
    }

    public double getThrowerPosZ() {
        return this.entityData.get(THROWER_Z);
    }

    public void setCoordinates(double playerX, double playerY, double playerZ) {
        this.entityData.set(THROWER_X, (float) playerX);
        this.entityData.set(THROWER_Y, (float) playerY);
        this.entityData.set(THROWER_Z, (float) playerZ);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if (!this.level().isClientSide() && getOwner() != null) {
            System.out.println("Block is Hit");
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        ItemStack stack = getItem();
        Entity target = pResult.getEntity();

        if (!this.level().isClientSide() && getOwner() != null) {
            if (!stack.isEmpty()) {
                 if (target instanceof LivingEntity livingEntity && getOwner() instanceof LivingEntity shooter) {
                    applyEntityHitEnchantments(target);
                    if (!stack.isEnchanted()) {
                        livingEntity.hurt(this.damageSources().mobProjectile(this, shooter), getImpactDamage());
                    }
                }
            }
        }
        System.out.println("Entity is Hit");
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
            setCoordinates(getOwner().getX(), getOwner().getY() + getOwner().getEyeHeight() / 2, getOwner().getZ());

            double throwerX = getOwner().getX();
            double throwerY = getOwner().getY() + getOwner().getEyeHeight() / 2;
            double throwerZ = getOwner().getZ();

            setCoordinates(getOwner().getX(), getOwner().getY() + getOwner().getEyeHeight() / 2, getOwner().getZ());

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

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);

        tag.putDouble("ThrowerX", getThrowerPosX());
        tag.putDouble("ThrowerY", getThrowerPosY());
        tag.putDouble("ThrowerZ", getThrowerPosZ());

        tag.putFloat("ThrowerYaw", this.entityData.get(THROWER_YAW));
    }


    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setCoordinates(tag.getDouble("ThrowerX"), tag.getDouble("ThrowerY"), tag.getDouble("ThrowerZ"));
        this.entityData.set(THROWER_YAW, tag.getFloat("ThrowerYaw"));
    }
}
