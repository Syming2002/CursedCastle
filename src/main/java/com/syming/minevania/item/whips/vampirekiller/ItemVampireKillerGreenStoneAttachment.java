package com.syming.minevania.item.whips.vampirekiller;

import com.syming.minevania.item.MVItem;
import com.syming.minevania.tab.CCTabs;

public class ItemVampireKillerGreenStoneAttachment extends ItemVampireKillerWhip {

    public ItemVampireKillerGreenStoneAttachment(String name, float attackDamage, float attackSpeed, float reach) {
        super(attackDamage, attackSpeed, reach);

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
