package com.syming.cursedcastle.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.model.ShadeModel;
import com.syming.cursedcastle.entity.monster.shade.ShadeEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;

public class HeatShadeFlameLayer<T extends ShadeEntity, M extends ShadeModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation FLAME_LAYER = ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "textures/entity/shade/heatshade/heat_shade_flame.png");

    public HeatShadeFlameLayer(RenderLayerParent<T, M> pRenderer) {
        super(pRenderer);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        VertexConsumer vertexconsumer = pBuffer.getBuffer(this.renderType());
        int i = FastColor.ARGB32.color(255, 255, 255, 255);
        this.getParentModel().renderToBuffer(pPoseStack, vertexconsumer, 15728640, OverlayTexture.NO_OVERLAY, i);
    }

    private RenderType renderType() {
        return RenderType.entityTranslucentEmissive(FLAME_LAYER);
    }


}
