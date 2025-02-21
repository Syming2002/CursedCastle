package com.syming.castlevaniacraft.item.whips;

import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.tab.CCTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class ItemUndeadKiller extends ItemWhip {

    public ItemUndeadKiller(String name, float attackDamage, float attackSpeed, float reach)
    {
        super(attackDamage, attackSpeed, reach);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);

        CCItem.ITEMS.add(this);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        super.hitEntity(stack, target, attacker);
        if (target instanceof EntityZombie || target instanceof EntitySkeleton) {
            target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 10.0F);
        }
        return true;
    }

    @Override
    public float getReach() {
        return 16.0F;
    }
}
