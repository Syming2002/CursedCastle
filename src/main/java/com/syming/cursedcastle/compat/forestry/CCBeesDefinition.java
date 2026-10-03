package com.syming.cursedcastle.compat.forestry;

import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.fluid.CCFluid;
import com.syming.cursedcastle.tag.CCTag;
import forestry.api.apiculture.ForestryBeeEffects;
import forestry.api.apiculture.ForestryBeeSpecies;
import forestry.api.core.HumidityType;
import forestry.api.core.TemperatureType;
import forestry.api.genetics.alleles.BeeChromosomes;
import forestry.api.genetics.alleles.ForestryAlleles;
import forestry.api.plugin.IApicultureRegistration;
import forestry.apiculture.features.ApicultureItems;
import forestry.apiculture.items.EnumHoneyComb;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.level.block.Blocks;

public class CCBeesDefinition {

    public static void defineNewCCBees(IApicultureRegistration apiculture) {
        apiculture.registerSpecies(CCBeesSpecies.FROZEN_SHADE, CCTaxa.GENUS_SHADE, CCTaxa.FROZEN_SHADE, true, TextColor.fromRgb(0xFFFFFF))
                .setBody(TextColor.fromRgb(0x9BB8CD))
                .addProduct(ApicultureItems.BEE_COMBS.stack(EnumHoneyComb.FROZEN), 0.3F)
                .addProduct(CCBeesApicultureItems.CC_BEES_COMB.stack(CCBeesEnumHoneyComb.FROZEN), 0.15F)
                .setGenome(iGenomeBuilder -> {
                    iGenomeBuilder.set(BeeChromosomes.FLOWER_TYPE, CCBeesAlleles.FLOWER_TYPE_ICE);
                    iGenomeBuilder.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_5);
                    iGenomeBuilder.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_1);
                    iGenomeBuilder.set(BeeChromosomes.TOLERATES_RAIN, ForestryAlleles.TRUE);
                    iGenomeBuilder.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_GLACIAL);
                })
                .addMutations(iMutationsRegistration -> {
                    iMutationsRegistration.add(ForestryBeeSpecies.GLACIAL, ForestryBeeSpecies.DEMONIC, 15)
                            .requireResource(Blocks.PACKED_ICE.defaultBlockState());
                })
                .setTemperature(TemperatureType.ICY)
                .setHumidity(HumidityType.NORMAL)
                .setAuthority("Syming");

        apiculture.registerSpecies(CCBeesSpecies.HEAT_SHADE, CCTaxa.GENUS_SHADE, CCTaxa.HEAT_SHADE, true, TextColor.fromRgb(0xF24A18))
                .setBody(TextColor.fromRgb(0x360908))
                .addProduct(ApicultureItems.BEE_COMBS.stack(EnumHoneyComb.SIMMERING), 0.3F)
                .addProduct(CCBeesApicultureItems.CC_BEES_COMB.stack(CCBeesEnumHoneyComb.WARMED), 0.15F)
                .setGenome(iGenomeBuilder -> {
                    iGenomeBuilder.set(BeeChromosomes.FLOWER_TYPE, CCBeesAlleles.FLOWER_TYPE_FIRE);
                    iGenomeBuilder.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_UP_5);
                    iGenomeBuilder.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_1);
                    iGenomeBuilder.set(BeeChromosomes.TOLERATES_RAIN, ForestryAlleles.FALSE);
                    iGenomeBuilder.set(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_IGNITION);

                })
                .addMutations(iMutationsRegistration -> {
                    iMutationsRegistration.add(ForestryBeeSpecies.SINISTER, ForestryBeeSpecies.DEMONIC, 15)
                            .requireResource(Blocks.LAVA.defaultBlockState());
                })
                .setTemperature(TemperatureType.HOT)
                .setHumidity(HumidityType.ARID)
                .setAuthority("Syming");

        apiculture.registerSpecies(CCBeesSpecies.VAMPIRE, CCTaxa.GENUS_UNDEAD, CCTaxa.VAMPIRE, true, TextColor.fromRgb(0x0C0B0F))
                .setBody(TextColor.fromRgb(0x8E1D26))
                .addProduct(ApicultureItems.BEE_COMBS.stack(EnumHoneyComb.HONEY), 0.3F)
                .addProduct(CCBeesApicultureItems.CC_BEES_COMB.stack(CCBeesEnumHoneyComb.BLOOD), 0.15F)
                .setGenome(iGenomeBuilder -> {
                    iGenomeBuilder.set(BeeChromosomes.FLOWER_TYPE, CCBeesAlleles.FLOWER_TYPE_BLOOD);
                })
                .addMutations(iMutationsRegistration -> {
                    iMutationsRegistration.add(ForestryBeeSpecies.DEMONIC, ForestryBeeSpecies.SINISTER, 10)
                            .requireResource(CCBlock.BLOOD_BLOCK.get().defaultBlockState());
                })
                .setTemperature(TemperatureType.HELLISH)
                .setHumidity(HumidityType.ARID)
                .setAuthority("Syming");

        apiculture.registerSpecies(CCBeesSpecies.DRACULA, CCTaxa.GENUS_UNDEAD, CCTaxa.DRACULA, true, TextColor.fromRgb(0x0C0B0F))
                .setBody(TextColor.fromRgb(0x8E1D26))
                .addProduct(ApicultureItems.BEE_COMBS.stack(EnumHoneyComb.HONEY), 0.3F)
                .addProduct(CCBeesApicultureItems.CC_BEES_COMB.stack(CCBeesEnumHoneyComb.BLOOD), 0.15F)
                .setGenome(iGenomeBuilder -> {
                    iGenomeBuilder.set(BeeChromosomes.FLOWER_TYPE, CCBeesAlleles.FLOWER_TYPE_BLOOD);
                })
                .addMutations(iMutationsRegistration -> {
                    iMutationsRegistration.add(ForestryBeeSpecies.IMPERIAL, CCBeesSpecies.VAMPIRE, 5)
                            .requireResource(CCBlock.BLOOD_BLOCK.get().defaultBlockState());
                })
                .setTemperature(TemperatureType.HELLISH)
                .setHumidity(HumidityType.ARID)
                .setGlint(true)
                .setAuthority("Syming");
    }

}
