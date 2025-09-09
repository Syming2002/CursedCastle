package com.syming.cursedcastle.loot;

import com.mojang.serialization.Codec;
import com.syming.cursedcastle.CursedCastleMod;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CCLootModifier {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, CursedCastleMod.MOD_ID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ZOMBIE_SOUL =
            LOOT_MODIFIER_SERIALIZERS.register("add_soul", AddSoulModifier.CODEC);


    public static void register(IEventBus eventBus) {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }
}
