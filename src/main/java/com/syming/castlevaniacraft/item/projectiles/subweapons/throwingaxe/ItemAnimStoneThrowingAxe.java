package com.syming.castlevaniacraft.item.projectiles.subweapons.throwingaxe;

import com.syming.castlevaniacraft.item.CCItem;
import net.minecraft.item.Item;

public class ItemAnimStoneThrowingAxe extends Item {

    public ItemAnimStoneThrowingAxe(String name) {
        super();
        setMaxStackSize(1);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(null);

        CCItem.ITEMS.add(this);
    }

}
