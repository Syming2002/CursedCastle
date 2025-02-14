package com.syming.castlevaniacraft.item.projectiles.subweapons.throwingaxe;

import com.syming.castlevaniacraft.item.MVItem;
import net.minecraft.item.Item;

public class ItemAnimDiamondThrowingAxe extends Item {

    public ItemAnimDiamondThrowingAxe(String name) {
        super();
        setMaxStackSize(1);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(null);

        MVItem.ITEMS.add(this);
    }

}
