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
    private int villagersSpawned;
    private int crucifixSpawned;

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

        StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(structureMinX, structureMinY, structureMinZ, 0, 0, -3, 13, 42, 38, facing);
        if (!canVillageGoDeeper(structureBoundingBox) || StructureComponent.findIntersecting(pieces, structureBoundingBox) != null) {
            return null;
        }

        System.out.println("Big Church buildComponent() at " + structureMinX + ", " + structureMinY + ", " + structureMinZ + "and facing: " + facing);

        return new StructureBigChurch(startPieces, componentType, random, structureBoundingBox, facing);
    }

    private void generateBigChurch(World world, Random rand, StructureBoundingBox structureBoundingBoxIn) {
        IBlockState air = Blocks.AIR.getDefaultState();

        IBlockState cobblestone = Blocks.COBBLESTONE.getDefaultState();

        IBlockState stairCobblestoneEastBottomOuterLeft = Blocks.STONE_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.EAST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_LEFT);

        IBlockState stairCobblestoneNorthBottomStraight = Blocks.STONE_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.NORTH)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT);

        IBlockState stairCobblestoneWestBottomOuterRight = Blocks.STONE_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.WEST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_RIGHT);

        IBlockState stairCobblestoneEastBottomStraight = Blocks.STONE_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.EAST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT);

        IBlockState stairCobblestoneWestBottomStraight = Blocks.STONE_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.WEST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT);

        IBlockState stairCobblestoneSouthBottomOuterRight = Blocks.STONE_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.SOUTH)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_RIGHT);

        IBlockState stairCobblestoneSouthBottomOuterLeft = Blocks.STONE_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.SOUTH)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.OUTER_LEFT);

        IBlockState stairCobblestoneSouthBottomStraight = Blocks.STONE_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.SOUTH)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT);


        IBlockState glowstone = Blocks.GLOWSTONE.getDefaultState();

        IBlockState slabCobblestoneTop = Blocks.STONE_SLAB.getDefaultState()
                .withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.COBBLESTONE)
                .withProperty(BlockStoneSlab.HALF, BlockSlab.EnumBlockHalf.TOP);

        IBlockState cobblestoneWall = Blocks.COBBLESTONE_WALL.getDefaultState();

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

        IBlockState stairCobblestoneWestBottomInnerRight = Blocks.STONE_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.WEST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.INNER_RIGHT);

        IBlockState stairCobblestoneEastBottomInnerLeft = Blocks.STONE_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.EAST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.INNER_LEFT);


        Block stainedGlass = Blocks.STAINED_GLASS;

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

        for (int i = 5; i < 8; ++i) {
            for (int j = 4; j < 23; ++j) {
                this.setBlockState(world, air, i, j, 5, structureBoundingBoxIn);
            }
        }

        for (int i = 4; i < 9; ++i) {
            for (int j = 4; j < 23; ++j) {
                this.setBlockState(world, air, i, j, 6, structureBoundingBoxIn);
            }
        }

        for (int i = 3; i < 10; ++i) {
            for (int j = 4; j < 23; ++j) {
                this.setBlockState(world, air, i, j, 7, structureBoundingBoxIn);
            }
        }

        for (int i = 2; i < 11; ++i) {
            for (int j = 4; j < 23; ++j) {
                for (int k = 29; k < 31; ++k) {
                    this.setBlockState(world, air, i, j, k, structureBoundingBoxIn);
                }
            }
        }



        for (int i = 3; i < 10; ++i) {
            for (int j = 5; j < 23; ++j) {
                for (int k = 29; k < 31; ++k) {
                    this.setBlockState(world, air, i, j, k, structureBoundingBoxIn);
                }
            }
        }

        for (int i = 4; i < 9; ++i) {
            for (int j = 5; j < 23; ++j) {
                for (int k = 31; k < 33; ++k) {
                    this.setBlockState(world, air, i, j, k, structureBoundingBoxIn);
                }
            }
        }

        // Stairs Entrance

        // Left Side First Stairs
        for (int k = -2; k < 4; ++k) {
            this.setBlockState(world, stairCobblestoneEastBottomStraight, 1, 0, k, structureBoundingBoxIn);
        }

        // Left Side Second Stairs
        for (int k = -1; k < 4; ++k) {
            this.setBlockState(world, cobblestone, 2, 0, k, structureBoundingBoxIn);
        }
        for (int k = -1; k < 4; ++k) {
            this.setBlockState(world, stairCobblestoneEastBottomStraight, 2, 1, k, structureBoundingBoxIn);
        }

        // Left Side Third Stairs
        for (int j = 0; j < 2; ++j) {
            for (int k = 0; k < 4; ++k) {
                this.setBlockState(world, cobblestone, 3, j, k, structureBoundingBoxIn);
            }
        }
        for (int k = 0; k < 4; ++k) {
            this.setBlockState(world, stairCobblestoneEastBottomStraight, 3, 2, k, structureBoundingBoxIn);
        }

        // Left Side Fourth Stairs
        for (int j = 0; j < 3; ++j) {
            for (int k = 1; k < 5; ++k) {
                this.setBlockState(world, cobblestone, 4, j, k, structureBoundingBoxIn);
            }
        }
        for (int k = 1; k < 4; ++k) {
            this.setBlockState(world, stairCobblestoneEastBottomStraight, 4, 3, k, structureBoundingBoxIn);
        }



        // Left Corner Fourth Stairs
        this.setBlockState(world, stairCobblestoneSouthBottomOuterLeft, 4, 3, 4,structureBoundingBoxIn);




        // First Stairs
        this.setBlockState(world, stairCobblestoneEastBottomOuterLeft, 1, 0, -3, structureBoundingBoxIn);
        for (int i = 2; i < 11; ++i) {
            this.setBlockState(world, stairCobblestoneNorthBottomStraight, i, 0, -3, structureBoundingBoxIn);
        }
        this.setBlockState(world, stairCobblestoneWestBottomOuterRight, 11, 0, -3, structureBoundingBoxIn);


        // Second Stairs
        for (int i = 2; i < 11; ++i) {
            this.setBlockState(world, cobblestone, i, 0, -2, structureBoundingBoxIn);
        }
        this.setBlockState(world, stairCobblestoneEastBottomOuterLeft, 2, 1, -2, structureBoundingBoxIn);
        for (int i = 3; i < 10; ++i) {
            this.setBlockState(world, stairCobblestoneNorthBottomStraight, i, 1, -2, structureBoundingBoxIn);
        }
        this.setBlockState(world, stairCobblestoneWestBottomOuterRight, 10, 1, -2, structureBoundingBoxIn);


        // Third Stairs
        for (int i = 3; i < 10; ++i) {
            for (int j = 0; j < 2; ++j) {
                this.setBlockState(world, cobblestone, i, j, -1, structureBoundingBoxIn);
            }
        }
        this.setBlockState(world, stairCobblestoneEastBottomOuterLeft, 3, 2, -1, structureBoundingBoxIn);
        for (int i = 4; i < 9; ++i) {
            this.setBlockState(world, stairCobblestoneNorthBottomStraight, i, 2, -1, structureBoundingBoxIn);
        }
        this.setBlockState(world, stairCobblestoneWestBottomOuterRight, 9, 2, -1, structureBoundingBoxIn);


        // Fourth Stairs
        for (int i = 4; i < 9; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.setBlockState(world, cobblestone, i, j, 0, structureBoundingBoxIn);
            }
        }
        this.setBlockState(world, stairCobblestoneEastBottomOuterLeft, 4, 3, 0, structureBoundingBoxIn);
        for (int i = 5; i < 8; ++i) {
            this.setBlockState(world, stairCobblestoneNorthBottomStraight, i, 3, 0, structureBoundingBoxIn);
        }
        this.setBlockState(world, stairCobblestoneWestBottomOuterRight, 8, 3, 0, structureBoundingBoxIn);



        // Floor Cobblestone Length
        for (int i = 5; i < 8; ++i) {
            for (int j = 0; j < 4; ++j) {
                for (int k = 1; k < 34; ++k) {
                    this.setBlockState(world, cobblestone, i, j, k, structureBoundingBoxIn);
                }
            }
        }

        this.setBlockState(world, glowstone, 6, 3, 10, structureBoundingBoxIn);
        this.setBlockState(world, glowstone, 6, 3, 16, structureBoundingBoxIn);
        this.setBlockState(world, glowstone, 6, 3, 22, structureBoundingBoxIn);
        this.setBlockState(world, glowstone, 6, 3, 28, structureBoundingBoxIn);


        for (int j = 0; j < 4; ++j) {
            for (int k = 8; k < 29; ++k) {
                this.setBlockState(world, cobblestone, 2, j, k, structureBoundingBoxIn);
            }
        }
        for (int j = 0; j < 4; ++j) {
            for (int k = 7; k < 31; ++k) {
                this.setBlockState(world, cobblestone, 3, j, k, structureBoundingBoxIn);
            }
        }
        for (int j = 0; j < 4; ++j) {
            for (int k = 6; k < 33; ++k) {
                this.setBlockState(world, cobblestone, 4, j, k, structureBoundingBoxIn);
            }
        }




        for (int j = 0; j < 4; ++j) {
            for (int k = 6; k < 33; ++k) {
                this.setBlockState(world, cobblestone, 8, j, k, structureBoundingBoxIn);
            }
        }
        for (int j = 0; j < 4; ++j) {
            for (int k = 7; k < 31; ++k) {
                this.setBlockState(world, cobblestone, 9, j, k, structureBoundingBoxIn);
            }
        }
        for (int j = 0; j < 4; ++j) {
            for (int k = 8; k < 29; ++k) {
                this.setBlockState(world, cobblestone, 10, j, k, structureBoundingBoxIn);
            }
        }




        // Right Side First Stairs
        for (int k = -2; k < 4; ++k) {
            this.setBlockState(world, stairCobblestoneWestBottomStraight, 11, 0, k, structureBoundingBoxIn);
        }

        // Right Side Second Stairs
        for (int k = -1; k < 4; ++k) {
            this.setBlockState(world, cobblestone, 10, 0, k, structureBoundingBoxIn);
        }
        for (int k = -1; k < 4; ++k) {
            this.setBlockState(world, stairCobblestoneWestBottomStraight, 10, 1, k, structureBoundingBoxIn);
        }

        // Right Side Third Stairs
        for (int j = 0; j < 2; ++j) {
            for (int k = 0; k < 4; ++k) {
                this.setBlockState(world, cobblestone, 9, j, k, structureBoundingBoxIn);
            }
        }
        for (int k = 0; k < 4; ++k) {
            this.setBlockState(world, stairCobblestoneWestBottomStraight, 9, 2, k, structureBoundingBoxIn);
        }

        // Right Side Fourth Stairs
        for (int j = 0; j < 3; ++j) {
            for (int k = 1; k < 5; ++k) {
                this.setBlockState(world, cobblestone, 8, j, k, structureBoundingBoxIn);
            }
        }
        for (int k = 1; k < 4; ++k) {
            this.setBlockState(world, stairCobblestoneWestBottomStraight, 8, 3, k, structureBoundingBoxIn);
        }

        // Right Corner Fourth Stairs
        this.setBlockState(world, stairCobblestoneSouthBottomOuterRight, 8, 3, 4, structureBoundingBoxIn);



        // Entrance
        for (int j = 4; j < 6; ++j) {
            this.setBlockState(world, cobblestone, 5, j, 4, structureBoundingBoxIn);
        }

        for (int i = 5; i < 8; ++i) {
            for (int j = 6; j < 23; ++j) {
                this.setBlockState(world, cobblestone, i, j, 4, structureBoundingBoxIn);
            }
        }

        for (int j = 4; j < 6; ++j) {
            this.setBlockState(world, cobblestone, 7, j, 4, structureBoundingBoxIn);
        }

        // Door

        this.createVillageDoor(world, structureBoundingBoxIn, rand, 6, 4, 4, EnumFacing.NORTH);




        // Right Side Church

        for (int j = 0; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 4, j, 5, structureBoundingBoxIn);
        }
        for (int j = 0; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 3, j, 6, structureBoundingBoxIn);
        }
        for (int j = 0; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 2, j, 7, structureBoundingBoxIn);
        }




        for (int j = 0; j < 23; ++j) {
            for (int k = 8; k < 12; ++k) {
                this.setBlockState(world, cobblestone, 1, j, k, structureBoundingBoxIn);
            }
        }

        for (int j = 0; j < 10; ++j) {
            this.setBlockState(world, cobblestone, 1, j, 12, structureBoundingBoxIn);
        }

        for (int j = 17; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 1, j, 12, structureBoundingBoxIn);
        }

        for (int j = 0; j < 9; ++j) {
            this.setBlockState(world, cobblestone, 1, j, 13, structureBoundingBoxIn);
        }

        for (int j = 18; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 1, j, 13, structureBoundingBoxIn);
        }

        for (int j = 0; j < 10; ++j) {
            this.setBlockState(world, cobblestone, 1, j, 14, structureBoundingBoxIn);
        }

        for (int j = 17; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 1, j, 14, structureBoundingBoxIn);
        }

        for (int j = 0; j < 23; ++j) {
            for (int k = 15; k < 18; ++k) {
                this.setBlockState(world, cobblestone, 1, j, k, structureBoundingBoxIn);
            }
        }

        for (int j = 0; j < 10; ++j) {
            this.setBlockState(world, cobblestone, 1, j, 15, structureBoundingBoxIn);
        }

        for (int j = 17; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 1, j, 15, structureBoundingBoxIn);
        }

        for (int j = 0; j < 9; ++j) {
            this.setBlockState(world, cobblestone, 1, j, 16, structureBoundingBoxIn);
        }

        for (int j = 18; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 1, j, 16, structureBoundingBoxIn);
        }

        for (int j = 0; j < 10; ++j) {
            this.setBlockState(world, cobblestone, 1, j, 17, structureBoundingBoxIn);
        }

        for (int j = 17; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 1, j, 17, structureBoundingBoxIn);
        }

        for (int j = 0; j < 10; ++j) {
            this.setBlockState(world, cobblestone, 1, j, 18, structureBoundingBoxIn);
        }

        for (int j = 17; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 1, j, 18, structureBoundingBoxIn);
        }

        for (int j = 0; j < 9; ++j) {
            this.setBlockState(world, cobblestone, 1, j, 19, structureBoundingBoxIn);
        }

        for (int j = 18; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 1, j, 19, structureBoundingBoxIn);
        }

        for (int j = 0; j < 10; ++j) {
            this.setBlockState(world, cobblestone, 1, j, 20, structureBoundingBoxIn);
        }

        for (int j = 17; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 1, j, 20, structureBoundingBoxIn);
        }

        for (int j = 0; j < 23; ++j) {
            for (int k = 21; k < 24; ++k) {
                this.setBlockState(world, cobblestone, 1, j, k, structureBoundingBoxIn);
            }
        }

        for (int j = 0; j < 10; ++j) {
            this.setBlockState(world, cobblestone, 1, j, 24, structureBoundingBoxIn);
        }

        for (int j = 17; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 1, j, 24, structureBoundingBoxIn);
        }

        for (int j = 0; j < 9; ++j) {
            this.setBlockState(world, cobblestone, 1, j, 25, structureBoundingBoxIn);
        }

        for (int j = 18; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 1, j, 25, structureBoundingBoxIn);
        }

        for (int j = 0; j < 10; ++j) {
            this.setBlockState(world, cobblestone, 1, j, 26, structureBoundingBoxIn);
        }

        for (int j = 17; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 1, j, 26, structureBoundingBoxIn);
        }

        for (int j = 0; j < 23; ++j) {
            for (int k = 27; k < 29; ++k) {
                this.setBlockState(world, cobblestone, 1, j, k, structureBoundingBoxIn);
            }
        }


        for (int j = 0; j < 23; ++j) {
            for (int k = 29; k < 31; ++k) {
                this.setBlockState(world, cobblestone, 2, j, k, structureBoundingBoxIn);
            }
        }

        for (int j = 0; j < 23; ++j) {
            for (int k = 31; k < 33; ++k) {
                this.setBlockState(world, cobblestone, 3, j, k, structureBoundingBoxIn);
            }
        }


        // Left Side Church

        for (int j = 0; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 8, j, 5, structureBoundingBoxIn);
        }
        for (int j = 0; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 9, j, 6, structureBoundingBoxIn);
        }
        for (int j = 0; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 10, j, 7, structureBoundingBoxIn);
        }




        for (int j = 0; j < 23; ++j) {
            for (int k = 8; k < 12; ++k) {
                this.setBlockState(world, cobblestone, 11, j, k, structureBoundingBoxIn);
            }
        }

        for (int j = 0; j < 10; ++j) {
            this.setBlockState(world, cobblestone, 11, j, 12, structureBoundingBoxIn);
        }

        for (int j = 17; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 11, j, 12, structureBoundingBoxIn);
        }

        for (int j = 0; j < 9; ++j) {
            this.setBlockState(world, cobblestone, 11, j, 13, structureBoundingBoxIn);
        }

        for (int j = 18; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 11, j, 13, structureBoundingBoxIn);
        }

        for (int j = 0; j < 10; ++j) {
            this.setBlockState(world, cobblestone, 11, j, 14, structureBoundingBoxIn);
        }

        for (int j = 17; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 11, j, 14, structureBoundingBoxIn);
        }

        for (int j = 0; j < 23; ++j) {
            for (int k = 15; k < 18; ++k) {
                this.setBlockState(world, cobblestone, 11, j, k, structureBoundingBoxIn);
            }
        }

        for (int j = 0; j < 10; ++j) {
            this.setBlockState(world, cobblestone, 11, j, 15, structureBoundingBoxIn);
        }

        for (int j = 17; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 11, j, 15, structureBoundingBoxIn);
        }

        for (int j = 0; j < 9; ++j) {
            this.setBlockState(world, cobblestone, 11, j, 16, structureBoundingBoxIn);
        }

        for (int j = 18; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 11, j, 16, structureBoundingBoxIn);
        }

        for (int j = 0; j < 10; ++j) {
            this.setBlockState(world, cobblestone, 11, j, 17, structureBoundingBoxIn);
        }

        for (int j = 17; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 11, j, 17, structureBoundingBoxIn);
        }


        for (int j = 0; j < 10; ++j) {
            this.setBlockState(world, cobblestone, 11, j, 18, structureBoundingBoxIn);
        }

        for (int j = 17; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 11, j, 18, structureBoundingBoxIn);
        }

        for (int j = 0; j < 9; ++j) {
            this.setBlockState(world, cobblestone, 11, j, 19, structureBoundingBoxIn);
        }

        for (int j = 18; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 11, j, 19, structureBoundingBoxIn);
        }

        for (int j = 0; j < 10; ++j) {
            this.setBlockState(world, cobblestone, 11, j, 20, structureBoundingBoxIn);
        }

        for (int j = 17; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 11, j, 20, structureBoundingBoxIn);
        }

        for (int j = 0; j < 23; ++j) {
            for (int k = 21; k < 24; ++k) {
                this.setBlockState(world, cobblestone, 11, j, k, structureBoundingBoxIn);
            }
        }

        for (int j = 0; j < 10; ++j) {
            this.setBlockState(world, cobblestone, 11, j, 24, structureBoundingBoxIn);
        }

        for (int j = 17; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 11, j, 24, structureBoundingBoxIn);
        }

        for (int j = 0; j < 9; ++j) {
            this.setBlockState(world, cobblestone, 11, j, 25, structureBoundingBoxIn);
        }

        for (int j = 18; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 11, j, 25, structureBoundingBoxIn);
        }

        for (int j = 0; j < 10; ++j) {
            this.setBlockState(world, cobblestone, 11, j, 26, structureBoundingBoxIn);
        }

        for (int j = 17; j < 23; ++j) {
            this.setBlockState(world, cobblestone, 11, j, 26, structureBoundingBoxIn);
        }


        for (int j = 0; j < 23; ++j) {
            for (int k = 27; k < 29; ++k) {
                this.setBlockState(world, cobblestone, 11, j, k, structureBoundingBoxIn);
            }
        }



        for (int j = 0; j < 23; ++j) {
            for (int k = 29; k < 31; ++k) {
                this.setBlockState(world, cobblestone, 10, j, k, structureBoundingBoxIn);
            }
        }
        for (int j = 0; j < 23; ++j) {
            for (int k = 31; k < 33; ++k) {
                this.setBlockState(world, cobblestone, 9, j, k, structureBoundingBoxIn);
            }
        }

        // Back Church

        for (int i = 4; i < 9; ++i) {
            for (int j = 0; j < 23; ++j) {
                this.setBlockState(world, cobblestone, i, j, 33, structureBoundingBoxIn);
            }
        }

        // Inside Church
        this.spawnVillagers(world, structureBoundingBoxIn, 6, 5, 30, 1);

        this.spawnCrucifix(world, structureBoundingBoxIn, 6, 5, 32, 1);

        for (int j = 8; j < 10; ++j) {
            this.setBlockState(world, fence, 6, j, 7, structureBoundingBoxIn);
        }

        this.setBlockState(world, glowstone, 6, 7, 7, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorSouthTopOpen, 6, 7, 6, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorNorthTopOpen, 6, 7, 8, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorWestTopOpen, 5, 7, 7, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorEastTopOpen, 7, 7, 7, structureBoundingBoxIn);

        this.setBlockState(world, glowstone, 4, 3, 6, structureBoundingBoxIn);
        this.setBlockState(world, glowstone, 8, 3, 6, structureBoundingBoxIn);

        // Right Stairs

        this.setBlockState(world, stairCobblestoneWestBottomStraight, 3, 4, 9, structureBoundingBoxIn);
        this.setBlockState(world, slabCobblestoneTop, 2, 4, 9, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneWestBottomStraight, 2, 5, 9, structureBoundingBoxIn);
        this.setBlockState(world, slabCobblestoneTop, 2, 5, 8, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneSouthBottomStraight, 2, 6, 8, structureBoundingBoxIn);
        this.setBlockState(world, slabCobblestoneTop, 3, 6, 8, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneEastBottomStraight, 3, 7, 8, structureBoundingBoxIn);
        this.setBlockState(world, slabCobblestoneTop, 3, 7, 7, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneSouthBottomStraight, 3, 8, 7, structureBoundingBoxIn);
        this.setBlockState(world, slabCobblestoneTop, 4, 8, 7, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneEastBottomStraight, 4, 9, 7, structureBoundingBoxIn);
        this.setBlockState(world, slabCobblestoneTop, 4, 9, 6, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneSouthBottomStraight, 4, 10, 6, structureBoundingBoxIn);

        // Left Stairs

        this.setBlockState(world, stairCobblestoneEastBottomStraight, 9, 4, 9, structureBoundingBoxIn);
        this.setBlockState(world, slabCobblestoneTop, 10, 4, 9, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneEastBottomStraight, 10, 5, 9, structureBoundingBoxIn);
        this.setBlockState(world, slabCobblestoneTop, 10, 5, 8, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneSouthBottomStraight, 10, 6, 8, structureBoundingBoxIn);
        this.setBlockState(world, slabCobblestoneTop, 9, 6, 8, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneWestBottomStraight, 9, 7, 8, structureBoundingBoxIn);
        this.setBlockState(world, slabCobblestoneTop, 9, 7, 7, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneSouthBottomStraight, 9, 8, 7, structureBoundingBoxIn);
        this.setBlockState(world, slabCobblestoneTop, 8, 8, 7, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneWestBottomStraight, 8, 9, 7, structureBoundingBoxIn);
        this.setBlockState(world, slabCobblestoneTop, 8, 9, 6, structureBoundingBoxIn);
        this.setBlockState(world, stairCobblestoneSouthBottomStraight, 8, 10, 6, structureBoundingBoxIn);

        // Second Floor

        for (int i = 5; i < 8; ++i) {
            this.setBlockState(world, cobblestone, i, 10, 7, structureBoundingBoxIn);
        }

        for (int i = 5; i < 8; ++i) {
            for (int k = 5; k < 7; ++k) {
                this.setBlockState(world, slabCobblestoneTop, i, 10, k, structureBoundingBoxIn);
            }
        }

        for (int i = 4; i < 9; ++i) {
            this.setBlockState(world, slabCobblestoneTop, i, 10, 8, structureBoundingBoxIn);
        }

        this.setBlockState(world, slabCobblestoneTop, 2, 10, 8, structureBoundingBoxIn);
        this.setBlockState(world, slabCobblestoneTop, 10, 10, 8, structureBoundingBoxIn);

        for (int i = 2; i < 11; ++i) {
            for (int k = 9; k < 11; ++k) {
                this.setBlockState(world, slabCobblestoneTop, i, 10, k, structureBoundingBoxIn);
            }
        }


        // Chest

        this.generateChest(world, structureBoundingBoxIn, rand, 7, 11, 5, CCTreasures.CHURCH);
        this.generateChest(world, structureBoundingBoxIn, rand, 5, 11, 5, CCTreasures.CHURCH);


        // Cobblestone Wall

        for (int i = 2; i < 11; ++i) {
            this.setBlockState(world, cobblestoneWall, i, 11, 10, structureBoundingBoxIn);
        }

        for (int k = 5; k < 16; ++k) {
            this.setBlockState(world, fence, 6, 19, k, structureBoundingBoxIn);
        }

        for (int j = 15; j < 23; ++j) {
            this.setBlockState(world, fence, 6, j, 7, structureBoundingBoxIn);
        }

        this.setBlockState(world, glowstone, 6, 14, 7, structureBoundingBoxIn);

        this.setBlockState(world, trapDoorSouthTopOpen, 6, 14, 6, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorNorthTopOpen, 6, 14, 8, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorWestTopOpen, 5, 14, 7, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorEastTopOpen, 7, 14, 7, structureBoundingBoxIn);



        // Chairs

        for (int i = 2; i < 5; ++i) {
            this.setBlockState(world, stairWoodSouthBottomStraight, i, 4, 10, structureBoundingBoxIn);
        }
        for (int i = 2; i < 5; ++i) {
            this.setBlockState(world, stairWoodSouthBottomStraight, i, 4, 12, structureBoundingBoxIn);
        }
        for (int i = 2; i < 5; ++i) {
            this.setBlockState(world, stairWoodSouthBottomStraight, i, 4, 14, structureBoundingBoxIn);
        }
        for (int i = 2; i < 5; ++i) {
            this.setBlockState(world, stairWoodSouthBottomStraight, i, 4, 16, structureBoundingBoxIn);
        }
        for (int i = 2; i < 5; ++i) {
            this.setBlockState(world, stairWoodSouthBottomStraight, i, 4, 18, structureBoundingBoxIn);
        }
        for (int i = 2; i < 5; ++i) {
            this.setBlockState(world, stairWoodSouthBottomStraight, i, 4, 20, structureBoundingBoxIn);
        }
        for (int i = 2; i < 5; ++i) {
            this.setBlockState(world, stairWoodSouthBottomStraight, i, 4, 22, structureBoundingBoxIn);
        }
        for (int i = 2; i < 5; ++i) {
            this.setBlockState(world, stairWoodSouthBottomStraight, i, 4, 24, structureBoundingBoxIn);
        }
        for (int i = 2; i < 5; ++i) {
            this.setBlockState(world, stairWoodSouthBottomStraight, i, 4, 26, structureBoundingBoxIn);
        }

        this.setBlockState(world, glowstone, 2, 3, 11, structureBoundingBoxIn);
        this.setBlockState(world, glowstone, 2, 3, 13, structureBoundingBoxIn);
        this.setBlockState(world, glowstone, 2, 3, 15, structureBoundingBoxIn);
        this.setBlockState(world, glowstone, 2, 3, 17, structureBoundingBoxIn);
        this.setBlockState(world, glowstone, 2, 3, 19, structureBoundingBoxIn);
        this.setBlockState(world, glowstone, 2, 3, 21, structureBoundingBoxIn);
        this.setBlockState(world, glowstone, 2, 3, 23, structureBoundingBoxIn);
        this.setBlockState(world, glowstone, 2, 3, 25, structureBoundingBoxIn);
        this.setBlockState(world, glowstone, 2, 3, 27, structureBoundingBoxIn);





        for (int i = 8; i < 11; ++i) {
            this.setBlockState(world, stairWoodSouthBottomStraight, i, 4, 10, structureBoundingBoxIn);
        }
        for (int i = 8; i < 11; ++i) {
            this.setBlockState(world, stairWoodSouthBottomStraight, i, 4, 12, structureBoundingBoxIn);
        }
        for (int i = 8; i < 11; ++i) {
            this.setBlockState(world, stairWoodSouthBottomStraight, i, 4, 14, structureBoundingBoxIn);
        }
        for (int i = 8; i < 11; ++i) {
            this.setBlockState(world, stairWoodSouthBottomStraight, i, 4, 16, structureBoundingBoxIn);
        }
        for (int i = 8; i < 11; ++i) {
            this.setBlockState(world, stairWoodSouthBottomStraight, i, 4, 18, structureBoundingBoxIn);
        }
        for (int i = 8; i < 11; ++i) {
            this.setBlockState(world, stairWoodSouthBottomStraight, i, 4, 20, structureBoundingBoxIn);
        }
        for (int i = 8; i < 11; ++i) {
            this.setBlockState(world, stairWoodSouthBottomStraight, i, 4, 22, structureBoundingBoxIn);
        }
        for (int i = 8; i < 11; ++i) {
            this.setBlockState(world, stairWoodSouthBottomStraight, i, 4, 24, structureBoundingBoxIn);
        }
        for (int i = 8; i < 11; ++i) {
            this.setBlockState(world, stairWoodSouthBottomStraight, i, 4, 26, structureBoundingBoxIn);
        }

        this.setBlockState(world, glowstone, 10, 3, 11, structureBoundingBoxIn);
        this.setBlockState(world, glowstone, 10, 3, 13, structureBoundingBoxIn);
        this.setBlockState(world, glowstone, 10, 3, 15, structureBoundingBoxIn);
        this.setBlockState(world, glowstone, 10, 3, 17, structureBoundingBoxIn);
        this.setBlockState(world, glowstone, 10, 3, 19, structureBoundingBoxIn);
        this.setBlockState(world, glowstone, 10, 3, 21, structureBoundingBoxIn);
        this.setBlockState(world, glowstone, 10, 3, 23, structureBoundingBoxIn);
        this.setBlockState(world, glowstone, 10, 3, 25, structureBoundingBoxIn);
        this.setBlockState(world, glowstone, 10, 3, 27, structureBoundingBoxIn);


        for (int j = 9; j < 23; ++j) {
            this.setBlockState(world, fence, 6, j, 16, structureBoundingBoxIn);
        }

        this.setBlockState(world, glowstone, 6, 8, 16, structureBoundingBoxIn);

        this.setBlockState(world, trapDoorSouthTopOpen, 6, 8, 15, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorNorthTopOpen, 6, 8, 17, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorWestTopOpen, 5, 8, 16, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorEastTopOpen, 7, 8, 16, structureBoundingBoxIn);


        for (int j = 9; j < 23; ++j) {
            this.setBlockState(world, fence, 6, j, 22, structureBoundingBoxIn);
        }

        this.setBlockState(world, glowstone, 6, 8, 22, structureBoundingBoxIn);

        this.setBlockState(world, trapDoorSouthTopOpen, 6, 8, 21, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorNorthTopOpen, 6, 8, 23, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorWestTopOpen, 5, 8, 22, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorEastTopOpen, 7, 8, 22, structureBoundingBoxIn);


        for (int k = 17; k < 30; ++k) {
            this.setBlockState(world, fence, 6, 15, k, structureBoundingBoxIn);
        }

        for (int i = 2; i < 11; ++i) {
            this.setBlockState(world, fence, i, 15, 16, structureBoundingBoxIn);
        }

        for (int i = 2; i < 11; ++i) {
            this.setBlockState(world, fence, i, 15, 22, structureBoundingBoxIn);
        }

        for (int i = 2; i < 11; ++i) {
            this.setBlockState(world, fence, i, 15, 28, structureBoundingBoxIn);
        }
        // Table Floor

        this.setBlockState(world, stairCobblestoneEastBottomInnerLeft, 2, 4,28, structureBoundingBoxIn);

        for (int i = 3; i < 5; ++i) {
            this.setBlockState(world, stairCobblestoneNorthBottomStraight, i, 4, 28, structureBoundingBoxIn);
        }

        for (int i = 3; i < 10; ++i) {
            for (int k = 29; k < 31; ++k) {
                this.setBlockState(world, cobblestone, i, 4, k, structureBoundingBoxIn);
            }
        }

        for (int i = 4; i < 9; ++i) {
            for (int k = 31; k < 33; ++k) {
                this.setBlockState(world, cobblestone, i, 4, k, structureBoundingBoxIn);
            }
        }

        for (int i = 8; i < 10; ++i) {
            this.setBlockState(world, stairCobblestoneNorthBottomStraight, i, 4, 28, structureBoundingBoxIn);
        }

        this.setBlockState(world, stairCobblestoneWestBottomInnerRight, 10, 4,28, structureBoundingBoxIn);

        this.setBlockState(world, glowstone, 4, 4, 32, structureBoundingBoxIn);
        this.setBlockState(world, glowstone, 8, 4, 32, structureBoundingBoxIn);

        for (int j = 10; j < 23; ++j) {
            this.setBlockState(world, fence, 6, j, 30, structureBoundingBoxIn);
        }

        this.setBlockState(world, glowstone, 6, 9, 30, structureBoundingBoxIn);

        this.setBlockState(world, trapDoorSouthTopOpen, 6, 9, 29, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorNorthTopOpen, 6, 9, 31, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorWestTopOpen, 5, 9, 30, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorEastTopOpen, 7, 9, 30, structureBoundingBoxIn);

        // Table

        this.setBlockState(world, stairWoodNorthTopStraight, 5, 5, 30, structureBoundingBoxIn);

        for (int i = 5; i < 8; ++i) {
            this.setBlockState(world, slabWoodTop, i, 5, 29, structureBoundingBoxIn);
        }
        this.setBlockState(world, stairWoodNorthTopStraight, 7, 5, 30, structureBoundingBoxIn);

        // Stained Glass Windows

        this.setBlockState(world, stainedGlassRandom, 1, 17, 13, structureBoundingBoxIn);

        for (int j = 10; j < 17; ++j) {
            for (int k = 12; k < 15; ++k) {
                int colorMetadata0 = rand.nextInt(16);
                EnumDyeColor dyeColor0 = EnumDyeColor.byMetadata(colorMetadata0);
                IBlockState stainedGlassRandom0 = stainedGlass.getDefaultState().withProperty(BlockStainedGlass.COLOR, dyeColor0);
                this.setBlockState(world, stainedGlassRandom0, 1, j, k, structureBoundingBoxIn);
            }
        }

        this.setBlockState(world, stainedGlassRandom, 1, 9, 13, structureBoundingBoxIn);


        this.setBlockState(world, stainedGlassRandom, 1, 17, 19, structureBoundingBoxIn);

        for (int j = 10; j < 17; ++j) {
            for (int k = 18; k < 21; ++k) {
                int colorMetadata0 = rand.nextInt(16);
                EnumDyeColor dyeColor0 = EnumDyeColor.byMetadata(colorMetadata0);
                IBlockState stainedGlassRandom0 = stainedGlass.getDefaultState().withProperty(BlockStainedGlass.COLOR, dyeColor0);
                this.setBlockState(world, stainedGlassRandom0, 1, j, k, structureBoundingBoxIn);
            }
        }

        this.setBlockState(world, stainedGlassRandom, 1, 9, 19, structureBoundingBoxIn);


        this.setBlockState(world, stainedGlassRandom, 1, 17, 25, structureBoundingBoxIn);

        for (int j = 10; j < 17; ++j) {
            for (int k = 24; k < 27; ++k) {
                int colorMetadata0 = rand.nextInt(16);
                EnumDyeColor dyeColor0 = EnumDyeColor.byMetadata(colorMetadata0);
                IBlockState stainedGlassRandom0 = stainedGlass.getDefaultState().withProperty(BlockStainedGlass.COLOR, dyeColor0);
                this.setBlockState(world, stainedGlassRandom0, 1, j, k, structureBoundingBoxIn);
            }
        }

        this.setBlockState(world, stainedGlassRandom, 1, 9, 25, structureBoundingBoxIn);






        this.setBlockState(world, stainedGlassRandom, 6, 17, 33, structureBoundingBoxIn);

        for (int i = 5; i < 8; ++i) {
            for (int j = 11; j < 17; ++j) {
                int colorMetadata0 = rand.nextInt(16);
                EnumDyeColor dyeColor0 = EnumDyeColor.byMetadata(colorMetadata0);
                IBlockState stainedGlassRandom0 = stainedGlass.getDefaultState().withProperty(BlockStainedGlass.COLOR, dyeColor0);
                this.setBlockState(world, stainedGlassRandom0, i, j, 33, structureBoundingBoxIn);
            }
        }






        this.setBlockState(world, stainedGlassRandom, 11, 17, 13, structureBoundingBoxIn);

        for (int j = 10; j < 17; ++j) {
            for (int k = 12; k < 15; ++k) {
                int colorMetadata0 = rand.nextInt(16);
                EnumDyeColor dyeColor0 = EnumDyeColor.byMetadata(colorMetadata0);
                IBlockState stainedGlassRandom0 = stainedGlass.getDefaultState().withProperty(BlockStainedGlass.COLOR, dyeColor0);
                this.setBlockState(world, stainedGlassRandom0, 11, j, k, structureBoundingBoxIn);
            }
        }

        this.setBlockState(world, stainedGlassRandom, 11, 9, 13, structureBoundingBoxIn);




        this.setBlockState(world, stainedGlassRandom, 11, 17, 19, structureBoundingBoxIn);

        for (int j = 10; j < 17; ++j) {
            for (int k = 18; k < 21; ++k) {
                int colorMetadata0 = rand.nextInt(16);
                EnumDyeColor dyeColor0 = EnumDyeColor.byMetadata(colorMetadata0);
                IBlockState stainedGlassRandom0 = stainedGlass.getDefaultState().withProperty(BlockStainedGlass.COLOR, dyeColor0);
                this.setBlockState(world, stainedGlassRandom0, 11, j, k, structureBoundingBoxIn);
            }
        }

        this.setBlockState(world, stainedGlassRandom, 11, 9, 19, structureBoundingBoxIn);




        this.setBlockState(world, stainedGlassRandom, 11, 17, 25, structureBoundingBoxIn);

        for (int j = 10; j < 17; ++j) {
            for (int k = 24; k < 27; ++k) {
                int colorMetadata0 = rand.nextInt(16);
                EnumDyeColor dyeColor0 = EnumDyeColor.byMetadata(colorMetadata0);
                IBlockState stainedGlassRandom0 = stainedGlass.getDefaultState().withProperty(BlockStainedGlass.COLOR, dyeColor0);
                this.setBlockState(world, stainedGlassRandom0, 11, j, k, structureBoundingBoxIn);
            }
        }

        this.setBlockState(world, stainedGlassRandom, 11, 9, 25, structureBoundingBoxIn);

        // Roof

        for (int k = 6; k < 33; ++k) {
            this.setBlockState(world, slabWoodTop, 8, 23, k, structureBoundingBoxIn);
        }

        for (int k = 7; k < 31; ++k) {
            this.setBlockState(world, slabWoodTop, 9, 23, k, structureBoundingBoxIn);
        }

        for (int k = 8; k < 29; ++k) {
            this.setBlockState(world, slabWoodTop, 10, 23, k, structureBoundingBoxIn);
        }

        for (int i = 5; i < 8; ++i) {
            for (int k = 5; k < 33; ++k) {
                this.setBlockState(world, slabWoodTop, i, 23, k, structureBoundingBoxIn);
            }
        }

        for (int k = 6; k < 33; ++k) {
            this.setBlockState(world, slabWoodTop, 4, 23, k, structureBoundingBoxIn);
        }

        for (int k = 7; k < 31; ++k) {
            this.setBlockState(world, slabWoodTop, 3, 23, k, structureBoundingBoxIn);
        }

        for (int k = 8; k < 29; ++k) {
            this.setBlockState(world, slabWoodTop, 2, 23, k, structureBoundingBoxIn);
        }


        // Log Beam
        if (!(this.biome instanceof BiomeSnow || this.biome instanceof BiomeDesert || this.biome instanceof BiomeMesa || this.biome instanceof BiomeMushroomIsland)) {
            IBlockState logAxisZ = this.getBiomeSpecificBlockState(Blocks.LOG.getDefaultState())
                    .withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Z);
            for (int k = 5; k < 33; ++k) {
                this.setBlockState(world, logAxisZ, 6, 23, k, structureBoundingBoxIn);
            }
        }



        // Front Roof

        for (int i = 5; i < 8; ++i) {
            this.setBlockState(world, stairWoodNorthTopStraight, i, 22, 3, structureBoundingBoxIn);
        }

        for (int i = 5; i < 8; ++i) {
            this.setBlockState(world, stairWoodNorthBottomStraight, i, 23, 4, structureBoundingBoxIn);
        }

        // Right Roof

        this.setBlockState(world, stairWoodWestTopOuterRight, 8, 22, 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthTopInnerLeft, 8, 22, 4, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestTopOuterRight, 9, 22, 4, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthTopInnerLeft, 9, 22, 5, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestTopOuterRight, 10, 22, 5, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthTopInnerLeft, 10, 22, 6, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestTopOuterRight, 11, 22, 6, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthTopInnerLeft, 11, 22, 7, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestTopOuterRight, 12, 22, 7, structureBoundingBoxIn);

        for (int k = 8; k < 29; ++k) {
            this.setBlockState(world, stairWoodWestTopStraight, 12, 22, k, structureBoundingBoxIn);
        }

        this.setBlockState(world, stairWoodSouthTopOuterRight, 12, 22, 29, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodSouthTopInnerRight, 11, 22, 29, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestTopStraight, 11, 22, 30, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestTopOuterLeft, 11, 22, 31, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodSouthTopInnerRight, 10, 22, 31, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestTopStraight, 10, 22, 32, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodSouthTopOuterRight, 10, 22, 33, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodSouthTopInnerRight, 9, 22, 33, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestTopOuterLeft, 9, 22, 34, structureBoundingBoxIn);



        this.setBlockState(world, stairWoodWestBottomOuterRight, 8, 23, 4, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthBottomInnerLeft, 8, 23, 5, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthBottomOuterLeft, 9, 23, 5, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestBottomInnerRight, 9, 23, 6, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthBottomOuterLeft, 10, 23, 6, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestBottomInnerRight, 10, 23, 7, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthBottomOuterLeft, 11, 23, 7, structureBoundingBoxIn);

        for (int k = 8; k < 29; ++k) {
            this.setBlockState(world, stairWoodWestBottomStraight, 11, 23, k, structureBoundingBoxIn);
        }

        this.setBlockState(world, stairWoodSouthBottomOuterRight, 11, 23, 29, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestBottomInnerLeft, 10, 23, 29, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestBottomStraight, 10, 23, 30, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodSouthBottomOuterRight, 10, 23, 31, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestBottomInnerLeft, 9, 23, 31, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestBottomStraight, 9, 23, 32, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodWestBottomOuterLeft, 9, 23, 33, structureBoundingBoxIn);

        // Left Roof

        this.setBlockState(world, stairWoodNorthTopOuterRight, 4, 22, 3, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastTopInnerLeft, 4, 22, 4, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthTopOuterRight, 3, 22, 4, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastTopInnerLeft, 3, 22, 5, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthTopOuterRight, 2, 22, 5, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastTopInnerLeft, 2, 22, 6, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthTopOuterRight, 1, 22, 6, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastTopInnerLeft, 1, 22, 7, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthTopOuterRight, 0, 22, 7, structureBoundingBoxIn);

        for (int k = 8; k < 29; ++k) {
            this.setBlockState(world, stairWoodEastTopStraight, 0, 22, k, structureBoundingBoxIn);
        }

        this.setBlockState(world, stairWoodSouthTopOuterLeft, 0, 22, 29, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastTopInnerRight, 1, 22, 29, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastTopStraight, 1, 22, 30, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodSouthTopOuterLeft, 1, 22, 31, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastTopInnerRight, 2, 22, 31, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastTopStraight, 2, 22, 32, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodSouthTopOuterLeft, 2, 22, 33, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastTopInnerRight, 3, 22, 33, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodSouthTopOuterLeft, 3, 22, 34, structureBoundingBoxIn);



        this.setBlockState(world, stairWoodEastBottomOuterLeft, 4, 23, 4, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastBottomInnerLeft, 4, 23, 5, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthBottomOuterRight, 3, 23, 5, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastBottomInnerLeft, 3, 23, 6, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthBottomOuterRight, 2, 23, 6, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastBottomInnerLeft, 2, 23, 7, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodNorthBottomOuterRight, 1, 23, 7, structureBoundingBoxIn);

        for (int k = 8; k < 29; ++k) {
            this.setBlockState(world, stairWoodEastBottomStraight, 1, 23, k, structureBoundingBoxIn);
        }

        this.setBlockState(world, stairWoodSouthBottomOuterLeft, 1, 23, 29, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodSouthBottomInnerLeft, 2, 23, 29, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastBottomStraight, 2, 23, 30, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodSouthBottomOuterLeft, 2, 23, 31, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastBottomInnerLeft, 3, 23, 31, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastBottomStraight, 3, 23, 32, structureBoundingBoxIn);
        this.setBlockState(world, stairWoodEastBottomOuterRight, 3, 23, 33, structureBoundingBoxIn);


        // Back Roof

        for (int i = 4; i < 9; ++i) {
            this.setBlockState(world, stairWoodSouthTopStraight, i, 22, 34, structureBoundingBoxIn);
        }

        for (int i = 4; i < 9; ++i) {
            this.setBlockState(world, stairWoodSouthBottomStraight, i, 23, 33, structureBoundingBoxIn);
        }


        // Roof Bell

        // Inside Roof Bell


        for (int j = 28; j < 34; ++j) {
            this.setBlockState(world, fence, 6, j, 23, structureBoundingBoxIn);
        }

        this.setBlockState(world, glowstone, 6, 27, 23, structureBoundingBoxIn);

        this.setBlockState(world, trapDoorSouthTopOpen, 6, 27, 22, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorNorthTopOpen, 6, 27, 24, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorWestTopOpen, 5, 27, 23, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorEastTopOpen, 7, 27, 23, structureBoundingBoxIn);



        for (int j = 28; j < 34; ++j) {
            this.setBlockState(world, fence, 6, j, 27, structureBoundingBoxIn);
        }

        this.setBlockState(world, glowstone, 6, 27, 27, structureBoundingBoxIn);

        this.setBlockState(world, trapDoorSouthTopOpen, 6, 27, 26, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorNorthTopOpen, 6, 27, 28, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorWestTopOpen, 5, 27, 27, structureBoundingBoxIn);
        this.setBlockState(world, trapDoorEastTopOpen, 7, 27, 27, structureBoundingBoxIn);


        // Front Roof Bell

        for (int i = 5; i < 8; ++i) {
            for (int j = 24; j < 28; ++j) {
                this.setBlockState(world, plankWood, i, j, 18, structureBoundingBoxIn);
            }
        }

        for (int i = 5; i < 8; ++i) {
            for (int j = 28; j < 31; ++j) {
                this.setBlockState(world, plankWood, i, j, 19, structureBoundingBoxIn);
            }
        }

        for (int i = 5; i < 8; ++i) {
            this.setBlockState(world, plankWood, i, 31, 20, structureBoundingBoxIn);
        }

        this.setBlockState(world, plankWood, 6, 32, 20, structureBoundingBoxIn);

        for (int j = 31; j < 33; ++j) {
            this.setBlockState(world, plankWood, 7, j, 21, structureBoundingBoxIn);
        }
        for (int j = 31; j < 33; ++j) {
            this.setBlockState(world, plankWood, 5, j, 21, structureBoundingBoxIn);
        }



        this.setBlockState(world, glass, 6, 27, 18,structureBoundingBoxIn);

        for (int i = 5; i < 8; ++i) {
            this.setBlockState(world, glass, i, 26, 18, structureBoundingBoxIn);
        }

        this.setBlockState(world, glass, 6, 25, 18,structureBoundingBoxIn);



        // Right Roof Bell

        for (int j = 24; j < 28; ++j) {
            this.setBlockState(world, plankWood, 8, j, 19, structureBoundingBoxIn);
        }

        for (int j = 24; j < 28; ++j) {
            for (int k = 20; k < 22; ++k) {
                this.setBlockState(world, plankWood, 9, j, k, structureBoundingBoxIn);
            }
        }

        for (int j = 24; j < 28; ++j) {
            for (int k = 22; k < 29; ++k) {
                this.setBlockState(world, plankWood, 10, j, k, structureBoundingBoxIn);
            }
        }

        for (int j = 24; j < 28; ++j) {
            for (int k = 29; k < 31; ++k) {
                this.setBlockState(world, plankWood, 9, j, k, structureBoundingBoxIn);
            }
        }

        for (int j = 24; j < 28; ++j) {
            this.setBlockState(world, plankWood, 8, j, 31, structureBoundingBoxIn);
        }



        for (int j = 28; j < 31; ++j) {
            for (int k = 20; k < 22; ++k) {
                this.setBlockState(world, plankWood, 8, j, k, structureBoundingBoxIn);
            }
        }

        for (int j = 28; j < 31; ++j) {
            for (int k = 22; k < 29; ++k) {
                this.setBlockState(world, plankWood, 9, j, k, structureBoundingBoxIn);
            }
        }

        for (int j = 28; j < 31; ++j) {
            for (int k = 29; k < 31; ++k) {
                this.setBlockState(world, plankWood, 8, j, k, structureBoundingBoxIn);
            }
        }

        for (int j = 31; j < 33; ++j) {
            for (int k = 22; k < 29; ++k) {
                this.setBlockState(world, plankWood, 8, j, k, structureBoundingBoxIn);
            }
        }


        this.setBlockState(world, glass, 10, 27, 25, structureBoundingBoxIn);

        for (int k = 24; k < 27; ++k) {
            this.setBlockState(world, glass, 10, 26, k, structureBoundingBoxIn);
        }

        this.setBlockState(world, glass, 10, 25, 25, structureBoundingBoxIn);

        // Left Roof Bell

        for (int j = 24; j < 28; ++j) {
            this.setBlockState(world, plankWood, 4, j, 19, structureBoundingBoxIn);
        }

        for (int j = 24; j < 28; ++j) {
            for (int k = 20; k < 22; ++k) {
                this.setBlockState(world, plankWood, 3, j, k, structureBoundingBoxIn);
            }
        }

        for (int j = 24; j < 28; ++j) {
            for (int k = 22; k < 29; ++k) {
                this.setBlockState(world, plankWood, 2, j, k, structureBoundingBoxIn);
            }
        }

        for (int j = 24; j < 28; ++j) {
            for (int k = 29; k < 31; ++k) {
                this.setBlockState(world, plankWood, 3, j, k, structureBoundingBoxIn);
            }
        }

        for (int j = 24; j < 28; ++j) {
            this.setBlockState(world, plankWood, 4, j, 31, structureBoundingBoxIn);
        }

        for (int j = 28; j < 31; ++j) {
            for (int k = 20; k < 22; ++k) {
                this.setBlockState(world, plankWood, 4, j, k, structureBoundingBoxIn);
            }
        }

        for (int j = 28; j < 31; ++j) {
            for (int k = 22; k < 29; ++k) {
                this.setBlockState(world, plankWood, 3, j, k, structureBoundingBoxIn);
            }
        }

        for (int j = 28; j < 31; ++j) {
            for (int k = 29; k < 31; ++k) {
                this.setBlockState(world, plankWood, 4, j, k, structureBoundingBoxIn);
            }
        }


        for (int j = 31; j < 33; ++j) {
            for (int k = 22; k < 29; ++k) {
                this.setBlockState(world, plankWood, 4, j, k, structureBoundingBoxIn);
            }
        }

        this.setBlockState(world, glass, 2, 27, 25, structureBoundingBoxIn);

        for (int k = 24; k < 27; ++k) {
            this.setBlockState(world, glass, 2, 26, k, structureBoundingBoxIn);
        }

        this.setBlockState(world, glass, 2, 25, 25, structureBoundingBoxIn);


        // Back Roof Bell

        for (int i = 5; i < 8; ++i) {
            for (int j = 24; j < 28; ++j) {
                this.setBlockState(world, plankWood, i, j, 32, structureBoundingBoxIn);
            }
        }

        for (int i = 5; i < 8; ++i) {
            for (int j = 28; j < 31; ++j) {
                this.setBlockState(world, plankWood, i, j, 31, structureBoundingBoxIn);
            }
        }

        for (int i = 5; i < 8; ++i) {
            this.setBlockState(world, plankWood, i, 31, 30, structureBoundingBoxIn);
        }

        this.setBlockState(world, plankWood, 6, 32, 30, structureBoundingBoxIn);

        for (int j = 31; j < 33; ++j) {
            this.setBlockState(world, plankWood, 7, j, 29, structureBoundingBoxIn);
        }
        for (int j = 31; j < 33; ++j) {
            this.setBlockState(world, plankWood, 5, j, 29, structureBoundingBoxIn);
        }

        this.setBlockState(world, glass, 6, 27, 32,structureBoundingBoxIn);

        for (int i = 5; i < 8; ++i) {
            this.setBlockState(world, glass, i, 26, 32, structureBoundingBoxIn);
        }

        this.setBlockState(world, glass, 6, 25, 32,structureBoundingBoxIn);

        // Top Roof Bell

        for (int k = 22; k < 29; ++k) {
            this.setBlockState(world, plankWood, 7, 33, k, structureBoundingBoxIn);
        }

        this.setBlockState(world, plankWood, 6, 33, 21, structureBoundingBoxIn);

        for (int k = 22; k < 25; ++k) {
            this.setBlockState(world, plankWood, 6, 34, k, structureBoundingBoxIn);
        }

        this.setBlockState(world, plankWood, 5, 34, 25, structureBoundingBoxIn);

        this.setBlockState(world, plankWood, 6, 35, 25, structureBoundingBoxIn);

        this.setBlockState(world, plankWood, 7, 34, 25, structureBoundingBoxIn);

        for (int k = 26; k < 29; ++k) {
            this.setBlockState(world, plankWood, 6, 34, k, structureBoundingBoxIn);
        }

        this.setBlockState(world, plankWood, 6, 33, 29, structureBoundingBoxIn);

        for (int k = 22; k < 29; ++k) {
            this.setBlockState(world, plankWood, 5, 33, k, structureBoundingBoxIn);
        }

        // Roof Cross

        this.setBlockState(world, fenceWest, 7, 40, 25, structureBoundingBoxIn);

        for (int j = 36; j < 42; ++j) {
            this.setBlockState(world, fence, 6, j, 25, structureBoundingBoxIn);
        }

        this.setBlockState(world, fenceEast, 5, 40, 25, structureBoundingBoxIn);

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
        return registry.getID(VillagerInit.villagerHighPriest);
    }

    @Override
    protected IBlockState getBiomeSpecificBlockState(IBlockState blockstateIn) {
        net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID event = new net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID(startPiece == null ? null : startPiece.biome, blockstateIn);
        net.minecraftforge.common.MinecraftForge.TERRAIN_GEN_BUS.post(event);
        if (event.getResult() == net.minecraftforge.fml.common.eventhandler.Event.Result.DENY) return event.getReplacement();
        if (this.structureType == 1) {
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
        else if (this.structureType == 3) {
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
        else if (this.structureType == 2) {
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
        } else if (this.biome instanceof BiomeSnow) {
            if (blockstateIn.getBlock() == Blocks.LOG || blockstateIn.getBlock() == Blocks.LOG2) {
                return Blocks.SNOW.getDefaultState();
            }

            if (blockstateIn.getBlock() == Blocks.PLANKS) {
                return Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.SPRUCE);
            }

            if (blockstateIn.getBlock() == Blocks.OAK_STAIRS) {
                return Blocks.SPRUCE_STAIRS.getDefaultState()
                        .withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING))
                        .withProperty(BlockStairs.HALF, blockstateIn.getValue(BlockStairs.HALF))
                        .withProperty(BlockStairs.SHAPE, blockstateIn.getValue(BlockStairs.SHAPE));
            }

            if (blockstateIn.getBlock() == Blocks.COBBLESTONE)
            {
                return Blocks.SNOW.getDefaultState();
            }

            if (blockstateIn.getBlock() == Blocks.OAK_FENCE) {
                return Blocks.SPRUCE_FENCE.getDefaultState();
            }

            if (blockstateIn.getBlock() == Blocks.WOODEN_SLAB) {
                return Blocks.WOODEN_SLAB.getDefaultState().withProperty(BlockWoodSlab.VARIANT, BlockPlanks.EnumType.SPRUCE).withProperty(BlockWoodSlab.HALF, BlockSlab.EnumBlockHalf.TOP);
            }
        }
        return blockstateIn;
    }

    @Override
    protected @NotNull BlockDoor biomeDoor() {

        if (biome instanceof BiomeJungle) {
            return Blocks.JUNGLE_DOOR;
        } else if (biome == Biomes.ROOFED_FOREST || biome == Biomes.MUTATED_ROOFED_FOREST) {
            return Blocks.DARK_OAK_DOOR;
        } else if (biome == Biomes.ICE_PLAINS || biome == Biomes.ICE_MOUNTAINS) {
            return Blocks.SPRUCE_DOOR;
        }

        if (this.structureType == 2) {
            return Blocks.ACACIA_DOOR;
        } else if (this.structureType == 3) {
            return Blocks.SPRUCE_DOOR;
        } else {
            return Blocks.OAK_DOOR;
        }
    }

    @Override
    protected void spawnVillagers(World worldIn, StructureBoundingBox structurebb, int x, int y, int z, int count) {
        if (this.villagersSpawned < count) {
            for (int i = this.villagersSpawned; i < count; ++i) {
                int j = this.getXWithOffset(x + i, z);
                int k = this.getYWithOffset(y);
                int l = this.getZWithOffset(x + i, z);

                if (!structurebb.isVecInside(new BlockPos(j, k, l)))
                {
                    break;
                }

                ++this.villagersSpawned;

                if (this.isZombieInfested)
                {
                    EntityZombieVillager entityzombievillager = new EntityZombieVillager(worldIn);
                    entityzombievillager.setLocationAndAngles((double)j + 0.5D, (double)k, (double)l + 0.5D, 0.0F, 0.0F);
                    entityzombievillager.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityzombievillager)), (IEntityLivingData)null);
                    entityzombievillager.setForgeProfession(this.chooseForgeProfession(i, net.minecraftforge.fml.common.registry.VillagerRegistry.FARMER));
                    entityzombievillager.enablePersistence();
                    worldIn.spawnEntity(entityzombievillager);
                }
                else
                {
                    EntityVillager entityvillager = new EntityVillager(worldIn);
                    entityvillager.setLocationAndAngles((double)j + 0.5D, (double)k, (double)l + 0.5D, 0.0F, 0.0F);
                    net.minecraftforge.fml.common.registry.VillagerRegistry.setRandomProfession(entityvillager, worldIn.rand);
                    entityvillager.setProfession(this.chooseForgeProfession(i, entityvillager.getProfessionForge()));
                    entityvillager.finalizeMobSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityvillager)), (IEntityLivingData)null, false);
                    worldIn.spawnEntity(entityvillager);
                }
            }
        }
    }

    protected void spawnCrucifix(World worldIn, StructureBoundingBox structurebb, int x, int y, int z, int count) {
        if (this.crucifixSpawned < count) {
            for (int i = this.crucifixSpawned; i < count; ++i) {

                EnumFacing facing = this.getCoordBaseMode();

                int j = this.getXWithOffset(x, z);
                int k = this.getYWithOffset(y);
                int l = this.getZWithOffset(x, z);

                if (!structurebb.isVecInside(new BlockPos(j, k, l))) {
                    break;
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
        }
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
}
