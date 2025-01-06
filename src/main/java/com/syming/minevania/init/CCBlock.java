package com.syming.minevania.init;

import com.syming.minevania.block.BlockBloodFluid;
import com.syming.minevania.block.BlockHolyWaterFluid;
import com.syming.minevania.block.draculacastlestone.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class CCBlock {

    public static final List<Block> BLOCKS = new ArrayList<>();

    // Dracula's Castle Stone Blocks
    public static final Block DRACULA_CASTLE_STONE = new BlockDraculaCastleStone("dracula_castle_stone");
    public static final Block DRACULA_CASTLE_COBBLESTONE = new BlockDraculaCastleCobblestone("dracula_castle_cobblestone");
    public static final Block DRACULA_CASTLE_STONE_BRICK = new BlockDraculaCastleStoneBrick("dracula_castle_stonebrick");
    public static final BlockStairs DRACULA_CASTLE_STONE_STAIRS = new BlockDraculaCastleStoneStairs("dracula_castle_stonebrick_stairs", CCBlock.DRACULA_CASTLE_STONE_BRICK.getDefaultState().withProperty(BlockDraculaCastleStoneBrick.VARIANT, BlockDraculaCastleStoneBrick.EnumType.DEFAULT));
    public static final BlockSlab DRACULA_CASTLE_STONE_SLAB_DOUBLE = new BlockDoubleDraculaCastleStoneSlab("dracula_castle_stonebrick_slab_double", Material.ROCK, CCBlock.DRACULA_CASTLE_STONE_SLAB_HALF);
    public static final BlockSlab DRACULA_CASTLE_STONE_SLAB_HALF = new BlockHalfDraculaCastleStoneSlab("dracula_castle_stonebrick_slab_half", Material.ROCK, CCBlock.DRACULA_CASTLE_STONE_SLAB_HALF, CCBlock.DRACULA_CASTLE_STONE_SLAB_DOUBLE);
    public static final BlockDraculaCastleStoneWall DRACULA_CASTLE_STONE_WALL = new BlockDraculaCastleStoneWall("dracula_castle_stonebrick_wall", CCBlock.DRACULA_CASTLE_STONE_BRICK);

    // Fluids

    public static final Block HOLY_WATER_FLUID = new BlockHolyWaterFluid("holy_water_fluid", FluidInit.HOLY_WATER_FLUID, Material.WATER);
    public static final Block BLOOD_FLUID = new BlockBloodFluid("blood_fluid", FluidInit.BLOOD_FLUID, Material.WATER);




}
