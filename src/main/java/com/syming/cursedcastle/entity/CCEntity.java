package com.syming.cursedcastle.entity;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.magic.*;
import com.syming.cursedcastle.entity.monster.MedusaSnakeEntity;
import com.syming.cursedcastle.entity.monster.arachne.ArachneEntity;
import com.syming.cursedcastle.entity.monster.HarpyEntity;
import com.syming.cursedcastle.entity.monster.boss.GrimReaperEntity;
import com.syming.cursedcastle.entity.monster.boss.MedusaEntity;
import com.syming.cursedcastle.entity.monster.boss.RusalkaEntity;
import com.syming.cursedcastle.entity.monster.skeleton.ClearBoneSkeletonEntity;
import com.syming.cursedcastle.entity.monster.skeleton.GoldenSkeletonEntity;
import com.syming.cursedcastle.entity.monster.skeleton.ThrowingBoneSkeletonEntity;
import com.syming.cursedcastle.entity.monster.bat.familiar.FamiliarBatEntity;
import com.syming.cursedcastle.entity.monster.zombie.friendly.FriendlyZombieEntity;
import com.syming.cursedcastle.entity.monster.zombie.FireZombieEntity;
import com.syming.cursedcastle.entity.monster.zombie.FrostZombieEntity;
import com.syming.cursedcastle.entity.projectile.*;
import com.syming.cursedcastle.entity.projectile.subweapon.*;
import com.syming.cursedcastle.entity.monster.skeleton.BloodSkeletonEntity;
import com.syming.cursedcastle.entity.monster.bat.VampireBatEntity;
import com.syming.cursedcastle.entity.monster.boss.dracula.DraculaEntity;
import com.syming.cursedcastle.entity.monster.shade.FrozenShadeEntity;
import com.syming.cursedcastle.entity.monster.shade.HeatShadeEntity;
import com.syming.cursedcastle.entity.monster.shade.PoisonShadeEntity;
import com.syming.cursedcastle.entity.monster.shade.ThunderShadeEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

