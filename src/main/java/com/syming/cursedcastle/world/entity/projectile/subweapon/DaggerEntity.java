package com.syming.cursedcastle.world.entity.projectile.subweapon;

import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.subweapon.DaggerItem;
import com.syming.cursedcastle.world.entity.CCEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;

public class DaggerEntity extends AbstractArrow {

    public static final EntityDataAccessor<ItemStack> ITEM_STACK = SynchedEntityData.defineId(DaggerEntity.class, EntityDataSerializers.ITEM_STACK);
    private Tier tier;

    public DaggerEntity(Level pLevel, Tier pTier) {
        super(CCEntity.DAGGER_TYPES.get(pTier).get(), pLevel);
        this.pickup = AbstractArrow.Pickup.DISALLOWED;
        this.tier = pTier;
    }

    public DaggerEntity(double pX, double pY, double pZ, Level pLevel, Tier pTier) {
        super(CCEntity.DAGGER_TYPES.get(pTier).get(), pX, pY, pZ, pLevel);
        this.tier = pTier;
    }

    public DaggerEntity(LivingEntity pShooter, Level pLevel, ItemStack pStack, Tier pTier) {
        super(CCEntity.DAGGER_TYPES.get(pTier).get(), pShooter, pLevel);
        this.tier = pTier;
        if (pShooter instanceof Player) {
            this.pickup = AbstractArrow.Pickup.ALLOWED;
        }
        setItem(pStack);
    }

    public Tier getTier() {
        return this.tier;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        this.entityData.define(ITEM_STACK, ItemStack.EMPTY);
    }

    public void setItem(ItemStack pStack) {
        if (!pStack.is(CCItem.WOODEN_DAGGER.get())
                || !pStack.is(CCItem.STONE_DAGGER.get())
                || !pStack.is(CCItem.IRON_DAGGER.get())
                || !pStack.is(CCItem.GOLDEN_DAGGER.get())
                || !pStack.is(CCItem.DIAMOND_DAGGER.get())
                || !pStack.is(CCItem.NETHERITE_DAGGER.get())
                || pStack.hasTag()) {
            this.getEntityData().set(ITEM_STACK, pStack.copyWithCount(1));
        }

    }

    protected ItemStack getItemRaw() {
        return (ItemStack)this.getEntityData().get(ITEM_STACK);
    }

    @Override
    public ItemStack getPickupItem() {
        ItemStack stack = getItemRaw();
        if (stack != null && stack.getItem() instanceof DaggerItem daggerItem) {
            Tier daggerItemTier = daggerItem.getTier();
            if (daggerItemTier == Tiers.WOOD) {
                return new ItemStack(CCItem.WOODEN_DAGGER.get());
            } else if (daggerItemTier == Tiers.STONE) {
                return new ItemStack(CCItem.STONE_DAGGER.get());
            } else if (daggerItemTier == Tiers.IRON) {
                return new ItemStack(CCItem.IRON_DAGGER.get());
            } else if (daggerItemTier == Tiers.GOLD) {
                return new ItemStack(CCItem.GOLDEN_DAGGER.get());
            } else if (daggerItemTier == Tiers.DIAMOND) {
                return new ItemStack(CCItem.DIAMOND_DAGGER.get());
            } else if (daggerItemTier == Tiers.NETHERITE) {
                return new ItemStack(CCItem.NETHERITE_DAGGER.get());
            }
        }
        return new ItemStack(CCItem.WOODEN_DAGGER.get());
    }

    @Override
    public double getBaseDamage() {
        return this.getTier().getAttackDamageBonus() + 1.0F;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);

        ItemStack stack = getPickupItem();

        if (stack != null && !stack.isEmpty()) {
            tag.put("Dagger", stack.save(new CompoundTag()));
        }
    }


    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        ItemStack stack = ItemStack.of(tag.getCompound("Dagger"));
        setItem(stack);
    }
}
