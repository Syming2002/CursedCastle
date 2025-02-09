package com.syming.minevania.item.whips;

import com.syming.minevania.item.MVItem;
import com.syming.minevania.tab.CCTabs;

public class ItemRoseStemWhip extends ItemWhip {

    public ItemRoseStemWhip(String name, float attackDamage, float attackSpeed) {
        super(attackDamage, attackSpeed);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);

        MVItem.ITEMS.add(this);
    }

    @Override
    public float getReach() {
        return 8.0F;
    }
}
