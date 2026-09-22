//package com.syming.cursedcastle.compat.forestry;
//
//import com.google.gson.JsonElement;
//import com.mojang.serialization.JsonOps;
//import com.syming.cursedcastle.tag.CCTag;
//import forestry.api.core.genetics.ForestryTaxa;
//import forestry.apiimpl.plugin.GeneticRegistration;
//import forestry.core.engine.genetics.TaxonDefinition;
//import net.minecraft.data.CachedOutput;
//import net.minecraft.data.DataProvider;
//import net.minecraft.data.PackOutput;
//import net.minecraft.resources.ResourceLocation;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//import java.util.concurrent.CompletableFuture;
//
//public class CCTaxonProvider implements DataProvider {
//
//    private final PackOutput.PathProvider pathProvider;
//    private final Map<ResourceLocation, TaxonDefinition> pending = new LinkedHashMap<>();
//
//    public CCTaxonProvider(PackOutput packOutput) {
//        this.pathProvider = packOutput.createPathProvider(PackOutput.Target.DATA_PACK, "taxon");
//    }
//
//
//    @Override
//    public CompletableFuture<?> run(CachedOutput cachedOutput) {
//        this.pending.clear();
//        addTaxa();
//        var futures = this.pending.entrySet().stream().map(entry -> {
//            JsonElement json = TaxonDefinition.CODEC.encodeStart(JsonOps.INSTANCE, entry.getValue()).getOrThrow();
//            return DataProvider.saveStable(cachedOutput, json, this.pathProvider.json(entry.getKey()));
//        }).toArray(CompletableFuture[]::new);
//        return CompletableFuture.allOf(futures);
//    }
//
//    @Override
//    public String getName() {
//        return "Cursed Castle Taxa";
//    }
//
//    private void addTaxa() {
//        GeneticRegistration geneticRegistration = new GeneticRegistration();
//        CCBeesTaxonomy.defineTaxa(geneticRegistration);
//    }
//}
