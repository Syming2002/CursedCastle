package com.syming.cursedcastle.world.village;

import com.mojang.datafixers.util.Pair;
import com.syming.cursedcastle.CursedCastleMod;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = CursedCastleMod.MOD_ID)
public class CCVillage {

    private static final ResourceKey<StructureProcessorList> BIG_CHURCH_LIST_KEY = ResourceKey.create(Registries.PROCESSOR_LIST, new ResourceLocation(CursedCastleMod.MOD_ID, "big_church"));

    private static void addBuildingToPool(Registry<StructureTemplatePool> templatePoolRegistry,
                                          Registry<StructureProcessorList> processorListRegistry,
                                          ResourceLocation poolRL,
                                          String nbtPieceRL) {


        Holder<StructureProcessorList> bigChurchProcessorList = processorListRegistry.getHolderOrThrow(BIG_CHURCH_LIST_KEY);

        StructureTemplatePool pool = templatePoolRegistry.get(poolRL);
        if (pool == null) return;

        SinglePoolElement piece = SinglePoolElement.legacy(nbtPieceRL,
                bigChurchProcessorList).apply(StructureTemplatePool.Projection.RIGID);


        pool.templates.add(piece);
        List<Pair<StructurePoolElement, Integer>> listOfPieceEntries = new ArrayList<>(pool.rawTemplates);
        listOfPieceEntries.add(new Pair<>(piece, 1));
        pool.rawTemplates = listOfPieceEntries;

    }

    /**
     * We use FMLServerAboutToStartEvent as the dynamic registry exists now and all JSON worldgen files were parsed.
     * Mod compat is best done here.
     */
    @SubscribeEvent
    public static void addNewVillageBuilding(final ServerAboutToStartEvent event) {
        addBigChurchBuilding(event, "minecraft");
        addBigChurchBuilding(event, "repurposed_structures");
    }


    private static void addBigChurchBuilding(ServerAboutToStartEvent event, String modId) {
        Registry<Biome> biomeRegistry = event.getServer().registryAccess().registryOrThrow(Registries.BIOME);
        Registry<StructureTemplatePool> templatePoolRegistry = event.getServer().registryAccess().registry(Registries.TEMPLATE_POOL).orElseThrow();
        Registry<StructureProcessorList> processorListRegistry = event.getServer().registryAccess().registry(Registries.PROCESSOR_LIST).orElseThrow();

        for (Holder.Reference<Biome> holder : biomeRegistry.holders().toList()) {
            ResourceKey<Biome> key = holder.key();
            ResourceLocation id = key.location();

            if (id.getNamespace().equals("minecraft")) {
                if (modId.equals("minecraft")) {
                    addBuildingToPool(templatePoolRegistry, processorListRegistry, id.withPath("village/" + id.getPath() + "/streets"), CursedCastleMod.MOD_ID + ":village/" + id.getPath() + "/big_church");
                } else if (modId.equals("repurposed_structures")) {
                    if (id.getPath().equals("bamboo_jungle")) {
                        addBuildingToPool(templatePoolRegistry, processorListRegistry, new ResourceLocation("repurposed_structures:villages/bamboo/streets"), CursedCastleMod.MOD_ID + ":village/" + id.getPath() + "/big_church");
                    } else if (id.getPath().equals("cherry_grove")) {
                        addBuildingToPool(templatePoolRegistry, processorListRegistry, new ResourceLocation("repurposed_structures:villages/cherry/streets"), CursedCastleMod.MOD_ID + ":village/" + id.getPath() + "/big_church");
                    } else {
                        addBuildingToPool(templatePoolRegistry, processorListRegistry, new ResourceLocation("repurposed_structures:villages/" + id.getPath() + "/streets"), CursedCastleMod.MOD_ID + ":village/" + id.getPath() + "/big_church");
                    }
                }
            }
        }
    }
}
