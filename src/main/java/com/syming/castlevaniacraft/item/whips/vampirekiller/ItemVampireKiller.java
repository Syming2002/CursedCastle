package com.syming.castlevaniacraft.item.whips.vampirekiller;

import com.syming.castlevaniacraft.item.MVItem;
import com.syming.castlevaniacraft.tab.CCTabs;

public class ItemVampireKiller extends ItemVampireKillerWhip {

    public ItemVampireKiller(String name, float attackDamage, float attackSpeed, float reach)
    {
        super(attackDamage, attackSpeed, reach);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);

        MVItem.ITEMS.add(this);
    }

    @Override
    public float getReach()
    {
        return 12.0F;
    }
}
