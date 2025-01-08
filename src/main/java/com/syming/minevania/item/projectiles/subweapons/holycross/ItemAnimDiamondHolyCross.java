package com.syming.minevania.item.projectiles.subweapons.holycross;

import com.syming.minevania.item.MVItem;
import net.minecraft.item.Item;

public class ItemAnimDiamondHolyCross extends Item {

    public ItemAnimDiamondHolyCross(String name) {
        super();
        setMaxStackSize(1);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(null);

        MVItem.ITEMS.add(this);
    }
}
