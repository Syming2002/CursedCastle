package com.syming.minevania.item.projectiles;

import com.syming.minevania.item.MVItem;
import com.syming.minevania.tab.CCTabs;
import net.minecraft.item.Item;

public class ItemScytheProjectile extends Item {

    public ItemScytheProjectile(String name) {
        super();
        setMaxStackSize(64);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);

        MVItem.ITEMS.add(this);
    }
}
