package com.syming.castlevaniacraft.world.gen.structure.draculcastle;

import com.deeperdepths.common.blocks.BlockDDStairs;
import com.deeperdepths.common.blocks.BlockDDStone;
import com.deeperdepths.common.blocks.DeeperDepthsBlocks;
import com.deeperdepths.common.blocks.enums.EnumStoneType;
import com.syming.castlevaniacraft.block.CCBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class StructureDraculaCastle extends StructureComponent {
    @Override
    protected void writeStructureToNBT(NBTTagCompound tagCompound) {

    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {

    }

    @Override
    public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
        
        return false;
    }

    private void generateDraculaCastle(World world, Random random, StructureBoundingBox structureBoundingBox) {

    }

    private IBlockState getRandomDeepslateStone(Random random) {
        IBlockState deepslate = DeeperDepthsBlocks.DEEPSLATE.getDefaultState();
        IBlockState deepslateTile = DeeperDepthsBlocks.STONE.getDefaultState().withProperty(BlockDDStone.VARIANT, EnumStoneType.DEEPSLATE_TILE);
        IBlockState crackedDeepslateTile = DeeperDepthsBlocks.STONE.getDefaultState().withProperty(BlockDDStone.VARIANT, EnumStoneType.CRACKED_DEEPSLATE_TILE);
        IBlockState cobbledDeepslate = DeeperDepthsBlocks.STONE.getDefaultState().withProperty(BlockDDStone.VARIANT, EnumStoneType.COBBLED_DEEPSLATE);
        IBlockState deepslateBrick = DeeperDepthsBlocks.STONE.getDefaultState().withProperty(BlockDDStone.VARIANT, EnumStoneType.DEEPSLATE_BRICK);
        IBlockState deepslateStone = DeeperDepthsBlocks.STONE.getDefaultState();

        float randomStone = random.nextFloat();

        if (randomStone < 0.15F) {
            return crackedDeepslateTile;
        } else if (randomStone < 0.25F) {
            return deepslateTile;
        } else if (randomStone < 0.35F) {
            return deepslateBrick;
        } else if (randomStone < 0.45F) {
            return deepslateStone;
        } else if (randomStone < 0.55F) {
            return cobbledDeepslate;
        } else {
            return deepslate;
        }
    }

    private IBlockState getDeepslateStairsFacingNorth(EnumStoneType type) {
        return DeeperDepthsBlocks.STONE_STAIRS.get(type).getDefaultState();
    }

    private IBlockState getDeepslateStoneWall() {
        return DeeperDepthsBlocks.STONE_WALL.getDefaultState();
    }

    private IBlockState getDeepslateStoneSlab() {
        return DeeperDepthsBlocks.STONE_SLAB.getDefaultState();
    }
}
