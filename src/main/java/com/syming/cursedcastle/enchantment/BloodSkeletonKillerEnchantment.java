package com.syming.cursedcastle.enchantment;

import com.syming.cursedcastle.entity.mob.skeleton.BloodSkeletonEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
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
    //    @Override
//    public void doPostAttack(LivingEntity pAttacker, Entity pTarget, int pLevel) {
//        if (pTarget instanceof LivingEntity livingEntity) {
//            if (livingEntity instanceof BloodSkeletonEntity bloodSkeleton) {
//                bloodSkeleton
//            }
//        }
//        super.doPostAttack(pAttacker, pTarget, pLevel);
//    }
}
