package com.syming.cursedcastle.world;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.CCEntity;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;

public class CCBiomeModifiers {

    public static final ResourceKey<BiomeModifier> SPAWN_FROZEN_SHADE = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "spawn_frozen_shade"));
    public static final ResourceKey<BiomeModifier> SPAWN_POISON_SHADE = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "spawn_poison_shade"));
    public static final ResourceKey<BiomeModifier> SPAWN_HEAT_SHADE = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "spawn_heat_shade"));

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var biomes = context.lookup(Registries.BIOME);

        context.register(SPAWN_FROZEN_SHADE, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_COLD_OVERWORLD),
                List.of(new MobSpawnSettings.SpawnerData(CCEntity.FROZEN_SHADE.get(), 50, 1, 1))));

        context.register(SPAWN_POISON_SHADE, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_JUNGLE),
                List.of(new MobSpawnSettings.SpawnerData(CCEntity.POISON_SHADE.get(), 70, 1, 1))));

        context.register(SPAWN_HEAT_SHADE, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_HOT_NETHER),
                List.of(new MobSpawnSettings.SpawnerData(CCEntity.HEAT_SHADE.get(), 60, 1, 1))));
    }
}
