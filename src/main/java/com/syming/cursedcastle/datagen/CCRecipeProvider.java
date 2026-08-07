package com.syming.cursedcastle.datagen;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.item.CCItem;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.util.Map;
import java.util.function.Consumer;

public class CCRecipeProvider extends RecipeProvider {

    private static final Map<Tier, TagKey<Item>> MATERIAL_ITEMS = Map.of(
            Tiers.WOOD, ItemTags.PLANKS,
            Tiers.STONE, Tags.Items.COBBLESTONE,
            Tiers.IRON, Tags.Items.INGOTS_IRON,
            Tiers.GOLD, Tags.Items.INGOTS_GOLD,
            Tiers.DIAMOND, Tags.Items.GEMS_DIAMOND,
            Tiers.NETHERITE, Tags.Items.INGOTS_NETHERITE
    );

    public CCRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        buildLeatherString(pWriter);
        buildGoldenNugget(pWriter);
        buildLeatherWhip(pWriter);

        buildHolyCross(pWriter);
        buildThrowingAxe(pWriter);
    }

    private void buildLeatherWhip(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, CCItem.LEATHER_WHIP.get())
                .pattern("  L")
                .pattern(" L ")
                .pattern("L  ")
                .define('L', CCItem.LEATHER_STRING.get())
                .unlockedBy(getHasName(CCItem.LEATHER_STRING.get()), has(CCItem.LEATHER_STRING.get()))
                .save(pWriter);
    }

    private void buildLeatherString(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CCItem.LEATHER_STRING.get())
                .pattern(" L ")
                .pattern("L L")
                .pattern(" L ")
                .define('L', Items.LEATHER)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
                .save(pWriter);
    }

    private void buildGoldenNugget(Consumer<FinishedRecipe> pWriter) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GOLD_NUGGET, 4)
                .requires(CCItem.GOLDEN_BONE.get())
                .unlockedBy(getHasName(CCItem.GOLDEN_BONE.get()), has(CCItem.GOLDEN_BONE.get()))
                .save(pWriter);
    }

    private void buildHolyCross(Consumer<FinishedRecipe> pWriter) {
        for (var entry : MATERIAL_ITEMS.entrySet()) {
            Tier tier = entry.getKey();
            TagKey<Item> material = entry.getValue();

            if (tier == Tiers.NETHERITE) {
                SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(CCItem.HOLY_CROSS_ITEM_TYPES.get(Tiers.DIAMOND).get()),
                        Ingredient.of(Items.NETHERITE_INGOT),
                        RecipeCategory.COMBAT,
                        CCItem.HOLY_CROSS_ITEM_TYPES.get(Tiers.NETHERITE).get())
                        .unlocks(getHasName(CCItem.HOLY_CROSS_ITEM_TYPES.get(Tiers.DIAMOND).get()), has(material))
                        .save(pWriter, new ResourceLocation(CursedCastleMod.MOD_ID, "netherite_holy_cross"));
            } else {
                ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, CCItem.HOLY_CROSS_ITEM_TYPES.get(tier).get())
                        .pattern(" M ")
                        .pattern("MMM")
                        .pattern(" M ")
                        .define('M', material)
                        .unlockedBy(getHasName(CCItem.HOLY_CROSS_ITEM_TYPES.get(tier).get()), has(CCItem.HOLY_CROSS_ITEM_TYPES.get(tier).get()))
                        .save(pWriter);
            }
        }
    }

    private void buildThrowingAxe(Consumer<FinishedRecipe> pWriter) {
        for (var entry : MATERIAL_ITEMS.entrySet()) {
            Tier tier = entry.getKey();
            TagKey<Item> material = entry.getValue();

            if (tier == Tiers.NETHERITE) {
                SmithingTransformRecipeBuilder.smithing(
                                Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                                Ingredient.of(CCItem.THROWING_AXE_ITEM_TYPES.get(Tiers.DIAMOND).get()),
                                Ingredient.of(Items.NETHERITE_INGOT),
                                RecipeCategory.COMBAT,
                                CCItem.THROWING_AXE_ITEM_TYPES.get(Tiers.NETHERITE).get())
                        .unlocks(getHasName(CCItem.THROWING_AXE_ITEM_TYPES.get(Tiers.DIAMOND).get()), has(material))
                        .save(pWriter, new ResourceLocation(CursedCastleMod.MOD_ID, "netherite_throwing_axe"));
            } else {
                ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, CCItem.THROWING_AXE_ITEM_TYPES.get(tier).get())
                        .pattern("MMS")
                        .pattern("MSM")
                        .pattern("SMM")
                        .define('M', material)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(CCItem.THROWING_AXE_ITEM_TYPES.get(tier).get()), has(CCItem.THROWING_AXE_ITEM_TYPES.get(tier).get()))
                        .save(pWriter);
            }
        }
    }
}
