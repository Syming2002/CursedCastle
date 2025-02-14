package com.syming.castlevaniacraft.item.projectiles;

import com.syming.castlevaniacraft.item.MVItem;
import com.syming.castlevaniacraft.tab.CCTabs;
import net.minecraft.item.Item;

public class ItemThrowingBone extends Item {

    public ItemThrowingBone(String name) {
        this.setTranslationKey(name);
        this.setRegistryName(name);
        this.setCreativeTab(CCTabs.CREATIVE_TAB);

        MVItem.ITEMS.add(this);
    }

}
