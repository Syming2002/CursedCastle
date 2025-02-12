package com.syming.minevania.item.whips.vampirekiller;

import com.syming.minevania.item.MVItem;
import com.syming.minevania.tab.CCTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class ItemVampireKillerRedStoneAttachment extends ItemVampireKillerWhip {

    public ItemVampireKillerRedStoneAttachment(String name, float attackDamage, float attackSpeed, float reach) {
        super(attackDamage, attackSpeed, reach);

        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);
        setContainerItem(MVItem.vampireKiller);

        MVItem.ITEMS.add(this);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        super.hitEntity(stack, target, attacker);
        target.setFire(5);
        return true;
    }

    @Override
    public float getReach() {
        return 12.0F;
    }
}
