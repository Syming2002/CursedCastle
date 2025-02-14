package com.syming.castlevaniacraft.item.whips;

import com.syming.castlevaniacraft.item.MVItem;
import com.syming.castlevaniacraft.tab.CCTabs;

public class ItemRoseStemWhip extends ItemWhip {

    public ItemRoseStemWhip(String name, float attackDamage, float attackSpeed, float reach) {
        super(attackDamage, attackSpeed, reach);
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
