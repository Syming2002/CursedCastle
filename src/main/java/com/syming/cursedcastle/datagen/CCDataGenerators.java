package com.syming.cursedcastle.datagen;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.damagetype.CCDamageType;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = CursedCastleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CCDataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        DataGenerator.PackGenerator packGenerator = generator.getVanillaPack(true);

        generator.addProvider(event.includeClient(), new CCBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new CCBlockModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new CCItemModelProvider(packOutput, existingFileHelper));

        CCBlockTagGenerator blockTagGenerator = generator.addProvider(event.includeServer(), new CCBlockTagGenerator(packOutput, lookupProvider, existingFileHelper));

        generator.addProvider(event.includeServer(), new CCItemTagGenerator(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeServer(), new CCPoiTypeTagsProvider(packOutput, lookupProvider, existingFileHelper));

        generator.addProvider(event.includeServer(), new CCDamageType.CCDamageTypeProvider(packOutput, lookupProvider));

        generator.addProvider(event.includeServer(), (DataProvider.Factory<CCDamageType.CCDamageTypeTagsProvider>) output -> new CCDamageType.CCDamageTypeTagsProvider(packOutput, lookupProvider, existingFileHelper));

        // Advancements
        packGenerator.addProvider(output -> new CCAdvancementGenerator(output, existingFileHelper));
//        generator.addProvider(event.includeServer(), new CCGlobalLootModifiersProvider(packOutput));
    }
}
