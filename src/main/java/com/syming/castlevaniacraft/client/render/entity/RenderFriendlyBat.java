package com.syming.castlevaniacraft.client.render.entity;

import com.syming.castlevaniacraft.client.models.entity.ModelFriendlyBat;
import com.syming.castlevaniacraft.entity.mobs.friendly.EntityFriendlyBat;
import com.syming.castlevaniacraft.util.MVReferences;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

public class RenderFriendlyBat extends RenderLiving<EntityFriendlyBat> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MVReferences.MODID, "textures/entity/friendly_bat.png");

    public RenderFriendlyBat(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelFriendlyBat(), 0.25F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityFriendlyBat entity) {
        return TEXTURE;
    }

    @Override
    protected void preRenderCallback(EntityFriendlyBat entitylivingbaseIn, float partialTickTime) {
        GlStateManager.scale(0.35F, 0.35F, 0.35F);
    }

    @Override
    protected void applyRotations(EntityFriendlyBat entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        GlStateManager.translate(0.0F, MathHelper.cos(ageInTicks * 0.3F) * 0.1F, 0.0F);
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
    }
}
