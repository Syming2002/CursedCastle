package com.syming.cursedcastle.datagen;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.block.CrucifixBlock;
import com.syming.cursedcastle.block.DraculaCastleStoneBrickBlock;
import com.syming.cursedcastle.block.head.CCHeadBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

public class CCBlockModelProvider extends BlockModelProvider {
    public CCBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CursedCastleMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (DeferredHolder<Block, ? extends Block> holder : CCBlock.BLOCKS.getEntries()) {

            final Block block = holder.get();
            final String path = BuiltInRegistries.BLOCK.getKey(block).getPath();
            if (block instanceof CCHeadBlock ccHeadBlock) {
                SkullBlock.Type type = ccHeadBlock.getType();
                if (type == CCHeadBlock.CCHeadTypes.DRACULA) {
                    registerDraculaHeadBlock(path);
                }
            }
        }
    }

    private void registerDraculaHeadBlock(String name) {
        getBuilder(name).parent(new ModelFile.UncheckedModelFile("block/skull"));
    }
}
