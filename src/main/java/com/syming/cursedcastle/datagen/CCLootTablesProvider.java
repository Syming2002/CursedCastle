package com.syming.cursedcastle.datagen;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.loot.CCLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CCLootTablesProvider extends LootTableProvider {
    public CCLootTablesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, CCLootTables.getCCLootTables(), List.of(
                new LootTableProvider.SubProviderEntry(CCEntityLootSubProvider::new, LootContextParamSets.ENTITY)
        ), completableFuture);
    }
}
