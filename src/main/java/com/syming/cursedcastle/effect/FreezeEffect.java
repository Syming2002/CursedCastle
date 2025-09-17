package com.syming.cursedcastle.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class FreezeEffect extends MobEffect {
    public FreezeEffect() {
        super(MobEffectCategory.HARMFUL, 0x8FEBCC);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity instanceof Player player) {
            player.setDeltaMovement(Vec3.ZERO);
            player.hurtMarked = true;
            player.setJumping(false);
            player.setSpeed(0.0F);
            player.setSprinting(false);
        }
        if (pLivingEntity instanceof Mob mob) {
            mob.getNavigation().stop();
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