public class CCEntity {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, CursedCastleMod.MOD_ID);

    // Entity Living
    public static final Supplier<EntityType<ArachneEntity>> ARACHNE = ENTITY_TYPES.register("arachne", () -> EntityType.Builder.<ArachneEntity>of(ArachneEntity::new, MobCategory.MONSTER)
            .sized(0.625F, 1.25F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("arachne"));

    public static final Supplier<EntityType<BloodSkeletonEntity>> BLOOD_SKELETON = ENTITY_TYPES.register("blood_skeleton", () -> EntityType.Builder.<BloodSkeletonEntity>of(BloodSkeletonEntity::new, MobCategory.MONSTER)
            .sized(0.6F, 1.99F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("blood_skeleton"));

    public static final Supplier<EntityType<ClearBoneSkeletonEntity>> CLEAR_BONE_SKELETON = ENTITY_TYPES.register("clear_bone_skeleton", () -> EntityType.Builder.<ClearBoneSkeletonEntity>of(ClearBoneSkeletonEntity::new, MobCategory.MONSTER)
            .sized(0.5F, 1.99F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("clear_bone_skeleton"));

    public static final Supplier<EntityType<DraculaEntity>> DRACULA = ENTITY_TYPES.register("dracula", () -> EntityType.Builder.<DraculaEntity>of(DraculaEntity::new, MobCategory.MONSTER)
            .sized(0.6F, 2.5F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("dracula"));

    public static final Supplier<EntityType<FamiliarBatEntity>> FAMILIAR_BAT = ENTITY_TYPES.register("familiar_bat", () -> EntityType.Builder.<FamiliarBatEntity>of(FamiliarBatEntity::new, MobCategory.MONSTER)
            .sized(0.25F, 0.20625F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("familiar_bat"));

    public static final Supplier<EntityType<FireZombieEntity>> FIRE_ZOMBIE = ENTITY_TYPES.register("fire_zombie", () -> EntityType.Builder.<FireZombieEntity>of(FireZombieEntity::new, MobCategory.MONSTER)
            .sized(0.5F, 1.99F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("fire_zombie"));

    public static final Supplier<EntityType<FriendlyZombieEntity>> FRIENDLY_ZOMBIE = ENTITY_TYPES.register("friendly_zombie", () -> EntityType.Builder.<FriendlyZombieEntity>of(FriendlyZombieEntity::new, MobCategory.CREATURE)
            .sized(0.6F, 1.95F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("friendly_zombie"));

    public static final Supplier<EntityType<FrostZombieEntity>> FROST_ZOMBIE = ENTITY_TYPES.register("frost_zombie", () -> EntityType.Builder.<FrostZombieEntity>of(FrostZombieEntity::new, MobCategory.MONSTER)
            .sized(0.5F, 1.99F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("frost_zombie"));

    public static final Supplier<EntityType<FrozenShadeEntity>> FROZEN_SHADE = ENTITY_TYPES.register("frozen_shade", () -> EntityType.Builder.<FrozenShadeEntity>of(FrozenShadeEntity::new, MobCategory.MONSTER)
            .sized(0.5F, 1.875F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("frozen_shade"));

    public static final Supplier<EntityType<GoldenSkeletonEntity>> GOLDEN_SKELETON = ENTITY_TYPES.register("golden_skeleton", () -> EntityType.Builder.<GoldenSkeletonEntity>of(GoldenSkeletonEntity::new, MobCategory.MONSTER)
            .sized(0.5F, 1.99F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("golden_skeleton"));

    public static final Supplier<EntityType<GrimReaperEntity>> GRIM_REAPER = ENTITY_TYPES.register("grim_reaper", () -> EntityType.Builder.<GrimReaperEntity>of(GrimReaperEntity::new, MobCategory.MONSTER)
            .sized(0.5F, 1.99F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("grim_reaper"));

    public static final Supplier<EntityType<HarpyEntity>> HARPY = ENTITY_TYPES.register("harpy", () -> EntityType.Builder.<HarpyEntity>of(HarpyEntity::new, MobCategory.MONSTER)
            .sized(0.6875F, 2.625F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("harpy"));

    public static final Supplier<EntityType<HeatShadeEntity>> HEAT_SHADE = ENTITY_TYPES.register("heat_shade", () -> EntityType.Builder.<HeatShadeEntity>of(HeatShadeEntity::new, MobCategory.MONSTER)
            .sized(0.5F, 1.875F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("heat_shade"));

    public static final Supplier<EntityType<MedusaEntity>> MEDUSA = ENTITY_TYPES.register("medusa", () -> EntityType.Builder.<MedusaEntity>of(MedusaEntity::new, MobCategory.MONSTER)
            .sized(0.5F, 1.75F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("medusa"));

    public static final Supplier<EntityType<MedusaSnakeEntity>> MEDUSA_SNAKE = ENTITY_TYPES.register("medusa_snake", () -> EntityType.Builder.<MedusaSnakeEntity>of(MedusaSnakeEntity::new, MobCategory.MONSTER)
            .sized(0.125F, 0.0625F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("medusa_snake"));

    public static final Supplier<EntityType<PoisonShadeEntity>> POISON_SHADE = ENTITY_TYPES.register("poison_shade", () -> EntityType.Builder.<PoisonShadeEntity>of(PoisonShadeEntity::new, MobCategory.MONSTER)
            .sized(0.5F, 1.875F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("poison_shade"));

    public static final Supplier<EntityType<RusalkaEntity>> RUSALKA = ENTITY_TYPES.register("rusalka", () -> EntityType.Builder.<RusalkaEntity>of(RusalkaEntity::new, MobCategory.MONSTER)
            .sized(0.5F, 1.99F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("rusalka"));

    public static final Supplier<EntityType<ThrowingBoneSkeletonEntity>> THROWING_BONE_SKELETON = ENTITY_TYPES.register("throwing_bone_skeleton", () -> EntityType.Builder.<ThrowingBoneSkeletonEntity>of(ThrowingBoneSkeletonEntity::new, MobCategory.MONSTER)
            .sized(0.5F, 1.99F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("throwing_bone_skeleton"));

    public static final Supplier<EntityType<ThunderShadeEntity>> THUNDER_SHADE = ENTITY_TYPES.register("thunder_shade", () -> EntityType.Builder.<ThunderShadeEntity>of(ThunderShadeEntity::new, MobCategory.MONSTER)
            .sized(0.5F, 1.875F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("thunder_shade"));

    public static final Supplier<EntityType<VampireBatEntity>> VAMPIRE_BAT = ENTITY_TYPES.register("vampire_bat", () -> EntityType.Builder.<VampireBatEntity>of(VampireBatEntity::new, MobCategory.MONSTER)
            .sized(0.25F, 0.20625F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("vampire_bat"));

    // Magic Effect Entity

    public static final Supplier<EntityType<CobwebProjectileEntity>> COBWEB_PROJECTILE = ENTITY_TYPES.register("cobweb_projectile", () -> EntityType.Builder.<CobwebProjectileEntity>of(CobwebProjectileEntity::new, MobCategory.MISC)
            .sized(0.5F, 0.5F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .clientTrackingRange(4)
            .build("cobweb_projectile"));

    public static final Supplier<EntityType<FireBreathEntity>> FIRE_BREATH = ENTITY_TYPES.register("fire_breath", () -> EntityType.Builder.<FireBreathEntity>of(FireBreathEntity::new, MobCategory.MISC)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("fire_breath"));

    public static final Supplier<EntityType<FirePillarEntity>> FIRE_PILLAR = ENTITY_TYPES.register("fire_pillar", () -> EntityType.Builder.<FirePillarEntity>of(FirePillarEntity::new, MobCategory.MISC)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("fire_pillar"));

    public static final Supplier<EntityType<HarpyFeatherEntity>> HARPY_FEATHER = ENTITY_TYPES.register("harpy_feather", () -> EntityType.Builder.<HarpyFeatherEntity>of(HarpyFeatherEntity::new, MobCategory.MISC)
            .sized(0.5F, 0.5F)
            .setTrackingRange(64)
            .clientTrackingRange(4)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("harpy_feather"));

    public static final Supplier<EntityType<IceBreathEntity>> ICE_BREATH = ENTITY_TYPES.register("ice_breath", () -> EntityType.Builder.<IceBreathEntity>of(IceBreathEntity::new, MobCategory.MISC)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("ice_breath"));

    public static final Supplier<EntityType<IceSpikeEntity>> ICE_SPIKE = ENTITY_TYPES.register("ice_spike", () -> EntityType.Builder.<IceSpikeEntity>of(IceSpikeEntity::new, MobCategory.MISC)
            .sized(0.5F, 0.5F)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .clientTrackingRange(4)
            .updateInterval(1)
            .build("ice_spike"));

    public static final Supplier<EntityType<PoisonBreathEntity>> POISON_BREATH = ENTITY_TYPES.register("poison_breath", () -> EntityType.Builder.<PoisonBreathEntity>of(PoisonBreathEntity::new, MobCategory.MISC)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("poison_breath"));

    public static final Supplier<EntityType<ThunderBreathEntity>> THUNDER_BREATH = ENTITY_TYPES.register("thunder_breath", () -> EntityType.Builder.<ThunderBreathEntity>of(ThunderBreathEntity::new, MobCategory.MISC)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .updateInterval(1)
            .build("thunder_breath"));

    // Throwable
    public static final Map<Tier, Supplier<EntityType<DaggerEntity>>> DAGGER_TYPES = new HashMap<>();

    public static final Supplier<EntityType<HolyBibleEntity>> HOLY_BIBLE = ENTITY_TYPES.register("holy_bible",
            () -> EntityType.Builder.<HolyBibleEntity>of(HolyBibleEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(1)
                    .build("holy_bible"));

    public static final Map<Tier, Supplier<EntityType<HolyCrossEntity>>> HOLY_CROSS_TYPES = new HashMap<>();

    public static final Supplier<EntityType<HolyWaterEntity>> HOLY_WATER = ENTITY_TYPES.register("holy_water",
            () -> EntityType.Builder.<HolyWaterEntity>of(HolyWaterEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(1)
                    .build("holy_water"));



    public static final Map<Tier, Supplier<EntityType<ThrowingAxeEntity>>> THROWING_AXE_TYPES = new HashMap<>();

    public static final Supplier<EntityType<ThrowingBoneEntity>> THROWING_BONE = ENTITY_TYPES.register("throwing_bone",
            () -> EntityType.Builder.<ThrowingBoneEntity>of(ThrowingBoneEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(1)
                    .build("throwing_bone"));

    public static final Supplier<EntityType<ThrowingBloodBoneEntity>> THROWING_BLOOD_BONE = ENTITY_TYPES.register("blood_throwing_bone",
            () -> EntityType.Builder.<ThrowingBloodBoneEntity>of(ThrowingBloodBoneEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(1)
                    .build("blood_throwing_bone"));

    public static final Supplier<EntityType<ThrowingGoldenBoneEntity>> THROWING_GOLDEN_BONE = ENTITY_TYPES.register("golden_throwing_bone",
            () -> EntityType.Builder.<ThrowingGoldenBoneEntity>of(ThrowingGoldenBoneEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(1)
                    .build("golden_throwing_bone"));

    static {
        for (Tiers tier : Tiers.values()) {
            DAGGER_TYPES.put(tier, ENTITY_TYPES.register(tier.name().toLowerCase(Locale.ROOT) + '_' + "dagger",
                    () -> EntityType.Builder.<DaggerEntity>of(
                                    ((entityType, level) -> new DaggerEntity(level, tier)),
                                    MobCategory.MISC)
                            .sized(0.5F, 0.5F)
                            .clientTrackingRange(4)
                            .updateInterval(1)
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
                            .build(tier.name().toLowerCase(Locale.ROOT) + '_' + "holy_cross"))
            );
        }
    }

    public static final Supplier<EntityType<FireHolyCrossEntity>> FIRE_HOLY_CROSS = ENTITY_TYPES.register("fire_holy_cross",
            () -> EntityType.Builder.<FireHolyCrossEntity>of(FireHolyCrossEntity::new, MobCategory.MISC)
                    .setTrackingRange(64)
                    .clientTrackingRange(4)
                    .setShouldReceiveVelocityUpdates(true)
                    .updateInterval(1)
                    .build("fire_holy_cross"));

    public static final Supplier<EntityType<IceHolyCrossEntity>> ICE_HOLY_CROSS = ENTITY_TYPES.register("ice_holy_cross",
            () -> EntityType.Builder.<IceHolyCrossEntity>of(IceHolyCrossEntity::new, MobCategory.MISC)
                    .setTrackingRange(64)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .setShouldReceiveVelocityUpdates(true)
                    .updateInterval(1)
                    .build("ice_holy_cross"));

    static {
        for (Tiers tier : Tiers.values()) {
            THROWING_AXE_TYPES.put(tier, ENTITY_TYPES.register(tier.name().toLowerCase(Locale.ROOT) + '_' + "throwing_axe",
                    () -> EntityType.Builder.<ThrowingAxeEntity>of(
                                    ((entityType, level) -> new ThrowingAxeEntity(level, tier)),
                                    MobCategory.MISC)
                            .sized(0.5F, 0.5F)
                            .clientTrackingRange(4)
                            .updateInterval(20)
                            .build(tier.name().toLowerCase(Locale.ROOT) + '_' + "throwing_axe"))
            );
        }
    }

    public static final Supplier<EntityType<ScytheProjectileEntity>> SCYTHE_PROJECTILE = ENTITY_TYPES.register("scythe_projectile",
            () -> EntityType.Builder.<ScytheProjectileEntity>of(ScytheProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .setTrackingRange(64)
                    .clientTrackingRange(4)
                    .setShouldReceiveVelocityUpdates(true)
                    .updateInterval(1)
                    .build("scythe_projectile"));

    // Misc
    public static final Supplier<EntityType<HolyFireEntity>> HOLY_FIRE = ENTITY_TYPES.register("holy_fire",
            () -> EntityType.Builder.<HolyFireEntity>of(HolyFireEntity::new, MobCategory.MISC)
                    .setTrackingRange(64)
                    .clientTrackingRange(4)
                    .setShouldReceiveVelocityUpdates(true)
                    .updateInterval(1)
                    .build("holy_fire"))
            ;
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
