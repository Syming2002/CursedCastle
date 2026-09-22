//package com.syming.cursedcastle.compat.forestry;
//
//import com.syming.cursedcastle.CursedCastleMod;
//import com.syming.cursedcastle.block.CCBlock;
//import forestry.api.apiculture.ForestryBeeSpecies;
//import forestry.api.core.genetics.ForestrySpeciesTypes;
//import forestry.api.core.genetics.IMutationCondition;
//import forestry.api.core.genetics.alleles.Allele;
//import forestry.core.engine.genetics.mutations.MutationConditionRequiresResource;
//import forestry.core.engine.genetics.mutations.MutationRecipe;
//import net.minecraft.core.HolderLookup;
//import net.minecraft.core.registries.Registries;
//import net.minecraft.data.CachedOutput;
//import net.minecraft.data.DataProvider;
//import net.minecraft.data.PackOutput;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.crafting.Recipe;
//import net.minecraft.world.level.block.Blocks;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.CompletableFuture;
//
//public class CCMutationProvider implements DataProvider {
//    private final PackOutput.PathProvider pathProvider;
//    private final CompletableFuture<HolderLookup.Provider> lookupProvider;
//    private final List<MutationRecipe> pending = new ArrayList<>();
//
//    public CCMutationProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
//        this.pathProvider = output.createRegistryElementsPathProvider(Registries.RECIPE);
//        this.lookupProvider = lookupProvider;
//    }
//
//    @Override
//    public CompletableFuture<?> run(CachedOutput cachedOutput) {
//        return this.lookupProvider.thenCompose(registries -> {
//           this.pending.clear();
//           addCCMutations();
//           return flush(cachedOutput, registries);
//        });
//    }
//
//    @Override
//    public String getName() {
//        return "Cursed Castle Mutation Recipes";
//    }
//
//    private CompletableFuture<?> flush(CachedOutput cachedOutput, HolderLookup.Provider lookupProvider) {
//        Map<String, Integer> counters = new HashMap<>();
//        List<CompletableFuture<?>> futures = new ArrayList<>();
//        for (MutationRecipe builder : this.pending) {
//            String base = folder(builder.getSpeciesTypeId()) + '/' + builder.getResultId().getPath();
//            int n = counters.merge(base, 1, Integer::sum);
//            ResourceLocation id = ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, base + '_' + n);
//            futures.add(DataProvider.saveStable(cachedOutput, lookupProvider, Recipe.CODEC, builder, this.pathProvider.json(id)));
//        }
//        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
//    }
//
//    private static String folder(ResourceLocation speciesTypeId) {
//        if (speciesTypeId.equals(ForestrySpeciesTypes.BEE)) {
//            return "bee_mutation";
//        }
//        throw new IllegalArgumentException("Unknown species type for mutation: " + speciesTypeId);
//    }
//
//    private MutationRecipe add(ResourceLocation speciesTypeId, ResourceLocation id, ResourceLocation first, ResourceLocation second, ResourceLocation result, float chance, List<IMutationCondition> conditions, Map<ResourceLocation, Allele<?>> resultAlleles) {
//        MutationRecipe builder = new MutationRecipe(speciesTypeId, id, first, second, result, chance, conditions, resultAlleles);
//        this.pending.add(builder);
//        return builder;
//    }
//
//    private void addCCMutations() {
//        bees();
//    }
//
//    private void bees() {
//        add(ForestrySpeciesTypes.BEE, CCBeesSpecies.FROZEN_SHADE, ForestryBeeSpecies.GLACIAL, ForestryBeeSpecies.DEMONIC, CCBeesSpecies.FROZEN_SHADE, 0.15F, List.of(new MutationConditionRequiresResource(Blocks.PACKED_ICE.defaultBlockState())), Map.of());
//        add(ForestrySpeciesTypes.BEE, CCBeesSpecies.HEAT_SHADE, ForestryBeeSpecies.SINISTER, ForestryBeeSpecies.DEMONIC, CCBeesSpecies.HEAT_SHADE, 0.15F, List.of(new MutationConditionRequiresResource(Blocks.LAVA.defaultBlockState())), Map.of());
//        add(ForestrySpeciesTypes.BEE, CCBeesSpecies.VAMPIRE, ForestryBeeSpecies.DEMONIC, ForestryBeeSpecies.SINISTER, CCBeesSpecies.VAMPIRE, 0.05F, List.of(new MutationConditionRequiresResource(CCBlock.BLOOD_BLOCK.get().defaultBlockState())), Map.of());
//        add(ForestrySpeciesTypes.BEE, CCBeesSpecies.DRACULA, ForestryBeeSpecies.IMPERIAL, CCBeesSpecies.VAMPIRE, CCBeesSpecies.DRACULA, 0.025F, List.of(new MutationConditionRequiresResource(CCBlock.BLOOD_BLOCK.get().defaultBlockState())), Map.of());
//    }
//}
