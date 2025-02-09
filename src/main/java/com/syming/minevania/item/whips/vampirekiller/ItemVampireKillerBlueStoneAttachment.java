package com.syming.minevania.item.whips.vampirekiller;

import com.syming.minevania.item.MVItem;
import com.syming.minevania.tab.CCTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemVampireKillerBlueStoneAttachment extends ItemVampireKillerWhip {

    public ItemVampireKillerBlueStoneAttachment(String name, float attackDamage, float attackSpeed) {
        super(attackDamage, attackSpeed);

        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);
        setContainerItem(MVItem.vampireKiller);

        MVItem.ITEMS.add(this);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        super.hitEntity(stack, target, attacker);
        if (target instanceof EntityLiving living) {
            living.setNoAI(true);
            living.getEntityData().setInteger("NoAITicks", 100);
        } else if (target instanceof EntityPlayer player) {
            player.setVelocity(0.0D, 0.0D, 0.0D);
            player.setJumping(false);
            player.getEntityData().setInteger("PlayerCannotMove", 100);
        }
        return true;
    }

    @Override
    public float getReach() {
        return 12.0F;
    }
}
