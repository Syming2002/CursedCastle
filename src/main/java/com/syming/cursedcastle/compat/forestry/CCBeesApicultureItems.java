package com.syming.cursedcastle.compat.forestry;

import forestry.modules.features.FeatureItemGroup;
import forestry.modules.features.FeatureProvider;
import forestry.modules.features.IFeatureRegistry;
import forestry.modules.features.ModFeatureRegistry;

import static com.syming.cursedcastle.compat.forestry.CCBeesModule.cursedcastle;

@FeatureProvider
public class CCBeesApicultureItems {

    public static final IFeatureRegistry REGISTRY = ModFeatureRegistry.get(cursedcastle("core"));
    public static final FeatureItemGroup<CCBeesItemHoneyComb, CCBeesEnumHoneyComb> CC_BEES_COMB = REGISTRY.itemGroup(CCBeesItemHoneyComb::new, "bee_comb", CCBeesEnumHoneyComb.values());


}
