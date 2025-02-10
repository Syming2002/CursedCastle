package com.syming.minevania.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.BossInfoClient;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.UUID;

@SideOnly(Side.CLIENT)
public class MVGui {
    private static final MVRenderBossBar bossBar = new MVRenderBossBar();

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.BossInfo event) {
        Minecraft mc = Minecraft.getMinecraft();
        BossInfoClient bossInfo = event.getBossInfo();
        UUID bossUUID = bossInfo.getUniqueId();
        if (mc.world == null) return;

        if (MVRenderBossBar.isDraculaBossBar(bossUUID)) {
            bossBar.drawDraculaBossHealthBar(event.getX(), event.getY(), bossInfo);
            event.setIncrement(event.getIncrement());
        }
    }
}
