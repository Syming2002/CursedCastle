package com.syming.cursedcastle.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.model.HarpyFeatherModel;
import com.syming.cursedcastle.client.model.RusalkaModel;
import com.syming.cursedcastle.entity.magic.HarpyFeatherEntity;
import com.syming.cursedcastle.entity.mob.boss.RusalkaEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class RusalkaRenderer extends MobRenderer<RusalkaEntity, RusalkaModel> {
    public static final ResourceLocation RUSALKA_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/rusalka.png");

    public RusalkaRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new RusalkaModel(), 0.25F);
    }

    @Override
    public void render(RusalkaEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(RusalkaEntity pEntity) {
        return RUSALKA_TEXTURE;
    }
}
