package com.syming.castlevaniacraft.proxy;

import com.syming.castlevaniacraft.tile.TileEntityAlchemyTable;
import com.syming.castlevaniacraft.tile.TileEntityDraculaHead;
import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

    public void registerItemRenderer(Item item, int meta, String id)
    {

    }

    public void registerItemVariantsRenderer(Item item, int meta, String fileName) {

    }

    public void render() {

    }

    public void registerItemsVariantsRenderer() {

    }

    public void registerModelBakeryVariants() {

    }

    public EntityPlayer getPlayerEntityFromContext(MessageContext ctx)
    {
        return ctx.getServerHandler().player;
    }

    public World getClientWorld() {
        return null;
    }

    public EntityPlayerSP getPlayer() {
        return null;
    }

    public void preInit(FMLPreInitializationEvent event) {

    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }

    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityAlchemyTable.class, new ResourceLocation(CCReferences.MODID,"alchemy_table"));
        GameRegistry.registerTileEntity(TileEntityDraculaHead.class, new ResourceLocation(CCReferences.MODID,"dracula_head"));
    }
}
