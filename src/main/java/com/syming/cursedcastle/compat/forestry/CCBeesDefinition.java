//package com.syming.cursedcastle.compat.forestry;
//
//import forestry.api.apiculture.ForestryBeeEffects;
//import forestry.api.core.HumidityType;
//import forestry.api.core.TemperatureType;
//import forestry.api.core.genetics.alleles.BeeChromosomes;
//import forestry.api.core.genetics.alleles.ForestryAlleles;
//import forestry.api.plugin.IApicultureRegistration;
//import forestry.apiculture.bees.EnumHoneyComb;
//import forestry.apiculture.features.ApicultureItems;
//import net.minecraft.network.chat.TextColor;
//
//public class CCBeesDefinition {
//
//    public static void defineNewCCBees(IApicultureRegistration apiculture) {
//        apiculture.registerSpecies(CCBeesSpecies.FROZEN_SHADE, CCTaxa.GENUS_SHADE, CCTaxa.FROZEN_SHADE, true, TextColor.fromRgb(0x1298FF))
//                .setBody(TextColor.fromRgb(0x044FB0))
//                .addProduct(ApicultureItems.BEE_COMBS.stack(EnumHoneyComb.FROZEN), 0.3F)
//                .addProduct(CCBeesApicultureItems.CC_BEES_COMB.stack(CCBeesEnumHoneyComb.FROZEN), 0.15F)
//                .setGenome(iGenomeBuilder -> {
//                    iGenomeBuilder.set(BeeChromosomes.FLOWER_TYPE, CCBeesFlowerType.ICE);
//                    iGenomeBuilder.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_5);
//                    iGenomeBuilder.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_1);
//                    iGenomeBuilder.set(BeeChromosomes.TOLERATES_RAIN, ForestryAlleles.TRUE);
//                    iGenomeBuilder.set(BeeChromosomes.EFFECT, ForestryBeeEffects.GLACIAL);
//                })
//                .setTemperature(TemperatureType.ICY)
//                .setHumidity(HumidityType.NORMAL)
//                .setAuthority("Syming");
//
//        apiculture.registerSpecies(CCBeesSpecies.HEAT_SHADE, CCTaxa.GENUS_SHADE, CCTaxa.HEAT_SHADE, true, TextColor.fromRgb(0xF44211))
//                .setBody(TextColor.fromRgb(0xC41105))
//                .addProduct(ApicultureItems.BEE_COMBS.stack(EnumHoneyComb.SIMMERING), 0.3F)
//                .addProduct(CCBeesApicultureItems.CC_BEES_COMB.stack(CCBeesEnumHoneyComb.WARMED), 0.15F)
//                .setGenome(iGenomeBuilder -> {
//                    iGenomeBuilder.set(BeeChromosomes.FLOWER_TYPE, CCBeesFlowerType.FIRE);
//                    iGenomeBuilder.set(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_UP_5);
//                    iGenomeBuilder.set(BeeChromosomes.HUMIDITY_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_1);
//                    iGenomeBuilder.set(BeeChromosomes.TOLERATES_RAIN, ForestryAlleles.FALSE);
//                    iGenomeBuilder.set(BeeChromosomes.EFFECT, ForestryBeeEffects.IGNITION);
//
//                })
//                .setTemperature(TemperatureType.HOT)
//                .setHumidity(HumidityType.ARID)
//                .setAuthority("Syming");
//
//        apiculture.registerSpecies(CCBeesSpecies.VAMPIRE, CCTaxa.GENUS_UNDEAD, CCTaxa.VAMPIRE, true, TextColor.fromRgb(0x0C0B0F))
//                .setBody(TextColor.fromRgb(0x8E1D26))
//                .addProduct(ApicultureItems.BEE_COMBS.stack(EnumHoneyComb.HONEY), 0.3F)
//                .addProduct(CCBeesApicultureItems.CC_BEES_COMB.stack(CCBeesEnumHoneyComb.BLOOD), 0.15F)
//                .setGenome(iGenomeBuilder -> {
//                    iGenomeBuilder.set(BeeChromosomes.FLOWER_TYPE, CCBeesFlowerType.BLOOD);
//                })
//                .setTemperature(TemperatureType.HELLISH)
//                .setHumidity(HumidityType.ARID)
//                .setAuthority("Syming");
//
//        apiculture.registerSpecies(CCBeesSpecies.DRACULA, CCTaxa.GENUS_UNDEAD, CCTaxa.DRACULA, true, TextColor.fromRgb(0x0C0B0F))
//                .setBody(TextColor.fromRgb(0x8E1D26))
//                .addProduct(ApicultureItems.BEE_COMBS.stack(EnumHoneyComb.HONEY), 0.3F)
//                .addProduct(CCBeesApicultureItems.CC_BEES_COMB.stack(CCBeesEnumHoneyComb.BLOOD), 0.15F)
//                .setGenome(iGenomeBuilder -> {
//                    iGenomeBuilder.set(BeeChromosomes.FLOWER_TYPE, CCBeesFlowerType.BLOOD);
//                })
//                .setTemperature(TemperatureType.HELLISH)
//                .setHumidity(HumidityType.ARID)
//                .setGlint(true)
//                .setAuthority("Syming");
//    }
//
//}
