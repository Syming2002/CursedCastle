package com.syming.castlevaniacraft.world.gen.structure.cemetery;

import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.*;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class CemeteryCreationHandler implements VillagerRegistry.IVillageCreationHandler {

    public static void registerCemeteryComponents() {
        MapGenStructureIO.registerStructureComponent(StructureCemetery.class, new ResourceLocation(CCReferences.MODID,"C").toString());
    }

    @Override
    public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int i) {
        return new StructureVillagePieces.PieceWeight(getComponentClass(), 33, 3);
    }

    @Override
    public Class<? extends StructureVillagePieces.Village> getComponentClass() {
        return StructureCemetery.class;
    }

    @Override
    @Nullable
    public StructureVillagePieces.Village buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List<StructureComponent> pieces, Random random, int minX, int minY, int minZ, EnumFacing facing, int type) {

        return StructureCemetery.buildComponent(startPiece, pieces, random, minX, minY, minZ, facing, type);
    }
}
