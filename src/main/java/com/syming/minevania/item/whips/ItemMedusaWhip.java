package com.syming.minevania.item.whips;

import com.syming.minevania.item.CCItem;
import com.syming.minevania.tab.CCTabs;
import com.syming.minevania.util.Helpers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class ItemMedusaWhip extends ItemWhip {

    public ItemMedusaWhip(String name, float attackDamage, float attackSpeed, float attackReach)
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
        target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 100, true, true));
        Helpers.playSound(attacker.world, attacker, "whip_impact", 0.5F, 1.0F);
        return true;
    }
}
