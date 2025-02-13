package com.syming.minevania.world.gen.structure;

import com.syming.minevania.world.gen.structure.cave.RusalkaCave;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class CCWorldGenerator implements IWorldGenerator {

    public static final RusalkaCave RUSALKA_CAVE = new RusalkaCave();

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimension() == 0) { //Overworld Generator
            generateOverworld(world, random, chunkX, chunkZ);
            System.out.println("A Rusalka Cave has spawn at " + chunkX + ", " + chunkZ);
        }
    }

    private void generateOverworld(World world, Random random, int chunkX, int chunkZ) {
        int x = (chunkX * 16) + random.nextInt(16);
        int z = (chunkZ * 16) + random.nextInt(16);
        int y = 16 + random.nextInt(17);

        if (random.nextInt(200) == 0) {
            BlockPos pos = new BlockPos(x, y, z);
            RUSALKA_CAVE.generate(world, random, pos);
        }
    }
}
