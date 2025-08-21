package com.syming.cursedcastle.client.event;

import com.syming.cursedcastle.world.entity.mob.boss.dracula.DraculaEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.LerpingBossEvent;
import net.minecraft.world.BossEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DraculaBossBar {
    public static final Map<UUID, Integer> BOSS_EVENT = new HashMap<>();

    public static void drawDraculaBossHealthBar(GuiGraphics guiGraphics, int x, int y, LerpingBossEvent bossEvent) {
        int entityID = BOSS_EVENT.get(bossEvent.getId());
        if (Minecraft.getInstance().level != null && Minecraft.getInstance().level.getEntity(entityID) instanceof DraculaEntity dracula) {
            drawDraculaBar(guiGraphics, x, y, bossEvent, dracula);
        }
    }

    public static void drawDraculaBar(GuiGraphics guiGraphics, int x, int y, BossEvent bossEvent, DraculaEntity dracula) {
        if (dracula.getCustomBossBar() != null) {
            x -= 37;
            guiGraphics.blit(dracula.getCustomBossBar(), x, y, -90, 0, 166, 256, 90, 256, 256);
            int health = (int) (bossEvent.getProgress() * 256.0F);
            if (health > 0) {
                guiGraphics.blit(dracula.getCustomBossBar(), x, y, -90, 0, 0, health, 90, 256, 256);
            }
        }
    }

    public static boolean isDraculaBossBar(UUID uuid) {
        return BOSS_EVENT.containsKey(uuid);
    }

}
