package com.syming.minevania.item.whips.vampirekiller;

//import com.syming.minevania.api.IExtendedReach;
import com.syming.minevania.item.CCItem;
import com.syming.minevania.tab.CCTabs;

public class ItemVampireKiller extends ItemVampireKillerWhip /*implements IExtendedReach*/ {

    public ItemVampireKiller(String name, float attackDamage, float attackSpeed, float attackReach)
    {
        super(attackDamage, attackSpeed, attackReach);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);

        CCItem.ITEMS.add(this);
    }

//    @Override
//    public float getReach()
//    {
//        return 12.5F;
//    }
}
