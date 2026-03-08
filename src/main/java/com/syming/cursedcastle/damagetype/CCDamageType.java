package com.syming.cursedcastle.damagetype;

import com.syming.cursedcastle.CursedCastleMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class CCDamageType {

    public static final ResourceKey<DamageType> FIRE = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(CursedCastleMod.MOD_ID, "fire"));
    public static final ResourceKey<DamageType> ICE = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(CursedCastleMod.MOD_ID, "ice"));
    public static final ResourceKey<DamageType> THUNDER = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(CursedCastleMod.MOD_ID, "thunder"));
    public static final ResourceKey<DamageType> WATER = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(CursedCastleMod.MOD_ID, "water"));
    public static final ResourceKey<DamageType> WIND = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(CursedCastleMod.MOD_ID, "wind"));
    public static final ResourceKey<DamageType> EARTH = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(CursedCastleMod.MOD_ID, "earth"));
    public static final ResourceKey<DamageType> HOLY = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(CursedCastleMod.MOD_ID, "holy"));
    public static final ResourceKey<DamageType> DARK = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(CursedCastleMod.MOD_ID, "dark"));

    public static List<ResourceKey<DamageType>> DAMAGE_TYPES_LIST = List.of(FIRE, ICE, THUNDER, WATER, WIND, EARTH, HOLY, DARK);

    public static DamageSource causeFireDamage(@Nullable Entity entity) {
        Holder<DamageType> holder = entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolder(FIRE).get();
        return new DamageSource(holder, entity);
    }

    public static DamageSource causeIceDamage(@Nullable Entity entity) {
        Holder<DamageType> holder = entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolder(ICE).get();
        return new DamageSource(holder, entity);
    }

    public static DamageSource causeThunderDamage(@Nullable Entity entity) {
        Holder<DamageType> holder = entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolder(THUNDER).get();
        return new DamageSource(holder, entity);
    }

    public static DamageSource causeWaterDamage(@Nullable Entity entity) {
        Holder<DamageType> holder = entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolder(WATER).get();
        return new DamageSource(holder, entity);
    }

    public static DamageSource causeWindDamage(@Nullable Entity entity) {
        Holder<DamageType> holder = entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolder(WIND).get();
        return new DamageSource(holder, entity);
    }

    public static DamageSource causeEarthDamage(@Nullable Entity entity) {
        Holder<DamageType> holder = entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolder(EARTH).get();
        return new DamageSource(holder, entity);
    }

    public static DamageSource causeHolyDamage(@Nullable Entity entity) {
        Holder<DamageType> holder = entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolder(HOLY).get();
        return new DamageSource(holder, entity);
    }

    public static DamageSource causeDarkDamage(@Nullable Entity entity) {
        Holder<DamageType> holder = entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolder(DARK).get();
        return new DamageSource(holder, entity);
    }

    public static class CCDamageTypeProvider extends DatapackBuiltinEntriesProvider {

        public CCDamageTypeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries, BUILDER, Set.of(CursedCastleMod.MOD_ID));
        }

        public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
                .add(Registries.DAMAGE_TYPE, CCDamageTypeProvider::bootstrapDamageType);

        public static void bootstrapDamageType(BootstapContext<DamageType> bootstapContext) {
            bootstapContext.register(FIRE, new DamageType("fire", DamageScaling.NEVER, 0.1F, DamageEffects.BURNING));
            bootstapContext.register(ICE, new DamageType("ice", DamageScaling.NEVER, 0.1F, DamageEffects.FREEZING));
            bootstapContext.register(THUNDER, new DamageType("thunder", DamageScaling.NEVER, 0.1F, DamageEffects.THORNS));
            bootstapContext.register(WATER, new DamageType("water", DamageScaling.NEVER, 0.1F, DamageEffects.DROWNING));
            bootstapContext.register(WIND, new DamageType("wind", DamageScaling.NEVER, 0.1F));
            bootstapContext.register(EARTH, new DamageType("earth", DamageScaling.NEVER, 0.1F));
            bootstapContext.register(HOLY, new DamageType("holy", DamageScaling.NEVER, 0.1F));
            bootstapContext.register(DARK, new DamageType("dark", DamageScaling.NEVER, 0.1F));
        }
    }

    public static class CCDamageTypeTagsProvider extends DamageTypeTagsProvider {

        public CCDamageTypeTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(pOutput, pLookupProvider, CursedCastleMod.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider pProvider) {

        }
    }
}
