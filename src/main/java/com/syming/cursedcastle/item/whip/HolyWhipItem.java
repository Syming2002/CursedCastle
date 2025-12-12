package com.syming.cursedcastle.item.whip;

import com.syming.cursedcastle.api.IElementalWeapon;
import com.syming.cursedcastle.datagen.CCDatapackBuiltinEntriesProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class HolyWhipItem extends WhipItem implements IElementalWeapon {
    public HolyWhipItem(Properties pProperties, int pAttackDamageModifier, float pAttackSpeedModifier, float pReachModifier) {
        super(pProperties, pAttackDamageModifier, pAttackSpeedModifier, pReachModifier);
    }


    @Override
    public DamageSource getElementalDamageSource(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return CCDatapackBuiltinEntriesProvider.source(attacker.level().registryAccess(), CCDatapackBuiltinEntriesProvider.HOLY);
    }
}
