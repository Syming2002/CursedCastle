package com.syming.castlevaniacraft.item.projectiles;

import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.tab.CCTabs;
import net.minecraft.item.Item;

public class ItemScytheProjectile extends Item {

    public ItemScytheProjectile(String name) {
        super();
        setMaxStackSize(64);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);

        CCItem.ITEMS.add(this);
    }
}
