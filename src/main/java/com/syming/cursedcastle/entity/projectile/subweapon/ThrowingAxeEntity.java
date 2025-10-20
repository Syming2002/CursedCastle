package com.syming.cursedcastle.entity.projectile.subweapon;

import com.syming.cursedcastle.item.CCItem;
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
        ItemStack stack = this.getItemRaw();
        if (stack.getItem() instanceof ThrowingAxeItem throwingAxeItem) {
            Tier throwingAxeItemTier = throwingAxeItem.getTier();
            if (throwingAxeItemTier == Tiers.WOOD) {
                return new ItemStack(CCItem.ANIM_WOOD_THROWING_AXE.get());
            } else if (throwingAxeItemTier == Tiers.STONE) {
                return new ItemStack(CCItem.ANIM_STONE_THROWING_AXE.get());
            } else if (throwingAxeItemTier == Tiers.IRON) {
                return new ItemStack(CCItem.ANIM_IRON_THROWING_AXE.get());
            } else if (throwingAxeItemTier == Tiers.GOLD) {
                return new ItemStack(CCItem.ANIM_GOLD_THROWING_AXE.get());
            } else if (throwingAxeItemTier == Tiers.DIAMOND) {
                return new ItemStack(CCItem.ANIM_DIAMOND_THROWING_AXE.get());
            } else if (throwingAxeItemTier == Tiers.NETHERITE) {
                return new ItemStack(CCItem.ANIM_NETHERITE_THROWING_AXE.get());
            }
        }
        return new ItemStack(CCItem.ANIM_WOOD_THROWING_AXE.get());
    }

    @Override
    protected Item getDefaultItem() {
        ItemStack stack = this.getItemRaw();
        if (stack.isEmpty()) {
            if (stack.getItem() instanceof ThrowingAxeItem throwingAxeItem) {
                Tier throwingAxeItemTier = throwingAxeItem.getTier();
                if (throwingAxeItemTier == Tiers.WOOD) {
                    return CCItem.WOODEN_THROWING_AXE.get();
                } else if (throwingAxeItemTier == Tiers.STONE) {
                    return CCItem.STONE_THROWING_AXE.get();
                } else if (throwingAxeItemTier == Tiers.IRON) {
                    return CCItem.IRON_THROWING_AXE.get();
                } else if (throwingAxeItemTier == Tiers.GOLD) {
                    return CCItem.GOLDEN_THROWING_AXE.get();
                } else if (throwingAxeItemTier == Tiers.DIAMOND) {
                    return CCItem.DIAMOND_THROWING_AXE.get();
                } else if (throwingAxeItemTier == Tiers.NETHERITE) {
                    return CCItem.NETHERITE_THROWING_AXE.get();
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

        x += this.random.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        y += this.random.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        z += this.random.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;

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
        setXRot((float) (Mth.atan2(y, (double)f1) * (180D / Math.PI)));
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
        ItemStack stack = getItemRaw();
        if (!this.level().isClientSide() && getOwner() != null) {
            spawnAtLocation(stack, 0.5F);
            stack.hurt(2, this.random, getOwner() instanceof ServerPlayer ? (ServerPlayer) getOwner() : null);
            discard();
            System.out.println("Block is Hit");
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        ItemStack stack = getItemRaw();
        Entity target = pResult.getEntity();

        if (!this.level().isClientSide() && getOwner() != null) {
            if (!stack.isEmpty()) {
                if (target instanceof LivingEntity livingEntity && getOwner() instanceof LivingEntity shooter) {
                    applyEntityHitEnchantments(livingEntity);
                    if (!stack.isEnchanted()) {
                        livingEntity.hurt(this.damageSources().mobProjectile(this, shooter), getImpactDamage());
                    }
                    stack.hurt(1, this.random, (ServerPlayer) shooter);
                    System.out.println("Entity is Hit");
                }
            }
        }

    }

    @Override
    public float getGravity() {
        return 0.10F;
    }
}
