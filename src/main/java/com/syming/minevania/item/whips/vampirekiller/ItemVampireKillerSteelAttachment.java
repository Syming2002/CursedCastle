package com.syming.minevania.item.whips.vampirekiller;

import com.syming.minevania.item.MVItem;
import com.syming.minevania.tab.CCTabs;

public class ItemVampireKillerSteelAttachment extends ItemVampireKillerWhip {

    public ItemVampireKillerSteelAttachment(String name, float attackDamage, float attackSpeed, float attackReach) {
        super(attackDamage, attackSpeed, attackReach);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);
        setContainerItem(MVItem.vampireKiller);

        MVItem.ITEMS.add(this);
    }
}
