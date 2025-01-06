package com.syming.minevania.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidBlood extends Fluid {

    public FluidBlood(String name, ResourceLocation still, ResourceLocation flow, ResourceLocation overlay) {
        super(name, still, flow, overlay);
        this.setUnlocalizedName(name);
        this.setDensity(1500);
        this.setViscosity(3000);
    }
}
