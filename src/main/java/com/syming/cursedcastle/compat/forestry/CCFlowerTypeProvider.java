//package com.syming.cursedcastle.compat.forestry;
//
//import com.google.gson.JsonElement;
//import com.mojang.serialization.JsonOps;
//import com.syming.cursedcastle.tag.CCTag;
//import forestry.api.apiculture.IFlowerType;
//import forestry.core.engine.genetics.flowers.TagFlowerType;
//import net.minecraft.data.CachedOutput;
//import net.minecraft.data.DataProvider;
//import net.minecraft.data.PackOutput;
//import net.minecraft.resources.ResourceLocation;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.CompletableFuture;
//
//public class CCFlowerTypeProvider implements DataProvider {
//
//    private final PackOutput.PathProvider pathProvider;
//    private final Map<ResourceLocation, IFlowerType> pending = new HashMap<>();
//
//    public CCFlowerTypeProvider(PackOutput packOutput) {
//        this.pathProvider = packOutput.createPathProvider(PackOutput.Target.DATA_PACK, "flower_type");
//    }
//
//    @Override
//    public CompletableFuture<?> run(CachedOutput cachedOutput) {
//        this.pending.clear();
//        addFlowerTypes();
//        var futures = this.pending.entrySet().stream().map(entry -> {
//            JsonElement json = IFlowerType.CODEC.encodeStart(JsonOps.INSTANCE, entry.getValue()).getOrThrow();
//            return DataProvider.saveStable(cachedOutput, json, this.pathProvider.json(entry.getKey()));
//        }).toArray(CompletableFuture[]::new);
//        return CompletableFuture.allOf(futures);
//    }
//
//    @Override
//    public String getName() {
//        return "Cursed Castle Flower Types";
//    }
//
//    private void addFlowerTypes() {
//        add(CCBeesFlowerType.BLOOD, new TagFlowerType(CCTag.Blocks.BLOOD_BLOCKS, false));
//        add(CCBeesFlowerType.ICE, new TagFlowerType(CCTag.Blocks.ICE_BLOCKS, false));
//        add(CCBeesFlowerType.FIRE, new TagFlowerType(CCTag.Blocks.FIRE_BLOCKS, false));
//    }
//
//    private void add(ResourceLocation id, IFlowerType flowerType) {
//        this.pending.put(id, flowerType);
//    }
//}
