package com.syming.cursedcastle.event;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.mob.friendly.FamiliarBatEntity;
import com.syming.cursedcastle.entity.mob.friendly.ZombieFriendlyEntity;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.network.CCPacketHandler;
import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.mob.BloodSkeletonEntity;
import com.syming.cursedcastle.entity.mob.VampireBatEntity;
import com.syming.cursedcastle.entity.mob.boss.dracula.DraculaEntity;
import com.syming.cursedcastle.entity.mob.shade.ShadeEntity;
import net.minecraft.client.ResourceLoadStateTracker;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

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
        event.put(CCEntity.DRACULA.get(), DraculaEntity.createMobAttributes().build());
        event.put(CCEntity.FAMILIAR_BAT.get(), FamiliarBatEntity.createMobAttributes().build());
        event.put(CCEntity.FROZEN_SHADE.get(), ShadeEntity.createMobAttributes().build());
        event.put(CCEntity.FRIENDLY_ZOMBIE.get(), ZombieFriendlyEntity.createMobAttributes().build());
        event.put(CCEntity.HEAT_SHADE.get(), ShadeEntity.createMobAttributes().build());
        event.put(CCEntity.POISON_SHADE.get(), ShadeEntity.createMobAttributes().build());
        event.put(CCEntity.THUNDER_SHADE.get(), ShadeEntity.createMobAttributes().build());
        event.put(CCEntity.VAMPIRE_BAT.get(), VampireBatEntity.createMobAttributes().build());
    }
}
