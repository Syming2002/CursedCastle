package com.syming.minevania.client.gui;

import com.syming.minevania.entity.mobs.boss.EntityDracula;
import com.syming.minevania.entity.mobs.boss.EntityGrimReaper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.BossInfoClient;
import net.minecraft.client.gui.Gui;
import net.minecraft.world.BossInfo;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SideOnly(Side.CLIENT)
public class MVRenderBossBar extends Gui {
    public static final Map<UUID, Integer> BOSS_MAP = new HashMap<>();

    public void drawHealthBar(int x, int y, BossInfoClient bossInfoClient) {
        int entityID = BOSS_MAP.get(bossInfoClient.getUniqueId());
        if (Minecraft.getMinecraft().world != null) {
            if (Minecraft.getMinecraft().world.getEntityByID(entityID) instanceof EntityDracula dracula) {
                drawDraculaBar(x, y, bossInfoClient, dracula);
            } else if (Minecraft.getMinecraft().world.getEntityByID(entityID) instanceof EntityGrimReaper grimReaper) {
                drawDeathBar(x, y, bossInfoClient, grimReaper);
            }
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

    public void drawDeathBar(int x, int y, BossInfo bossInfo, EntityGrimReaper grimReaper) {
        if (grimReaper.getCustomBossBar() != null) {
            Minecraft minecraft = Minecraft.getMinecraft();
            x -= 37;

            minecraft.getTextureManager().bindTexture(grimReaper.getCustomBossBar());
            drawTexturedModalRect(x, y, 0, 74, 256, 74);

            int health = (int) (bossInfo.getPercent() * 256.0F);
            if (health > 0) {
                drawTexturedModalRect(x, y, 0, 0, health, 74);
            }
        }
    }

    public static boolean isDraculaBossBar(UUID uuid) {
        return BOSS_MAP.containsKey(uuid);
    }

    public static boolean isDeathBossBar(UUID uuid) {
        return BOSS_MAP.containsKey(uuid);
    }
}
