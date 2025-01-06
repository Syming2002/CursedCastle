package com.syming.minevania.init;

import com.syming.minevania.world.gen.structure.cemetery.CemeteryCreationHandler;
import com.syming.minevania.world.gen.structure.church.BigChurchCreationHandler;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class WorldGenInit {
    public static final VillagerRegistry.IVillageCreationHandler BIG_CHURCH_HANDLER = new BigChurchCreationHandler();
    public static final VillagerRegistry.IVillageCreationHandler CEMETERY_HANDLER = new CemeteryCreationHandler();

}
