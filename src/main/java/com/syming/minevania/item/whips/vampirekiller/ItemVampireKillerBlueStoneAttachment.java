package com.syming.minevania.item.whips.vampirekiller;

import com.syming.minevania.item.CCItem;
import com.syming.minevania.tab.CCTabs;
import com.syming.minevania.util.Helpers;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemVampireKillerBlueStoneAttachment extends ItemVampireKillerWhip {

    public ItemVampireKillerBlueStoneAttachment(String name, float attackDamage, float attackSpeed, float attackReach) {
        super(attackDamage, attackSpeed, attackReach);

        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);
        setContainerItem(CCItem.vampireKiller);

        CCItem.ITEMS.add(this);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        if (target instanceof EntityLiving living) {
            living.setNoAI(true);
            living.getEntityData().setInteger("NoAITicks", 100);
        } else if (target instanceof EntityPlayer player) {
            player.setVelocity(0.0D, 0.0D, 0.0D);
            player.setJumping(false);
            player.getEntityData().setInteger("PlayerCannotMove", 100);
        }
        Helpers.playSound(attacker.world, attacker, "whip_impact", 0.5F, 1.0F);
        return true;
    }
}
