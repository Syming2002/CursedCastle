package com.syming.minevania.item.whips;

import com.syming.minevania.item.MVItem;
import com.syming.minevania.tab.CCTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class ItemMedusaWhip extends ItemWhip {

    public ItemMedusaWhip(String name, float attackDamage, float attackSpeed) {
        super(attackDamage, attackSpeed);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);

        MVItem.ITEMS.add(this);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        super.hitEntity(stack, target, attacker);
        target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 100, true, true));
        return true;
    }

    @Override
    public float getReach() {
        return 12.0F;
    }
}
