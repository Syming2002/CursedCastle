package com.syming.castlevaniacraft.fluid;

import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class CCFluid {

    public static final Fluid HOLY_WATER_FLUID = new FluidHolyWater("holy_water_fluid", new ResourceLocation(CCReferences.MODID + ":blocks/holy_water_still"),
           new ResourceLocation(CCReferences.MODID + ":blocks/holy_water_flow"),
            new ResourceLocation(CCReferences.MODID + ":blocks/holy_water_overlay"));

    public static final Fluid BLOOD_FLUID = new FluidBlood("blood_fluid", new ResourceLocation(CCReferences.MODID + ":blocks/blood_still"),
            new ResourceLocation(CCReferences.MODID +":blocks/blood_flow"),
            new ResourceLocation(CCReferences.MODID + ":blocks/blood_overlay"));

    public static void registerFluids()
    {
        registerFluid(HOLY_WATER_FLUID);
        registerFluid(BLOOD_FLUID);
    }

    public static void registerFluid(Fluid fluid)
    {
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
    }

}
