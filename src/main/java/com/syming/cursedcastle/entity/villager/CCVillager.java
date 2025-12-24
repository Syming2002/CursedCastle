package com.syming.cursedcastle.entity.villager;

import com.google.common.collect.ImmutableSet;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.tag.CCTag;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;
import java.util.stream.Collectors;

public class CCVillager {

    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, CursedCastleMod.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, CursedCastleMod.MOD_ID);

    public static final RegistryObject<PoiType> PRIEST_POI = POI_TYPES.register("priest_poi",
            () -> new PoiType(getAllCrucifixesFromTag(CCTag.Blocks.CRUCIFIX_BLOCKS),
                    1, 1));

    public static final RegistryObject<VillagerProfession> HIGH_PRIEST = VILLAGER_PROFESSIONS.register("high_priest", () -> new VillagerProfession("high_priest",
            poiTypeHolder -> poiTypeHolder.get() == PRIEST_POI.get(), poiTypeHolder -> poiTypeHolder.get() == PRIEST_POI.get(),
            ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_CLERIC));


    private static Set<BlockState> getAllCrucifixesFromTag(TagKey<Block> tag) {
        return BuiltInRegistries.BLOCK.getTag(tag)
                .map(blocks -> blocks.stream()
                        .flatMap(blockHolder -> blockHolder.value().getStateDefinition().getPossibleStates().stream())
                        .collect(Collectors.toSet()))
                .orElse(Set.of());
    }

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }

}
