package com.syming.minevania.item.whips;

import com.syming.minevania.item.CCItem;
import com.syming.minevania.tab.CCTabs;

public class ItemSteelWhip extends ItemWhip {

    public ItemSteelWhip(String name, float attackDamage, float attackSpeed, float attackReach) {
        super(attackDamage, attackSpeed, attackReach);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);

        CCItem.ITEMS.add(this);
    }
}
