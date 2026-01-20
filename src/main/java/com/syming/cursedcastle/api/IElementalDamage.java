package com.syming.cursedcastle.api;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public interface IElementalDamage {

    public DamageSource getElementalDamageSource(ItemStack stack, LivingEntity target, LivingEntity attacker);

}
