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
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD)), Ingredient.of(CCItem.BLOOD_SKELETON_BONE.get()), PotionUtils.setPotion(new ItemStack(Items.POTION), CCPotion.ANTI_BLOOD_SKELETON.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), CCPotion.ANTI_BLOOD_SKELETON.get())), Ingredient.of(Items.REDSTONE), PotionUtils.setPotion(new ItemStack(Items.POTION), CCPotion.LONG_ANTI_BLOOD_SKELETON.get())));
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), CCPotion.ANTI_BLOOD_SKELETON.get())), Ingredient.of(Items.GLOWSTONE_DUST), PotionUtils.setPotion(new ItemStack(Items.POTION), CCPotion.STRONG_ANTI_BLOOD_SKELETON.get())));
    }

}
