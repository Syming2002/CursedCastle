package com.syming.castlevaniacraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.BossInfoClient;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

        import java.util.UUID;

@SideOnly(Side.CLIENT)
public class CCGui {
    private static final CCRenderBossBar bossBar = new CCRenderBossBar();

    @SubscribeEvent
    public void onRenderBossInfo(RenderGameOverlayEvent.BossInfo event) {
        Minecraft mc = Minecraft.getMinecraft();
        BossInfoClient bossInfo = event.getBossInfo();
        UUID bossUUID = bossInfo.getUniqueId();
        if (mc.world == null) return;

        if (CCRenderBossBar.isCCBossBar(bossUUID)) {
            bossBar.drawHealthBar(event.getX(), event.getY(), bossInfo);
            event.setIncrement(event.getIncrement());
            event.setCanceled(true);
        }
    }
}
