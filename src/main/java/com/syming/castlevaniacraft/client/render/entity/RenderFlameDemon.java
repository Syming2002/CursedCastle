package com.syming.castlevaniacraft.client.render.entity;

import com.syming.castlevaniacraft.client.models.entity.ModelFlameDemon;
import com.syming.castlevaniacraft.entity.mobs.EntityFlameDemon;
import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class RenderFlameDemon extends RenderLiving<EntityFlameDemon> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(CCReferences.MODID, "textures/entity/flame_demon.png");
    public RenderFlameDemon(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelFlameDemon(), 0.8F);
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    @Override
    protected void preRenderCallback(EntityFlameDemon entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(3.25F, 3.25F, 3.25F);
    }

    @Override
    protected @Nullable ResourceLocation getEntityTexture(EntityFlameDemon entity) {
        return TEXTURE;
    }
}
