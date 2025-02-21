package com.syming.castlevaniacraft.item.projectiles;

import com.syming.castlevaniacraft.item.CCItem;
import net.minecraft.item.Item;

public class ItemAnimScytheProjectile extends Item {

    public ItemAnimScytheProjectile(String name) {
        super();
        setMaxStackSize(64);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(null);

        CCItem.ITEMS.add(this);
    }

}
