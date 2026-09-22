package com.syming.cursedcastle.client.gui;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;

public class CCPlayerMPHUDOverlay {

    private static final ResourceLocation MP_BAR_BACKGROUND = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/sprites/boss_bar/blue_background.png");
    private static final ResourceLocation MP_BAR_PROGRESS = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/sprites/boss_bar/blue_progress.png");

    public static void renderMPBar(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
        MultiPlayerGameMode gameMode = minecraft.gameMode;

        if (player == null && gameMode == null) return;

        if (!gameMode.getPlayerMode().isSurvival()) return;


        int screenWidth = minecraft.getWindow().getGuiScaledWidth();
        int screenHeight = minecraft.getWindow().getGuiScaledHeight();

        int barWidth = 182;

        int x = screenWidth / 2 - barWidth / 2;

        int y = screenHeight - 48;

        if (player.getArmorValue() > 0) {
            y -= 10;
        }

        float currentMP = ClientMPData.getPlayerMP();
        float maxMP = ClientMPData.getMaxPlayerMP();

        if (maxMP <= 0) {
            return;
        }

        float progress = Mth.clamp(
                currentMP / maxMP,
                0.0F,
                1.0F
        );

        int filled = (int) (progress * barWidth);

        guiGraphics.blit(
                MP_BAR_BACKGROUND,
                x,
                y,
                0,
                0,
                barWidth,
                5,
                182,
                5
        );

        if (filled > 0) {
            guiGraphics.blit(
                    MP_BAR_PROGRESS,
                    x,
                    y,
                    0,
                    0,
                    filled,
                    5,
                    182,
                    5
            );
        }

        String stringMP =
                (int) currentMP + " | " + (int) maxMP;

        Font font = minecraft.font;

        int textX = (screenWidth - font.width(stringMP)) / 2;

        int textY = y - 10;

        guiGraphics.drawString(
                font,
                stringMP,
                textX + 1,
                textY,
                0,
                false
        );

        guiGraphics.drawString(
                font,
                stringMP,
                textX - 1,
                textY,
                0,
                false
        );

        guiGraphics.drawString(
                font,
                stringMP,
                textX,
                textY + 1,
                0,
                false
        );

        guiGraphics.drawString(
                font,
                stringMP,
                textX,
                textY - 1,
                0,
                false
        );

        guiGraphics.drawString(
                font,
                stringMP,
                textX,
                textY,
                0x00B7EC,
                false
        );
    }
}
