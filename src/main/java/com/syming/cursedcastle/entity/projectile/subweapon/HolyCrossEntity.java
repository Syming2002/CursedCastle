package com.syming.cursedcastle.entity.projectile.subweapon;

import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.projectile.subweapon.HolyCrossItem;
import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.projectile.CCThrowableWeapon;
import com.syming.cursedcastle.item.spellbook.SpellBookElementType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.CuriosApi;

public class HolyCrossEntity extends CCThrowableWeapon {

    protected static final EntityDataAccessor<Float> THROWER_X = SynchedEntityData.defineId(HolyCrossEntity.class, EntityDataSerializers.FLOAT);
    protected static final EntityDataAccessor<Float> THROWER_Y = SynchedEntityData.defineId(HolyCrossEntity.class, EntityDataSerializers.FLOAT);
    protected static final EntityDataAccessor<Float> THROWER_Z = SynchedEntityData.defineId(HolyCrossEntity.class, EntityDataSerializers.FLOAT);
    protected static final EntityDataAccessor<Float> THROWER_YAW = SynchedEntityData.defineId(HolyCrossEntity.class, EntityDataSerializers.FLOAT);

    private Tier tier;

    public HolyCrossEntity(EntityType<? extends HolyCrossEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public HolyCrossEntity(Level pLevel, Tier pTier) {
        super(CCEntity.HOLY_CROSS_TYPES.get(pTier).get(), pLevel);
        this.tier = pTier;
    }

    public HolyCrossEntity(double pX, double pY, double pZ, Level pLevel, Tier pTier) {
        super(CCEntity.HOLY_CROSS_TYPES.get(pTier).get(), pX, pY, pZ, pLevel);
        this.tier = pTier;
    }

    public HolyCrossEntity(LivingEntity pShooter, Level pLevel, ItemStack pStack, Tier pTier) {
        super(CCEntity.HOLY_CROSS_TYPES.get(pTier).get(), pShooter, pLevel);
        this.tier = pTier;
        pShooter = (LivingEntity) getOwner();
        if (pShooter != null) {
            setCoordinates(pShooter.getX(), pShooter.getY() + pShooter.getEyeHeight(), pShooter.getZ());
        }
        setOwner(pShooter);
        setItem(pStack);
    }

    public Tier getTier() {
        return this.tier;
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
        ItemStack stack = getItemRaw();
        if (!this.level().isClientSide() && getOwner() != null) {
            spawnAtLocation(stack, 0.5F);
            stack.hurt(2, this.random, getOwner() instanceof ServerPlayer ? (ServerPlayer) getOwner() : null);
            discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        ItemStack stack = getItemRaw();
        Entity target = pResult.getEntity();

        if (!this.level().isClientSide() && getOwner() != null) {
            if (!stack.isEmpty()) {
                if (target == getOwner()) {
                    if (getOwner() instanceof Player player) {

                        player.getInventory().add(stack);

                        level().playSound(player, getX(), getY(), getZ(), SoundEvents.ITEM_PICKUP, SoundSource.NEUTRAL, 0.5F, 1.0F);

                        discard();
                    }
                } else if (target instanceof LivingEntity livingEntity && getOwner() instanceof LivingEntity shooter) {
                    applyEntityHitEnchantments(livingEntity);
                    livingEntity.hurt(this.damageSources().mobProjectile(this, shooter), getImpactDamage());
                    stack.hurt(1, this.random, (ServerPlayer) shooter);
                }
            }
        }

    }

    @Override
    public void playerTouch(Player player) {
        if (!this.level().isClientSide() && getOwner() == player) {
            ItemStack stack = getPickResult();
            if (stack != null) {
                if (stack.isEmpty()) {
                    return;
                }

                if (player.getInventory().add(stack)) {
                    this.level().playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_PICKUP, SoundSource.NEUTRAL, 0.5F, 1.0F);

                    player.take(this, 1);

                    discard();
                    return;
                }
            }
        }
        super.playerTouch(player);
    }
    
    @Override
    public void tick() {
        super.tick();
        ItemStack stack = getItemRaw();

        if (getOwner() != null) {
            setCoordinates(getOwner().getX(), getOwner().getY() + getOwner().getEyeHeight(), getOwner().getZ());


            double dx = this.getX() - getThrowerPosX();
            double dy = this.getY() - getThrowerPosY();
            double dz = this.getZ() - getThrowerPosZ();

            double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);

            if (distance > 0) {
                dx /= distance;
                dy /= distance;
                dz /= distance;

                Vec3 currentMotion = getDeltaMovement();

                setDeltaMovement(
                        currentMotion.x - (0.05D * dx),
                        currentMotion.y - (0.05D * dy),
                        currentMotion.z - (0.05D * dz)
                );
            }
            if (isInWater() && !this.level().isClientSide()) {

                if (stack.getMaxDamage() - stack.getDamageValue() > 0 /*&& !creativeUse()*/) {
                    spawnAtLocation(stack, 0.5F);
                }
                discard();


            }

            if (this.tickCount >= 100) {
                if (!level().isClientSide()) {
                    if (stack.getMaxDamage() - stack.getDamageValue() > 0 /*&& !creativeUse()*/) {
                        spawnAtLocation(stack, 0.5F);
                    }
                    discard();
                }
            }

            setXRot(getXRot() + (getXRot() - getYRot()) * 0.2F);
            setYRot(getYRot() + 20.0F);

            setRot(getYRot(), getXRot());
        }
    }


    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public float getImpactDamage() {
        return this.getTier().getAttackDamageBonus() + 2.0F;
    }

    @Override
    public ItemStack getItem() {
        ItemStack stack = this.getItemRaw();
        if (getOwner() != null) {
            if (stack.getItem() instanceof HolyCrossItem holyCrossItem) {
                Tier holyCrossItemTier = holyCrossItem.getTier();
                if (holyCrossItemTier == Tiers.WOOD) {
                    return new ItemStack(CCItem.ANIM_WOOD_HOLY_CROSS.get());
                } else if (holyCrossItemTier == Tiers.STONE) {
                    return new ItemStack(CCItem.ANIM_STONE_HOLY_CROSS.get());
                } else if (holyCrossItemTier == Tiers.IRON) {
                    return new ItemStack(CCItem.ANIM_IRON_HOLY_CROSS.get());
                } else if (holyCrossItemTier == Tiers.GOLD) {
                    return new ItemStack(CCItem.ANIM_GOLD_HOLY_CROSS.get());
                } else if (holyCrossItemTier == Tiers.DIAMOND) {
                    return new ItemStack(CCItem.ANIM_DIAMOND_HOLY_CROSS.get());
                } else if (holyCrossItemTier == Tiers.NETHERITE) {
                    return new ItemStack(CCItem.ANIM_NETHERITE_HOLY_CROSS.get());
                }
            }
        }
        return new ItemStack(CCItem.ANIM_WOOD_HOLY_CROSS.get());
    }

    @Override
    protected Item getDefaultItem() {
        ItemStack stack = this.getItemRaw();
        if (stack.isEmpty()) {
            if (stack.getItem() instanceof HolyCrossItem) {
                for (Tier tier : Tiers.values()) {
                    return CCItem.HOLY_CROSS_ITEM_TYPES.get(tier).get();
                }
            }
        }
        return stack.getItem();
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
