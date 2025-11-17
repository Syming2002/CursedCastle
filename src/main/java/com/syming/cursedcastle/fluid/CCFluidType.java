package com.syming.cursedcastle.fluid;

import com.syming.cursedcastle.CursedCastleMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

public class CCFluidType {

    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, CursedCastleMod.MOD_ID);

    public static final RegistryObject<FluidType> BLOOD_FLUID_TYPE = FLUID_TYPES.register("blood", () -> new BloodFluidType(FluidType.Properties.create(), WATER_STILL_RL, WATER_FLOWING_RL, WATER_OVERLAY_RL, 0xFF701914, new Vector3f(43.9F / 255F, 9.8F / 255F, 7.8F / 255F)));
    public static final RegistryObject<FluidType> HOLY_WATER_FLUID_TYPE = FLUID_TYPES.register("holy_water", () -> new HolyWaterFluidType(FluidType.Properties.create(), WATER_STILL_RL, WATER_FLOWING_RL, WATER_OVERLAY_RL, 0xFF6783BD, new Vector3f(40.4F / 255F, 51.4F / 255F, 74.1F / 255F)));


    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
