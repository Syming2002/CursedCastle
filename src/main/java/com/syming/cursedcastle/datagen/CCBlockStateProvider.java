package com.syming.cursedcastle.datagen;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.block.head.CCHeadBlock;
import com.syming.cursedcastle.block.head.CCWallHeadBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class CCBlockStateProvider extends BlockStateProvider {
    public CCBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CursedCastleMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(CCBlock.DRACULA_CASTLE_COBBLESTONE);
        blockWithItem(CCBlock.DRACULA_CASTLE_STONE);
        blockWithItem(CCBlock.DRACULA_CASTLE_STONE_BRICKS);


        slabBlock(CCBlock.DRACULA_CASTLE_STONE_BRICKS_SLAB.get(), blockTexture(CCBlock.DRACULA_CASTLE_STONE_BRICKS.get()), blockTexture(CCBlock.DRACULA_CASTLE_STONE_BRICKS.get()));

        skull(CCBlock.DRACULA_HEAD);
        skullWall(CCBlock.DRACULA_WALL_HEAD);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void skull(RegistryObject<Block> skullBlock) {
        simpleBlock(skullBlock.get(), models().getExistingFile(mcLoc("block/skull")));
    }

    private void skullWall(RegistryObject<Block> skullBlock) {
        simpleBlock(skullBlock.get(), models().getExistingFile(mcLoc("block/skull")));
    }
}
