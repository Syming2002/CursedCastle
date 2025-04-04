package com.syming.castlevaniacraft.block;

import com.syming.castlevaniacraft.block.draculacastlestone.*;
import com.syming.castlevaniacraft.fluid.CCFluid;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;

import java.util.ArrayList;
import java.util.List;

public class CCBlock {

    public static final List<Block> BLOCKS = new ArrayList<>();

    // Dracula's Castle Stone Blocks
    public static final Block DRACULA_CASTLE_STONE = new BlockDraculaCastleStone("dracula_castle_stone");
    public static final Block DRACULA_CASTLE_COBBLESTONE = new BlockDraculaCastleCobblestone("dracula_castle_cobblestone");
    public static final Block DRACULA_CASTLE_STONE_BRICK = new BlockDraculaCastleStoneBrick("dracula_castle_stonebrick");
    public static final BlockStairs DRACULA_CASTLE_STONE_STAIRS = new BlockDraculaCastleStoneStairs("dracula_castle_stonebrick_stairs", CCBlock.DRACULA_CASTLE_STONE_BRICK.getDefaultState().withProperty(BlockDraculaCastleStoneBrick.VARIANT, BlockDraculaCastleStoneBrick.EnumType.DEFAULT));
    public static final BlockSlab DRACULA_CASTLE_STONE_SLAB_DOUBLE = new BlockDoubleDraculaCastleStoneSlab("dracula_castle_stonebrick_slab_double");
    public static final BlockSlab DRACULA_CASTLE_STONE_SLAB_HALF = new BlockHalfDraculaCastleStoneSlab("dracula_castle_stonebrick_slab_half");
    public static final BlockDraculaCastleStoneWall DRACULA_CASTLE_STONE_WALL = new BlockDraculaCastleStoneWall("dracula_castle_stonebrick_wall", CCBlock.DRACULA_CASTLE_STONE_BRICK);

    // Fluids

    public static final BlockFluidClassic HOLY_WATER_FLUID = new BlockHolyWaterFluid("holy_water_fluid", CCFluid.HOLY_WATER_FLUID, Material.WATER);
    public static final BlockFluidClassic BLOOD_FLUID = new BlockBloodFluid("blood_fluid", CCFluid.BLOOD_FLUID, Material.WATER);


    // Tile Blocks

    public static final Block ALCHEMY_TABLE = new BlockAlchemyTable("alchemy_table");


}
