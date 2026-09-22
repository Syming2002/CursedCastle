package com.syming.cursedcastle.client.event;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.gui.CCPlayerMPHUDOverlay;
import com.syming.cursedcastle.client.gui.ClientMPData;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.LerpingBossEvent;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.CustomizeGuiOverlayEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

import java.util.UUID;

public class CCGuiEvent {

    @EventBusSubscriber(modid = CursedCastleMod.MOD_ID, value = Dist.CLIENT)
    public static class GuiEventForge {
        @SubscribeEvent
        public static void onRenderBossBar(CustomizeGuiOverlayEvent.BossEventProgress event) {
            GuiGraphics guiGraphics = event.getGuiGraphics();
            LerpingBossEvent bossEvent = event.getBossEvent();
            UUID bossUUID = bossEvent.getId();
            if (CCBossBar.isRusalkaBossBar(bossUUID)) {
                CCBossBar.drawRusalkaBossHealthBar(guiGraphics, event.getX(), event.getY(), bossEvent);
            }
            if (CCBossBar.isDraculaBossBar(bossUUID)) {
                CCBossBar.drawDraculaBossHealthBar(guiGraphics, event.getX(), event.getY(), bossEvent);
            }
        }
    }

    @EventBusSubscriber(modid = CursedCastleMod.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
    public static class GuiEventMod {
        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiLayersEvent event) {
            event.registerAboveAll(ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "mp"), CCPlayerMPHUDOverlay::renderMPBar);
        }
    }
}
