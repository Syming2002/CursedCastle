package com.syming.cursedcastle.event;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.mob.HarpyEntity;
import com.syming.cursedcastle.entity.mob.bat.VampireBatEntity;
import com.syming.cursedcastle.entity.mob.bat.familiar.FamiliarBatEntity;
import com.syming.cursedcastle.entity.mob.boss.GrimReaperEntity;
import com.syming.cursedcastle.entity.mob.boss.MedusaEntity;
import com.syming.cursedcastle.entity.mob.boss.RusalkaEntity;
import com.syming.cursedcastle.entity.mob.boss.dracula.DraculaEntity;
import com.syming.cursedcastle.entity.mob.friendly.ZombieFriendlyEntity;
import com.syming.cursedcastle.entity.mob.shade.ShadeEntity;
import com.syming.cursedcastle.entity.mob.skeleton.BloodSkeletonEntity;
import com.syming.cursedcastle.entity.mob.skeleton.ClearBoneSkeletonEntity;
import com.syming.cursedcastle.entity.mob.skeleton.GoldenSkeleton;
import com.syming.cursedcastle.entity.mob.skeleton.ThrowingBoneSkeletonEntity;
import com.syming.cursedcastle.entity.mob.zombie.CCZombieEntity;
import com.syming.cursedcastle.entity.villager.CCVillager;
import com.syming.cursedcastle.mp.PlayerMP;
import com.syming.cursedcastle.mp.PlayerMPProvider;
import com.syming.cursedcastle.network.CCPacketHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegistryObject;
import org.spongepowered.asm.mixin.MixinEnvironment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        event.put(CCEntity.DRACULA.get(), DraculaEntity.createMobAttributes().build());
        event.put(CCEntity.FAMILIAR_BAT.get(), FamiliarBatEntity.createMobAttributes().build());
        event.put(CCEntity.FIRE_ZOMBIE.get(), CCZombieEntity.createZombieAttributes().build());
        event.put(CCEntity.FROST_ZOMBIE.get(), CCZombieEntity.createZombieAttributes().build());
        event.put(CCEntity.FROZEN_SHADE.get(), ShadeEntity.createMobAttributes().build());
        event.put(CCEntity.GOLDEN_SKELETON.get(), GoldenSkeleton.createMobAttributes().build());
        event.put(CCEntity.FRIENDLY_ZOMBIE.get(), ZombieFriendlyEntity.createMobAttributes().build());
        event.put(CCEntity.GRIM_REAPER.get(), GrimReaperEntity.createMobAttributes().build());
        event.put(CCEntity.HARPY.get(), HarpyEntity.createHarpyAttributes().build());
        event.put(CCEntity.HEAT_SHADE.get(), ShadeEntity.createMobAttributes().build());
        event.put(CCEntity.MEDUSA.get(), MedusaEntity.createMobAttributes().build());
        event.put(CCEntity.POISON_SHADE.get(), ShadeEntity.createMobAttributes().build());
        event.put(CCEntity.RUSALKA.get(), RusalkaEntity.createMobAttributes().build());
        event.put(CCEntity.THROWING_BONE_SKELETON.get(), ThrowingBoneSkeletonEntity.createMobAttributes().build());
        event.put(CCEntity.THUNDER_SHADE.get(), ShadeEntity.createMobAttributes().build());
        event.put(CCEntity.VAMPIRE_BAT.get(), VampireBatEntity.createMobAttributes().build());
    }
}
