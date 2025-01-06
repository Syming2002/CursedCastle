package com.syming.minevania.item.whips;

import com.syming.minevania.api.IElementalWeapon;
import com.syming.minevania.damagesource.CCDamageSource;
import com.syming.minevania.item.CCItem;
import com.syming.minevania.tab.CCTabs;
import com.syming.minevania.util.Helpers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class ItemFlameWhip extends ItemWhip implements IElementalWeapon {

    public ItemFlameWhip(String name, float attackDamage, float attackSpeed, float attackReach) {
        super(attackDamage, attackSpeed, attackReach);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);

        CCItem.ITEMS.add(this);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        target.setFire((int) 2.5);
        Helpers.playSound(attacker.world, attacker, "whip_impact", 0.5F, 1.0F);
        getElementalDamageSource(stack, target, attacker);
        return true;
    }

    @Override
    public DamageSource getElementalDamageSource(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        return CCDamageSource.getFireDamage();
    }
}
