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
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;

public class HolyCrossEntity extends CCThrowableWeapon {

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
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        ItemStack stack = getItem();
        if (!this.level().isClientSide() && getOwner() != null && getOwner() instanceof Player player) {
            spawnAtLocation(stack, 0.5F);
            stack.hurtAndBreak(2, player, LivingEntity.getSlotForHand(player.getUsedItemHand()));
            discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        ItemStack stack = getItem();
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
                    stack.hurtAndBreak(1, shooter, LivingEntity.getSlotForHand(shooter.getUsedItemHand()));
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
        ItemStack stack = getItem();

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
    protected Item getDefaultItem() {
        for (Tier tier : Tiers.values()) {
            return CCItem.HOLY_CROSS_ITEM_TYPES.get(tier).get();
        }
        return ItemStack.EMPTY.getItem();
    }
}
