package com.syming.cursedcastle.client.renderer.entity.rusalka;

import com.mojang.blaze3d.vertex.PoseStack;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.model.RusalkaModel;
import com.syming.cursedcastle.entity.monster.boss.RusalkaEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RusalkaRenderer extends MobRenderer<RusalkaEntity, RusalkaModel> {
    public static final ResourceLocation RUSALKA_TEXTURE = ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "textures/entity/rusalka/rusalka.png");

    public RusalkaRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new RusalkaModel(pContext.bakeLayer(RusalkaModel.RUSALKA_LAYER)), 0.25F);
        addLayer(new RusalkaEyesLayer(this));
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
