package com.syming.minevania.item.projectiles;

import com.syming.minevania.item.CCItem;
import com.syming.minevania.tab.CCTabs;
import net.minecraft.item.ItemStack;

public class ItemScytheProjectile extends com.syming.minevania.api.CCItem {

    public ItemScytheProjectile(String name) {
        super();
        setMaxStackSize(64);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);

        CCItem.ITEMS.add(this);
    }

    @Override
    public float getHitDamage() {
        return 3;
    }

    @Override
    public double getAttackSpeed(ItemStack stack) {
        return -1;
    }

}
