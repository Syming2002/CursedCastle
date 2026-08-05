package com.syming.cursedcastle.client.gui;

import ca.weblite.objc.Client;
import com.mojang.blaze3d.systems.RenderSystem;
import com.syming.cursedcastle.network.CCMPData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class CCPlayerMPHUDOverlay {

    private static final ResourceLocation MP_BAR = new ResourceLocation("minecraft", "textures/gui/bars.png");

    public static final IGuiOverlay HUD_MP = (((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        LocalPlayer localPlayer = Minecraft.getInstance().player;

        if (localPlayer != null) {
            if (!localPlayer.isCreative() && !localPlayer.isSpectator()) {
                int barWidth = 182;
                int x = screenWidth / 2 - (barWidth / 2);

                int y = screenHeight - gui.leftHeight;


                float currentMP = ClientMPData.getPlayerMP();
                float maxMP = ClientMPData.getMaxPlayerMP();

                if (maxMP <= 0) return;

                float progress = currentMP / maxMP;
                progress = net.minecraft.util.Mth.clamp(progress, 0.0F, 1.0F);

                int filled = (int) (progress * barWidth);

                guiGraphics.blit(MP_BAR, x, y, 0, 10, barWidth, 5, 256, 256);

                if (filled > 0) {
                    guiGraphics.blit(MP_BAR, x, y, 0, 15, filled, 5, 256, 256);
                }

                String stringMP = (int) currentMP + " / " + (int) maxMP;

                int textX = (screenWidth - gui.getFont().width(stringMP)) / 2;
                int textY = y - 10;

                guiGraphics.drawString(gui.getFont(), stringMP, textX + 1, textY, 0, false);
                guiGraphics.drawString(gui.getFont(), stringMP, textX - 1, textY, 0, false);
                guiGraphics.drawString(gui.getFont(), stringMP, textX, textY + 1, 0, false);
                guiGraphics.drawString(gui.getFont(), stringMP, textX, textY - 1, 0, false);

                guiGraphics.drawString(gui.getFont(), stringMP, textX, textY, 0x00B7EC, false);

                gui.leftHeight += 10;
            }
        }
    }));
}
