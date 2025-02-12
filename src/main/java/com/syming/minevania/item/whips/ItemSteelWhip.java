package com.syming.minevania.item.whips;

import com.syming.minevania.item.MVItem;
import com.syming.minevania.tab.CCTabs;

public class ItemSteelWhip extends ItemWhip {

    public ItemSteelWhip(String name, float attackDamage, float attackSpeed, float reach) {
        super(attackDamage, attackSpeed, reach);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);

        MVItem.ITEMS.add(this);
    }

    @Override
    public float getReach() {
        return 12.0F;
    }
}
