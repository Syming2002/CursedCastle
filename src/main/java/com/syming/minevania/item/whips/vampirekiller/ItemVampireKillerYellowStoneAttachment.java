package com.syming.minevania.item.whips.vampirekiller;

import com.syming.minevania.item.CCItem;
import com.syming.minevania.tab.CCTabs;
import com.syming.minevania.util.Helpers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.item.ItemStack;

public class ItemVampireKillerYellowStoneAttachment extends ItemVampireKillerWhip {

    public ItemVampireKillerYellowStoneAttachment(String name, float attackDamage, float attackSpeed, float attackReach) {
        super(attackDamage, attackSpeed, attackReach);

        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);
        setContainerItem(CCItem.vampireKiller);

        CCItem.ITEMS.add(this);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        EntityLightningBolt lightningBolt = new EntityLightningBolt(target.world, target.posX, target.posY, target.posZ, false);
        target.world.spawnEntity(lightningBolt);
        stack.damageItem(1, attacker);
        Helpers.playSound(attacker.world, attacker, "whip_impact", 0.5F, 1.0F);
        return true;
    }
}
