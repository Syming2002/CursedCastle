package com.syming.cursedcastle.entity.villager;

import com.google.common.collect.ImmutableSet;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.block.ChurchAltarBlock;
import com.syming.cursedcastle.block.CrucifixBlock;
import com.syming.cursedcastle.block.TripleBlockPart;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.Set;

public class CCVillager {

    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, CursedCastleMod.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, CursedCastleMod.MOD_ID);


    public static final RegistryObject<PoiType> HIGH_PRIEST_POI = POI_TYPES.register("high_priest_poi", () -> {
        return new PoiType(ImmutableSet.copyOf(CCBlock.CHURCH_ALTAR.get()
                .getStateDefinition()
                .getPossibleStates()
                .stream()
                .filter(blockState -> blockState.getValue(ChurchAltarBlock.PART) == TripleBlockPart.MIDDLE)
                .toList()),
                1,
                1);
    });

    public static final RegistryObject<PoiType> LEADER_PRIEST_POI = POI_TYPES.register("leader_priest_poi", () -> {
        Set<BlockState> states = new HashSet<>();
        CCBlock.CRUCIFIX_BLOCK_TYPES.values().forEach(
                blockRegistryObject -> {
                    states.addAll(blockRegistryObject.get().getStateDefinition()
                            .getPossibleStates()
                            .stream()
                            .filter(blockState -> blockState.getValue(CrucifixBlock.HALF) == DoubleBlockHalf.LOWER || blockState.getValue(CrucifixBlock.HALF) == DoubleBlockHalf.UPPER)
                            .toList());
                }
        );
        return new PoiType(states, 2, 2);
    });

    public static final RegistryObject<VillagerProfession> HIGH_PRIEST =
            VILLAGER_PROFESSIONS.register("high_priest",
                    () -> new VillagerProfession(
                            "high_priest",
                            holder -> holder.get() == HIGH_PRIEST_POI.get(),
                            holder -> holder.get() == HIGH_PRIEST_POI.get(),
                            ImmutableSet.of(),
                            ImmutableSet.of(),
                            SoundEvents.VILLAGER_WORK_CLERIC
                    ));

    public static final RegistryObject<VillagerProfession> LEADER_PRIEST =
            VILLAGER_PROFESSIONS.register("leader_priest",
                    () -> new VillagerProfession(
                            "leader_priest",
                            holder -> holder.get() == LEADER_PRIEST_POI.get(),
                            holder -> holder.get() == LEADER_PRIEST_POI.get(),
                            ImmutableSet.of(),
                            ImmutableSet.of(),
                            SoundEvents.VILLAGER_WORK_CLERIC
                    ));

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }

}
