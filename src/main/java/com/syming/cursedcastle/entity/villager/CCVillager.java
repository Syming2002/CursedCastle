package com.syming.cursedcastle.entity.villager;

import com.google.common.collect.ImmutableSet;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.block.ChurchAltarBlock;
import com.syming.cursedcastle.block.CrucifixBlock;
import com.syming.cursedcastle.block.TripleBlockPart;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashSet;
import java.util.Set;

public class CCVillager {

    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, CursedCastleMod.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(BuiltInRegistries.VILLAGER_PROFESSION, CursedCastleMod.MOD_ID);


    public static final Holder<PoiType> HIGH_PRIEST_POI = POI_TYPES.register("high_priest_poi", () -> {
        return new PoiType(ImmutableSet.copyOf(CCBlock.CHURCH_ALTAR.get()
                .getStateDefinition()
                .getPossibleStates()
                .stream()
                .filter(blockState -> blockState.getValue(ChurchAltarBlock.PART) == TripleBlockPart.MIDDLE)
                .toList()),
                1,
                1);
    });

    public static final Holder<PoiType> LEADER_PRIEST_POI = POI_TYPES.register("leader_priest_poi", () -> {
        Set<BlockState> states = new HashSet<>();
        CCBlock.CRUCIFIX_BLOCK_TYPES.values().forEach(
                blockResourceKey -> {
                    states.addAll(blockResourceKey.get().getStateDefinition()
                            .getPossibleStates()
                            .stream()
                            .filter(blockState -> blockState.getValue(CrucifixBlock.HALF) == DoubleBlockHalf.LOWER || blockState.getValue(CrucifixBlock.HALF) == DoubleBlockHalf.UPPER)
                            .toList());
                }
        );
        return new PoiType(states, 2, 2);
    });

    public static final Holder<VillagerProfession> HIGH_PRIEST =
            VILLAGER_PROFESSIONS.register("high_priest",
                    () -> new VillagerProfession(
                            "high_priest",
                            holder -> holder == HIGH_PRIEST_POI,
                            holder -> holder == HIGH_PRIEST_POI,
                            ImmutableSet.of(),
                            ImmutableSet.of(),
                            SoundEvents.VILLAGER_WORK_CLERIC
                    ));

    public static final Holder<VillagerProfession> LEADER_PRIEST =
            VILLAGER_PROFESSIONS.register("leader_priest",
                    () -> new VillagerProfession(
                            "leader_priest",
                            holder -> holder == LEADER_PRIEST_POI,
                            holder -> holder == LEADER_PRIEST_POI,
                            ImmutableSet.of(),
                            ImmutableSet.of(),
                            SoundEvents.VILLAGER_WORK_CLERIC
                    ));

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
