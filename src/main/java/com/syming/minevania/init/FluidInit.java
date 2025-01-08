package com.syming.minevania.init;

import com.syming.minevania.fluid.FluidBlood;
import com.syming.minevania.fluid.FluidHolyWater;
import com.syming.minevania.util.MVReferences;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidInit {

    public static final Fluid HOLY_WATER_FLUID = new FluidHolyWater("holy_water_fluid", new ResourceLocation(MVReferences.MODID + ":blocks/holy_water_still"),
           new ResourceLocation(MVReferences.MODID + ":blocks/holy_water_flow"),
            new ResourceLocation(MVReferences.MODID + ":blocks/holy_water_overlay"));

    public static final Fluid BLOOD_FLUID = new FluidBlood("blood_fluid", new ResourceLocation(MVReferences.MODID + ":blocks/blood_still"),
            new ResourceLocation(MVReferences.MODID +":blocks/blood_flow"),
            new ResourceLocation(MVReferences.MODID + ":blocks/blood_overlay"));

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
