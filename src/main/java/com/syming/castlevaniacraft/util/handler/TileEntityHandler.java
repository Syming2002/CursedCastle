package com.syming.castlevaniacraft.util.handler;

import com.syming.castlevaniacraft.tile.TileEntityAlchemyTable;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {

    public static void register() {
        GameRegistry.registerTileEntity(TileEntityAlchemyTable.class, "alchemy_table");
    }

}
