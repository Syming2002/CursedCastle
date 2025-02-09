package com.syming.minevania.item.whips.vampirekiller;

import com.syming.minevania.item.MVItem;
import com.syming.minevania.tab.CCTabs;

public class ItemVampireKillerPlatinumAttachment extends ItemVampireKillerWhip {

    public ItemVampireKillerPlatinumAttachment(String name, float attackDamage, float attackSpeed) {
        super(attackDamage, attackSpeed);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);
        setContainerItem(MVItem.vampireKiller);

        MVItem.ITEMS.add(this);
    }

    @Override
    public float getReach() {
        return 12.0F;
    }
}
