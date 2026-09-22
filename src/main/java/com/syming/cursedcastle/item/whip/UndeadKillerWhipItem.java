package com.syming.cursedcastle.item.whip;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class UndeadKillerWhipItem extends WhipItem {
    public UndeadKillerWhipItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        super.hurtEnemy(pStack, pTarget, pAttacker);
        if (pTarget instanceof Zombie || pTarget instanceof AbstractSkeleton) {
            pTarget.hurt(pTarget.damageSources().playerAttack((Player) pAttacker), 10.0F);
        }
        return true;
    }
}
