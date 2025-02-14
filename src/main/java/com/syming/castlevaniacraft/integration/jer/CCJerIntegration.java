package com.syming.castlevaniacraft.integration.jer;

import jeresources.api.*;
import jeresources.util.LootTableHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CCJerIntegration {

    @JERPlugin
    public static IJERAPI jerapi;
    public static IDungeonRegistry dungeonRegistry;
    public static IMobRegistry mobRegistry;
    public static IPlantRegistry plantRegistry;
    public static World world;
    public static LootTableManager manager;

    public static void init() {
        dungeonRegistry = jerapi.getDungeonRegistry();
        mobRegistry = jerapi.getMobRegistry();
        plantRegistry = jerapi.getPlantRegistry();
        world = Minecraft.getMinecraft().world != null ? Minecraft.getMinecraft().world : jerapi.getWorld();
        manager = LootTableHelper.getManager(world);

        initJERMods();
    }

    private static void initJERMods() {
        JERCC.getInstance().registerModEntities();
    }
}
