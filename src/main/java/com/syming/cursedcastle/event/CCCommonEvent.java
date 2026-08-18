package com.syming.cursedcastle.event;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.monster.MedusaSnakeEntity;
import com.syming.cursedcastle.entity.monster.arachne.ArachneEntity;
import com.syming.cursedcastle.entity.monster.HarpyEntity;
import com.syming.cursedcastle.entity.monster.bat.VampireBatEntity;
import com.syming.cursedcastle.entity.monster.bat.familiar.FamiliarBatEntity;
import com.syming.cursedcastle.entity.monster.boss.GrimReaperEntity;
import com.syming.cursedcastle.entity.monster.boss.MedusaEntity;
import com.syming.cursedcastle.entity.monster.boss.RusalkaEntity;
import com.syming.cursedcastle.entity.monster.boss.dracula.DraculaEntity;
import com.syming.cursedcastle.entity.monster.zombie.friendly.FriendlyZombieEntity;
import com.syming.cursedcastle.entity.monster.shade.ShadeEntity;
import com.syming.cursedcastle.entity.monster.skeleton.BloodSkeletonEntity;
import com.syming.cursedcastle.entity.monster.skeleton.ClearBoneSkeletonEntity;
import com.syming.cursedcastle.entity.monster.skeleton.GoldenSkeleton;
import com.syming.cursedcastle.entity.monster.skeleton.ThrowingBoneSkeletonEntity;
import com.syming.cursedcastle.entity.monster.zombie.CCZombieEntity;
import com.syming.cursedcastle.network.CCPacketHandler;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
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
        event.put(CCEntity.ARACHNE.get(), ArachneEntity.createArachneAttributes().build());
        event.put(CCEntity.BLOOD_SKELETON.get(), BloodSkeletonEntity.createMobAttributes().build());
        event.put(CCEntity.CLEAR_BONE_SKELETON.get(), ClearBoneSkeletonEntity.createMobAttributes().build());
        event.put(CCEntity.DRACULA.get(), DraculaEntity.createMobAttributes().build());
        event.put(CCEntity.FAMILIAR_BAT.get(), FamiliarBatEntity.createMobAttributes().build());
        event.put(CCEntity.FIRE_ZOMBIE.get(), CCZombieEntity.createZombieAttributes().build());
        event.put(CCEntity.FROST_ZOMBIE.get(), CCZombieEntity.createZombieAttributes().build());
        event.put(CCEntity.FROZEN_SHADE.get(), ShadeEntity.createMobAttributes().build());
        event.put(CCEntity.GOLDEN_SKELETON.get(), GoldenSkeleton.createMobAttributes().build());
        event.put(CCEntity.FRIENDLY_ZOMBIE.get(), FriendlyZombieEntity.createMobAttributes().build());
        event.put(CCEntity.GRIM_REAPER.get(), GrimReaperEntity.createMobAttributes().build());
        event.put(CCEntity.HARPY.get(), HarpyEntity.createHarpyAttributes().build());
        event.put(CCEntity.HEAT_SHADE.get(), ShadeEntity.createMobAttributes().build());
        event.put(CCEntity.MEDUSA.get(), MedusaEntity.createMedusaAttributes().build());
        event.put(CCEntity.MEDUSA_SNAKE.get(), MedusaSnakeEntity.createMedusaSnakeAttributes().build());
        event.put(CCEntity.POISON_SHADE.get(), ShadeEntity.createMobAttributes().build());
        event.put(CCEntity.RUSALKA.get(), RusalkaEntity.createMobAttributes().build());
        event.put(CCEntity.THROWING_BONE_SKELETON.get(), ThrowingBoneSkeletonEntity.createMobAttributes().build());
        event.put(CCEntity.THUNDER_SHADE.get(), ShadeEntity.createMobAttributes().build());
        event.put(CCEntity.VAMPIRE_BAT.get(), VampireBatEntity.createMobAttributes().build());
    }

    @SubscribeEvent
    public static void entitySpawnsPlacement(SpawnPlacementRegisterEvent event) {
        event.register(CCEntity.FROZEN_SHADE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ShadeEntity::checkShadeSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(CCEntity.POISON_SHADE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ShadeEntity::checkShadeSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}
