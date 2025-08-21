package com.syming.castlevaniacraft.init;

import com.syming.castlevaniacraft.world.gen.structure.cemetery.CemeteryCreationHandler;
import com.syming.castlevaniacraft.world.gen.structure.church.BigChurchCreationHandler;
import com.syming.castlevaniacraft.world.gen.structure.draculcastle.MapGenDraculaCastle;
import com.syming.castlevaniacraft.world.gen.structure.draculcastle.StructureDraculaCastle;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class WorldGenInit {
    public static final VillagerRegistry.IVillageCreationHandler BIG_CHURCH_HANDLER = new BigChurchCreationHandler();
    public static final VillagerRegistry.IVillageCreationHandler CEMETERY_HANDLER = new CemeteryCreationHandler();

    public static void initStructure() {
        MapGenStructureIO.registerStructure(MapGenDraculaCastle.Start.class, "Dracula's Castle");
        MapGenStructureIO.registerStructureComponent(StructureDraculaCastle.class, "DrC");
    }
}
