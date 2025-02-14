package com.syming.castlevaniacraft.client.render.entity;

import com.syming.castlevaniacraft.client.models.entity.ModelGrimReaper;
import com.syming.castlevaniacraft.entity.mobs.boss.EntityGrimReaper;
import com.syming.castlevaniacraft.util.MVReferences;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class RenderGrimReaper extends RenderLiving<EntityGrimReaper> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MVReferences.MODID, "textures/entity/grim_reaper.png");

    public RenderGrimReaper(RenderManager renderManager) {
        super(renderManager, new ModelGrimReaper(), 0.5F);
    }

    @Override
    protected void preRenderCallback(EntityGrimReaper entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scale(1.5F, 1.5F, 1.5F);
    }

    @Override
    protected float getDeathMaxRotation(EntityGrimReaper entityLivingBaseIn) {
        return 0.0F;
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityGrimReaper entity) {
        return TEXTURE;
    }
}
