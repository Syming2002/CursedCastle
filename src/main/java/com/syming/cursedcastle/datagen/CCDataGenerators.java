package com.syming.cursedcastle.datagen;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.damagesource.CCDamageSource;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = CursedCastleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CCDataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new CCBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new CCBlockModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new CCItemModelProvider(packOutput, existingFileHelper));
        RegistrySetBuilder registrySetBuilder = new RegistrySetBuilder();
        CCDamageSource.register(registrySetBuilder);
        DatapackBuiltinEntriesProvider datapackBuiltinEntriesProvider = new DatapackBuiltinEntriesProvider(packOutput, lookupProvider, registrySetBuilder, Set.of(CursedCastleMod.MOD_ID));
        generator.addProvider(event.includeServer(), datapackBuiltinEntriesProvider);

        CCBlockTagGenerator blockTagGenerator = generator.addProvider(event.includeServer(), new CCBlockTagGenerator(packOutput, lookupProvider, existingFileHelper));

        generator.addProvider(event.includeServer(), new CCItemTagGenerator(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeServer(), new CCPoiTypeTagsProvider(packOutput, lookupProvider, existingFileHelper));

//        generator.addProvider(event.includeServer(), new CCGlobalLootModifiersProvider(packOutput));
    }
}
