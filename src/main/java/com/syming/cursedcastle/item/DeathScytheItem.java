package com.syming.cursedcastle.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class DeathScytheItem extends CCWeaponItem {
    public DeathScytheItem(Properties pProperties, int pAttackDamageModifier, float pAttackSpeedModifier, float pReachModifier) {
        super(pProperties, pAttackDamageModifier, pAttackSpeedModifier, pReachModifier);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        super.hurtEnemy(pStack, pTarget, pAttacker);

        if (pAttacker.getRandom().nextFloat() < 0.1F) {
            pTarget.hurt(pTarget.damageSources().generic(), 16.0F);
        } else {
            pTarget.hurt(pTarget.damageSources().generic(), this.attackDamage);
        }

        pStack.hurtAndBreak(1, pAttacker, (livingEntity) -> livingEntity.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return true;
    }
}
