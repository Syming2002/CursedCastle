package com.syming.cursedcastle.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class BloodSkeletonKillerEnchantment extends Enchantment {
    protected BloodSkeletonKillerEnchantment(Rarity pRarity, EquipmentSlot... pApplicableSlots) {
        super(pRarity, EnchantmentCategory.WEAPON, pApplicableSlots);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getMinCost(int pLevel) {
        return 8;
    }

    @Override
    public int getMaxCost(int pLevel) {
        return super.getMinCost(pLevel) + 10;
    }
}
