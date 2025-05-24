package com.syming.castlevaniacraft.world.gen.structure.church;

import com.syming.castlevaniacraft.entity.EntityCrucifix;
import com.syming.castlevaniacraft.init.VillagerInit;
import com.syming.castlevaniacraft.loot.CCTreasures;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.registries.ForgeRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class StructureBigChurch extends StructureVillagePieces.Village {

    private Biome biome = this.startPiece.biome;

    public StructureBigChurch() {}

    public StructureBigChurch(StructureVillagePieces.Start start, int type, Random random, StructureBoundingBox boundingBox, EnumFacing facing) {
        super(start, type);
        setCoordBaseMode(facing);
        this.boundingBox = boundingBox;
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound tagCompound) {}

    @Override
    protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager templateManager) {}

    @Override
    public boolean addComponentParts(World world, Random rand, StructureBoundingBox structureBoundingBoxIn) {
        if (this.averageGroundLvl < 0) {
            this.averageGroundLvl = this.getAverageGroundLevel(world, structureBoundingBoxIn);

            if (this.averageGroundLvl < 0) {
                return true;
            }

            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 42 - 1, 0);
        }

        int baseY = this.boundingBox.minY;
        for (int i = this.boundingBox.minX; i <= this.boundingBox.maxX; i++) {
            for (int k = this.boundingBox.minZ; k <= this.boundingBox.maxZ; k++) {
                BlockPos checkPos = new BlockPos(i, baseY - 1, k);
                IBlockState blockStateBelow = world.getBlockState(checkPos);

                if (blockStateBelow.getMaterial().isLiquid()) {
                    return false;
                }
            }
        }

        generateBigChurch(world, rand, structureBoundingBoxIn);
        return true;
    }

    @Nullable
    public static StructureBigChurch buildComponent(StructureVillagePieces.Start startPieces, List<StructureComponent> pieces, Random random, int structureMinX, int structureMinY, int structureMinZ, EnumFacing facing, int componentType) {

        int xOffset = 48;
        int zOffset = 48;

        structureMinX += xOffset;
        structureMinZ += zOffset;

        StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(structureMinX, structureMinY, structureMinZ, 0, 0, 0, 13, 42, 41, facing);
        if (!canVillageGoDeeper(structureBoundingBox) || StructureComponent.findIntersecting(pieces, structureBoundingBox) != null) {
            return null;
        }

        System.out.println("Big Church buildComponent() at " + structureMinX + ", " + structureMinY + ", " + structureMinZ + "and facing: " + facing);

        return new StructureBigChurch(startPieces, componentType, random, structureBoundingBox, facing);
    }

    private void generateBigChurch(World world, Random rand, StructureBoundingBox structureBoundingBoxIn) {
        IBlockState air = Blocks.AIR.getDefaultState();

        IBlockState cobblestone = this.getBiomeSpecificBlockState(Blocks.COBBLESTONE.getDefaultState());

        IBlockState stairCobblestoneEastBottomOuterLeft = this.getBiomeSpecificBlockState(Blocks.STONE_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.EAST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_LEFT));

        IBlockState stairCobblestoneNorthBottomStraight = this.getBiomeSpecificBlockState(Blocks.STONE_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.NORTH)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT));

        IBlockState stairCobblestoneWestBottomOuterRight = this.getBiomeSpecificBlockState(Blocks.STONE_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.WEST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_RIGHT));

        IBlockState stairCobblestoneEastBottomStraight = this.getBiomeSpecificBlockState(Blocks.STONE_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.EAST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT));

        IBlockState stairCobblestoneWestBottomStraight = this.getBiomeSpecificBlockState(Blocks.STONE_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.WEST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT));

        IBlockState stairCobblestoneSouthBottomOuterRight = this.getBiomeSpecificBlockState(Blocks.STONE_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.SOUTH)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_RIGHT));

        IBlockState stairCobblestoneSouthBottomOuterLeft = this.getBiomeSpecificBlockState(Blocks.STONE_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.SOUTH)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_LEFT));

        IBlockState stairCobblestoneSouthBottomStraight = this.getBiomeSpecificBlockState(Blocks.STONE_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.SOUTH)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT));


        IBlockState glowstone = Blocks.GLOWSTONE.getDefaultState();

        IBlockState slabCobblestoneTop = this.getBiomeSpecificBlockState(Blocks.STONE_SLAB.getDefaultState()
                .withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.COBBLESTONE)
                .withProperty(BlockStoneSlab.HALF, BlockSlab.EnumBlockHalf.TOP));

        IBlockState stairWoodSouthBottomStraight = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.SOUTH)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT));

        IBlockState slabWoodTop = this.getBiomeSpecificBlockState(Blocks.WOODEN_SLAB.getDefaultState()
                .withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.TOP));

        IBlockState stairWoodNorthTopStraight = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.NORTH)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT));

        IBlockState stairCobblestoneWestBottomInnerRight = this.getBiomeSpecificBlockState(Blocks.STONE_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.WEST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.INNER_RIGHT));

        IBlockState stairCobblestoneEastBottomInnerLeft = this.getBiomeSpecificBlockState(Blocks.STONE_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.EAST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.INNER_LEFT));

        int colorMetadata = rand.nextInt(16);
        EnumDyeColor dyeColor = EnumDyeColor.byMetadata(colorMetadata);
        IBlockState stainedGlassRandom = Blocks.STAINED_GLASS.getDefaultState().withProperty(BlockStainedGlass.COLOR, dyeColor);

        IBlockState stairWoodWestTopOuterRight = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.WEST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_RIGHT));

        IBlockState stairWoodNorthTopInnerLeft = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.NORTH)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.INNER_LEFT));

        IBlockState stairWoodWestTopStraight = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.WEST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT));

        IBlockState stairWoodSouthTopOuterRight = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.SOUTH)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_RIGHT));

        IBlockState stairWoodSouthTopInnerRight = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.SOUTH)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.INNER_RIGHT));

        IBlockState stairWoodSouthTopStraight = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.SOUTH)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT));

        IBlockState stairWoodEastTopInnerRight = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.EAST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.INNER_RIGHT));

        IBlockState stairWoodEastTopStraight = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.EAST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT));

        IBlockState stairWoodNorthTopOuterRight = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.NORTH)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_RIGHT));

        IBlockState stairWoodEastTopInnerLeft = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.EAST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.INNER_LEFT));

        IBlockState stairWoodNorthBottomStraight = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.NORTH)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT));

        IBlockState stairWoodWestBottomOuterRight = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.WEST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_RIGHT));

        IBlockState stairWoodNorthBottomInnerLeft = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.NORTH)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.INNER_LEFT));

        IBlockState stairWoodWestBottomStraight = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.WEST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT));

        IBlockState stairWoodSouthBottomOuterRight = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.SOUTH)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_RIGHT));

        IBlockState stairWoodWestBottomInnerLeft = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.WEST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.INNER_LEFT));

        IBlockState stairWoodEastBottomOuterRight = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.EAST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_RIGHT));

        IBlockState stairWoodEastBottomStraight = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.EAST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT));

        IBlockState stairWoodSouthBottomInnerLeft = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.SOUTH)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.INNER_LEFT));

        IBlockState stairWoodNorthBottomOuterRight = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.NORTH)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_RIGHT));

        IBlockState stairWoodEastBottomInnerLeft = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.EAST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.INNER_LEFT));

        IBlockState stairWoodWestTopOuterLeft = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.WEST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_LEFT));

        IBlockState stairWoodSouthTopOuterLeft = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.SOUTH)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.TOP)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_LEFT));

        IBlockState stairWoodEastBottomOuterLeft = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.EAST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_LEFT));

        IBlockState stairWoodSouthBottomOuterLeft = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.SOUTH)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_LEFT));

        IBlockState stairWoodNorthBottomOuterLeft = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.NORTH)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_LEFT));

        IBlockState stairWoodWestBottomInnerRight = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.WEST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.INNER_RIGHT));

        IBlockState stairWoodWestBottomOuterLeft = this.getBiomeSpecificBlockState(Blocks.OAK_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.WEST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_LEFT));

        IBlockState plankWood = this.getBiomeSpecificBlockState(Blocks.PLANKS.getDefaultState());

        IBlockState fence = this.getBiomeSpecificBlockState(Blocks.OAK_FENCE.getDefaultState());

        IBlockState fenceWest = this.getBiomeSpecificBlockState(Blocks.OAK_FENCE.getDefaultState())
                .cycleProperty(BlockFence.WEST);

        IBlockState fenceEast = this.getBiomeSpecificBlockState(Blocks.OAK_FENCE.getDefaultState())
                .cycleProperty(BlockFence.EAST);

        IBlockState trapDoorNorthTopOpen = this.getBiomeSpecificBlockState(Blocks.TRAPDOOR.getDefaultState())
                .withProperty(BlockTrapDoor.FACING, EnumFacing.NORTH)
                .withProperty(BlockTrapDoor.HALF, BlockTrapDoor.DoorHalf.TOP)
                .withProperty(BlockTrapDoor.OPEN, true);

        IBlockState trapDoorSouthTopOpen = this.getBiomeSpecificBlockState(Blocks.TRAPDOOR.getDefaultState())
                .withProperty(BlockTrapDoor.FACING, EnumFacing.SOUTH)
                .withProperty(BlockTrapDoor.HALF, BlockTrapDoor.DoorHalf.TOP)
                .withProperty(BlockTrapDoor.OPEN, true);

        IBlockState trapDoorEastTopOpen = this.getBiomeSpecificBlockState(Blocks.TRAPDOOR.getDefaultState())
                .withProperty(BlockTrapDoor.FACING, EnumFacing.EAST)
                .withProperty(BlockTrapDoor.HALF, BlockTrapDoor.DoorHalf.TOP)
                .withProperty(BlockTrapDoor.OPEN, true);

        IBlockState trapDoorWestTopOpen = this.getBiomeSpecificBlockState(Blocks.TRAPDOOR.getDefaultState())
                .withProperty(BlockTrapDoor.FACING, EnumFacing.WEST)
                .withProperty(BlockTrapDoor.HALF, BlockTrapDoor.DoorHalf.TOP)
                .withProperty(BlockTrapDoor.OPEN, true);

        IBlockState glass = Blocks.GLASS.getDefaultState();

        // Clear Area

