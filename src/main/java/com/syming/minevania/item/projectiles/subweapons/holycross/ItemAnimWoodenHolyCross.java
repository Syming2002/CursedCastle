package com.syming.minevania.item.projectiles.subweapons.holycross;

import com.syming.minevania.item.CCItem;
import net.minecraft.item.Item;

public class ItemAnimWoodenHolyCross extends Item {

    public ItemAnimWoodenHolyCross(String name) {
        super();
        setMaxStackSize(1);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(null);

        CCItem.ITEMS.add(this);
    }
}
