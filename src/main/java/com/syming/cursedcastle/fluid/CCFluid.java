package com.syming.cursedcastle.fluid;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.item.CCItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CCFluid {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, CursedCastleMod.MOD_ID);

    public static final Supplier<FlowingFluid> SOURCE_BLOOD = FLUIDS.register("source_blood", () -> new BaseFlowingFluid.Source(CCFluid.BLOOD_FLUID_PROPERTIES));
    public static final Supplier<FlowingFluid> FLOWING_BLOOD = FLUIDS.register("flowing_blood", () -> new BaseFlowingFluid.Flowing(CCFluid.BLOOD_FLUID_PROPERTIES));

    public static final BaseFlowingFluid.Properties BLOOD_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(CCFluidType.BLOOD_FLUID_TYPE, SOURCE_BLOOD, FLOWING_BLOOD)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(CCBlock.BLOOD_BLOCK).bucket(CCItem.BLOOD_BUCKET);

    public static final Supplier<FlowingFluid> SOURCE_HOLY_WATER = FLUIDS.register("source_holy_water", () -> new BaseFlowingFluid.Source(CCFluid.HOLY_WATER_FLUID_PROPERTIES));
    public static final Supplier<FlowingFluid> FLOWING_HOLY_WATER = FLUIDS.register("flowing_holy_water", () -> new BaseFlowingFluid.Flowing(CCFluid.HOLY_WATER_FLUID_PROPERTIES));

    public static final BaseFlowingFluid.Properties HOLY_WATER_FLUID_PROPERTIES = new BaseFlowingFluid.Properties(CCFluidType.HOLY_WATER_FLUID_TYPE, SOURCE_HOLY_WATER, FLOWING_HOLY_WATER)
            .slopeFindDistance(8).levelDecreasePerBlock(1).block(() -> (LiquidBlock) CCBlock.HOLY_WATER_BLOCK.get()).bucket(CCItem.HOLY_WATER_BUCKET);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
