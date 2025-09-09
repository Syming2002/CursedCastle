package com.syming.cursedcastle.mixin.client;

import com.syming.cursedcastle.client.event.DraculaBossBar;
import com.syming.cursedcastle.entity.mob.boss.dracula.DraculaEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.BossHealthOverlay;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BossHealthOverlay.class)
public class BossHealthOverlayMixin {

    @ModifyVariable(at = @At(value = "STORE"), method = "render(Lnet/minecraft/client/gui/GuiGraphics;)V", index = 7)
    private CustomizeGuiOverlayEvent.BossEventProgress event(CustomizeGuiOverlayEvent.BossEventProgress event) {
        if (Minecraft.getInstance().level != null && DraculaBossBar.BOSS_EVENT.containsKey(event.getBossEvent().getId()) && Minecraft.getInstance().level.getEntity(DraculaBossBar.BOSS_EVENT.get(event.getBossEvent().getId())) instanceof DraculaEntity) {
            event.setCanceled(true);
        }
        return event;
    }

}
