package com.syming.minevania.item.whips;

import com.syming.minevania.item.MVItem;
import com.syming.minevania.tab.CCTabs;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class ItemJetBlackWhip extends ItemWhip {

    public ItemJetBlackWhip(String name, float attackDamage, float attackSpeed)
    {
        super(attackDamage, attackSpeed);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);

        MVItem.ITEMS.add(this);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        super.hitEntity(stack, target, attacker);
        if (target instanceof EntityMob  || target instanceof EntityAgeable || target instanceof EntityGolem) {
            target.addPotionEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE, 20, 2, true, false));
        }
        return true;
    }

    @Override
    public float getReach() {
        return 12.0F;
    }
}
