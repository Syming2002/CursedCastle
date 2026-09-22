package com.syming.cursedcastle.enchantment;

import com.mojang.serialization.MapCodec;
import com.syming.cursedcastle.CursedCastleMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CCEnchantmentEffectEntity {

    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENCHANTMENTS = DeferredRegister.create(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE, CursedCastleMod.MOD_ID);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> BLOOD_SKELETON_KILLER = ENCHANTMENTS.register("blood_skeleton_killer", () -> new BloodSkeletonKillerEnchantmentEffect().codec());

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }

}
