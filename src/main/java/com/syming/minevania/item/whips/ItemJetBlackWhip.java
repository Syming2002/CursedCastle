package com.syming.minevania.item.whips;

import com.syming.minevania.item.CCItem;
import com.syming.minevania.tab.CCTabs;
import com.syming.minevania.util.Helpers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class ItemJetBlackWhip extends ItemWhip {

    public ItemJetBlackWhip(String name, float attackDamage, float attackSpeed, float attackReach)
    {
        super(attackDamage, attackSpeed, attackReach);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);

        CCItem.ITEMS.add(this);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        if (target instanceof EntityZombie || target instanceof EntitySkeleton || target instanceof EntityStray || target instanceof EntityVillager || target instanceof EntityIronGolem || target instanceof EntitySnowman) {
            target.addPotionEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE, 20, 2, true, false));
        } else {
            target.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 20, 2, true, false));
        }

        Helpers.playSound(attacker.world, attacker, "whip_impact", 0.5F, 1.0F);
        return true;
    }
}
