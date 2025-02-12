package com.syming.minevania.client.gui;

import com.syming.minevania.entity.mobs.boss.EntityDracula;
import com.syming.minevania.util.MVReferences;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.BossInfoClient;
import net.minecraft.client.gui.Gui;
import net.minecraft.network.play.server.SPacketUpdateBossInfo;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.BossInfo;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SideOnly(Side.CLIENT)
public class MVRenderBossBar extends Gui {
    public static final Map<UUID, Integer> BOSS_MAP = new HashMap<>();

    public void drawDraculaBossHealthBar(int x, int y, BossInfoClient bossInfoClient) {
        int entityID = BOSS_MAP.get(bossInfoClient.getUniqueId());
        if (Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().world.getEntityByID(entityID) instanceof EntityDracula dracula) {
            drawDraculaBar(x, y, bossInfoClient, dracula);
        }
    }

    public void drawDraculaBar(int x, int y, BossInfo bossInfo, EntityDracula dracula) {
        if (dracula.getCustomBossBar() != null) {
            Minecraft minecraft = Minecraft.getMinecraft();
            x -= 37;

            minecraft.getTextureManager().bindTexture(dracula.getCustomBossBar());
            drawTexturedModalRect(x, y, 0, 166, 256, 90);

            int health = (int) (bossInfo.getPercent() * 256.0F);
            if (health > 0) {
                drawTexturedModalRect(x, y, 0, 0, health, 90);
            }
        }
    }

    public static boolean isDraculaBossBar(UUID uuid) {
        return BOSS_MAP.containsKey(uuid);
    }
}
