package com.syming.minevania.item.whips;

import com.syming.minevania.api.IElementalWeapon;
import com.syming.minevania.damagesource.CCDamageSource;
import com.syming.minevania.item.MVItem;
import com.syming.minevania.tab.CCTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class ItemFlameWhip extends ItemWhip implements IElementalWeapon {

    public ItemFlameWhip(String name, float attackDamage, float attackSpeed, float reach) {
        super(attackDamage, attackSpeed, reach);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);

        MVItem.ITEMS.add(this);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        super.hitEntity(stack, target, attacker);
        target.setFire((int) 2.5);
        getElementalDamageSource(stack, target, attacker);
        return true;
    }

    @Override
    public DamageSource getElementalDamageSource(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        return CCDamageSource.getFireDamage();
    }

    @Override
    public float getReach() {
        return 12.0F;
    }
}
