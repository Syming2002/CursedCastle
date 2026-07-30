package com.syming.cursedcastle.datagen;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.block.CrucifixBlock;
import com.syming.cursedcastle.block.DraculaCastleStoneBrickBlock;
import com.syming.cursedcastle.block.head.CCHeadBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CCBlockModelProvider extends BlockModelProvider {
    public CCBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CursedCastleMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (RegistryObject<Block> registryObject : CCBlock.BLOCKS.getEntries()) {

            final Block block = registryObject.get();
            final String path = ForgeRegistries.BLOCKS.getKey(block).getPath();
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
