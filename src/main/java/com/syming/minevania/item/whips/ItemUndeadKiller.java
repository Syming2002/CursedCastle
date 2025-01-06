package com.syming.minevania.item.whips;

import com.syming.minevania.item.CCItem;
import com.syming.minevania.tab.CCTabs;
import com.syming.minevania.util.Helpers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class ItemUndeadKiller extends ItemWhip {

    public ItemUndeadKiller(String name, float attackDamage, float attackSpeed, float attackReach)
    {
        super(attackDamage, attackSpeed, attackReach);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);

        CCItem.ITEMS.add(this);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        stack.damageItem(1, attacker);
        if (target instanceof EntityZombie || target instanceof EntitySkeleton)
        {
            target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 10.0F);
        }
        Helpers.playSound(attacker.world, attacker, "whip_impact", 0.5F, 1.0F);
        return true;
    }
}
