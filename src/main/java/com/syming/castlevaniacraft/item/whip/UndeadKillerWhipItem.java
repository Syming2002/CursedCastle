package com.syming.castlevaniacraft.item.whip;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class UndeadKillerWhipItem extends WhipItem {
    public UndeadKillerWhipItem(Properties pProperties, int pAttackDamageModifier, float pAttackSpeedModifier, float pReachModifier) {
        super(pProperties, pAttackDamageModifier, pAttackSpeedModifier, pReachModifier);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        super.hurtEnemy(pStack, pTarget, pAttacker);
        if (pTarget instanceof Zombie || pTarget instanceof AbstractSkeleton) {
            pTarget.hurt(pTarget.damageSources().playerAttack((Player) pAttacker), 10.0F);
        }
        pStack.hurtAndBreak(1, pAttacker, (p_43296_) -> p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return true;
    }
}
