package com.syming.minevania.item.projectiles;

import com.syming.minevania.item.CCItem;
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
