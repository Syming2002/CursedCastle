package com.syming.cursedcastle.compat.forestry;

import com.syming.cursedcastle.tag.CCTag;
import forestry.api.ForestryConstants;
import forestry.api.client.plugin.IClientRegistration;
import forestry.api.plugin.IApicultureRegistration;
import forestry.api.plugin.IForestryPlugin;
import forestry.api.plugin.IGeneticRegistration;
import forestry.apiculture.bees.EnumHoneyComb;
import forestry.apiculture.features.ApicultureItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CCBeesPlugin implements IForestryPlugin {
    @Override
    public void registerGenetics(IGeneticRegistration genetics) {

    }

    @Override
    public void registerApiculture(@SuppressWarnings("null") IApicultureRegistration apiculture) {

    }

    @Override
    public void registerClient(Consumer<Consumer<IClientRegistration>> registrar) {
    }

    @Override
    public ResourceLocation id() {
        return CCBeesModule.cursedcastle("core");
    }

    @Override
    public boolean shouldLoad() {
        return true;
    }

    private static Supplier<List<ItemStack>> getCCHoneyComb(CCBeesEnumHoneyComb enumHoneyComb) {
        return () -> List.of(CCBeesApicultureItems.CC_BEES_COMB.stack(enumHoneyComb));
    }

    private static Supplier<List<ItemStack>> getForestryComb(EnumHoneyComb enumHoneyComb) {
        return () -> List.of(ApicultureItems.BEE_COMBS.stack(enumHoneyComb));
    }
}
