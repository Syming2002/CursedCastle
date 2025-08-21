package com.syming.castlevaniacraft.world.gen.structure.draculcastle;

import com.syming.castlevaniacraft.block.CCBlock;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

public class StructureDraculaCastle extends StructureComponent {

    public StructureDraculaCastle() {}

    @Override
    protected void writeStructureToNBT(NBTTagCompound tagCompound) {

    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager p_143011_2_) {

    }

    @Override
    public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
        generateDraculaCastle(worldIn, randomIn, structureBoundingBoxIn);
        return true;
    }

    private void generateDraculaCastle(World world, Random random, StructureBoundingBox structureBoundingBox) {
        int radius = 4 + random.nextInt(6);

        for (int x = -radius; x <= radius; ++x) {
            for (int z = -radius; z <= radius; ++z) {
                for (int y = -radius; y <= radius; ++y) {
                    double distance = Math.sqrt(x * x + y * y + z * z);

                    if (Math.abs(distance - radius) < 0.5) {
                        setBlockState(world, getRandomDeepslateStone(random), x, y, z, structureBoundingBox);
                    } else if (distance <= radius) {
                        if (y == 0) {
                            setBlockState(world, getRandomDeepslateStone(random), x, y, z, structureBoundingBox);
                        } else if (y < 0) {
                            setBlockState(world, getRandomDeepslateStone(random), x, y, z, structureBoundingBox);
                        } else {
                            setBlockState(world, getRandomDeepslateStone(random), x, y, z, structureBoundingBox);
                        }
                    }
                }
            }
        }
    }

    private IBlockState getRandomDeepslateStone(Random random) {
        IBlockState draculaStoneBrick = CCBlock.DRACULA_CASTLE_STONE_BRICK.getDefaultState();
        IBlockState draculaStone = CCBlock.DRACULA_CASTLE_STONE.getDefaultState();


        float randomStone = random.nextFloat();

        if (randomStone < 0.33F) {
            return draculaStone;
        } else {
            return draculaStoneBrick;
        }
    }

    private IBlockState getDraculaStoneBrickStairsFacingNorth() {
        return CCBlock.DRACULA_CASTLE_STONEBRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH);
    }

    private IBlockState getDraculaStoneBrickWall() {
        return CCBlock.DRACULA_CASTLE_STONEBRICK_WALL.getDefaultState();
    }

    private IBlockState getDraculaStoneBrickSlabBottom() {
        return CCBlock.DRACULA_CASTLE_STONEBRICK_SLAB_HALF.getDefaultState().withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM);
    }
}
