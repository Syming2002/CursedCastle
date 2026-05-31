package com.syming.cursedcastle.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public class HolyEffect extends MobEffect {
    public static final UUID LUCK_UUID = UUID.fromString("3705dbb1-860b-4d38-8c78-07f4d793bc50");
    public static final UUID MAX_HEALTH_UUID = UUID.fromString("a00dbde7-154c-4e81-86b0-1f3c0fda4baa");
    public static final UUID ATTACK_SPEED_UUID = UUID.fromString("853d5396-2b26-4c39-a3bf-e422f783155c");

    public HolyEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x66CCFF);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity instanceof Player player) {
            addAttributeModifier(Attributes.LUCK, LUCK_UUID.toString(), 4.0D, AttributeModifier.Operation.ADDITION);
            addAttributeModifier(Attributes.MAX_HEALTH, MAX_HEALTH_UUID.toString(), 20.0D, AttributeModifier.Operation.ADDITION);
            if (player.getMaxHealth() > player.getHealth()) {
                player.setHealth(player.getMaxHealth());
            }
            addAttributeModifier(Attributes.ATTACK_SPEED, ATTACK_SPEED_UUID.toString(), 0.4D, AttributeModifier.Operation.ADDITION);
        }
        if (pLivingEntity instanceof Monster monster) {
            monster.addEffect(new MobEffectInstance(MobEffects.WITHER, 20, 2, false, true));
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
