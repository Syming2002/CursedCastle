package com.syming.cursedcastle.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class DeathScytheItem extends Item {
    public DeathScytheItem() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        super.hurtEnemy(pStack, pTarget, pAttacker);

        if (pAttacker.getRandom().nextFloat() < 0.1F) {
            pTarget.hurt(pTarget.damageSources().generic(), 16.0F);
        } else {
            pTarget.hurt(pTarget.damageSources().generic(), 8.0F);
        }

        pStack.hurtAndBreak(1, pAttacker, (livingEntity) -> livingEntity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return true;
    }
}
