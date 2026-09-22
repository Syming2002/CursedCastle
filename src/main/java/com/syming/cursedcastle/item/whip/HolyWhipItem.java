package com.syming.cursedcastle.item.whip;

import com.syming.cursedcastle.api.IElementalDamage;
import com.syming.cursedcastle.damagetype.CCDamageType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class HolyWhipItem extends WhipItem implements IElementalDamage {
    public HolyWhipItem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public DamageSource getElementalDamageSource(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return CCDamageType.causeHolyDamage(target);
    }
}