//        for (int i = 5; i < 8; ++i) {
//            for (int j = 4; j < 23; ++j) {
//                this.setBlockState(world, air, i, j, 5, structureBoundingBoxIn);
//            }
//        }
//
//        for (int i = 4; i < 9; ++i) {
//            for (int j = 4; j < 23; ++j) {
//                this.setBlockState(world, air, i, j, 6, structureBoundingBoxIn);
//            }
//        }
//
//        for (int i = 3; i < 10; ++i) {
//            for (int j = 4; j < 23; ++j) {
//                this.setBlockState(world, air, i, j, 7, structureBoundingBoxIn);
//            }
//        }
//
//        for (int i = 2; i < 11; ++i) {
//            for (int j = 4; j < 23; ++j) {
//                for (int k = 29; k < 31; ++k) {
//                    this.setBlockState(world, air, i, j, k, structureBoundingBoxIn);
//                }
//            }
//        }
//
//
//
//        for (int i = 3; i < 10; ++i) {
//            for (int j = 5; j < 23; ++j) {
//                for (int k = 29; k < 31; ++k) {
//                    this.setBlockState(world, air, i, j, k, structureBoundingBoxIn);
//                }
//            }
//        }
//
//        for (int i = 4; i < 9; ++i) {
//            for (int j = 5; j < 23; ++j) {
//                for (int k = 31; k < 33; ++k) {
//                    this.setBlockState(world, air, i, j, k, structureBoundingBoxIn);
//                }
//            }
//        }

        // Stairs Entrance

        // Left Side First Stairs
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 0, -2 + 3, 1, 0, 3 + 3, stairCobblestoneEastBottomStraight, stairCobblestoneEastBottomStraight, false);


        // Left Side Second Stairs
        this.fillWithBlocks(world, structureBoundingBoxIn, 2, 0, -1 + 3, 2, 0, 3 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 2, 1, -1 + 3, 2, 1, 3 + 3, stairCobblestoneEastBottomStraight, stairCobblestoneEastBottomStraight, false);


        // Left Side Third Stairs
        this.fillWithBlocks(world, structureBoundingBoxIn, 3, 0, 0 + 3, 3, 1, 3 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 3, 2, 0 + 3, 3, 2, 3 + 3, stairCobblestoneEastBottomStraight, stairCobblestoneEastBottomStraight, false);


        // Left Side Fourth Stairs
        this.fillWithBlocks(world, structureBoundingBoxIn, 4, 0, 1 + 3, 4, 2, 4 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 4, 3, 1 + 3, 4, 3, 3 + 3, stairCobblestoneEastBottomStraight, stairCobblestoneEastBottomStraight, false);


        // First Stairs
        this.fillWithBlocks(world, structureBoundingBoxIn, 2, 0, -3 + 3, 10, 0, -3 + 3, stairCobblestoneNorthBottomStraight, stairCobblestoneNorthBottomStraight, false);


        // Second Stairs
        this.fillWithBlocks(world, structureBoundingBoxIn, 2, 0, -2 + 3, 10, 0, -2 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 3, 1, -2 + 3, 9, 1, -2 + 3, stairCobblestoneNorthBottomStraight, stairCobblestoneNorthBottomStraight, false);


        // Third Stairs
        this.fillWithBlocks(world, structureBoundingBoxIn, 3, 0, -1 + 3, 9, 1, -1 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 4, 2, -1 + 3, 8, 2, -1 + 3, stairCobblestoneNorthBottomStraight, stairCobblestoneNorthBottomStraight, false);


        // Fourth Stairs
        this.fillWithBlocks(world, structureBoundingBoxIn, 4, 0, 0 + 3, 8, 2, 0 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 5, 3, 0 + 3, 7, 3, 0 + 3, stairCobblestoneNorthBottomStraight, stairCobblestoneNorthBottomStraight, false);


        // Left Corner Fourth Stairs
        this.setBlockState(world, stairCobblestoneSouthBottomOuterLeft, 4, 3, 4 + 3, structureBoundingBoxIn);


        // First Stairs - Corners
        this.setBlockState(world, stairCobblestoneEastBottomOuterLeft, 1, 0, -3 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneWestBottomOuterRight, 11, 0, -3 + 3, structureBoundingBoxIn);


        // Second Stairs - Corners
        this.setBlockState(world, stairCobblestoneEastBottomOuterLeft, 2, 1, -2 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneWestBottomOuterRight, 10, 1, -2 + 3, structureBoundingBoxIn);


        // Third Stairs - Corners
        this.setBlockState(world, stairCobblestoneEastBottomOuterLeft, 3, 2, -1 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneWestBottomOuterRight, 9, 2, -1 + 3, structureBoundingBoxIn);


        // Fourth Stairs - Corners
        this.setBlockState(world, stairCobblestoneEastBottomOuterLeft, 4, 3, 0 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneWestBottomOuterRight, 8, 3, 0 + 3, structureBoundingBoxIn);

        // Floor Cobblestone Length
        this.fillWithBlocks(world, structureBoundingBoxIn, 5, 0, 1 + 3, 7, 3, 33 + 3, cobblestone, cobblestone, false);


        // Left side sloped floor (rows 2 to 4)
        this.fillWithBlocks(world, structureBoundingBoxIn, 2, 0, 8 + 3, 2, 3, 28 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 3, 0, 7 + 3, 3, 3, 30 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 4, 0, 6 + 3, 4, 3, 32 + 3, cobblestone, cobblestone, false);


        // Right side sloped floor (rows 8 to 10)
        this.fillWithBlocks(world, structureBoundingBoxIn, 8, 0, 6 + 3, 8, 3, 32 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 9, 0, 7 + 3, 9, 3, 30 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 10, 0, 8 + 3, 10, 3, 28 + 3, cobblestone, cobblestone, false);


        // Right Side Second Stairs platform
        this.fillWithBlocks(world, structureBoundingBoxIn, 10, 0, 0 + 3, 10, 0, 3 + 3, cobblestone, cobblestone, false);


        // Right Side Third Stairs platform
        this.fillWithBlocks(world, structureBoundingBoxIn, 9, 0, 0 + 3, 9, 1, 3 + 3, cobblestone, cobblestone, false);


        // Right Side Fourth Stairs platform
        this.fillWithBlocks(world, structureBoundingBoxIn, 8, 0, 1 + 3, 8, 2, 4 + 3, cobblestone, cobblestone, false);

        // Glowstone placements
        this.setBlockState(world, glowstone, 6, 3, 10 + 3, structureBoundingBoxIn);
        this.setBlockState(world, glowstone, 6, 3, 16 + 3, structureBoundingBoxIn);
        this.setBlockState(world, glowstone, 6, 3, 22 + 3, structureBoundingBoxIn);
        this.setBlockState(world, glowstone, 6, 3, 28 + 3, structureBoundingBoxIn);


        // Right Side First Stairs
        for (int k = -2 + 3; k < 4 + 3; ++k) {
            this.setBlockState(world, stairCobblestoneWestBottomStraight, 11, 0, k, structureBoundingBoxIn);
        }


        // Right Side Second Stairs
        for (int k = -1 + 3; k < 4 + 3; ++k) {
            this.setBlockState(world, stairCobblestoneWestBottomStraight, 10, 1, k, structureBoundingBoxIn);
        }


        // Right Side Third Stairs
        for (int k = 0 + 3; k < 4 + 3; ++k) {
            this.setBlockState(world, stairCobblestoneWestBottomStraight, 9, 2, k, structureBoundingBoxIn);
        }


        // Right Side Fourth Stairs
        for (int k = 1 + 3; k < 4 + 3; ++k) {
            this.setBlockState(world, stairCobblestoneWestBottomStraight, 8, 3, k, structureBoundingBoxIn);
        }


        // Right Corner Fourth Stairs
        this.setBlockState(world, stairCobblestoneSouthBottomOuterRight, 8, 3, 4 + 3, structureBoundingBoxIn);

        // Entrance vertical pillars
        this.fillWithBlocks(world, structureBoundingBoxIn, 5, 4, 4 + 3, 5, 5, 4 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 7, 4, 4 + 3, 7, 5, 4 + 3, cobblestone, cobblestone, false);


        // Entrance central vertical wall
        this.fillWithBlocks(world, structureBoundingBoxIn, 5, 6, 4 + 3, 7, 22, 4 + 3, cobblestone, cobblestone, false);


        // Right Side Church
        this.fillWithBlocks(world, structureBoundingBoxIn, 4, 0, 5 + 3, 4, 22, 5 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 3, 0, 6 + 3, 3, 22, 6 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 2, 0, 7 + 3, 2, 22, 7 + 3, cobblestone, cobblestone, false);


        // Door
        this.createVillageDoor(world, structureBoundingBoxIn, rand, 6, 4, 4 + 3, EnumFacing.NORTH);


        // Side wall bands and borders
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 0, 8 + 3, 1, 22, 11 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 0, 12 + 3, 1, 9, 12 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 17, 12 + 3, 1, 22, 12 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 0, 13 + 3, 1, 8, 13 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 18, 13 + 3, 1, 22, 13 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 0, 14 + 3, 1, 9, 14 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 17, 14 + 3, 1, 22, 14 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 0, 15 + 3, 1, 22, 17 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 0, 15 + 3, 1, 9, 15 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 17, 15 + 3, 1, 22, 15 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 0, 16 + 3, 1, 8, 16 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 18, 16 + 3, 1, 22, 16 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 0, 17 + 3, 1, 9, 17 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 17, 17 + 3, 1, 22, 17 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 0, 18 + 3, 1, 9, 18 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 17, 18 + 3, 1, 22, 18 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 0, 19 + 3, 1, 8, 19 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 18, 19 + 3, 1, 22, 19 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 0, 20 + 3, 1, 9, 20 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 17, 20 + 3, 1, 22, 20 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 0, 21 + 3, 1, 22, 23 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 0, 24 + 3, 1, 9, 24 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 17, 24 + 3, 1, 22, 24 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 0, 25 + 3, 1, 8, 25 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 18, 25 + 3, 1, 22, 25 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 0, 26 + 3, 1, 9, 26 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 17, 26 + 3, 1, 22, 26 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 0, 27 + 3, 1, 22, 28 + 3, cobblestone, cobblestone, false);

        // Right-edge elevation
        this.fillWithBlocks(world, structureBoundingBoxIn, 2, 0, 29 + 3, 2, 22, 30 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 3, 0, 31 + 3, 3, 22, 32 + 3, cobblestone, cobblestone, false);


        // Left Side Church

        // Front columns
        this.fillWithBlocks(world, structureBoundingBoxIn, 8, 0, 5 + 3, 8, 22, 5 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 9, 0, 6 + 3, 9, 22, 6 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 10, 0, 7 + 3, 10, 22, 7 + 3, cobblestone, cobblestone, false);


        // Main wall band
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 0, 8 + 3, 11, 22, 11 + 3, cobblestone, cobblestone, false);


        // Symmetric left-right edges
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 0, 12 + 3, 11, 9, 12 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 17, 12 + 3, 11, 22, 12 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 0, 13 + 3, 11, 8, 13 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 18, 13 + 3, 11, 22, 13 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 0, 14 + 3, 11, 9, 14 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 17, 14 + 3, 11, 22, 14 + 3, cobblestone, cobblestone, false);


        // Central body
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 0, 15 + 3, 11, 22, 17 + 3, cobblestone, cobblestone, false);


        // Edges on central body
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 0, 15 + 3, 11, 9, 15 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 17, 15 + 3, 11, 22, 15 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 0, 16 + 3, 11, 8, 16 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 18, 16 + 3, 11, 22, 16 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 0, 17 + 3, 11, 9, 17 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 17, 17 + 3, 11, 22, 17 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 0, 18 + 3, 11, 9, 18 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 17, 18 + 3, 11, 22, 18 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 0, 19 + 3, 11, 8, 19 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 18, 19 + 3, 11, 22, 19 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 0, 20 + 3, 11, 9, 20 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 17, 20 + 3, 11, 22, 20 + 3, cobblestone, cobblestone, false);

        // Solid rear block
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 0, 21 + 3, 11, 22, 23 + 3, cobblestone, cobblestone, false);


        // Tail end edge pattern
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 0, 24 + 3, 11, 9, 24 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 17, 24 + 3, 11, 22, 24 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 0, 25 + 3, 11, 8, 25 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 18, 25 + 3, 11, 22, 25 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 0, 26 + 3, 11, 9, 26 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 17, 26 + 3, 11, 22, 26 + 3, cobblestone, cobblestone, false);


        // Rear wall strip
        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 0, 27 + 3, 11, 22, 28 + 3, cobblestone, cobblestone, false);


        // Left tapering roof section
        this.fillWithBlocks(world, structureBoundingBoxIn, 10, 0, 29 + 3, 10, 22, 30 + 3, cobblestone, cobblestone, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 9, 0, 31 + 3, 9, 22, 32 + 3, cobblestone, cobblestone, false);

        // Back Church Wall
        this.fillWithBlocks(world, structureBoundingBoxIn, 4, 0, 33 + 3, 8, 22, 33 + 3, cobblestone, cobblestone, false);


        // Inside Church
        spawnVillagers(world, structureBoundingBoxIn, 6, 5, 30 + 3, VillagerInit.villagerHighPriest);
        this.spawnCrucifix(world, structureBoundingBoxIn, 6, 5, 32 + 3);


        // Altar Fence & Light
        this.fillWithBlocks(world, structureBoundingBoxIn, 6, 8, 7 + 3, 6, 9, 7 + 3, fence, fence, false);
        this.setBlockState(world, glowstone, 6, 7, 7 + 3, structureBoundingBoxIn);


        // Trapdoors around glowstone
        this.setBlockState(world, trapDoorSouthTopOpen, 6, 7, 6 + 3, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorNorthTopOpen, 6, 7, 8 + 3, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorWestTopOpen, 5, 7, 7 + 3, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorEastTopOpen, 7, 7, 7 + 3, structureBoundingBoxIn);


        // Ambient Glowstone (symmetric)
        this.setBlockState(world, glowstone, 4, 3, 6 + 3, structureBoundingBoxIn);
        this.setBlockState(world, glowstone, 8, 3, 6 + 3, structureBoundingBoxIn);


        // Right Stairs
        this.setBlockState(world, stairCobblestoneWestBottomStraight, 3, 4, 9 + 3, structureBoundingBoxIn);
        this.setBlockState(world, slabCobblestoneTop, 2, 4, 9 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneWestBottomStraight, 2, 5, 9 + 3, structureBoundingBoxIn);
        this.setBlockState(world, slabCobblestoneTop, 2, 5, 8 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneSouthBottomStraight, 2, 6, 8 + 3, structureBoundingBoxIn);
        this.setBlockState(world, slabCobblestoneTop, 3, 6, 8 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneEastBottomStraight, 3, 7, 8 + 3, structureBoundingBoxIn);
        this.setBlockState(world, slabCobblestoneTop, 3, 7, 7 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneSouthBottomStraight, 3, 8, 7 + 3, structureBoundingBoxIn);
        this.setBlockState(world, slabCobblestoneTop, 4, 8, 7 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneEastBottomStraight, 4, 9, 7 + 3, structureBoundingBoxIn);
        this.setBlockState(world, slabCobblestoneTop, 4, 9, 6 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneSouthBottomStraight, 4, 10, 6 + 3, structureBoundingBoxIn);


        // Left Stairs
        this.setBlockState(world, stairCobblestoneEastBottomStraight, 9, 4, 9 + 3, structureBoundingBoxIn);
        this.setBlockState(world, slabCobblestoneTop, 10, 4, 9 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneEastBottomStraight, 10, 5, 9 + 3, structureBoundingBoxIn);
        this.setBlockState(world, slabCobblestoneTop, 10, 5, 8 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneSouthBottomStraight, 10, 6, 8 + 3, structureBoundingBoxIn);
        this.setBlockState(world, slabCobblestoneTop, 9, 6, 8 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneWestBottomStraight, 9, 7, 8 + 3, structureBoundingBoxIn);
        this.setBlockState(world, slabCobblestoneTop, 9, 7, 7 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneSouthBottomStraight, 9, 8, 7 + 3, structureBoundingBoxIn);
        this.setBlockState(world, slabCobblestoneTop, 8, 8, 7 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneWestBottomStraight, 8, 9, 7 + 3, structureBoundingBoxIn);
        this.setBlockState(world, slabCobblestoneTop, 8, 9, 6 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneSouthBottomStraight, 8, 10, 6 + 3, structureBoundingBoxIn);

        // Floor center cobblestone
        this.fillWithBlocks(world, structureBoundingBoxIn, 5, 10, 7 + 3, 7, 10, 7 + 3, cobblestone, cobblestone, false);


        // Floor slabs (front portion)
        this.fillWithBlocks(world, structureBoundingBoxIn, 5, 10, 5 + 3, 7, 10, 6 + 3, slabCobblestoneTop, slabCobblestoneTop, false);


        // Edge slabs
        this.fillWithBlocks(world, structureBoundingBoxIn, 4, 10, 8 + 3, 8, 10, 8 + 3, slabCobblestoneTop, slabCobblestoneTop, false);
        this.setBlockState(world, slabCobblestoneTop, 2, 10, 8 + 3, structureBoundingBoxIn);
        this.setBlockState(world, slabCobblestoneTop, 10, 10, 8 + 3, structureBoundingBoxIn);


        // Back slab platform
        this.fillWithBlocks(world, structureBoundingBoxIn, 2, 10, 9 + 3, 10, 10, 10 + 3, slabCobblestoneTop, slabCobblestoneTop, false);


        // Villager spawns
        spawnVillagers(world, structureBoundingBoxIn, 6, 11, 6 + 3, VillagerInit.villagerLeaderPriest);
        spawnVillagers(world, structureBoundingBoxIn, 6, 11, 8 + 3, VillagerInit.villagerLeaderPriest);


        // Chests
        this.generateChest(world, structureBoundingBoxIn, rand, 7, 11, 5 + 3, CCTreasures.CHURCH);
        this.generateChest(world, structureBoundingBoxIn, rand, 5, 11, 5 + 3, CCTreasures.CHURCH);


        // Fence
        this.fillWithBlocks(world, structureBoundingBoxIn, 2, 11, 10 + 3, 10, 11, 10 + 3, fence, fence, false);


        // Vertical fence behind altar
        this.fillWithBlocks(world, structureBoundingBoxIn, 6, 19, 5 + 3, 6, 19, 15 + 3, fence, fence, false);


        // Vertical fence front-center
        this.fillWithBlocks(world, structureBoundingBoxIn, 6, 15, 7 + 3, 6, 22, 7 + 3, fence, fence, false);


        // Glowstone altar light
        this.setBlockState(world, glowstone, 6, 14, 7 + 3, structureBoundingBoxIn);


        // Trapdoors around glowstone
        this.setBlockState(world, trapDoorSouthTopOpen, 6, 14, 6 + 3, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorNorthTopOpen, 6, 14, 8 + 3, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorWestTopOpen, 5, 14, 7 + 3, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorEastTopOpen, 7, 14, 7 + 3, structureBoundingBoxIn);


        // Chairs (left and right side)
        for (int z = 10 + 3; z <= 26 + 3; z += 2) {
            for (int x = 2; x < 5; x++) {
                this.setBlockState(world, stairWoodSouthBottomStraight, x, 4, z, structureBoundingBoxIn);
            }
            for (int x = 8; x < 11; x++) {
                this.setBlockState(world, stairWoodSouthBottomStraight, x, 4, z, structureBoundingBoxIn);
            }
        }


        // Left glowstone lanterns
        for (int z = 11 + 3; z <= 27 + 3; z += 2) {
            this.setBlockState(world, glowstone, 2, 3, z, structureBoundingBoxIn);
        }


        // Right glowstone lanterns
        for (int z = 11 + 3; z <= 27 + 3; z += 2) {
            this.setBlockState(world, glowstone, 10, 3, z, structureBoundingBoxIn);
        }


        for (int j = 9; j < 23; ++j) {
            this.setBlockState(world, fence, 6, j, 16 + 3, structureBoundingBoxIn);
        }

        this.setBlockState(world, glowstone, 6, 8, 16 + 3, structureBoundingBoxIn);

        this.setBlockState(world, trapDoorSouthTopOpen, 6, 8, 15 + 3, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorNorthTopOpen, 6, 8, 17 + 3, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorWestTopOpen, 5, 8, 16 + 3, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorEastTopOpen, 7, 8, 16 + 3, structureBoundingBoxIn);


        for (int j = 9; j < 23; ++j) {
            this.setBlockState(world, fence, 6, j, 22 + 3, structureBoundingBoxIn);
        }

        this.setBlockState(world, glowstone, 6, 8, 22 + 3, structureBoundingBoxIn);

        this.setBlockState(world, trapDoorSouthTopOpen, 6, 8, 21 + 3, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorNorthTopOpen, 6, 8, 23 + 3, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorWestTopOpen, 5, 8, 22 + 3, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorEastTopOpen, 7, 8, 22 + 3, structureBoundingBoxIn);


        // Perimeter Fence (Z-Axis at center)
        this.fillWithBlocks(world, structureBoundingBoxIn, 6, 15, 17 + 3, 6, 15, 29 + 3, fence, fence, false);


        // Perimeter Fence (X-Axis rows)
        this.fillWithBlocks(world, structureBoundingBoxIn, 2, 15, 16 + 3, 10, 15, 16 + 3, fence, fence, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 2, 15, 22 + 3, 10, 15, 22 + 3, fence, fence, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 2, 15, 28 + 3, 10, 15, 28 + 3, fence, fence, false);


        // Table Floor Outline
        this.setBlockState(world, stairCobblestoneEastBottomInnerLeft, 2, 4, 28 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneWestBottomInnerRight, 10, 4, 28 + 3, structureBoundingBoxIn);


        // Table Floor North-facing stairs (middle)
        this.setBlockState(world, stairCobblestoneNorthBottomStraight, 3, 4, 28 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneNorthBottomStraight, 4, 4, 28 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneNorthBottomStraight, 8, 4, 28 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneNorthBottomStraight, 9, 4, 28 + 3, structureBoundingBoxIn);


        // Cobblestone floor center
        this.fillWithBlocks(world, structureBoundingBoxIn, 3, 4, 29 + 3, 9, 4, 30 + 3, cobblestone, cobblestone, false);


        // Cobblestone floor forward section
        this.fillWithBlocks(world, structureBoundingBoxIn, 4, 4, 31 + 3, 8, 4, 32 + 3, cobblestone, cobblestone, false);


        // Glowstone at back of table floor
        this.setBlockState(world, glowstone, 4, 4, 32 + 3, structureBoundingBoxIn);
        this.setBlockState(world, glowstone, 8, 4, 32 + 3, structureBoundingBoxIn);


        // Vertical fence column behind table
        this.fillWithBlocks(world, structureBoundingBoxIn, 6, 10, 30 + 3, 6, 22, 30 + 3, fence, fence, false);


        // Glowstone light above table
        this.setBlockState(world, glowstone, 6, 9, 30 + 3, structureBoundingBoxIn);


        // Trapdoors around light
        this.setBlockState(world, trapDoorSouthTopOpen, 6, 9, 29 + 3, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorNorthTopOpen, 6, 9, 31 + 3, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorWestTopOpen, 5, 9, 30 + 3, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorEastTopOpen, 7, 9, 30 + 3, structureBoundingBoxIn);


        // Table Structure (Top slabs and chairs)
        this.setBlockState(world, stairWoodNorthTopStraight, 5, 5, 30 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthTopStraight, 7, 5, 30 + 3, structureBoundingBoxIn);
        this.fillWithBlocks(world, structureBoundingBoxIn, 5, 5, 29 + 3, 7, 5, 29 + 3, slabWoodTop, slabWoodTop, false);


        // Top and bottom fixed blocks
        this.setBlockState(world, stainedGlassRandom, 1, 17, 13 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stainedGlassRandom, 1, 9, 13 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stainedGlassRandom, 1, 17, 19 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stainedGlassRandom, 1, 9, 19 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stainedGlassRandom, 1, 17, 25 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stainedGlassRandom, 1, 9, 25 + 3, structureBoundingBoxIn);

        this.setBlockState(world, stainedGlassRandom, 11, 17, 13 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stainedGlassRandom, 11, 9, 13 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stainedGlassRandom, 11, 17, 19 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stainedGlassRandom, 11, 9, 19 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stainedGlassRandom, 11, 17, 25 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stainedGlassRandom, 11, 9, 25 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stainedGlassRandom, 6, 17, 33 + 3, structureBoundingBoxIn);


        // Create a selector
        StructureComponent.BlockSelector stainedSelector = new RandomStainedGlassSelector(stainedGlassRandom);


        // Fill regions
        this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 1, 10, 12 + 3, 1, 16, 14 + 3, false, rand, stainedSelector);
        this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 1, 10, 18 + 3, 1, 16, 20 + 3, false, rand, stainedSelector);
        this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 1, 10, 24 + 3, 1, 16, 26 + 3, false, rand, stainedSelector);
        this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 11, 10, 12 + 3, 11, 16, 14 + 3, false, rand, stainedSelector);
        this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 11, 10, 18 + 3, 11, 16, 20 + 3, false, rand, stainedSelector);
        this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 11, 10, 24 + 3, 11, 16, 26 + 3, false, rand, stainedSelector);
        this.fillWithRandomizedBlocks(world, structureBoundingBoxIn, 5, 11, 33 + 3, 7, 16, 33 + 3, false, rand, stainedSelector);

        // Main Roof Slab Surface
        this.fillWithBlocks(world, structureBoundingBoxIn, 2, 23, 8 + 3, 2, 23, 28 + 3, slabWoodTop, slabWoodTop, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 3, 23, 7 + 3, 3, 23, 30 + 3, slabWoodTop, slabWoodTop, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 4, 23, 6 + 3, 4, 23, 32 + 3, slabWoodTop, slabWoodTop, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 5, 23, 5 + 3, 7, 23, 32 + 3, slabWoodTop, slabWoodTop, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 8, 23, 6 + 3, 8, 23, 32 + 3, slabWoodTop, slabWoodTop, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 9, 23, 7 + 3, 9, 23, 30 + 3, slabWoodTop, slabWoodTop, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 10, 23, 8 + 3, 10, 23, 28 + 3, slabWoodTop, slabWoodTop, false);


        // Log Beam (if biome is not snow, desert, mesa, or hills)
        if (!(biome instanceof BiomeSnow || biome instanceof BiomeDesert || biome instanceof BiomeMesa || biome instanceof BiomeHills)) {
            IBlockState logAxisZ = this.getBiomeSpecificBlockState(Blocks.LOG.getDefaultState())
                    .withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z);
            this.fillWithBlocks(world, structureBoundingBoxIn, 6, 23, 5 + 3, 6, 23, 32 + 3, logAxisZ, logAxisZ, false);
        }


        // Front Roof
        this.fillWithBlocks(world, structureBoundingBoxIn, 5, 22, 3 + 3, 7, 22, 3 + 3, stairWoodNorthTopStraight, stairWoodNorthTopStraight, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 5, 23, 4 + 3, 7, 23, 4 + 3, stairWoodNorthBottomStraight, stairWoodNorthBottomStraight, false);


        // === Right Roof (Top)
        this.setBlockState(world, stairWoodWestTopOuterRight, 8, 22, 3 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthTopInnerLeft, 8, 22, 4 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestTopOuterRight, 9, 22, 4 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthTopInnerLeft, 9, 22, 5 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestTopOuterRight, 10, 22, 5 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthTopInnerLeft, 10, 22, 6 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestTopOuterRight, 11, 22, 6 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthTopInnerLeft, 11, 22, 7 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestTopOuterRight, 12, 22, 7 + 3, structureBoundingBoxIn);

        this.fillWithBlocks(world, structureBoundingBoxIn, 12, 22, 8 + 3, 12, 22, 28 + 3, stairWoodWestTopStraight, stairWoodWestTopStraight, false);
        this.setBlockState(world, stairWoodSouthTopOuterRight, 12, 22, 29 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodSouthTopInnerRight, 11, 22, 29 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestTopStraight, 11, 22, 30 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestTopOuterLeft, 11, 22, 31 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodSouthTopInnerRight, 10, 22, 31 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestTopStraight, 10, 22, 32 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodSouthTopOuterRight, 10, 22, 33 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodSouthTopInnerRight, 9, 22, 33 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestTopOuterLeft, 9, 22, 34 + 3, structureBoundingBoxIn);


        // === Right Roof (Bottom)
        this.setBlockState(world, stairWoodWestBottomOuterRight, 8, 23, 4 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthBottomInnerLeft, 8, 23, 5 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthBottomOuterLeft, 9, 23, 5 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestBottomInnerRight, 9, 23, 6 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthBottomOuterLeft, 10, 23, 6 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestBottomInnerRight, 10, 23, 7 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthBottomOuterLeft, 11, 23, 7 + 3, structureBoundingBoxIn);

        this.fillWithBlocks(world, structureBoundingBoxIn, 11, 23, 8 + 3, 11, 23, 28 + 3, stairWoodWestBottomStraight, stairWoodWestBottomStraight, false);
        this.setBlockState(world, stairWoodSouthBottomOuterRight, 11, 23, 29 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestBottomInnerLeft, 10, 23, 29 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestBottomStraight, 10, 23, 30 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodSouthBottomOuterRight, 10, 23, 31 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestBottomInnerLeft, 9, 23, 31 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestBottomStraight, 9, 23, 32 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestBottomOuterLeft, 9, 23, 33 + 3, structureBoundingBoxIn);


        // === Left Roof (Top)
        this.setBlockState(world, stairWoodNorthTopOuterRight, 4, 22, 3 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastTopInnerLeft, 4, 22, 4 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthTopOuterRight, 3, 22, 4 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastTopInnerLeft, 3, 22, 5 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthTopOuterRight, 2, 22, 5 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastTopInnerLeft, 2, 22, 6 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthTopOuterRight, 1, 22, 6 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastTopInnerLeft, 1, 22, 7 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthTopOuterRight, 0, 22, 7 + 3, structureBoundingBoxIn);

        this.fillWithBlocks(world, structureBoundingBoxIn, 0, 22, 8 + 3, 0, 22, 28 + 3, stairWoodEastTopStraight, stairWoodEastTopStraight, false);
        this.setBlockState(world, stairWoodSouthTopOuterLeft, 0, 22, 29 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastTopInnerRight, 1, 22, 29 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastTopStraight, 1, 22, 30 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodSouthTopOuterLeft, 1, 22, 31 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastTopInnerRight, 2, 22, 31 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastTopStraight, 2, 22, 32 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodSouthTopOuterLeft, 2, 22, 33 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastTopInnerRight, 3, 22, 33 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodSouthTopOuterLeft, 3, 22, 34 + 3, structureBoundingBoxIn);


        // === Left Roof (Bottom)
        this.setBlockState(world, stairWoodEastBottomOuterLeft, 4, 23, 4 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastBottomInnerLeft, 4, 23, 5 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthBottomOuterRight, 3, 23, 5 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastBottomInnerLeft, 3, 23, 6 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthBottomOuterRight, 2, 23, 6 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastBottomInnerLeft, 2, 23, 7 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthBottomOuterRight, 1, 23, 7 + 3, structureBoundingBoxIn);

        this.fillWithBlocks(world, structureBoundingBoxIn, 1, 23, 8 + 3, 1, 23, 28 + 3, stairWoodEastBottomStraight, stairWoodEastBottomStraight, false);
        this.setBlockState(world, stairWoodSouthBottomOuterLeft, 1, 23, 29 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodSouthBottomInnerLeft, 2, 23, 29 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastBottomStraight, 2, 23, 30 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodSouthBottomOuterLeft, 2, 23, 31 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastBottomInnerLeft, 3, 23, 31 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastBottomStraight, 3, 23, 32 + 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastBottomOuterRight, 3, 23, 33 + 3, structureBoundingBoxIn);


        // Top row
        this.fillWithBlocks(world, structureBoundingBoxIn, 4, 22, 34 + 3, 8, 22, 34 + 3, stairWoodSouthTopStraight, stairWoodSouthTopStraight, false);


        // Bottom row
        this.fillWithBlocks(world, structureBoundingBoxIn, 4, 23, 33 + 3, 8, 23, 33 + 3, stairWoodSouthBottomStraight, stairWoodSouthBottomStraight, false);


        // First fence column
        this.fillWithBlocks(world, structureBoundingBoxIn, 6, 28, 23 + 3, 6, 33, 23 + 3, fence, fence, false);


        // Glowstone and trapdoors remain set individually
        this.setBlockState(world, glowstone, 6, 27, 23 + 3, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorSouthTopOpen, 6, 27, 22 + 3, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorNorthTopOpen, 6, 27, 24 + 3, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorWestTopOpen, 5, 27, 23 + 3, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorEastTopOpen, 7, 27, 23 + 3, structureBoundingBoxIn);


        // Second fence column
        this.fillWithBlocks(world, structureBoundingBoxIn, 6, 28, 27 + 3, 6, 33, 27 + 3, fence, fence, false);

        this.setBlockState(world, glowstone, 6, 27, 27 + 3, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorSouthTopOpen, 6, 27, 26 + 3, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorNorthTopOpen, 6, 27, 28 + 3, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorWestTopOpen, 5, 27, 27 + 3, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorEastTopOpen, 7, 27, 27 + 3, structureBoundingBoxIn);


        // Front wall (lower)
        this.fillWithBlocks(world, structureBoundingBoxIn, 5, 24, 18 + 3, 7, 27, 18 + 3, plankWood, plankWood, false);


        // Middle wall
        this.fillWithBlocks(world, structureBoundingBoxIn, 5, 28, 19 + 3, 7, 30, 19 + 3, plankWood, plankWood, false);


        // Upper layer
        this.fillWithBlocks(world, structureBoundingBoxIn, 5, 31, 20 + 3, 7, 31, 20 + 3, plankWood, plankWood, false);


        // Roof peak
        this.setBlockState(world, plankWood, 6, 32, 20 + 3, structureBoundingBoxIn);


        // Left and right sides
        this.fillWithBlocks(world, structureBoundingBoxIn, 5, 31, 21 + 3, 5, 32, 21 + 3, plankWood, plankWood, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 7, 31, 21 + 3, 7, 32, 21 + 3, plankWood, plankWood, false);


        // Glass
        this.setBlockState(world, glass, 6, 27, 18 + 3, structureBoundingBoxIn);
        this.fillWithBlocks(world, structureBoundingBoxIn, 5, 26, 18 + 3, 7, 26, 18 + 3, glass, glass, false);
        this.setBlockState(world, glass, 6, 25, 18 + 3, structureBoundingBoxIn);



        this.fillWithBlocks(world, structureBoundingBoxIn, 8, 24, 19 + 3, 8, 27, 19 + 3, plankWood, plankWood, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 9, 24, 20 + 3, 9, 27, 21 + 3, plankWood, plankWood, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 10, 24, 22 + 3, 10, 27, 28 + 3, plankWood, plankWood, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 9, 24, 29 + 3, 9, 27, 30 + 3, plankWood, plankWood, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 8, 24, 31 + 3, 8, 27, 31 + 3, plankWood, plankWood, false);

        this.fillWithBlocks(world, structureBoundingBoxIn, 8, 28, 20 + 3, 8, 30, 21 + 3, plankWood, plankWood, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 9, 28, 22 + 3, 9, 30, 28 + 3, plankWood, plankWood, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 8, 28, 29 + 3, 8, 30, 30 + 3, plankWood, plankWood, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 8, 31, 22 + 3, 8, 32, 28 + 3, plankWood, plankWood, false);


        // Glass
        this.setBlockState(world, glass, 10, 27, 25 + 3, structureBoundingBoxIn);
        this.fillWithBlocks(world, structureBoundingBoxIn, 10, 26, 24 + 3, 10, 26, 26 + 3, glass, glass, false);
        this.setBlockState(world, glass, 10, 25, 25 + 3, structureBoundingBoxIn);



        this.fillWithBlocks(world, structureBoundingBoxIn, 4, 24, 19 + 3, 4, 27, 19 + 3, plankWood, plankWood, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 3, 24, 20 + 3, 3, 27, 21 + 3, plankWood, plankWood, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 2, 24, 22 + 3, 2, 27, 28 + 3, plankWood, plankWood, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 3, 24, 29 + 3, 3, 27, 30 + 3, plankWood, plankWood, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 4, 24, 31 + 3, 4, 27, 31 + 3, plankWood, plankWood, false);

        this.fillWithBlocks(world, structureBoundingBoxIn, 4, 28, 20 + 3, 4, 30, 21 + 3, plankWood, plankWood, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 3, 28, 22 + 3, 3, 30, 28 + 3, plankWood, plankWood, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 4, 28, 29 + 3, 4, 30, 30 + 3, plankWood, plankWood, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 4, 31, 22 + 3, 4, 32, 28 + 3, plankWood, plankWood, false);


        // Glass
        this.setBlockState(world, glass, 2, 27, 25 + 3, structureBoundingBoxIn);
        this.fillWithBlocks(world, structureBoundingBoxIn, 2, 26, 24 + 3, 2, 26, 26 + 3, glass, glass, false);
        this.setBlockState(world, glass, 2, 25, 25 + 3, structureBoundingBoxIn);

        // Back Roof Bell

        this.fillWithBlocks(world, structureBoundingBoxIn, 5, 24, 32 + 3, 7, 27, 32 + 3, plankWood, plankWood, false);

        this.fillWithBlocks(world, structureBoundingBoxIn, 5, 28, 31 + 3, 7, 30, 31 + 3, plankWood, plankWood, false);


        // Top row: single row at y=31
        this.fillWithBlocks(world, structureBoundingBoxIn, 5, 31, 30 + 3, 7, 31, 30 + 3, plankWood, plankWood, false);


        // Peak block
        this.setBlockState(world, plankWood, 6, 32, 30 + 3, structureBoundingBoxIn);


        // Outer columns
        this.fillWithBlocks(world, structureBoundingBoxIn, 5, 31, 29 + 3, 5, 32, 29 + 3, plankWood, plankWood, false);
        this.fillWithBlocks(world, structureBoundingBoxIn, 7, 31, 29 + 3, 7, 32, 29 + 3, plankWood, plankWood, false);


        // Glass window center
        this.setBlockState(world, glass, 6, 27, 32 + 3, structureBoundingBoxIn);


        // Glass row
        this.fillWithBlocks(world, structureBoundingBoxIn, 5, 26, 32 + 3, 7, 26, 32 + 3, glass, glass, false);


        // Glass bottom
        this.setBlockState(world, glass, 6, 25, 32 + 3, structureBoundingBoxIn);




        // Top Roof Bell

        // Right side top layer
        this.fillWithBlocks(world, structureBoundingBoxIn, 7, 33, 22 + 3, 7, 33, 28 + 3, plankWood, plankWood, false);


        // Left side top layer
        this.fillWithBlocks(world, structureBoundingBoxIn, 5, 33, 22 + 3, 5, 33, 28 + 3, plankWood, plankWood, false);


        // Peak tip
        this.setBlockState(world, plankWood, 6, 33, 21 + 3, structureBoundingBoxIn);


        // Front ramp
        this.fillWithBlocks(world, structureBoundingBoxIn, 6, 34, 22 + 3, 6, 34, 24 + 3, plankWood, plankWood, false);


        // Middle three-way cap
        this.fillWithBlocks(world, structureBoundingBoxIn, 5, 34, 25 + 3, 7, 34, 25 + 3, plankWood, plankWood, false);


        // Rear ramp
        this.fillWithBlocks(world, structureBoundingBoxIn, 6, 34, 26 + 3, 6, 34, 28 + 3, plankWood, plankWood, false);


        // Peak end
        this.setBlockState(world, plankWood, 6, 33, 29 + 3, structureBoundingBoxIn);




        // Roof Cross

        // Cross west side
        this.setBlockState(world, fenceWest, 7, 40, 25 + 3, structureBoundingBoxIn);


        // Vertical fence post
        this.fillWithBlocks(world, structureBoundingBoxIn, 6, 36, 25 + 3, 6, 41, 25 + 3, fence, fence, false);


        // Cross east side
        this.setBlockState(world, fenceEast, 5, 40, 25 + 3, structureBoundingBoxIn);

        // Foundation

        for (int i = 1; i < 12; ++i) {
            for (int k = -3; k < 4; ++k) {
                this.clearCurrentPositionBlocksUpwards(world, i, 42, k, structureBoundingBoxIn);
                this.replaceAirAndLiquidDownwards(world, cobblestone, i, -1, k, structureBoundingBoxIn);
            }
        }

        for (int i = 4; i < 9; ++i) {
            for (int k = 4; k < 34; ++k) {
                this.clearCurrentPositionBlocksUpwards(world, i, 42, k, structureBoundingBoxIn);
                this.replaceAirAndLiquidDownwards(world, cobblestone, i, -1, k, structureBoundingBoxIn);
            }
        }

        for (int k = 8; k < 29; ++k) {
            this.clearCurrentPositionBlocksUpwards(world, 1, 42, k, structureBoundingBoxIn);
            this.replaceAirAndLiquidDownwards(world, cobblestone, 1, -1, k, structureBoundingBoxIn);
        }

        for (int k = 7; k < 31; ++k) {
            this.clearCurrentPositionBlocksUpwards(world, 2, 42, k, structureBoundingBoxIn);
            this.replaceAirAndLiquidDownwards(world, cobblestone, 2, -1, k, structureBoundingBoxIn);
        }

        for (int k = 6; k < 33; ++k) {
            this.clearCurrentPositionBlocksUpwards(world, 3, 42, k, structureBoundingBoxIn);
            this.replaceAirAndLiquidDownwards(world, cobblestone, 3, -1, k, structureBoundingBoxIn);
        }




        for (int k = 6; k < 33; ++k) {
            this.clearCurrentPositionBlocksUpwards(world, 9, 42, k, structureBoundingBoxIn);
            this.replaceAirAndLiquidDownwards(world, cobblestone, 9, -1, k, structureBoundingBoxIn);
        }
        for (int k = 7; k < 31; ++k) {
            this.clearCurrentPositionBlocksUpwards(world, 10, 42, k, structureBoundingBoxIn);
            this.replaceAirAndLiquidDownwards(world, cobblestone, 10, -1, k, structureBoundingBoxIn);
        }

        for (int k = 8; k < 29; ++k) {
            this.clearCurrentPositionBlocksUpwards(world, 11, 42, k, structureBoundingBoxIn);
            this.replaceAirAndLiquidDownwards(world, cobblestone, 11, -1, k, structureBoundingBoxIn);
        }
    }

    @Override
    protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession) {
        ForgeRegistry<VillagerRegistry.VillagerProfession> registry = (ForgeRegistry<VillagerRegistry.VillagerProfession>) ForgeRegistries.VILLAGER_PROFESSIONS;

        if (villagersSpawnedIn == 0) {
            return registry.getID(VillagerInit.villagerHighPriest);
        } else if (villagersSpawnedIn == 2) {
            return registry.getID(VillagerInit.villagerLeaderPriest);
        } else {
            return currentVillagerProfession;
        }
    }

    @Override
    protected IBlockState getBiomeSpecificBlockState(IBlockState blockstateIn) {
        net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID event = new net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID(startPiece == null ? null : startPiece.biome, blockstateIn);
        net.minecraftforge.common.MinecraftForge.TERRAIN_GEN_BUS.post(event);
        if (event.getResult() == net.minecraftforge.fml.common.eventhandler.Event.Result.DENY) return event.getReplacement();
        if (this.biome instanceof BiomeDesert) {
            if (blockstateIn.getBlock() == Blocks.LOG || blockstateIn.getBlock() == Blocks.LOG2) {
                return Blocks.SANDSTONE.getDefaultState();
            }

            if (blockstateIn.getBlock() == Blocks.COBBLESTONE)
            {
                return Blocks.SANDSTONE.getStateFromMeta(BlockSandStone.EnumType.DEFAULT.getMetadata());
            }

            if (blockstateIn.getBlock() == Blocks.PLANKS)
            {
                return Blocks.SANDSTONE.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata());
            }

            if (blockstateIn.getBlock() == Blocks.OAK_STAIRS)
            {
                return Blocks.SANDSTONE_STAIRS.getDefaultState()
                        .withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING))
                        .withProperty(BlockStairs.HALF, blockstateIn.getValue(BlockStairs.HALF))
                        .withProperty(BlockStairs.SHAPE, blockstateIn.getValue(BlockStairs.SHAPE));
            }

            if (blockstateIn.getBlock() == Blocks.STONE_STAIRS)
            {
                return Blocks.SANDSTONE_STAIRS.getDefaultState()
                        .withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING))
                        .withProperty(BlockStairs.HALF, blockstateIn.getValue(BlockStairs.HALF))
                        .withProperty(BlockStairs.SHAPE, blockstateIn.getValue(BlockStairs.SHAPE));
            }

            if (blockstateIn.getBlock() == Blocks.GRAVEL)
            {
                return Blocks.SANDSTONE.getDefaultState();
            }

            if (blockstateIn.getBlock() == Blocks.WOODEN_SLAB) {
                return Blocks.STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.SAND).withProperty(BlockStoneSlab.HALF, BlockSlab.EnumBlockHalf.TOP);
            }
        }
        else if (this.biome instanceof BiomeTaiga || this.biome instanceof BiomeSnow) {
            if (blockstateIn.getBlock() == Blocks.LOG || blockstateIn.getBlock() == Blocks.LOG2) {
                return Blocks.LOG.getDefaultState()
                        .withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
            }

            if (blockstateIn.getBlock() == Blocks.PLANKS) {
                return Blocks.PLANKS.getDefaultState()
                        .withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.SPRUCE);
            }

            if (blockstateIn.getBlock() == Blocks.OAK_STAIRS) {
                return Blocks.SPRUCE_STAIRS.getDefaultState()
                        .withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING))
                        .withProperty(BlockStairs.HALF, blockstateIn.getValue(BlockStairs.HALF))
                        .withProperty(BlockStairs.SHAPE, blockstateIn.getValue(BlockStairs.SHAPE));
            }

            if (blockstateIn.getBlock() == Blocks.OAK_FENCE) {
                return Blocks.SPRUCE_FENCE.getDefaultState();
            }

            if (blockstateIn.getBlock() == Blocks.TORCH) {
                return Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, blockstateIn.getValue(BlockTorch.FACING));
            }

            if (blockstateIn.getBlock() == Blocks.WOODEN_SLAB) {
                return Blocks.WOODEN_SLAB.getDefaultState().withProperty(BlockWoodSlab.VARIANT, BlockPlanks.EnumType.SPRUCE).withProperty(BlockWoodSlab.HALF, BlockSlab.EnumBlockHalf.TOP);
            }
        }
        else if (this.biome instanceof BiomeSavanna) {
            if (blockstateIn.getBlock() == Blocks.LOG || blockstateIn.getBlock() == Blocks.LOG2) {
                return Blocks.LOG2.getDefaultState()
                        .withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA);
            }

            if (blockstateIn.getBlock() == Blocks.PLANKS) {
                return Blocks.PLANKS.getDefaultState()
                        .withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.ACACIA);
            }

            if (blockstateIn.getBlock() == Blocks.OAK_STAIRS) {
                return Blocks.ACACIA_STAIRS.getDefaultState()
                        .withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING))
                        .withProperty(BlockStairs.HALF, blockstateIn.getValue(BlockStairs.HALF))
                        .withProperty(BlockStairs.SHAPE, blockstateIn.getValue(BlockStairs.SHAPE));
            }

            if (blockstateIn.getBlock() == Blocks.COBBLESTONE) {
                return Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA).withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y);
            }

            if (blockstateIn.getBlock() == Blocks.OAK_FENCE) {
                return Blocks.ACACIA_FENCE.getDefaultState();
            }

            if (blockstateIn.getBlock() == Blocks.WOODEN_SLAB) {
                return Blocks.WOODEN_SLAB.getDefaultState().withProperty(BlockWoodSlab.VARIANT, BlockPlanks.EnumType.ACACIA).withProperty(BlockWoodSlab.HALF, BlockSlab.EnumBlockHalf.TOP);
            }
        } else if (this.biome instanceof BiomeJungle) {

            if (blockstateIn.getBlock() == Blocks.LOG || blockstateIn.getBlock() == Blocks.LOG2) {
                return Blocks.LOG.getDefaultState()
                        .withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
            }

            if (blockstateIn.getBlock() == Blocks.PLANKS) {
                return Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.JUNGLE);
            }

            if (blockstateIn.getBlock() == Blocks.OAK_STAIRS) {
                return Blocks.JUNGLE_STAIRS.getDefaultState()
                        .withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING))
                        .withProperty(BlockStairs.HALF, blockstateIn.getValue(BlockStairs.HALF))
                        .withProperty(BlockStairs.SHAPE, blockstateIn.getValue(BlockStairs.SHAPE));
            }

            if (blockstateIn.getBlock() == Blocks.COBBLESTONE)
            {
                return Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE).withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y);
            }

            if (blockstateIn.getBlock() == Blocks.OAK_FENCE) {
                return Blocks.JUNGLE_FENCE.getDefaultState();
            }

            if (blockstateIn.getBlock() == Blocks.WOODEN_SLAB) {
                return Blocks.WOODEN_SLAB.getDefaultState().withProperty(BlockWoodSlab.VARIANT, BlockPlanks.EnumType.JUNGLE).withProperty(BlockWoodSlab.HALF, BlockSlab.EnumBlockHalf.TOP);
            }
        } else if (this.biome == Biomes.ROOFED_FOREST || this.biome == Biomes.MUTATED_ROOFED_FOREST) {

            if (blockstateIn.getBlock() == Blocks.LOG || blockstateIn.getBlock() == Blocks.LOG2) {
                return Blocks.LOG.getDefaultState()
                        .withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.DARK_OAK);
            }

            if (blockstateIn.getBlock() == Blocks.PLANKS) {
                return Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.DARK_OAK);
            }

            if (blockstateIn.getBlock() == Blocks.OAK_STAIRS) {
                return Blocks.DARK_OAK_STAIRS.getDefaultState()
                        .withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING))
                        .withProperty(BlockStairs.HALF, blockstateIn.getValue(BlockStairs.HALF))
                        .withProperty(BlockStairs.SHAPE, blockstateIn.getValue(BlockStairs.SHAPE));
            }

            if (blockstateIn.getBlock() == Blocks.COBBLESTONE)
            {
                return Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK).withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y);
            }

            if (blockstateIn.getBlock() == Blocks.OAK_FENCE) {
                return Blocks.DARK_OAK_FENCE.getDefaultState();
            }

            if (blockstateIn.getBlock() == Blocks.WOODEN_SLAB) {
                return Blocks.WOODEN_SLAB.getDefaultState().withProperty(BlockWoodSlab.VARIANT, BlockPlanks.EnumType.DARK_OAK).withProperty(BlockWoodSlab.HALF, BlockSlab.EnumBlockHalf.TOP);
            }
        }
        return blockstateIn;
    }

    @Override
    protected @NotNull BlockDoor biomeDoor() {

        if (this.biome instanceof BiomeJungle) {
            return Blocks.JUNGLE_DOOR;
        } else if (this.biome == Biomes.ROOFED_FOREST || this.biome == Biomes.MUTATED_ROOFED_FOREST) {
            return Blocks.DARK_OAK_DOOR;
        } else if (this.biome instanceof BiomeTaiga || this.biome instanceof BiomeSnow) {
            return Blocks.SPRUCE_DOOR;
        } else if (this.biome instanceof BiomeSavanna) {
            return Blocks.ACACIA_DOOR;
        } else {
            return Blocks.OAK_DOOR;
        }
    }

    protected void spawnVillagers(World worldIn, StructureBoundingBox structurebb, int x, int y, int z, VillagerRegistry.VillagerProfession profession) {
        int j = this.getXWithOffset(x, z);
        int k = this.getYWithOffset(y);
        int l = this.getZWithOffset(x, z);

        if (!structurebb.isVecInside(new BlockPos(j, k, l))) {
            return;
        }

        if (this.isZombieInfested) {
            EntityZombieVillager entityzombievillager = new EntityZombieVillager(worldIn);
            entityzombievillager.setLocationAndAngles((double) j + 0.5D, (double) k, (double) l + 0.5D, 0.0F, 0.0F);
            entityzombievillager.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityzombievillager)), (IEntityLivingData) null);
            entityzombievillager.setForgeProfession(chooseForgeProfession(1, profession));
            entityzombievillager.enablePersistence();
            worldIn.spawnEntity(entityzombievillager);
        } else {
            EntityVillager entityvillager = new EntityVillager(worldIn);
            entityvillager.setLocationAndAngles((double) j + 0.5D, (double) k, (double) l + 0.5D, 0.0F, 0.0F);
            net.minecraftforge.fml.common.registry.VillagerRegistry.setRandomProfession(entityvillager, worldIn.rand);
            entityvillager.setProfession(this.chooseForgeProfession(1, profession));
            entityvillager.finalizeMobSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityvillager)), (IEntityLivingData) null, false);
            worldIn.spawnEntity(entityvillager);
        }

    }

    protected void spawnCrucifix(World worldIn, StructureBoundingBox structurebb, int x, int y, int z) {

        EnumFacing facing = this.getCoordBaseMode();

        int j = this.getXWithOffset(x, z);
        int k = this.getYWithOffset(y);
        int l = this.getZWithOffset(x, z);

        if (!structurebb.isVecInside(new BlockPos(j, k, l))) {
            return;
        }

        Biome biome = worldIn.getBiome(new BlockPos(j, k, l));
        BlockPlanks.EnumType woodType = getWoodTypeFromBiome(biome);
        EntityCrucifix entityCrucifix = new EntityCrucifix(worldIn, x, y, z, woodType);

        float rotationYaw = 0.0F;
        float rotationPitch = 0.0F;
        if (facing != null) {
            switch (facing) {
                case NORTH:
                    rotationYaw = 0.0F;
                    break;
                case SOUTH:
                    rotationYaw = 180.0F;
                    break;
                case WEST:
                    rotationYaw = -90.0F;
                    break;
                case EAST:
                    rotationYaw = 90.0F;
                    break;
            }
        }
        entityCrucifix.setLocationAndAngles((double) j + 0.5D, (double) k, (double) l + 0.5D, rotationYaw, rotationPitch);
        worldIn.spawnEntity(entityCrucifix);
    }

    private BlockPlanks.EnumType getWoodTypeFromBiome(Biome biome) {
        if (biome == Biomes.FOREST || biome == Biomes.PLAINS) {
            return BlockPlanks.EnumType.OAK;
        } else if (biome == Biomes.TAIGA || biome == Biomes.COLD_TAIGA || biome instanceof BiomeSnow) {
            return BlockPlanks.EnumType.SPRUCE;
        } else if (biome == Biomes.SAVANNA) {
            return BlockPlanks.EnumType.ACACIA;
        } else if (biome instanceof BiomeJungle) {
            return BlockPlanks.EnumType.JUNGLE;
        } else if (biome == Biomes.ROOFED_FOREST) {
            return BlockPlanks.EnumType.DARK_OAK;
        } else if (biome == Biomes.BIRCH_FOREST) {
            return BlockPlanks.EnumType.BIRCH;
        }

        if (this.structureType == 2) {
            return BlockPlanks.EnumType.ACACIA;
        } else if (this.structureType == 3) {
            return BlockPlanks.EnumType.SPRUCE;
        } else {
            return BlockPlanks.EnumType.OAK;
        }
    }

    private static class RandomStainedGlassSelector extends StructureComponent.BlockSelector {
        private final IBlockState stainedGlass;

        public RandomStainedGlassSelector(IBlockState stainedGlass) {
            this.stainedGlass = stainedGlass;
        }

        @Override
        public void selectBlocks(Random rand, int x, int y, int z, boolean isBoundary) {
            int colorMetadata = rand.nextInt(16);
            EnumDyeColor dyeColor = EnumDyeColor.byMetadata(colorMetadata);
            this.blockstate = stainedGlass.withProperty(BlockStainedGlass.COLOR, dyeColor);
        }
    }
}
