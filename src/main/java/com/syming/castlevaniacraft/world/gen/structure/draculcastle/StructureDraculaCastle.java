package com.syming.castlevaniacraft.world.gen.structure.draculcastle;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPrismarine;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
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

    private void addEntranceDraculaCastle(World world, Random random, StructureBoundingBox structureBoundingBox) {

        
    }

    private IBlockState getRandomStone(Random random) {
        IBlockState cobblestone = Blocks.COBBLESTONE.getDefaultState();
        IBlockState stone = Blocks.STONE.getDefaultState();
        IBlockState stoneBrick = Blocks.STONEBRICK.getDefaultState();
        IBlockState mossyStoneBrick = stoneBrick.withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY);

        float randomStone = random.nextFloat();

        if (randomStone < 0.25F) {
            return mossyStoneBrick;
        } else if (randomStone < 0.35F) {
            return cobblestone;
        } else if (randomStone < 0.45F) {
            return stone;
        } else {
            return stoneBrick;
        }
    }
}
