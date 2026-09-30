package com.syming.cursedcastle.loot;

import com.google.common.collect.Sets;
import com.syming.cursedcastle.CursedCastleMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.HashSet;
import java.util.Set;

public class CCLootTables {

    private static final Set<ResourceKey<LootTable>> CC_LOOT_TABLES = new HashSet<>();

    // Entity Loot
    public static final ResourceKey<LootTable> ARACHNE = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "entities/arachne"));


    public static Set<ResourceKey<LootTable>> getCCLootTables() {
        return CC_LOOT_TABLES;
    }
}
