package com.syming.castlevaniacraft.world.gen.structure.draculcastle;

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

    private IBlockState getRandomStone(Random random) {
        IBlockState draculaCobblestone = CCBlock.DRACULA_CASTLE_COBBLESTONE.getDefaultState();
        IBlockState draculaStone = CCBlock.DRACULA_CASTLE_STONE.getDefaultState();
        IBlockState draculaStonebrick = CCBlock.DRACULA_CASTLE_STONE_BRICK.getDefaultState();

        float randomStone = random.nextFloat();

        if (randomStone < 0.35F) {
            return draculaCobblestone;
        } else if (randomStone < 0.45F) {
            return draculaStone;
        } else {
            return draculaStonebrick;
        }
    }

    private IBlockState getDraculaStoneStair() {
        return CCBlock.DRACULA_CASTLE_STONE_STAIRS.getDefaultState();
    }

    private IBlockState getDraculaStonebrickWall() {
        return CCBlock.DRACULA_CASTLE_STONE_WALL.getDefaultState();
    }

    private IBlockState getDraculaStonebrickSlab() {
        return CCBlock.DRACULA_CASTLE_STONE_SLAB_HALF.getDefaultState();
    }
}
