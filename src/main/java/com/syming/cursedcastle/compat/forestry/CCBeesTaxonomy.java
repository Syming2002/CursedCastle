package com.syming.cursedcastle.compat.forestry;

import forestry.api.genetics.ForestryTaxa;
import forestry.api.genetics.alleles.BeeChromosomes;
import forestry.api.genetics.alleles.ForestryAlleles;
import forestry.api.plugin.IGeneticRegistration;

public class CCBeesTaxonomy {

    public static void defineTaxa(IGeneticRegistration geneticRegistration) {
        geneticRegistration.defineTaxon(ForestryTaxa.CLASS_INSECTS, ForestryTaxa.ORDER_HYMNOPTERA, order -> {
            order.defineSubTaxon(ForestryTaxa.FAMILY_BEES, family -> {
                family.defineSubTaxon(CCTaxa.GENUS_UNDEAD, taxon -> {
                    taxon.setDefaultChromosome(BeeChromosomes.TEMPERATURE_TOLERANCE, ForestryAlleles.TOLERANCE_BOTH_5);
                    taxon.setDefaultChromosome(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_1);
                    taxon.setDefaultChromosome(BeeChromosomes.TOLERATES_RAIN, ForestryAlleles.TRUE);
                    taxon.setDefaultChromosome(BeeChromosomes.EFFECT, ForestryAlleles.EFFECT_AGGRESSIVE);
                    taxon.setDefaultChromosome(BeeChromosomes.ACTIVITY, ForestryAlleles.ACTIVITY_NOCTURNAL);
                });

                family.defineSubTaxon(CCTaxa.GENUS_SHADE, taxon -> {
                    taxon.setDefaultChromosome(BeeChromosomes.FERTILITY, ForestryAlleles.FERTILITY_3);
                    taxon.setDefaultChromosome(BeeChromosomes.ACTIVITY, ForestryAlleles.ACTIVITY_NOCTURNAL);
                });
            });
        });
    }
}
