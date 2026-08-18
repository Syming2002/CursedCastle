package com.syming.cursedcastle.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.model.MedusaModel;
import com.syming.cursedcastle.entity.monster.boss.MedusaEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MedusaRenderer extends MobRenderer<MedusaEntity, MedusaModel> {
    private static final ResourceLocation MEDUSA_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/medusa.png");

    public MedusaRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new MedusaModel(pContext.bakeLayer(MedusaModel.MEDUSA_LAYER)), 0.75F);
    }

    @Override
    public void render(MedusaEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(MedusaEntity pEntity) {
        return MEDUSA_TEXTURE;
    }
}
