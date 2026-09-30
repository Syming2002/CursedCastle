package com.syming.cursedcastle.compat.forestry;

import forestry.core.platform.item.IColoredItem;
import forestry.core.platform.item.ItemForestry;
import net.minecraft.world.item.ItemStack;

public class CCBeesItemHoneyComb extends ItemForestry implements IColoredItem {
    private final CCBeesEnumHoneyComb enumHoneyComb;

    public CCBeesItemHoneyComb(CCBeesEnumHoneyComb enumHoneyComb) {
        this.enumHoneyComb = enumHoneyComb;
    }

    public CCBeesEnumHoneyComb getEnumHoneyComb() {
        return this.enumHoneyComb;
    }

    @Override
    public int getColorFromItemStack(ItemStack itemStack, int i) {
        CCBeesEnumHoneyComb enumHoneyComb = this.enumHoneyComb;

        if (i == 1) {
            return enumHoneyComb.getPrimaryColor().getRGB();
        } else {
            return enumHoneyComb.getSecondaryColor().getRGB();
        }
    }
}
