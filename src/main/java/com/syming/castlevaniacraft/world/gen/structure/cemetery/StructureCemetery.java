package com.syming.castlevaniacraft.world.gen.structure.cemetery;

import com.syming.castlevaniacraft.entity.mobs.shade.EntityFrozenShade;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityHeatShade;
import com.syming.castlevaniacraft.loot.CCTreasures;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.template.TemplateManager;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class StructureCemetery extends StructureVillagePieces.Village {

    private Biome biome = this.startPiece.biome;
    private boolean hasSpawner;
    private boolean spawnerPlaced;


    public StructureCemetery() {
    }

    public StructureCemetery(StructureVillagePieces.Start start, int type, Random random, StructureBoundingBox boundingBox, EnumFacing facing) {
        super(start, type);
        setCoordBaseMode(facing);
        this.boundingBox = boundingBox;
    }

    @Override
    protected void writeStructureToNBT(NBTTagCompound tagCompound) {
        tagCompound.setBoolean("hps", this.spawnerPlaced);
    }

    @Override
    protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager manager) {
        this.spawnerPlaced = tagCompound.getBoolean("hps");
    }

    @Nullable
    public static StructureCemetery buildComponent(StructureVillagePieces.Start startPieces, List<StructureComponent> pieces, Random random, int structureMinX, int structureMinY, int structureMinZ, EnumFacing facing, int componentType) {

        int xOffset = 16;
        int zOffset = 16;

        structureMinX += xOffset;
        structureMinZ += zOffset;

        StructureBoundingBox structureBoundingBox = StructureBoundingBox.getComponentToAddBoundingBox(structureMinX, structureMinY, structureMinZ, 0, 0, 0, 11, 4, 6, facing);
        if (!canVillageGoDeeper(structureBoundingBox) || StructureComponent.findIntersecting(pieces, structureBoundingBox) != null) {
            return null;
        }

        System.out.println("Cemetery buildComponent() at " + structureMinX + ", " + structureMinY + ", " + structureMinZ);

        return new StructureCemetery(startPieces, componentType, random, structureBoundingBox, facing);
    }

    @Override
    public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
        if (this.averageGroundLvl < 0) {
            this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

            if (this.averageGroundLvl < 0) {
                return true;
            }

            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 4 - 1, 0);
        }

        int baseY = this.boundingBox.minY;
        for (int i = this.boundingBox.minX; i <= this.boundingBox.maxX; i++) {
            for (int k = this.boundingBox.minZ; k <= this.boundingBox.maxZ; k++) {
                BlockPos checkPos = new BlockPos(i, baseY - 1, k);
                IBlockState blockStateBelow = worldIn.getBlockState(checkPos);

                if (blockStateBelow.getMaterial().isLiquid()) {
                    return false;
                }
            }
        }
        generateCemetery(worldIn, randomIn, structureBoundingBoxIn);
        return true;
    }

    private void generateCemetery(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {

        IBlockState stoneSlab = this.getBiomeSpecificBlockState(Blocks.STONE_SLAB.getDefaultState()
                .withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.STONE)
                .withProperty(BlockStoneSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM));

        IBlockState stone = this.getBiomeSpecificBlockState(Blocks.STONE.getDefaultState());

        IBlockState stairStoneSouthBottomStraight = this.getBiomeSpecificBlockState(Blocks.STONE_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.SOUTH)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT));

        IBlockState stairStoneWestBottomStraight = this.getBiomeSpecificBlockState(Blocks.STONE_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.WEST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT));

        IBlockState stairStoneEastBottomStraight = this.getBiomeSpecificBlockState(Blocks.STONE_STAIRS.getDefaultState()
                .withProperty(BlockStairs.FACING, EnumFacing.EAST)
                .withProperty(BlockStairs.HALF, BlockStairs.EnumHalf.BOTTOM)
                .withProperty(BlockStairs.SHAPE, BlockStairs.EnumShape.STRAIGHT));

        this.generateChest(worldIn, structureBoundingBoxIn, randomIn, 2, -1, 3, CCTreasures.CEMETERY);
        this.generateChest(worldIn, structureBoundingBoxIn, randomIn, 8, -1, 3, CCTreasures.CEMETERY);

        for (int k = 1; k < 4; ++k) {
            this.setBlockState(worldIn, stoneSlab, 2, 0, k, structureBoundingBoxIn);
        }

        for (int k = 1; k < 4; ++k) {
            this.setBlockState(worldIn, stoneSlab, 8, 0, k, structureBoundingBoxIn);
        }

        for (int j = 0; j < 2; ++j) {
            this.setBlockState(worldIn, stone, 2, j, 4, structureBoundingBoxIn);
        }

        for (int j = 0; j < 2; ++j) {
            this.setBlockState(worldIn, stone, 8, j, 4, structureBoundingBoxIn);
        }

        this.setBlockState(worldIn, stone, 1, 1, 4, structureBoundingBoxIn);
        this.setBlockState(worldIn, stone, 2, 2, 4, structureBoundingBoxIn);
        this.setBlockState(worldIn, stone, 3, 1, 4, structureBoundingBoxIn);

        this.setBlockState(worldIn, stone, 7, 1, 4, structureBoundingBoxIn);
        this.setBlockState(worldIn, stone, 8, 2, 4, structureBoundingBoxIn);
        this.setBlockState(worldIn, stone, 9, 1, 4, structureBoundingBoxIn);

        this.setBlockState(worldIn, stone, 1, 0, 4, structureBoundingBoxIn);
        this.setBlockState(worldIn, stone, 3, 0, 4, structureBoundingBoxIn);

        this.setBlockState(worldIn, stone, 7, 0, 4, structureBoundingBoxIn);
        this.setBlockState(worldIn, stone, 9, 0, 4, structureBoundingBoxIn);

        this.setBlockState(worldIn, stairStoneSouthBottomStraight, 8, 3, 4, structureBoundingBoxIn);
        this.setBlockState(worldIn, stairStoneSouthBottomStraight, 2, 3, 4, structureBoundingBoxIn);

        this.setBlockState(worldIn, stairStoneEastBottomStraight, 0, 0, 4, structureBoundingBoxIn);
        this.setBlockState(worldIn, stairStoneEastBottomStraight, 6, 0, 4, structureBoundingBoxIn);

        this.setBlockState(worldIn, stairStoneEastBottomStraight, 1, 2, 4, structureBoundingBoxIn);
        this.setBlockState(worldIn, stairStoneEastBottomStraight, 7, 2, 4, structureBoundingBoxIn);

        this.setBlockState(worldIn, stairStoneWestBottomStraight, 4, 0, 4, structureBoundingBoxIn);
        this.setBlockState(worldIn, stairStoneWestBottomStraight, 10, 0, 4, structureBoundingBoxIn);

        this.setBlockState(worldIn, stairStoneWestBottomStraight, 3, 2, 4, structureBoundingBoxIn);
        this.setBlockState(worldIn, stairStoneWestBottomStraight, 9, 2, 4, structureBoundingBoxIn);

        for (int i = 1; i < 4; ++i) {
            this.setBlockState(worldIn, stairStoneSouthBottomStraight, i, 0, 5, structureBoundingBoxIn);
        }

        for (int i = 7; i < 10; ++i) {
            this.setBlockState(worldIn, stairStoneSouthBottomStraight, i, 0, 5, structureBoundingBoxIn);
        }

        if (!this.hasSpawner && !this.spawnerPlaced) {
            BlockPos blockPos = new BlockPos(this.getXWithOffset(5, 2), this.getYWithOffset(-2), this.getZWithOffset(5, 2));
            if (structureBoundingBoxIn.isVecInside(blockPos)) {
                this.hasSpawner = true;
                this.spawnerPlaced = true;
                worldIn.setBlockState(blockPos, Blocks.MOB_SPAWNER.getDefaultState(), 2);
                TileEntity tileEntity = worldIn.getTileEntity(blockPos);

                if (structureType == 1 || biome instanceof BiomeDesert || biome instanceof BiomeMesa) {
                    if (tileEntity instanceof TileEntityMobSpawner) {
                        ((TileEntityMobSpawner) tileEntity).getSpawnerBaseLogic().setEntityId(EntityList.getKey(EntityHeatShade.class));
                    }

                } else if (structureType == 3 || biome instanceof BiomeSnow) {
                    if (tileEntity instanceof TileEntityMobSpawner) {
                        ((TileEntityMobSpawner) tileEntity).getSpawnerBaseLogic().setEntityId(EntityList.getKey(EntityFrozenShade.class));
                    }
                } else {
                    this.hasSpawner = false;
                }
            }
        }
    }

    @Override
    protected IBlockState getBiomeSpecificBlockState(IBlockState blockstateIn) {
        net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID event = new net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID(startPiece == null ? null : startPiece.biome, blockstateIn);
        net.minecraftforge.common.MinecraftForge.TERRAIN_GEN_BUS.post(event);
        if (event.getResult() == net.minecraftforge.fml.common.eventhandler.Event.Result.DENY)
            return event.getReplacement();
        if (this.structureType == 1) {
            if (blockstateIn.getBlock() == Blocks.LOG || blockstateIn.getBlock() == Blocks.LOG2) {
                return Blocks.SANDSTONE.getDefaultState();
            }

            if (blockstateIn.getBlock() == Blocks.STONE) {
                return Blocks.SANDSTONE.getStateFromMeta(BlockSandStone.EnumType.DEFAULT.getMetadata());
            }

            if (blockstateIn.getBlock() == Blocks.STONE_STAIRS) {
                return Blocks.SANDSTONE_STAIRS.getDefaultState()
                        .withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING))
                        .withProperty(BlockStairs.HALF, blockstateIn.getValue(BlockStairs.HALF))
                        .withProperty(BlockStairs.SHAPE, blockstateIn.getValue(BlockStairs.SHAPE));
            }

            if (blockstateIn.getBlock() == Blocks.GRASS_PATH) {
                return Blocks.GRAVEL.getDefaultState();
            }

            if (blockstateIn.getBlock() == Blocks.STONE_SLAB) {
                return Blocks.STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.SAND).withProperty(BlockStoneSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM);
            }
        } else if (this.structureType == 3) {
            if (blockstateIn.getBlock() == Blocks.LOG || blockstateIn.getBlock() == Blocks.LOG2) {
                return Blocks.LOG.getDefaultState()
                        .withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
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

            if (blockstateIn.getBlock() == Blocks.OAK_FENCE) {
                return Blocks.SPRUCE_FENCE.getDefaultState();
            }

            if (blockstateIn.getBlock() == Blocks.TORCH) {
                return Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, blockstateIn.getValue(BlockTorch.FACING));
            }
        } else if (this.structureType == 2) {
            if (blockstateIn.getBlock() == Blocks.LOG || blockstateIn.getBlock() == Blocks.LOG2) {
                return Blocks.LOG2.getDefaultState()
                        .withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.ACACIA);
            }

            if (blockstateIn.getBlock() == Blocks.PLANKS) {
                return Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.ACACIA);
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
        } else if (biome instanceof BiomeJungle) {
            if (blockstateIn.getBlock() == Blocks.LOG || blockstateIn.getBlock() == Blocks.LOG2)
            {
                return Blocks.LOG.getDefaultState()
                        .withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
            }

            if (blockstateIn.getBlock() == Blocks.PLANKS)
            {
                return Blocks.PLANKS.getDefaultState()
                        .withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.JUNGLE);
            }

            if (blockstateIn.getBlock() == Blocks.OAK_STAIRS)
            {
                return Blocks.JUNGLE_STAIRS.getDefaultState()
                        .withProperty(BlockStairs.FACING, blockstateIn.getValue(BlockStairs.FACING))
                        .withProperty(BlockStairs.HALF, blockstateIn.getValue(BlockStairs.HALF))
                        .withProperty(BlockStairs.SHAPE, blockstateIn.getValue(BlockStairs.SHAPE));
            }

            if (blockstateIn.getBlock() == Blocks.COBBLESTONE || blockstateIn.getBlock() == Blocks.STONE)
            {
                return Blocks.MOSSY_COBBLESTONE.getDefaultState();
            }

            if (blockstateIn.getBlock() == Blocks.OAK_FENCE)
            {
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
}
