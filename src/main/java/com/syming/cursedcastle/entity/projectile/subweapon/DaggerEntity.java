package com.syming.cursedcastle.entity.projectile.subweapon;

import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.projectile.subweapon.DaggerItem;
import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.item.spellbook.SpellBookElementType;
import com.syming.cursedcastle.util.SpellBookUtil;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

public class DaggerEntity extends AbstractArrow {

    public static final EntityDataAccessor<ItemStack> ITEM_STACK = SynchedEntityData.defineId(DaggerEntity.class, EntityDataSerializers.ITEM_STACK);
    private Tier tier;

    public DaggerEntity(Level pLevel, Tier pTier) {
        super(CCEntity.DAGGER_TYPES.get(pTier).get(), pLevel);
        this.pickup = Pickup.DISALLOWED;
        this.tier = pTier;
    }

    public DaggerEntity(double pX, double pY, double pZ, Level pLevel, Tier pTier) {
        super(CCEntity.DAGGER_TYPES.get(pTier).get(), pX, pY, pZ, pLevel);
        this.tier = pTier;
    }

    public DaggerEntity(Level pLevel, LivingEntity pShooter, ItemStack pStack, Tier pTier) {
        super(CCEntity.DAGGER_TYPES.get(pTier).get(), pShooter, pLevel);
        this.tier = pTier;
        setItem(pStack);
        this.pickup = Pickup.DISALLOWED;
    }

    public DaggerEntity(LivingEntity pShooter, Level pLevel, ItemStack pStack, Tier pTier) {
        super(CCEntity.DAGGER_TYPES.get(pTier).get(), pShooter, pLevel);
        this.tier = pTier;
        if (pShooter instanceof Player) {
            this.pickup = Pickup.DISALLOWED;
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

    @Override
    public void tick() {
        super.tick();
        if (getOwner() != null) {
            if (getOwner() instanceof Player player) {
                if (onGround() && (!player.isCreative() || !player.isSpectator())) {
                    this.pickup = Pickup.ALLOWED;
                }
            }

            if (SpellBookUtil.isWearingSpellBook(SpellBookElementType.FIRE, (Player) getOwner())) {
                if (this.level().isClientSide()) {
                    double offsetX = (this.random.nextDouble() - 0.5D) * 0.5D;
                    double offsetY = this.random.nextDouble() * 0.02D;
                    double offsetZ = (this.random.nextDouble() - 0.5D) * 0.5D;

                    double particleX = this.getX() + offsetX;
                    double particleY = this.getY() + offsetY;
                    double particleZ = this.getZ() + offsetZ;

                    double motionY = 0.02D + this.random.nextDouble() * 0.02D;

                    this.level().addParticle(ParticleTypes.FLAME, particleX, particleY, particleZ, 0.0D, motionY, 0.0D);
                }
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);

        if (getOwner() != null) {
            if (pResult.getEntity() != getOwner()) {
                if (SpellBookUtil.isWearingSpellBook(SpellBookElementType.FIRE, (Player) getOwner())) {
                    pResult.getEntity().setSecondsOnFire(5);
                }
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);

        setOnGround(true);
    }

    @Override
    public void playerTouch(Player pPlayer) {

        if (onGround() && (!pPlayer.isCreative() || !pPlayer.isSpectator())) {
            ItemStack stack = getItemRaw();

            if (!this.level().isClientSide()) {

                stack.hurtAndBreak(
                        1,
                        pPlayer,
                        (player -> player.broadcastBreakEvent(EquipmentSlot.MAINHAND))
                );

                pPlayer.take(this, 1);

                pPlayer.getInventory().add(stack);

                discard();
            }
        }
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
    public @Nullable ItemStack getPickResult() {
        ItemStack stack = getPickupItem();
        if (stack.getItem() instanceof DaggerItem daggerItem) {
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

    @Override
    public boolean isNoGravity() {
        return SpellBookUtil.isWearingSpellBook(SpellBookElementType.FIRE, (Player) getOwner());
    }
}
