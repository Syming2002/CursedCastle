package com.syming.cursedcastle.datagen;

import com.syming.cursedcastle.CursedCastleMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class CCDatapackBuiltinEntriesProvider extends DatapackBuiltinEntriesProvider {

    public static final ResourceKey<DamageType> FIRE = create("fire");
    public static final ResourceKey<DamageType> ICE = create("ice");
    public static final ResourceKey<DamageType> THUNDER = create("thunder");
    public static final ResourceKey<DamageType> WATER = create("water");
    public static final ResourceKey<DamageType> WIND = create("wind");
    public static final ResourceKey<DamageType> EARTH = create("earth");
    public static final ResourceKey<DamageType> HOLY = create("holy");
    public static final ResourceKey<DamageType> DARK = create("dark");

    public CCDatapackBuiltinEntriesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(CursedCastleMod.MOD_ID));
    }

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.DAMAGE_TYPE, CCDatapackBuiltinEntriesProvider::bootstrapDamageType);

    private static ResourceKey<DamageType> create(String pName) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(CursedCastleMod.MOD_ID, pName));
    }

    public static DamageSource source(RegistryAccess access, ResourceKey<DamageType> type, @Nullable Entity entityDirect, @Nullable Entity entityCausing) {
        return new DamageSource(access.registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(type), entityDirect, entityCausing);
    }

    public static DamageSource source(RegistryAccess access, ResourceKey<DamageType> type, @Nullable Entity entity) {
        return new DamageSource(access.registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(type), entity, null);
    }

    public static DamageSource source(RegistryAccess access, ResourceKey<DamageType> type) {
        return new DamageSource(access.registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(type), null, null);
    }

    public static void bootstrapDamageType(BootstapContext<DamageType> pContext) {
        pContext.register(FIRE, new DamageType("fire", DamageScaling.NEVER, 0.1F, DamageEffects.BURNING));
        pContext.register(ICE, new DamageType("ice", DamageScaling.NEVER, 0.1F, DamageEffects.FREEZING));
        pContext.register(THUNDER, new DamageType("thunder", DamageScaling.NEVER, 0.1F, DamageEffects.THORNS));
        pContext.register(WATER, new DamageType("water", DamageScaling.NEVER, 0.1F, DamageEffects.DROWNING));
        pContext.register(WIND, new DamageType("wind", DamageScaling.NEVER, 0.1F));
        pContext.register(EARTH, new DamageType("earth", DamageScaling.NEVER, 0.1F));
        pContext.register(HOLY, new DamageType("holy", DamageScaling.NEVER, 0.1F));
        pContext.register(DARK, new DamageType("dark", DamageScaling.NEVER, 0.1F));
    }
}
