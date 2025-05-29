package com.syming.castlevaniacraft.world.gen.structure;

import com.syming.castlevaniacraft.world.gen.structure.cave.RusalkaCave;
import com.syming.castlevaniacraft.world.gen.structure.draculcastle.StructureDraculaCastle;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.util.Rotation;
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

        }
    }

    private void generateOverworld(World world, Random random, int chunkX, int chunkZ) {
        int x = (chunkX * 16) + random.nextInt(16);
        int z = (chunkZ * 16) + random.nextInt(16);
        int y = 16 + random.nextInt(17);

        if (random.nextInt(500) == 0) {
            BlockPos pos = new BlockPos(x, y, z);
            RUSALKA_CAVE.generate(world, random, pos);
            System.out.println("A Rusalka Cave has spawn at " + x + ", " + z);
        }

        if (random.nextInt(1500) == 0) {
            BlockPos pos = new BlockPos(x, 64, z);

//            StructureDraculaCastle.(world, pos, "dracula_castle_entrance", Rotation.NONE);

            System.out.println("A Dracula Castle has spawn at " + x + ", " + z);
        }
    }
}
