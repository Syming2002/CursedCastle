package com.syming.cursedcastle.compat.forestry;

import forestry.api.IForestryApi;
import forestry.api.apiculture.IFlowerType;
import forestry.api.genetics.alleles.BeeChromosomes;
import forestry.api.genetics.alleles.IAlleleManager;
import forestry.api.genetics.alleles.IRegistryAllele;
import forestry.api.plugin.IForestryPlugin;

public class CCBeesAlleles {

    public static final IAlleleManager REGISTRY = IForestryApi.INSTANCE.getAlleleManager();

    // Flowers
    public static final IRegistryAllele<IFlowerType> FLOWER_TYPE_BLOOD = REGISTRY.registryAllele(CCBeesFlowerType.BLOOD, BeeChromosomes.FLOWER_TYPE);
    public static final IRegistryAllele<IFlowerType> FLOWER_TYPE_ICE = REGISTRY.registryAllele(CCBeesFlowerType.ICE, BeeChromosomes.FLOWER_TYPE);
    public static final IRegistryAllele<IFlowerType> FLOWER_TYPE_FIRE = REGISTRY.registryAllele(CCBeesFlowerType.FIRE, BeeChromosomes.FLOWER_TYPE);

}
