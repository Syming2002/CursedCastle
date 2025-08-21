package com.syming.castlevaniacraft.world.gen.structure.church;

import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import java.util.List;
import java.util.Random;

public class BigChurchCreationHandler implements VillagerRegistry.IVillageCreationHandler {

    public static void registerBigChurchComponents() {
        MapGenStructureIO.registerStructureComponent(StructureBigChurch.class, new ResourceLocation(CCReferences.MODID,"Bc").toString());
    }

    @Override
    public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int i) {
        return new StructureVillagePieces.PieceWeight(getComponentClass(), 100, 1);
    }

    @Override
    public Class<? extends StructureVillagePieces.Village> getComponentClass() {
        return StructureBigChurch.class;
    }

    @Override
    public StructureVillagePieces.Village buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List<StructureComponent> pieces, Random random, int minX, int minY, int minZ, EnumFacing facing, int type) {

        return StructureBigChurch.buildComponent(startPiece, pieces, random, minX, minY, minZ, facing, type);
    }
}
