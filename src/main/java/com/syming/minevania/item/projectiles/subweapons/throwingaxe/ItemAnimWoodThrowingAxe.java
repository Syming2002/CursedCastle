package com.syming.minevania.item.projectiles.subweapons.throwingaxe;

import com.syming.minevania.item.MVItem;
import net.minecraft.item.Item;

public class ItemAnimWoodThrowingAxe extends Item {

    public ItemAnimWoodThrowingAxe(String name) {
        super();
        setMaxStackSize(1);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(null);

        MVItem.ITEMS.add(this);
    }

}
