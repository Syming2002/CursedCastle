package com.syming.cursedcastle.datagen;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.block.CrucifixBlock;
import com.syming.cursedcastle.block.head.CCHeadBlock;
import com.syming.cursedcastle.block.head.CCWallHeadBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
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

        for (WoodType woodType : WoodType.values().toList()) {
            crucifixBlock(CCBlock.CRUCIFIX_BLOCK_TYPES.get(woodType).get(), woodType);
        }
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

    private void crucifixBlock(Block crucifixBlock, WoodType woodType) {
        getVariantBuilder(crucifixBlock)
                .forAllStates(state -> {
                    Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
                    int yRot = switch (direction) {
                        case NORTH -> 0;
                        case SOUTH -> 180;
                        case EAST -> 90;
                        case WEST -> 270;
                        default -> 0;
                    };
                    return ConfiguredModel.builder()
                            .modelFile(new ModelFile.UncheckedModelFile(modLoc("block/" + woodType.name() + '_' + "crucifix")))
                            .rotationY(yRot)
                            .build();
                });
        simpleBlockItem(crucifixBlock, new ModelFile.UncheckedModelFile(modLoc("block/" + woodType.name() + '_' + "crucifix")));
    }
}
