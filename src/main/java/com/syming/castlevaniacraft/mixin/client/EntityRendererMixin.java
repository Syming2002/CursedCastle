package com.syming.castlevaniacraft.mixin.client;

import com.syming.castlevaniacraft.client.gui.MVGui;
import com.syming.castlevaniacraft.util.ReachFixUtil;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin {

    /**
     * @author Syming
     * @reason Add attack indicator to Extended Reach
     */
    @Overwrite
    private void getMouseOver(float partialTicks) {
        ReachFixUtil.getMouseOver(partialTicks);
    }

}
