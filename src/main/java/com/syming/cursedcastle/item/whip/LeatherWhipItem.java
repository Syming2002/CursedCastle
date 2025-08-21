package com.syming.cursedcastle.item.whip;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class LeatherWhipItem extends WhipItem {
    public LeatherWhipItem(Properties pProperties, int pAttackDamageModifier, float pAttackSpeedModifier, float pReachModifier) {
        super(pProperties, pAttackDamageModifier, pAttackSpeedModifier, pReachModifier);
    }

    @Override
    public boolean isValidRepairItem(ItemStack pStack, ItemStack pRepairCandidate) {
        if (pStack.isDamaged()) {
            pRepairCandidate.is(Items.LEATHER);
        }
        return false;
    }
}
