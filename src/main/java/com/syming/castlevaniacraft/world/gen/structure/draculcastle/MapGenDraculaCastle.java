package com.syming.castlevaniacraft.world.gen.structure.draculcastle;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class MapGenDraculaCastle extends MapGenStructure {

    public MapGenDraculaCastle() {
    }

    @Override
    public String getStructureName() {
        return "Dracula's Castle";
    }

    @Override
    public @Nullable BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
        return null;
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        return (chunkX % 16 == 0 && chunkZ % 16 == 0);
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ) {
        return new Start(this.world, this.rand, chunkX, chunkZ);
    }

    public static class Start extends StructureStart
    {
        public Start()
        {
        }

        public Start(World worldIn, Random random, int chunkX, int chunkZ)
        {
            super(chunkX, chunkZ);
            StructureDraculaCastle draculaCastle = new StructureDraculaCastle();
            this.components.add(draculaCastle);

            this.updateBoundingBox();

            int blockX = chunkX * 16;
            int blockZ = chunkZ * 16;
            System.out.println("Dracula's Castle has spawned at " + blockX + " and " + blockZ);
        }
    }
}
