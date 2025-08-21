package com.syming.cursedcastle.tag;

import com.syming.cursedcastle.CursedCastleMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class CCTag {

    public static class Blocks {
        public static final TagKey<Block> ALL_BLOCKS = tag("all_blocks");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(CursedCastleMod.MOD_ID, name));
        }
    }
}
