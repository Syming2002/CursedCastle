package com.syming.castlevaniacraft.item.projectiles.subweapons.holycross;

import com.syming.castlevaniacraft.item.CCItem;
import net.minecraft.item.Item;

public class ItemAnimFireHolyCross extends Item {

    public ItemAnimFireHolyCross(String name) {
        setMaxStackSize(1);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(null);

        CCItem.ITEMS.add(this);
    }
}
