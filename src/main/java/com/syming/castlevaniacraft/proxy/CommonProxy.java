package com.syming.castlevaniacraft.proxy;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

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
}
