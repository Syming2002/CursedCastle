package com.syming.cursedcastle.datagen;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.damagetype.CCDamageType;
import com.syming.cursedcastle.enchantment.CCEnchantment;
import com.syming.cursedcastle.world.CCBiomeModifiers;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class CCRegistrySetBuilder extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.ENCHANTMENT, CCEnchantment::bootstrap)
            .add(Registries.DAMAGE_TYPE, CCDamageType.CCDamageTypeProvider::bootstrapDamageType)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, CCBiomeModifiers::bootstrap);

    public CCRegistrySetBuilder(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(CursedCastleMod.MOD_ID));
    }
}
