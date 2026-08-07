package com.syming.cursedcastle.compat.forestry;

import forestry.api.client.IClientModuleHandler;
import forestry.api.modules.ForestryModule;
import forestry.api.modules.IForestryModule;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;

import java.util.function.Consumer;

import static com.syming.cursedcastle.CursedCastleMod.MOD_ID;

@ForestryModule
public class CCBeesModule implements IForestryModule {

    @Override
    public ResourceLocation getId() {
        return cursedcastle("core");
    }

    @Override
    public void registerClientHandler(Consumer<IClientModuleHandler> registrar) {
        if (ModList.get().isLoaded("forestry")) {
            registrar.accept(new CCBeesCoreClientHandler());
        }
    }

    public static ResourceLocation cursedcastle(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
