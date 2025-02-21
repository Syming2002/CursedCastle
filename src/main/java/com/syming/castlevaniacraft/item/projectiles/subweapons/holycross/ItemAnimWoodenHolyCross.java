package com.syming.castlevaniacraft.item.projectiles.subweapons.holycross;

import com.syming.castlevaniacraft.item.CCItem;
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
