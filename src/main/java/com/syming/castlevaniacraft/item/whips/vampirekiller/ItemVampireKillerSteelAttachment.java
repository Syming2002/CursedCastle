package com.syming.castlevaniacraft.item.whips.vampirekiller;

import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.tab.CCTabs;

public class ItemVampireKillerSteelAttachment extends ItemVampireKillerWhip {

    public ItemVampireKillerSteelAttachment(String name, float attackDamage, float attackSpeed, float reach) {
        super(attackDamage, attackSpeed, reach);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);
        setContainerItem(CCItem.vampireKiller);

        CCItem.ITEMS.add(this);
    }

    @Override
    public float getReach() {
        return 12.0F;
    }
}
