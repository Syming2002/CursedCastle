package com.syming.cursedcastle.item.whip.vampirekiller;

import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.whip.WhipToolItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CrushingStoneVampireKillerWhipItem extends WhipToolItem {
    public CrushingStoneVampireKillerWhipItem(Properties pProperties, int pAttackDamageModifier, float pAttackSpeedModifier, float pReachModifier, float pSpeed) {
        super(pProperties, pAttackDamageModifier, pAttackSpeedModifier, pReachModifier, pSpeed);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack pStack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack pStack) {
        return pStack.isDamaged() ? ItemStack.EMPTY : new ItemStack(CCItem.VAMPIRE_KILLER_WHIP.get());
    }

    @Override
    public void onCraftedBy(ItemStack pStack, Level pLevel, Player pPlayer) {
        if (!pStack.isDamaged()) {
            super.onCraftedBy(pStack, pLevel, pPlayer);
        }
    }
}
