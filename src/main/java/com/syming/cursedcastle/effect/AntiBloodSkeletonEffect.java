package com.syming.cursedcastle.effect;

import com.syming.cursedcastle.entity.mob.skeleton.BloodSkeletonEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class AntiBloodSkeletonEffect extends MobEffect {
    public AntiBloodSkeletonEffect() {
        super(MobEffectCategory.HARMFUL, 0x9E231C);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity instanceof BloodSkeletonEntity bloodSkeleton) {
            bloodSkeleton.hurt(pLivingEntity.damageSources().magic(), 5.0F);
        }

        if (pLivingEntity instanceof Player player) {
            player.addEffect(new MobEffectInstance(MobEffects.POISON, 40, pAmplifier));
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
