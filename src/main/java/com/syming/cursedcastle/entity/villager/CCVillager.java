package com.syming.cursedcastle.entity.villager;

import com.google.common.collect.ImmutableSet;
import com.syming.cursedcastle.CursedCastleMod;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CCVillager {

    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, CursedCastleMod.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, CursedCastleMod.MOD_ID);

    public static final RegistryObject<PoiType> PRIEST_POI = POI_TYPES.register("priest_poi",
            () -> new PoiType(ImmutableSet.copyOf(Blocks.DIAMOND_BLOCK.getStateDefinition().getPossibleStates()),
                    1, 1));

    public static final RegistryObject<VillagerProfession> HIGH_PRIEST = VILLAGER_PROFESSIONS.register("high_priest", () -> new VillagerProfession("high_priest",
            poiTypeHolder -> poiTypeHolder.get() == PRIEST_POI.get(), poiTypeHolder -> poiTypeHolder.get() == PRIEST_POI.get(),
            ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_CLERIC));


    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }

}
