package com.syming.castlevaniacraft.client.render.entity;

import com.syming.castlevaniacraft.client.models.entity.ModelVampireBat;
import com.syming.castlevaniacraft.entity.mobs.EntityVampireBat;
import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

public class RenderVampireBat extends RenderLiving<EntityVampireBat> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(CCReferences.MODID, "textures/entity/vampire_bat.png");

    public RenderVampireBat(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelVampireBat(), 0.25F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityVampireBat entity) {
        return TEXTURE;
    }

    @Override
    protected void preRenderCallback(EntityVampireBat entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scale(0.35F, 0.35F, 0.35F);
    }

    @Override
    protected void applyRotations(EntityVampireBat entityLiving, float ageInTicks, float rotationYaw, float partialTicks)
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
