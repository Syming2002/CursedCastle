package com.syming.cursedcastle.recipe;

import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.potion.CCPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

public class BrewingStandRecipe {

    public static void registerRecipes() {
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD)), Ingredient.of(Items.PACKED_ICE), PotionUtils.setPotion(new ItemStack(Items.POTION), CCPotion.FREEZE.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), CCPotion.FREEZE.get())), Ingredient.of(Items.REDSTONE), PotionUtils.setPotion(new ItemStack(Items.POTION), CCPotion.LONG_FREEZE.get())));

        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD)), Ingredient.of(Items.ENCHANTED_GOLDEN_APPLE), PotionUtils.setPotion(new ItemStack(Items.POTION), CCPotion.HOLY.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), CCPotion.HOLY.get())), Ingredient.of(Items.REDSTONE), PotionUtils.setPotion(new ItemStack(Items.POTION), CCPotion.LONG_HOLY.get())));
    }

}
