package com.syming.castlevaniacraft.init;

import com.syming.castlevaniacraft.world.gen.structure.cemetery.CemeteryCreationHandler;
import com.syming.castlevaniacraft.world.gen.structure.church.BigChurchCreationHandler;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class WorldGenInit {
    public static final VillagerRegistry.IVillageCreationHandler BIG_CHURCH_HANDLER = new BigChurchCreationHandler();
    public static final VillagerRegistry.IVillageCreationHandler CEMETERY_HANDLER = new CemeteryCreationHandler();

}
