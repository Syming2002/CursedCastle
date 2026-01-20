package com.syming.cursedcastle.compat.forestry;

import com.syming.cursedcastle.tag.CCTag;
import forestry.api.client.plugin.IClientRegistration;
import forestry.api.plugin.IApicultureRegistration;
import forestry.api.plugin.IForestryPlugin;
import forestry.api.plugin.IGeneticRegistration;
import forestry.apiculture.FlowerType;
import forestry.apiculture.features.ApicultureItems;
import forestry.apiculture.items.EnumHoneyComb;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CCBeesPlugin implements IForestryPlugin {
    @Override
    public void registerGenetics(IGeneticRegistration genetics) {
        CCBeesTaxonomy.defineTaxa(genetics);
    }

    @Override
    public void registerApiculture(@SuppressWarnings("null") IApicultureRegistration apiculture) {
        CCBeesDefinition.defineNewCCBees(apiculture);

        apiculture.registerFlowerType(CCBeesFlowerType.BLOOD, new FlowerType(CCTag.Blocks.BLOOD_BLOCKS, true));
        apiculture.registerFlowerType(CCBeesFlowerType.ICE, new FlowerType(CCTag.Blocks.ICE_BLOCKS, true));
        apiculture.registerFlowerType(CCBeesFlowerType.FIRE, new FlowerType(CCTag.Blocks.FIRE_BLOCKS, true));
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

    private static Supplier<List<ItemStack>> getHoneyComb(CCBeesEnumHoneyComb enumHoneyComb) {
        return () -> List.of(CCBeesApicultureItems.CC_BEES_COMB.stack(enumHoneyComb));
    }

    private static Supplier<List<ItemStack>> getForestryComb(EnumHoneyComb enumHoneyComb) {
        return () -> List.of(ApicultureItems.BEE_COMBS.stack(enumHoneyComb));
    }
}
