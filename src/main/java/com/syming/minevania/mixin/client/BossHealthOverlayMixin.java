package com.syming.minevania.mixin.client;

import com.syming.minevania.client.gui.MVRenderBossBar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiBossOverlay;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GuiBossOverlay.class)
public class BossHealthOverlayMixin {

    @ModifyVariable(at = @At(value = "STORE"), method = "net/minecraft/client/gui;renderBossHealth()V", index = 7)
    private RenderGameOverlayEvent.BossInfo event(RenderGameOverlayEvent.BossInfo event) {
        if (Minecraft.getMinecraft().world != null && MVRenderBossBar.BOSS_MAP.containsKey(event.getBossInfo().getUniqueId())) {
            event.setCanceled(true);
        }
        return event;
    }

}
