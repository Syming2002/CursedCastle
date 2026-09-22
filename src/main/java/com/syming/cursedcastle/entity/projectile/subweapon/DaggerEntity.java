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
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;

public class DaggerEntity extends AbstractArrow {

    public static final EntityDataAccessor<ItemStack> ITEM_STACK = SynchedEntityData.defineId(DaggerEntity.class, EntityDataSerializers.ITEM_STACK);
    private Tier tier;
    private boolean isLaunched = false;

    public DaggerEntity(Level pLevel, Tier pTier) {
        super(CCEntity.DAGGER_TYPES.get(pTier).get(), pLevel);
        this.pickup = Pickup.DISALLOWED;
        this.tier = pTier;
    }

    public DaggerEntity(double pX, double pY, double pZ, Level pLevel, Tier pTier, ItemStack stack) {
        super(CCEntity.DAGGER_TYPES.get(pTier).get(), pX, pY, pZ, pLevel, stack, stack);
    }

    public DaggerEntity(Level level, LivingEntity shooter, ItemStack stack, Tier tier) {
        super(CCEntity.DAGGER_TYPES.get(tier).get(), shooter, level, stack, stack);

        setItem(stack);
        if (shooter instanceof Player) {
            this.pickup = Pickup.ALLOWED;
        }
    }

    public DaggerEntity(LivingEntity shooter, Level level, ItemStack stack, Tier tier) {
        this(level, shooter, stack, tier);

        if (shooter instanceof Player) {
            this.pickup = Pickup.DISALLOWED;
        }
    }

    public Tier getTier() {
        return this.tier;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);

