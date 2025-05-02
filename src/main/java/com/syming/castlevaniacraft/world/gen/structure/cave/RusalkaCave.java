package com.syming.castlevaniacraft.world.gen.structure.cave;

import com.syming.castlevaniacraft.entity.mobs.boss.EntityRusalka;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.block.BlockPrismarine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class RusalkaCave extends WorldGenerator {
    
    private final IBlockState waterFloor = Blocks.WATER.getDefaultState();
    private final IBlockState air = Blocks.AIR.getDefaultState();

    private final IBlockState prismarine = Blocks.PRISMARINE.getDefaultState().withProperty(BlockPrismarine.VARIANT, BlockPrismarine.EnumType.ROUGH);
    private final IBlockState prismarineBrick = Blocks.PRISMARINE.getDefaultState().withProperty(BlockPrismarine.VARIANT, BlockPrismarine.EnumType.BRICKS);
    private final IBlockState prismarineDark = Blocks.PRISMARINE.getDefaultState().withProperty(BlockPrismarine.VARIANT, BlockPrismarine.EnumType.DARK);
    private final IBlockState seaLantern = Blocks.SEA_LANTERN.getDefaultState();

    private static final List<SpawnEntry> queue = new ArrayList<>();

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        generateBossRoom(worldIn, rand, position);
        return true;
    }

    private void generateBossRoom(World world, Random rand, BlockPos position) {

        int radius = 4 + rand.nextInt(6);

        for (int x = -radius; x <= radius; ++x) {
            for (int z = -radius; z <= radius; ++z) {
                for (int y = -radius; y <= radius; ++y) {
                    double distance = Math.sqrt(x * x + y * y + z * z);

                    IBlockState prismarineState = getRandomPrismarine(rand);
                    BlockPos targetPos = position.add(x, y, z);

                    if (Math.abs(distance - radius) < 0.5) {
                        world.setBlockState(targetPos, prismarineState, 2);
                    } else if (distance <= radius) {
                        if (y == 0) {
                            world.setBlockState(targetPos, this.waterFloor, 2);
                        } else if (y < 0) {
                            world.setBlockState(targetPos, prismarineState, 2);
                        } else {
                            world.setBlockState(targetPos, this.air, 2);
                        }
                    }
                }
            }
        }

        if (!world.isRemote) {
            BlockPos spawnPosition = position.add(0, 1, 0);
            EntityRusalka rusalka = new EntityRusalka(world);
            queueSpawn(world, spawnPosition, rusalka);
        }
    }

    private IBlockState getRandomPrismarine(Random rand) {
        int randomPrismarine = rand.nextInt(4);
        int randomSeaLantern = rand.nextInt(12);
        if (randomPrismarine == 0) {
            return prismarineDark;
        } else if (randomPrismarine == 1) {
            return prismarineBrick;
        } else if (randomPrismarine == 2) {
            return prismarine;
        }

        if (randomSeaLantern == 0) {
            return seaLantern;
        } else if (randomSeaLantern == 1) {
            return seaLantern;
        } else if (randomSeaLantern == 2) {
            return seaLantern;
        } else {
            return prismarine;
        }
    }

    public static void queueSpawn(World world, BlockPos pos, Entity entity) {
        if (!world.isRemote && entity != null) {
            queue.add(new SpawnEntry((WorldServer) world, pos, entity));
        }
    }

    public static void processQueue(WorldServer worldServer) {
        Iterator<SpawnEntry> iterator = queue.iterator();
        while (iterator.hasNext()) {
            SpawnEntry entry = iterator.next();
            if (entry.world == worldServer) {
                Entity entity = entry.entity;
                entity.setPosition(entry.pos.getX() + 0.5D, entry.pos.getY(), entry.pos.getZ() + 0.5D);
                worldServer.spawnEntity(entity);
                iterator.remove();
            }
        }
    }


    static class SpawnEntry {
        final WorldServer world;
        final BlockPos pos;
        final Entity entity;


        SpawnEntry(WorldServer world, BlockPos pos, Entity entity) {
            this.world = world;
            this.pos = pos;
            this.entity = entity;
        }
    }

}
