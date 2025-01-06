package com.syming.minevania.item.projectiles;

import com.syming.minevania.item.CCItem;
import com.syming.minevania.tab.CCTabs;
import net.minecraft.item.Item;

public class ItemThrowingBone extends Item {

    public ItemThrowingBone(String name) {
        this.setTranslationKey(name);
        this.setRegistryName(name);
        this.setCreativeTab(CCTabs.CREATIVE_TAB);

        CCItem.ITEMS.add(this);
    }

}
