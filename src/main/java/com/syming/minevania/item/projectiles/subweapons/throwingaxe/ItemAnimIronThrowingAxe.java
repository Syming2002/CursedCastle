package com.syming.minevania.item.projectiles.subweapons.throwingaxe;

import com.syming.minevania.item.CCItem;
import net.minecraft.item.Item;

public class ItemAnimIronThrowingAxe extends Item {

    public ItemAnimIronThrowingAxe(String name) {
        super();
        setMaxStackSize(1);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(null);

        CCItem.ITEMS.add(this);
    }

}
