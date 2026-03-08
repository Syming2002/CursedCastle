package com.syming.cursedcastle.entity.villager;

import com.google.common.collect.ImmutableSet;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.tag.CCTag;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CCVillager {

    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, CursedCastleMod.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, CursedCastleMod.MOD_ID);

    public static final RegistryObject<PoiType> PRIEST_POI = POI_TYPES.register("priest_poi", () -> {
        Set<BlockState> states = new HashSet<>();
        CCBlock.CRUCIFIX_BLOCK_TYPES.values().forEach(
                blockRegistryObject -> {
                    states.addAll(blockRegistryObject.get().getStateDefinition().getPossibleStates());
                }
        );
        return new PoiType(states, 1, 1);
    });

    public static final RegistryObject<VillagerProfession> HIGH_PRIEST =
            VILLAGER_PROFESSIONS.register("high_priest",
                    () -> new VillagerProfession(
                            "high_priest",
                            holder -> holder.get().equals(PRIEST_POI.get()),
                            holder -> holder.get().equals(PRIEST_POI.get()),
                            ImmutableSet.of(),
                            ImmutableSet.of(),
                            SoundEvents.VILLAGER_WORK_CLERIC
                    ));

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }

}
