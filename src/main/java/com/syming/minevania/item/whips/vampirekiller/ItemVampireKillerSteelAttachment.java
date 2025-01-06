package com.syming.minevania.item.whips.vampirekiller;

import com.syming.minevania.item.CCItem;
import com.syming.minevania.tab.CCTabs;

public class ItemVampireKillerSteelAttachment extends ItemVampireKillerWhip {

    public ItemVampireKillerSteelAttachment(String name, float attackDamage, float attackSpeed, float attackReach) {
        super(attackDamage, attackSpeed, attackReach);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);
        setContainerItem(CCItem.vampireKiller);

        CCItem.ITEMS.add(this);
    }
}
