package com.syming.cursedcastle.compat.forestry;

import forestry.api.ForestryConstants;
import forestry.api.client.IClientModuleHandler;
import forestry.api.modules.ForestryModule;
import forestry.api.modules.IForestryModule;
import forestry.modules.BlankForestryModule;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModList;

import java.util.function.Consumer;

import static com.syming.cursedcastle.CursedCastleMod.MOD_ID;

@ForestryModule
public class CCBeesModule extends BlankForestryModule {

    @Override
    public ResourceLocation getId() {
        return cursedcastle("core");
    }

    @Override
    public void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
        if (ModList.get().isLoaded(ForestryConstants.MOD_ID)) {
            registrar.accept(new CCBeesCoreClientHandler());
        }
    }

    public static ResourceLocation cursedcastle(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
