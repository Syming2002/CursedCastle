package com.syming.castlevaniacraft.client.render.entity;

import com.syming.castlevaniacraft.client.models.entity.ModelAggressiveBat;
import com.syming.castlevaniacraft.entity.mobs.EntityAggressiveBat;
import com.syming.castlevaniacraft.util.MVReferences;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

public class RenderAggressiveBat extends RenderLiving<EntityAggressiveBat> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MVReferences.MODID, "textures/entity/aggressive_bat.png");

    public RenderAggressiveBat(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelAggressiveBat(), 0.25F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityAggressiveBat entity) {
        return TEXTURE;
    }

    @Override
    protected void preRenderCallback(EntityAggressiveBat entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scale(0.35F, 0.35F, 0.35F);
    }

    @Override
    protected void applyRotations(EntityAggressiveBat entityLiving, float ageInTicks, float rotationYaw, float partialTicks)
    {
        if (entityLiving.getIsBatHanging())
        {
            GlStateManager.translate(0.0F, -0.1F, 0.0F);
        }
        else
        {
            GlStateManager.translate(0.0F, MathHelper.cos(ageInTicks * 0.3F) * 0.1F, 0.0F);
        }

        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }

}
