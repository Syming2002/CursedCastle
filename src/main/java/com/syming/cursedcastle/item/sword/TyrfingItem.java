package com.syming.cursedcastle.item.sword;

import com.syming.cursedcastle.item.CCWeaponItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class TyrfingItem extends CCWeaponItem {
    public TyrfingItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (pAttacker.getRandom().nextFloat() <= 0.45F) {
            pAttacker.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 100, 0));
        }
        if (pAttacker.getRandom().nextFloat() <= 0.25F) {
            pAttacker.hurt(pAttacker.damageSources().magic(), 4.0F);
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
