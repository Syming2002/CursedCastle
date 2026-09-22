package com.syming.cursedcastle.effect;

import com.syming.cursedcastle.CursedCastleMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;

public class HolyEffect extends MobEffect {
    public static final ResourceLocation LUCK_HOLY = ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "holy_luck");
    public static final ResourceLocation MAX_HEALTH_HOLY = ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "holy_max_health");
    public static final ResourceLocation ATTACK_SPEED_HOLY = ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "holy_attack_speed");

    public HolyEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x66CCFF);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (livingEntity instanceof Player player) {
            addAttributeModifier(Attributes.LUCK, LUCK_HOLY, 4.0D, AttributeModifier.Operation.ADD_VALUE);
            addAttributeModifier(Attributes.MAX_HEALTH, MAX_HEALTH_HOLY, 20.0D, AttributeModifier.Operation.ADD_VALUE);
            if (player.getMaxHealth() > player.getHealth()) {
                player.setHealth(player.getMaxHealth());
            }
            addAttributeModifier(Attributes.ATTACK_SPEED, ATTACK_SPEED_HOLY, 0.4D, AttributeModifier.Operation.ADD_VALUE);
        }
        if (livingEntity instanceof Monster monster) {
            monster.addEffect(new MobEffectInstance(MobEffects.WITHER, 20, 2, false, true));
        }
        return super.applyEffectTick(livingEntity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
