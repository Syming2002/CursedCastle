package com.syming.castlevaniacraft.integration.forestry.recipes;

import com.syming.castlevaniacraft.integration.forestry.bees.CCBeeDefinition;
import com.syming.castlevaniacraft.integration.forestry.items.CCCombType;
import com.syming.castlevaniacraft.integration.forestry.items.CCDropType;

import forestry.api.recipes.RecipeManagers;
import forestry.apiculture.ModuleApiculture;
import forestry.core.ModuleCore;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

import static com.syming.castlevaniacraft.integration.forestry.bees.CCBeeDefinition.getCCComb;

public class CentrifugeRecipes {

    public static void register() {
        centrifugeRecipes();
    }

    private static void centrifugeRecipes() {
        Map<ItemStack, Float> rottenCombProducts = new HashMap<>();

        Map<ItemStack, Float> boneCombProducts = new HashMap<>();

        Map<ItemStack, Float> bloodCombProducts = new HashMap<>();

        Map<ItemStack, Float> frozenCombProducts = new HashMap<>();

        Map<ItemStack, Float> holyWaterCombProducts = new HashMap<>();

        Map<ItemStack, Float> warmedCombProducts = new HashMap<>();

        ItemStack rottenFlesh = new ItemStack(Items.ROTTEN_FLESH);

        ItemStack bone = new ItemStack(Items.BONE);

        ItemStack boneMeal = new ItemStack(Items.DYE, 1, 15);

        ItemStack beeswax = ModuleCore.getItems().beeswax.getItemStack();

        ItemStack honeyDrop = ModuleApiculture.getItems().honeyDrop.getItemStack();

        ItemStack bloodDrop = CCBeeDefinition.getCCDrop(CCDropType.BLOOD);

        ItemStack frozenDrop = CCBeeDefinition.getCCDrop(CCDropType.FROZEN);

        ItemStack holyWaterDrop = CCBeeDefinition.getCCDrop(CCDropType.HOLY);

        ItemStack warmedDrop = CCBeeDefinition.getCCDrop(CCDropType.WARMED);

        rottenCombProducts.put(beeswax, 0.8F);
        rottenCombProducts.put(honeyDrop, 0.5F);
        rottenCombProducts.put(rottenFlesh, 0.3F);

        boneCombProducts.put(beeswax, 0.8F);
        boneCombProducts.put(honeyDrop, 0.5F);
        boneCombProducts.put(bone, 0.25F);
        boneCombProducts.put(boneMeal, 0.25F);

        bloodCombProducts.put(beeswax, 0.8F);
        bloodCombProducts.put(honeyDrop, 0.5F);
        bloodCombProducts.put(bloodDrop, 0.25F);

        frozenCombProducts.put(beeswax, 0.8F);
        frozenCombProducts.put(honeyDrop, 0.5F);
        frozenCombProducts.put(frozenDrop, 0.25F);

        holyWaterCombProducts.put(beeswax, 0.8F);
        holyWaterCombProducts.put(honeyDrop, 0.5F);
        holyWaterCombProducts.put(holyWaterDrop, 0.25F);

        warmedCombProducts.put(beeswax, 0.8F);
        warmedCombProducts.put(honeyDrop, 0.5F);
        warmedCombProducts.put(warmedDrop, 0.25F);

        RecipeManagers.centrifugeManager.addRecipe(20, getCCComb(CCCombType.HOLY), holyWaterCombProducts);

        RecipeManagers.centrifugeManager.addRecipe(20, getCCComb(CCCombType.BLOOD), bloodCombProducts);

        RecipeManagers.centrifugeManager.addRecipe(20, getCCComb(CCCombType.ROTTEN), rottenCombProducts);

        RecipeManagers.centrifugeManager.addRecipe(20, getCCComb(CCCombType.BONE), boneCombProducts);

        RecipeManagers.centrifugeManager.addRecipe(20, getCCComb(CCCombType.FROZEN), frozenCombProducts);

        RecipeManagers.centrifugeManager.addRecipe(20, getCCComb(CCCombType.WARMED), warmedCombProducts);
    }

}
