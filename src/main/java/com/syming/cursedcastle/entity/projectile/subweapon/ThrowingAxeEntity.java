package com.syming.cursedcastle.entity.projectile.subweapon;

import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.projectile.subweapon.HolyCrossItem;
import com.syming.cursedcastle.item.projectile.subweapon.ThrowingAxeItem;
import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.projectile.CCThrowableWeapon;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;

public class ThrowingAxeEntity extends CCThrowableWeapon {

    private Tier tier;

    public ThrowingAxeEntity(Level pLevel, Tier pTier) {
        super(CCEntity.THROWING_AXE_TYPES.get(pTier).get(), pLevel);
        this.tier = pTier;
    }

    public ThrowingAxeEntity(double pX, double pY, double pZ, Level pLevel, Tier pTier) {
        super(CCEntity.THROWING_AXE_TYPES.get(pTier).get(), pX, pY, pZ, pLevel);
        this.tier = pTier;
    }

    public ThrowingAxeEntity(LivingEntity pShooter, Level pLevel, ItemStack pStack, Tier pTier) {
        super(CCEntity.THROWING_AXE_TYPES.get(pTier).get(), pShooter, pLevel);
        this.tier = pTier;
        setItem(pStack);
    }

    public Tier getTier() {
        return this.tier;
    }

    @Override
    public float getImpactDamage() {
        return this.tier.getAttackDamageBonus() + 10.0F;
    }

    @Override
    public ItemStack getItem() {
        if (getOwner() != null) {

            Tier tier = getTier();
            if (tier == Tiers.WOOD) {
                return new ItemStack(CCItem.ANIM_WOOD_THROWING_AXE.get());
            } else if (tier == Tiers.STONE) {
                return new ItemStack(CCItem.ANIM_STONE_THROWING_AXE.get());
            } else if (tier == Tiers.IRON) {
                return new ItemStack(CCItem.ANIM_IRON_THROWING_AXE.get());
            } else if (tier == Tiers.GOLD) {
                return new ItemStack(CCItem.ANIM_GOLD_THROWING_AXE.get());
            } else if (tier == Tiers.DIAMOND) {
                return new ItemStack(CCItem.ANIM_DIAMOND_THROWING_AXE.get());
            } else if (tier == Tiers.NETHERITE) {
                return new ItemStack(CCItem.ANIM_NETHERITE_THROWING_AXE.get());
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public @Nullable ItemStack getPickResult() {
        if (getOwner() != null) {
            Tier tier = getTier();
            if (tier == Tiers.WOOD) {
                return new ItemStack(CCItem.THROWING_AXE_ITEM_TYPES.get(Tiers.WOOD).get());
            } else if (tier == Tiers.STONE) {
                return new ItemStack(CCItem.THROWING_AXE_ITEM_TYPES.get(Tiers.STONE).get());
            } else if (tier == Tiers.IRON) {
                return new ItemStack(CCItem.THROWING_AXE_ITEM_TYPES.get(Tiers.IRON).get());
            } else if (tier == Tiers.GOLD) {
                return new ItemStack(CCItem.THROWING_AXE_ITEM_TYPES.get(Tiers.GOLD).get());
            } else if (tier == Tiers.DIAMOND) {
                return new ItemStack(CCItem.THROWING_AXE_ITEM_TYPES.get(Tiers.DIAMOND).get());
            } else if (tier == Tiers.NETHERITE) {
                return new ItemStack(CCItem.THROWING_AXE_ITEM_TYPES.get(Tiers.NETHERITE).get());
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    protected Item getDefaultItem() {
        ItemStack stack = this.getItem();
        if (stack.isEmpty()) {
            if (stack.getItem() instanceof ThrowingAxeItem) {
                for (Tier tier : Tiers.values()) {
                    return CCItem.THROWING_AXE_ITEM_TYPES.get(tier).get();
                }
            }
        }
        return stack.getItem();
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        double length = Mth.sqrt((float) (x * x + y * y + z * z));
        x /= length;
        y /= length;
        z /= length;

        x += this.random.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
        y += this.random.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;
        z += this.random.nextGaussian() * 0.007499999832361937D * (double) inaccuracy;

        x *= velocity;
        y *= velocity;
        z *= velocity;

        double time = 0.0;
        double amplitude = 0.5;
        double frequency = 1.0;

        double xOffset = amplitude * Math.sin(frequency * time);
        double yOffset = amplitude * Math.cos(frequency * time);

        x += xOffset;
        y += yOffset;

        setDeltaMovement(x, y, z);

        float f1 = Mth.sqrt((float) (x * x + z * z));
        setYRot((float) (Mth.atan2(x, z) * (180D / Math.PI)));
        setXRot((float) (Mth.atan2(y, (double) f1) * (180D / Math.PI)));
    }

    @Override
    public void tick() {
        if (isAlive()) {
            super.tick();
        }
        if (this.tickCount > 100) {
            discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        ItemStack stack = getPickResult();
        if (!this.level().isClientSide() && getOwner() != null && getOwner() instanceof LivingEntity shooter) {
            spawnAtLocation(stack, 0.5F);
            stack.hurtAndBreak(2, shooter, LivingEntity.getSlotForHand(shooter.getUsedItemHand()));
            discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        ItemStack stack = getPickResult();
        Entity target = pResult.getEntity();

        if (!this.level().isClientSide() && getOwner() != null) {
            if (stack != null && !stack.isEmpty()) {
                if (target instanceof LivingEntity livingEntity && getOwner() instanceof LivingEntity shooter) {
                    applyEntityHitEnchantments(livingEntity);
                    if (!stack.isEnchanted()) {
                        livingEntity.hurt(this.damageSources().mobProjectile(this, shooter), getImpactDamage());
                    }
                    stack.hurtAndBreak(1, shooter, LivingEntity.getSlotForHand(shooter.getUsedItemHand()));
                }
            }
        }

    }

    @Override
    public double getDefaultGravity() {
        return 0.10F;
    }
}
