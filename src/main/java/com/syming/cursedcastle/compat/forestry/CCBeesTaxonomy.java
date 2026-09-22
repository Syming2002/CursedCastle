//package com.syming.cursedcastle.compat.forestry;
//
//import forestry.api.apiculture.ForestryActivityTypes;
//import forestry.api.apiculture.ForestryBeeEffects;
//import forestry.api.core.genetics.ForestryTaxa;
//import forestry.api.core.genetics.alleles.BeeChromosomes;
//import forestry.api.core.genetics.alleles.ForestryAlleles;
//import forestry.api.plugin.IGeneticRegistration;
//
//public class CCBeesTaxonomy {
//
//    public static void defineTaxa(IGeneticRegistration geneticRegistration) {
//        geneticRegistration.defineTaxon(ForestryTaxa.CLASS_INSECTS, ForestryTaxa.ORDER_HYMNOPTERA, order -> {
//            order.defineSubTaxon(ForestryTaxa.FAMILY_BEES, family -> {
//                family.defineSubTaxon(CCTaxa.GENUS_UNDEAD, taxon -> {
//                    taxon.setDefaultChromosome(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_DOWN_3);
//                    taxon.setDefaultChromosome(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
//                    taxon.setDefaultChromosome(BeeChromosomes.TOLERATES_RAIN, ForestryAlleles.TRUE);
//                    taxon.setDefaultChromosome(BeeChromosomes.EFFECT, ForestryBeeEffects.AGGRESSIVE);
//                    taxon.setDefaultChromosome(BeeChromosomes.ACTIVITY, ForestryActivityTypes.NOCTURNAL);
//                });
//
//                family.defineSubTaxon(CCTaxa.GENUS_SHADE, taxon -> {
//                    taxon.setDefaultChromosome(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_3);
//                    taxon.setDefaultChromosome(BeeChromosomes.ACTIVITY, ForestryActivityTypes.NOCTURNAL);
//                });
//            });
//        });
//    }
//}
