package com.syming.cursedcastle.item.whip;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;

public class LeatherWhipItem extends WhipItem {
    public LeatherWhipItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isValidRepairItem(ItemStack pStack, ItemStack pRepairCandidate) {
        if (pStack.isDamaged()) {
            pRepairCandidate.is(Items.LEATHER);
        }
        return false;
    }

    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return super.isEnchantable(pStack);
    }
}