        builder.define(ITEM_STACK, ItemStack.EMPTY);
    }

    @Override
    public void tick() {
        super.tick();
        if (getOwner() != null) {
            if (getOwner() instanceof Player player) {
                if (onGround() && (!player.isCreative() || !player.isSpectator()) && !(SpellBookUtil.isWearingSpellBook(SpellBookElementType.FIRE, player)
                        || SpellBookUtil.isWearingSpellBook(SpellBookElementType.ICE, player)
                        || SpellBookUtil.isWearingSpellBook(SpellBookElementType.WIND, player))) {
                    this.pickup = Pickup.ALLOWED;
                }

                if (SpellBookUtil.isWearingSpellBook(SpellBookElementType.FIRE, player)) {
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
                } else if (SpellBookUtil.isWearingSpellBook(SpellBookElementType.ICE, player)) {
                    if (this.level().isClientSide()) {
                        double offsetX = (this.random.nextDouble() - 0.5D) * 0.5D;
                        double offsetY = this.random.nextDouble() * 0.02D;
                        double offsetZ = (this.random.nextDouble() - 0.5D) * 0.5D;

                        double particleX = this.getX() + offsetX;
                        double particleY = this.getY() + offsetY;
                        double particleZ = this.getZ() + offsetZ;

                        double motionY = 0.02D + this.random.nextDouble() * 0.02D;

                        this.level().addParticle(ParticleTypes.SNOWFLAKE, particleX, particleY, particleZ, 0.0D, motionY, 0.0D);
                    }
                } else if (SpellBookUtil.isWearingSpellBook(SpellBookElementType.WIND, player)) {
                    if (!this.isLaunched) {
                        if (this.tickCount <= 1 && !onGround()) {
                            double posX = player.getX();
                            double posY = player.getY() + player.getEyeHeight();
                            double posZ = player.getZ();

                            setPos(posX, posY, posZ);
                        } else {
                            this.isLaunched = true;
                        }
                    }
                }
            }
            if (this.tickCount > 200) {
                discard();
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);

        if (getOwner() != null) {
            if (pResult.getEntity() != getOwner() && pResult.getEntity() instanceof LivingEntity living) {
                if (SpellBookUtil.isWearingSpellBook(SpellBookElementType.FIRE, (LivingEntity) getOwner())) {
                    living.setRemainingFireTicks(5);
                } else if (SpellBookUtil.isWearingSpellBook(SpellBookElementType.ICE, (LivingEntity) getOwner())) {
                    living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1, false, true));
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

        if (onGround() && (!pPlayer.isCreative() || !pPlayer.isSpectator()) && !(SpellBookUtil.isWearingSpellBook(SpellBookElementType.FIRE, pPlayer) || SpellBookUtil.isWearingSpellBook(SpellBookElementType.ICE, pPlayer) || SpellBookUtil.isWearingSpellBook(SpellBookElementType.WIND, pPlayer))) {
            ItemStack stack = getItemRaw();

            if (!this.level().isClientSide()) {

                stack.hurtAndBreak(1, pPlayer, LivingEntity.getSlotForHand(pPlayer.getUsedItemHand()));

                pPlayer.take(this, 1);

                pPlayer.getInventory().add(stack);

                discard();
            }
        }
    }

    public void setItem(ItemStack pStack) {
        if (!pStack.is(CCItem.DAGGER_ITEM_TYPES.get(Tiers.WOOD).get())
                || !pStack.is(CCItem.DAGGER_ITEM_TYPES.get(Tiers.STONE).get())
                || !pStack.is(CCItem.DAGGER_ITEM_TYPES.get(Tiers.IRON).get())
                || !pStack.is(CCItem.DAGGER_ITEM_TYPES.get(Tiers.GOLD).get())
                || !pStack.is(CCItem.DAGGER_ITEM_TYPES.get(Tiers.DIAMOND).get())
                || !pStack.is(CCItem.DAGGER_ITEM_TYPES.get(Tiers.NETHERITE).get())) {
            this.getEntityData().set(ITEM_STACK, pStack.copyWithCount(1));
        }
    }

    protected ItemStack getItemRaw() {
        return this.entityData.get(ITEM_STACK);
    }

    @Override
    public ItemStack getPickupItem() {
        ItemStack stack = getItemRaw();
        if (stack.getItem() instanceof DaggerItem daggerItem) {
            Tier daggerItemTier = daggerItem.getTier();
            if (daggerItemTier == Tiers.WOOD) {
                return new ItemStack(CCItem.DAGGER_ITEM_TYPES.get(daggerItemTier).get());
            } else if (daggerItemTier == Tiers.STONE) {
                return new ItemStack(CCItem.DAGGER_ITEM_TYPES.get(daggerItemTier).get());
            } else if (daggerItemTier == Tiers.IRON) {
                return new ItemStack(CCItem.DAGGER_ITEM_TYPES.get(daggerItemTier).get());
            } else if (daggerItemTier == Tiers.GOLD) {
                return new ItemStack(CCItem.DAGGER_ITEM_TYPES.get(daggerItemTier).get());
            } else if (daggerItemTier == Tiers.DIAMOND) {
                return new ItemStack(CCItem.DAGGER_ITEM_TYPES.get(daggerItemTier).get());
            } else if (daggerItemTier == Tiers.NETHERITE) {
                return new ItemStack(CCItem.DAGGER_ITEM_TYPES.get(daggerItemTier).get());
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        ItemStack stack = getItemRaw();
        if (stack.getItem() instanceof DaggerItem daggerItem) {
            Tier daggerItemTier = daggerItem.getTier();
            if (daggerItemTier == Tiers.WOOD) {
                return new ItemStack(CCItem.DAGGER_ITEM_TYPES.get(daggerItemTier).get());
            } else if (daggerItemTier == Tiers.STONE) {
                return new ItemStack(CCItem.DAGGER_ITEM_TYPES.get(daggerItemTier).get());
            } else if (daggerItemTier == Tiers.IRON) {
                return new ItemStack(CCItem.DAGGER_ITEM_TYPES.get(daggerItemTier).get());
            } else if (daggerItemTier == Tiers.GOLD) {
                return new ItemStack(CCItem.DAGGER_ITEM_TYPES.get(daggerItemTier).get());
            } else if (daggerItemTier == Tiers.DIAMOND) {
                return new ItemStack(CCItem.DAGGER_ITEM_TYPES.get(daggerItemTier).get());
            } else if (daggerItemTier == Tiers.NETHERITE) {
                return new ItemStack(CCItem.DAGGER_ITEM_TYPES.get(daggerItemTier).get());
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public @Nullable ItemStack getPickResult() {
        ItemStack stack = getPickupItem();
        if (stack.getItem() instanceof DaggerItem daggerItem) {
            Tier daggerItemTier = daggerItem.getTier();
            if (daggerItemTier == Tiers.WOOD) {
                return new ItemStack(CCItem.DAGGER_ITEM_TYPES.get(daggerItemTier).get());
            } else if (daggerItemTier == Tiers.STONE) {
                return new ItemStack(CCItem.DAGGER_ITEM_TYPES.get(daggerItemTier).get());
            } else if (daggerItemTier == Tiers.IRON) {
                return new ItemStack(CCItem.DAGGER_ITEM_TYPES.get(daggerItemTier).get());
            } else if (daggerItemTier == Tiers.GOLD) {
                return new ItemStack(CCItem.DAGGER_ITEM_TYPES.get(daggerItemTier).get());
            } else if (daggerItemTier == Tiers.DIAMOND) {
                return new ItemStack(CCItem.DAGGER_ITEM_TYPES.get(daggerItemTier).get());
            } else if (daggerItemTier == Tiers.NETHERITE) {
                return new ItemStack(CCItem.DAGGER_ITEM_TYPES.get(daggerItemTier).get());
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public double getBaseDamage() {
        return this.getTier().getAttackDamageBonus() + 1.0F;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);

        ItemStack stack = getDefaultPickupItem();

        if (stack != null && !stack.isEmpty()) {
            tag.put("Dagger", stack.save(this.level().registryAccess()));
        }
    }


    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        ItemStack stack = ItemStack.parseOptional(this.level().registryAccess(), tag.getCompound("Dagger"));
        setItem(stack);
    }

    @Override
    public boolean isNoGravity() {
        return SpellBookUtil.isWearingSpellBook(SpellBookElementType.FIRE, (LivingEntity) getOwner()) || SpellBookUtil.isWearingSpellBook(SpellBookElementType.ICE, (LivingEntity) getOwner());
    }
}
