package com.syming.cursedcastle.client.event;

import com.syming.cursedcastle.entity.mob.boss.RusalkaEntity;
import com.syming.cursedcastle.entity.mob.boss.dracula.DraculaEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.LerpingBossEvent;
import net.minecraft.world.BossEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CCBossBar {
    public static final Map<UUID, Integer> BOSS_EVENT = new HashMap<>();

    public static void drawDraculaBossHealthBar(GuiGraphics guiGraphics, int x, int y, LerpingBossEvent bossEvent) {
        int entityID = BOSS_EVENT.get(bossEvent.getId());
        if (Minecraft.getInstance().level != null && Minecraft.getInstance().level.getEntity(entityID) instanceof DraculaEntity dracula) {
            drawDraculaBar(guiGraphics, x, y, bossEvent, dracula);
        }
    }

    public static void drawRusalkaBossHealthBar(GuiGraphics guiGraphics, int x, int y, LerpingBossEvent bossEvent) {
        int entityID = BOSS_EVENT.get(bossEvent.getId());
        if (Minecraft.getInstance().level != null && Minecraft.getInstance().level.getEntity(entityID) instanceof RusalkaEntity rusalka) {
            drawRusalkaBar(guiGraphics, x, y, bossEvent, rusalka);
        }
    }

    public static void drawDraculaBar(GuiGraphics guiGraphics, int x, int y, BossEvent bossEvent, DraculaEntity dracula) {
        if (dracula.getCustomBossBar() != null && dracula.getCustomBossBarBackground() != null) {
            x -= 37;
            guiGraphics.blit(dracula.getCustomBossBarBackground(), x, y, 0, 0, 248, 48, 248, 48);
            int health = (int) (bossEvent.getProgress() * 150.0F);
            if (health > 0) {
                guiGraphics.blit(dracula.getCustomBossBar(), x + 49, y, 49, 0, health, 48, 248, 48);
            }
        }
    }

    public static void drawRusalkaBar(GuiGraphics guiGraphics, int x, int y, BossEvent bossEvent, RusalkaEntity rusalka) {
        if (rusalka.getCustomBossBarBackground() != null && rusalka.getCustomBossBar() != null) {
            x -= 37;
            guiGraphics.blit(rusalka.getCustomBossBarBackground(), x, y, 0, 0, 256, 59, 256, 59);
            int health = (int) (bossEvent.getProgress() * 170.0F);
            if (health > 0) {
                guiGraphics.blit(rusalka.getCustomBossBar(), x + 43, y, 43, 0, health, 59, 256, 59);
            }
        }
    }

    public static boolean isDraculaBossBar(UUID uuid) {
        return BOSS_EVENT.containsKey(uuid);
    }

    public static boolean isRusalkaBossBar(UUID uuid) {
        return BOSS_EVENT.containsKey(uuid);
    }

}
