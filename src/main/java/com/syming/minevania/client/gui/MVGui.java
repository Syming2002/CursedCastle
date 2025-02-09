package com.syming.minevania.client.gui;

import com.syming.minevania.entity.mobs.boss.EntityDracula;
import com.syming.minevania.networking.MVNetworking;
import com.syming.minevania.networking.MessageDisplayBossInfo;
import com.syming.minevania.networking.MessageRemoveBossInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.BossInfoClient;
import net.minecraft.entity.Entity;
import net.minecraft.world.BossInfo;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.UUID;

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
            MVNetworking.network.sendToServer(new MessageDisplayBossInfo());
        } else {
            MVNetworking.network.sendToServer(new MessageRemoveBossInfo());
        }
    }
}
