package com.syming.cursedcastle.client.event;

import com.syming.cursedcastle.CursedCastleMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.LerpingBossEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = CursedCastleMod.MOD_ID, value = Dist.CLIENT)
public class GuiEvent {

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
