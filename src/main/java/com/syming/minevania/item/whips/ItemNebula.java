package com.syming.minevania.item.whips;

import com.syming.minevania.api.IElementalWeapon;
import com.syming.minevania.damagesource.CCDamageSource;
import com.syming.minevania.item.MVItem;
import com.syming.minevania.tab.CCTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;

public class ItemNebula extends ItemWhip implements IElementalWeapon {

    public ItemNebula(String name, float attackDamage, float attackSpeed, float reach) {
        super(attackDamage, attackSpeed, reach);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);

        MVItem.ITEMS.add(this);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        super.hitEntity(stack, target, attacker);
        target.world.playSound(null, target.posX, target.posY, target.posZ, SoundEvents.ENTITY_LIGHTNING_THUNDER, SoundCategory.MASTER, 0.5F, 1.0F);
        getElementalDamageSource(stack, target, attacker);
        return true;
    }

    @Override
    public DamageSource getElementalDamageSource(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        return CCDamageSource.getThunderDamage();
    }

    @Override
    public float getReach() {
        return 20.0F;
    }
}
