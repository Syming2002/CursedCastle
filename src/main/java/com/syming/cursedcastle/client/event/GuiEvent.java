package com.syming.cursedcastle.client.event;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.gui.CCPlayerMPHUDOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.LerpingBossEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;


public class GuiEvent {

    @Mod.EventBusSubscriber(modid = CursedCastleMod.MOD_ID, value = Dist.CLIENT)
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

        @SubscribeEvent
        public static void onPreRender(RenderGuiOverlayEvent.Pre event) {
        }
    }

    @Mod.EventBusSubscriber(modid = CursedCastleMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class GuiEventMod {
        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAbove(VanillaGuiOverlay.EXPERIENCE_BAR.id(), "mp", CCPlayerMPHUDOverlay.HUD_MP);
        }
    }
}
