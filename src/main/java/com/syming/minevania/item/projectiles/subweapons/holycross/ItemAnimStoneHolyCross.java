package com.syming.minevania.item.projectiles.subweapons.holycross;

import com.syming.minevania.item.CCItem;
import net.minecraft.item.Item;

public class ItemAnimStoneHolyCross extends Item {

    public ItemAnimStoneHolyCross(String name) {
        super();
        setMaxStackSize(1);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(null);

        CCItem.ITEMS.add(this);
    }
}
