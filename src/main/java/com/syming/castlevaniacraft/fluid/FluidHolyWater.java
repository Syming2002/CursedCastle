package com.syming.castlevaniacraft.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidHolyWater extends Fluid {

    public FluidHolyWater(String name, ResourceLocation still, ResourceLocation flow, ResourceLocation overlay)
    {
        super(name, still, flow, overlay);
        this.setUnlocalizedName(name);
    }
}
