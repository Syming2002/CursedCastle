package com.syming.minevania.tab;

import com.syming.minevania.item.CCItem;
import com.syming.minevania.util.CCReferences;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CCTabs {

    public static final CCCreativeTab CREATIVE_TAB = new CCCreativeTab();

    public static class CCCreativeTab extends CreativeTabs {
        public CCCreativeTab() {
            super(CCReferences.MODID);
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(CCItem.leatherWhip);
        }
    }

}
