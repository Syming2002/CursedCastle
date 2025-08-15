package com.syming.castlevaniacraft.client.gui;

import com.syming.castlevaniacraft.entity.mobs.boss.EntityDracula;
import com.syming.castlevaniacraft.entity.mobs.boss.EntityGrimReaper;
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
public class CCRenderBossBar extends Gui {
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
            drawTexturedModalRect(x, y, 0, 189, 256, 67);

            int health = (int) (bossInfo.getPercent() * 178.0F);
            if (health > 0) {
                drawTexturedModalRect(x + 39, y, 39, 0, health, 67);
            }
        }
    }

    public void drawDeathBar(int x, int y, BossInfo bossInfo, EntityGrimReaper grimReaper) {
        if (grimReaper.getCustomBossBar() != null) {
            Minecraft minecraft = Minecraft.getMinecraft();
            x -= 37;

            minecraft.getTextureManager().bindTexture(grimReaper.getCustomBossBar());
            drawTexturedModalRect(x, y, 0, 330, 256, 182);

            int health = (int) (bossInfo.getPercent() * 250.0F);
            if (health > 0) {
                drawTexturedModalRect(x + 3, y, 3, 0, health, 182);
            }
        }
    }

    public static boolean isCCBossBar(UUID uuid) {
        return BOSS_MAP.containsKey(uuid);
    }
}
