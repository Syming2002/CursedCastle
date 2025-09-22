package com.syming.cursedcastle.entity;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.magic.*;
import com.syming.cursedcastle.entity.mob.skeleton.ThrowingBoneSkeletonEntity;
import com.syming.cursedcastle.entity.mob.bat.familiar.FamiliarBatEntity;
import com.syming.cursedcastle.entity.mob.friendly.ZombieFriendlyEntity;
import com.syming.cursedcastle.entity.projectile.ThrowingBoneEntity;
import com.syming.cursedcastle.entity.projectile.subweapon.*;
import com.syming.cursedcastle.entity.mob.skeleton.BloodSkeletonEntity;
import com.syming.cursedcastle.entity.mob.bat.VampireBatEntity;
import com.syming.cursedcastle.entity.mob.boss.dracula.DraculaEntity;
import com.syming.cursedcastle.entity.mob.shade.FrozenShadeEntity;
import com.syming.cursedcastle.entity.mob.shade.HeatShadeEntity;
import com.syming.cursedcastle.entity.mob.shade.PoisonShadeEntity;
import com.syming.cursedcastle.entity.mob.shade.ThunderShadeEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CCEntity {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CursedCastleMod.MOD_ID);

    // Entity Living
    public static final RegistryObject<EntityType<BloodSkeletonEntity>> BLOOD_SKELETON = ENTITY_TYPES.register("blood_skeleton", () -> EntityType.Builder.<BloodSkeletonEntity>of(BloodSkeletonEntity::new, MobCategory.MONSTER)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("blood_skeleton"));

    public static final RegistryObject<EntityType<DraculaEntity>> DRACULA = ENTITY_TYPES.register("dracula", () -> EntityType.Builder.<DraculaEntity>of(DraculaEntity::new, MobCategory.MONSTER)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("dracula"));

    public static final RegistryObject<EntityType<FamiliarBatEntity>> FAMILIAR_BAT = ENTITY_TYPES.register("familiar_bat", () -> EntityType.Builder.<FamiliarBatEntity>of(FamiliarBatEntity::new, MobCategory.MONSTER)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("familiar_bat"));

    public static final RegistryObject<EntityType<ZombieFriendlyEntity>> FRIENDLY_ZOMBIE = ENTITY_TYPES.register("friendly_zombie", () -> EntityType.Builder.<ZombieFriendlyEntity>of(ZombieFriendlyEntity::new, MobCategory.CREATURE)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("friendly_zombie"));

    public static final RegistryObject<EntityType<FrozenShadeEntity>> FROZEN_SHADE = ENTITY_TYPES.register("frozen_shade", () -> EntityType.Builder.<FrozenShadeEntity>of(FrozenShadeEntity::new, MobCategory.MONSTER)
            .sized(0.5F, 1.875F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("frozen_shade"));

    public static final RegistryObject<EntityType<HeatShadeEntity>> HEAT_SHADE = ENTITY_TYPES.register("heat_shade", () -> EntityType.Builder.<HeatShadeEntity>of(HeatShadeEntity::new, MobCategory.MONSTER)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("heat_shade"));

    public static final RegistryObject<EntityType<PoisonShadeEntity>> POISON_SHADE = ENTITY_TYPES.register("poison_shade", () -> EntityType.Builder.<PoisonShadeEntity>of(PoisonShadeEntity::new, MobCategory.MONSTER)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("poison_shade"));

    public static final RegistryObject<EntityType<ThrowingBoneSkeletonEntity>> THROWING_BONE_SKELETON = ENTITY_TYPES.register("throwing_bone_skeleton", () -> EntityType.Builder.<ThrowingBoneSkeletonEntity>of(ThrowingBoneSkeletonEntity::new, MobCategory.MONSTER)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("throwing_bone_skeleton"));

    public static final RegistryObject<EntityType<ThunderShadeEntity>> THUNDER_SHADE = ENTITY_TYPES.register("thunder_shade", () -> EntityType.Builder.<ThunderShadeEntity>of(ThunderShadeEntity::new, MobCategory.MONSTER)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("thunder_shade"));

    public static final RegistryObject<EntityType<VampireBatEntity>> VAMPIRE_BAT = ENTITY_TYPES.register("vampire_bat", () -> EntityType.Builder.<VampireBatEntity>of(VampireBatEntity::new, MobCategory.MONSTER)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("vampire_bat"));

    // Magic Effect Entity

    public static final RegistryObject<EntityType<FireBreathEntity>> FIRE_BREATH = ENTITY_TYPES.register("fire_breath", () -> EntityType.Builder.<FireBreathEntity>of(FireBreathEntity::new, MobCategory.MISC)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("fire_breath"));

    public static final RegistryObject<EntityType<FirePillarEntity>> FIRE_PILLAR = ENTITY_TYPES.register("fire_pillar", () -> EntityType.Builder.<FirePillarEntity>of(FirePillarEntity::new, MobCategory.MISC)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("fire_pillar"));

    public static final RegistryObject<EntityType<IceBreathEntity>> ICE_BREATH = ENTITY_TYPES.register("ice_breath", () -> EntityType.Builder.<IceBreathEntity>of(IceBreathEntity::new, MobCategory.MISC)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("ice_breath"));

    public static final RegistryObject<EntityType<IceSpikeEntity>> ICE_SPIKE = ENTITY_TYPES.register("ice_spike", () -> EntityType.Builder.<IceSpikeEntity>of(IceSpikeEntity::new, MobCategory.MISC)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("ice_spike"));

    public static final RegistryObject<EntityType<PoisonBreathEntity>> POISON_BREATH = ENTITY_TYPES.register("poison_breath", () -> EntityType.Builder.<PoisonBreathEntity>of(PoisonBreathEntity::new, MobCategory.MISC)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("poison_breath"));

    public static final RegistryObject<EntityType<ThunderBreathEntity>> THUNDER_BREATH = ENTITY_TYPES.register("thunder_breath", () -> EntityType.Builder.<ThunderBreathEntity>of(ThunderBreathEntity::new, MobCategory.MISC)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("thunder_breath"));

    // Throwable
    public static final Map<Tier, RegistryObject<EntityType<DaggerEntity>>> DAGGER_TYPES = new HashMap<>();

    public static final RegistryObject<EntityType<HolyBibleEntity>> HOLY_BIBLE = ENTITY_TYPES.register("holy_bible",
            () -> EntityType.Builder.<HolyBibleEntity>of(HolyBibleEntity::new, MobCategory.MISC)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .setCustomClientFactory(((spawnEntity, level) -> new HolyBibleEntity(level)))
                    .build("holy_bible"));

    public static final Map<Tier, RegistryObject<EntityType<HolyCrossEntity>>> HOLY_CROSS_TYPES = new HashMap<>();

    public static final RegistryObject<EntityType<HolyWaterEntity>> HOLY_WATER = ENTITY_TYPES.register("holy_water",
            () -> EntityType.Builder.<HolyWaterEntity>of(HolyWaterEntity::new, MobCategory.MISC)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .setCustomClientFactory(((spawnEntity, level) -> new HolyWaterEntity(level)))
                    .build("holy_water"));

    public static final Map<Tier, RegistryObject<EntityType<ThrowingAxeEntity>>> THROWING_AXE_TYPES = new HashMap<>();

    public static final RegistryObject<EntityType<ThrowingBoneEntity>> THROWING_BONE = ENTITY_TYPES.register("throwing_bone",
            () -> EntityType.Builder.<ThrowingBoneEntity>of(ThrowingBoneEntity::new, MobCategory.MISC)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .setCustomClientFactory(((spawnEntity, level) -> new ThrowingBoneEntity(level)))
                    .build("throwing_bone"));

    static {
        for (Tiers tier : Tiers.values()) {
            DAGGER_TYPES.put(tier, ENTITY_TYPES.register(tier.name().toLowerCase(Locale.ROOT) + '_' + "dagger",
                    () -> EntityType.Builder.<DaggerEntity>of(
                                    ((entityType, level) -> new DaggerEntity(level, tier)),
                                    MobCategory.MISC)
                            .sized(0.5F, 0.5F)
                            .clientTrackingRange(4)
                            .updateInterval(20)
                            .setCustomClientFactory(((spawnEntity, level) -> new DaggerEntity(level, tier)))
                            .build(tier.name().toLowerCase(Locale.ROOT) + '_' + "dagger"))
            );
        }
    }

    static {
        for (Tiers tier : Tiers.values()) {
            HOLY_CROSS_TYPES.put(tier, ENTITY_TYPES.register(tier.name().toLowerCase(Locale.ROOT) + '_' + "holy_cross",
                    () -> EntityType.Builder.<HolyCrossEntity>of(
                                    ((entityType, level) -> new HolyCrossEntity(level, tier)),
                                    MobCategory.MISC)
                            .sized(0.5F, 0.5F)
                            .clientTrackingRange(4)
                            .updateInterval(20)
                            .setCustomClientFactory(((spawnEntity, level) -> new HolyCrossEntity(level, tier)))
                            .build(tier.name().toLowerCase(Locale.ROOT) + '_' + "holy_cross"))
            );
        }
    }

    static {
        for (Tiers tier : Tiers.values()) {
            THROWING_AXE_TYPES.put(tier, ENTITY_TYPES.register(tier.name().toLowerCase(Locale.ROOT) + '_' + "throwing_axe",
                    () -> EntityType.Builder.<ThrowingAxeEntity>of(
                                    ((entityType, level) -> new ThrowingAxeEntity(level, tier)),
                                    MobCategory.MISC)
                            .sized(0.5F, 0.5F)
                            .clientTrackingRange(4)
                            .updateInterval(20)
                            .setCustomClientFactory((spawnEntity, level) -> new ThrowingAxeEntity(level, tier))
                            .build(tier.name().toLowerCase(Locale.ROOT) + '_' + "throwing_axe"))
            );
        }
    }

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
