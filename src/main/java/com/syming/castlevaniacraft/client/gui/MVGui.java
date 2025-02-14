package com.syming.castlevaniacraft.client.gui;

//import com.syming.castlevaniacraft.networking.MessageAttackIndicator;
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
    public void onRenderBossInfo(RenderGameOverlayEvent.BossInfo event) {
        Minecraft mc = Minecraft.getMinecraft();
        BossInfoClient bossInfo = event.getBossInfo();
        UUID bossUUID = bossInfo.getUniqueId();
        if (mc.world == null) return;

        if (MVRenderBossBar.isDraculaBossBar(bossUUID)) {
            bossBar.drawHealthBar(event.getX(), event.getY(), bossInfo);
            event.setIncrement(event.getIncrement());
            event.setCanceled(true);
        } else if (MVRenderBossBar.isDeathBossBar(bossUUID)) {
            bossBar.drawHealthBar(event.getX(), event.getY(), bossInfo);
            event.setIncrement(event.getIncrement());
            event.setCanceled(true);
        }
    }
}
