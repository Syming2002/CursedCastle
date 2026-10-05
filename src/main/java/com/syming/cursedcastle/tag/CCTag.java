package com.syming.cursedcastle.tag;

import com.syming.cursedcastle.CursedCastleMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class CCTag {

    public static class Blocks {
        public static final TagKey<Block> ALL_BLOCKS = tag("all_blocks");
        public static final TagKey<Block> CRUCIFIX_BLOCKS = tag("crucifix");
        public static final TagKey<Block> BLOOD_BLOCKS = tag("blood");
        public static final TagKey<Block> ICE_BLOCKS = tag("ice");
        public static final TagKey<Block> FIRE_BLOCKS = tag("fire");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(CursedCastleMod.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> BULLET_SOULS = tag("bullet_souls");
        public static final TagKey<Item> ENCHANTED_SOULS = tag("enchanted_souls");
        public static final TagKey<Item> GUARDIAN_SOULS = tag("guardian_souls");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(CursedCastleMod.MOD_ID, name));
        }
    }
}
