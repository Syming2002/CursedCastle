package com.syming.cursedcastle.event;

import com.mojang.datafixers.util.Pair;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.mob.HarpyEntity;
import com.syming.cursedcastle.entity.mob.bat.familiar.FamiliarBatEntity;
import com.syming.cursedcastle.entity.mob.boss.GrimReaperEntity;
import com.syming.cursedcastle.entity.mob.boss.MedusaEntity;
import com.syming.cursedcastle.entity.mob.friendly.ZombieFriendlyEntity;
import com.syming.cursedcastle.entity.mob.skeleton.ClearBoneSkeletonEntity;
import com.syming.cursedcastle.entity.mob.skeleton.GoldenSkeleton;
import com.syming.cursedcastle.entity.mob.skeleton.ThrowingBoneSkeletonEntity;
import com.syming.cursedcastle.network.CCPacketHandler;
import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.mob.skeleton.BloodSkeletonEntity;
import com.syming.cursedcastle.entity.mob.bat.VampireBatEntity;
import com.syming.cursedcastle.entity.mob.boss.dracula.DraculaEntity;
import com.syming.cursedcastle.entity.mob.shade.ShadeEntity;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = CursedCastleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CCCommonEvent {


    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            CCPacketHandler.register();
        });
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(CCEntity.BLOOD_SKELETON.get(), BloodSkeletonEntity.createMobAttributes().build());
        event.put(CCEntity.CLEAR_BONE_SKELETON.get(), ClearBoneSkeletonEntity.createMobAttributes().build());

//        Map<WoodType, AttributeSupplier> CRUCIFIX_ATTRIBUTES_TYPE = new HashMap<>();
//
//        AttributeSupplier attributes = LivingEntity.createLivingAttributes().build();
//
//        for (WoodType woodType : WoodType.values().toList()) {
//
//            EntityType<? extends LivingEntity> type = CCEntity.CRUCIFIX_WOOD_TYPE.get(woodType).get();
//
//            event.put(type, attributes);
//
//            CRUCIFIX_ATTRIBUTES_TYPE.put(woodType, attributes);
//        }

        event.put(CCEntity.DRACULA.get(), DraculaEntity.createMobAttributes().build());
        event.put(CCEntity.FAMILIAR_BAT.get(), FamiliarBatEntity.createMobAttributes().build());
        event.put(CCEntity.FROZEN_SHADE.get(), ShadeEntity.createMobAttributes().build());
        event.put(CCEntity.GOLDEN_SKELETON.get(), GoldenSkeleton.createMobAttributes().build());
        event.put(CCEntity.FRIENDLY_ZOMBIE.get(), ZombieFriendlyEntity.createMobAttributes().build());
        event.put(CCEntity.GRIM_REAPER.get(), GrimReaperEntity.createMobAttributes().build());
        event.put(CCEntity.HARPY.get(), HarpyEntity.createHarpyAttributes().build());
        event.put(CCEntity.HEAT_SHADE.get(), ShadeEntity.createMobAttributes().build());
        event.put(CCEntity.MEDUSA.get(), MedusaEntity.createMobAttributes().build());
        event.put(CCEntity.POISON_SHADE.get(), ShadeEntity.createMobAttributes().build());
        event.put(CCEntity.THROWING_BONE_SKELETON.get(), ThrowingBoneSkeletonEntity.createMobAttributes().build());
        event.put(CCEntity.THUNDER_SHADE.get(), ShadeEntity.createMobAttributes().build());
        event.put(CCEntity.VAMPIRE_BAT.get(), VampireBatEntity.createMobAttributes().build());
    }
}
