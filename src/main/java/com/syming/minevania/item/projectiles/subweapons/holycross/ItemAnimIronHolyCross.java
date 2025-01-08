package com.syming.minevania.item.projectiles.subweapons.holycross;

import com.syming.minevania.item.MVItem;
import net.minecraft.item.Item;

public class ItemAnimIronHolyCross extends Item {

    public ItemAnimIronHolyCross(String name) {
        super();
        setMaxStackSize(1);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(null);

        MVItem.ITEMS.add(this);
    }
}
