package com.syming.castlevaniacraft.tab;

import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.util.CCReferences;
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
