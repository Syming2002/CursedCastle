package com.syming.cursedcastle.datagen;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.tag.CCTag;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class CCBlockTagGenerator extends BlockTagsProvider {
    public CCBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CursedCastleMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        for (Block block : ForgeRegistries.BLOCKS.getValues()) {
            tag(CCTag.Blocks.ALL_BLOCKS).add(block);
        }

        for (WoodType woodType : WoodType.values().toList()) {
            if (woodType != null) {
                if (woodType == WoodType.OAK
                        || woodType == WoodType.SPRUCE
                        || woodType == WoodType.BIRCH
                        || woodType == WoodType.JUNGLE
                        || woodType == WoodType.ACACIA
                        || woodType == WoodType.DARK_OAK
                        || woodType == WoodType.CRIMSON
                        || woodType == WoodType.WARPED
                        || woodType == WoodType.MANGROVE
                        || woodType == WoodType.CHERRY
                        || woodType == WoodType.BAMBOO) {
                    tag(CCTag.Blocks.CRUCIFIX_BLOCKS)
                            .add(CCBlock.CRUCIFIX_BLOCK_TYPES.get(woodType).get());
                    tag(BlockTags.MINEABLE_WITH_AXE).add(CCBlock.CRUCIFIX_BLOCK_TYPES.get(woodType).get());
                }
            }
        }

        tag(BlockTags.NEEDS_STONE_TOOL).add(CCBlock.CHURCH_ALTAR.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(CCBlock.CHURCH_ALTAR.get());

        tag(CCTag.Blocks.BLOOD_BLOCKS)
                .add(CCBlock.BLOOD_BLOCK.get());

        tag(CCTag.Blocks.ICE_BLOCKS)
                .add(Blocks.ICE, Blocks.BLUE_ICE, Blocks.FROSTED_ICE, Blocks.PACKED_ICE);

        tag(CCTag.Blocks.FIRE_BLOCKS)
                .add(Blocks.MAGMA_BLOCK, Blocks.FIRE, Blocks.SOUL_FIRE, Blocks.LAVA);
    }
}
