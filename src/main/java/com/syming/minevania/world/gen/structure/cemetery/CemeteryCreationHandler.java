package com.syming.minevania.world.gen.structure.cemetery;

import com.syming.minevania.util.MVReferences;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.*;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class CemeteryCreationHandler implements VillagerRegistry.IVillageCreationHandler {

    public static void registerCemeteryComponents() {
        MapGenStructureIO.registerStructureComponent(StructureCemeteryPieces.class, new ResourceLocation(MVReferences.MODID,"Cemetery").toString());
    }

    @Override
    public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int i) {
        return new StructureVillagePieces.PieceWeight(getComponentClass(), 4, 2);
    }

    @Override
    public Class<? extends StructureVillagePieces.Village> getComponentClass() {
        return StructureCemeteryPieces.class;
    }

    @Override
    @Nullable
    public StructureVillagePieces.Village buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List<StructureComponent> pieces, Random random, int minX, int minY, int minZ, EnumFacing facing, int type) {

        return StructureCemeteryPieces.buildComponent(startPiece, pieces, random, minX, minY, minZ, facing, type);
    }
}
