package com.syming.cursedcastle.datagen;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.compat.curios.CCCuriosDataProvider;
import com.syming.cursedcastle.damagetype.CCDamageType;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = CursedCastleMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
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
        generator.addProvider(event.includeClient(), new CCLangENProvider(packOutput, "en_us"));

        CCBlockTagGenerator blockTagGenerator = generator.addProvider(event.includeServer(), new CCBlockTagGenerator(packOutput, lookupProvider, existingFileHelper));

        generator.addProvider(event.includeServer(), new CCItemTagGenerator(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeServer(), new CCPoiTypeTagsProvider(packOutput, lookupProvider, existingFileHelper));

        generator.addProvider(event.includeServer(), new CCRegistrySetBuilder(packOutput, lookupProvider));

        generator.addProvider(event.includeServer(), (DataProvider.Factory<CCDamageType.CCDamageTypeTagsProvider>) output -> new CCDamageType.CCDamageTypeTagsProvider(packOutput, lookupProvider, existingFileHelper));

        generator.addProvider(event.includeServer(), new CCCuriosDataProvider(packOutput, existingFileHelper, lookupProvider));

        generator.addProvider(event.includeServer(), new CCRecipeProvider(packOutput, lookupProvider));

//        generator.addProvider(event.includeServer(), new CCTaxonProvider(packOutput));
//        generator.addProvider(event.includeServer(), new CCFlowerTypeProvider(packOutput));
//        generator.addProvider(event.includeServer(), new CCMutationProvider(packOutput, lookupProvider));

        // Advancements
        packGenerator.addProvider(output -> new CCAdvancementProvider(output, lookupProvider, existingFileHelper));
//        generator.addProvider(event.includeServer(), new CCGlobalLootModifiersProvider(packOutput));
    }
}
