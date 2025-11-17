package com.syming.cursedcastle.fluid;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.item.CCItem;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CCFluid {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, CursedCastleMod.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOURCE_BLOOD = FLUIDS.register("source_blood", () -> new ForgeFlowingFluid.Source(CCFluid.BLOOD_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_BLOOD = FLUIDS.register("flowing_blood", () -> new ForgeFlowingFluid.Flowing(CCFluid.BLOOD_FLUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties BLOOD_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(CCFluidType.BLOOD_FLUID_TYPE, SOURCE_BLOOD, FLOWING_BLOOD)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(CCBlock.BLOOD_BLOCK).bucket(CCItem.BLOOD_BUCKET);

    public static final RegistryObject<FlowingFluid> SOURCE_HOLY_WATER = FLUIDS.register("source_holy_water", () -> new ForgeFlowingFluid.Source(CCFluid.HOLY_WATER_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_HOLY_WATER = FLUIDS.register("flowing_holy_water", () -> new ForgeFlowingFluid.Flowing(CCFluid.HOLY_WATER_FLUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties HOLY_WATER_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(CCFluidType.HOLY_WATER_FLUID_TYPE, SOURCE_HOLY_WATER, FLOWING_HOLY_WATER)
            .slopeFindDistance(8).levelDecreasePerBlock(1).block(() -> (LiquidBlock) CCBlock.HOLY_WATER_BLOCK.get()).bucket(CCItem.HOLY_WATER_BUCKET);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
