package com.syming.castlevaniacraft.integration.forestry.recipes;

import com.syming.castlevaniacraft.block.CCBlock;
import com.syming.castlevaniacraft.fluid.CCFluid;
import com.syming.castlevaniacraft.fluid.FluidBlood;
import com.syming.castlevaniacraft.integration.forestry.items.CCDropType;
import forestry.api.recipes.RecipeManagers;
import forestry.apiculture.ModuleApiculture;
import forestry.core.ModuleCore;
import forestry.core.fluids.BlockForestryFluid;
import forestry.core.fluids.FluidHelper;
import forestry.core.fluids.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import static com.syming.castlevaniacraft.integration.forestry.bees.CCBeeDefinition.getCCDrop;

public class SqueezerRecipes {

    public static void register() {
        squeezerRecipes();
    }

    private static void squeezerRecipes() {

        Fluid blood = CCFluid.BLOOD_FLUID;
        Fluid holyWater = CCFluid.HOLY_WATER_FLUID;
        Fluid ice = Fluids.ICE.getFluid();
        Fluid lava = FluidRegistry.LAVA;
        ItemStack propolis = ModuleApiculture.getItems().propolis.getItemStack();

        RecipeManagers.squeezerManager.addRecipe(
                20,
                getCCDrop(CCDropType.BLOOD),
                new FluidStack(blood, 25),
                propolis,
                5
        );

        RecipeManagers.squeezerManager.addRecipe(
                20,
                getCCDrop(CCDropType.FROZEN),
                new FluidStack(ice, 25),
                propolis,
                5
        );

        RecipeManagers.squeezerManager.addRecipe(
                20,
                getCCDrop(CCDropType.HOLY),
                new FluidStack(holyWater, 25),
                propolis,
                5
        );

        RecipeManagers.squeezerManager.addRecipe(
                20,
                getCCDrop(CCDropType.WARMED),
                new FluidStack(lava, 25),
                propolis,
                5
        );
    }


}
