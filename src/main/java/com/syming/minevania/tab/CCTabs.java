package com.syming.minevania.tab;

import com.syming.minevania.item.MVItem;
import com.syming.minevania.util.MVReferences;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CCTabs {

    public static final CCCreativeTab CREATIVE_TAB = new CCCreativeTab();

    public static class CCCreativeTab extends CreativeTabs {
        public CCCreativeTab() {
            super(MVReferences.MODID);
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(MVItem.leatherWhip);
        }
    }

}
