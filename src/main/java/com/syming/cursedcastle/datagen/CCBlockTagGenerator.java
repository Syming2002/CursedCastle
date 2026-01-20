package com.syming.cursedcastle.datagen;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.tag.CCTag;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class CCBlockTagGenerator extends BlockTagsProvider {
    public CCBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CursedCastleMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        for (WoodType woodType : WoodType.values().toList()) {
            tag(CCTag.Blocks.CRUCIFIX_BLOCKS)
                    .add(CCBlock.CRUCIFIX_BLOCK_TYPES.get(woodType).get());
        }
        tag(CCTag.Blocks.BLOOD_BLOCKS)
                .add(CCBlock.BLOOD_BLOCK.get());

        tag(CCTag.Blocks.ICE_BLOCKS)
                .add(Blocks.ICE, Blocks.BLUE_ICE, Blocks.FROSTED_ICE, Blocks.PACKED_ICE);

        tag(CCTag.Blocks.FIRE_BLOCKS)
                .add(Blocks.MAGMA_BLOCK, Blocks.FIRE, Blocks.SOUL_FIRE, Blocks.LAVA);
    }
}
