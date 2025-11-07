package com.syming.cursedcastle.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.syming.cursedcastle.client.model.CCBatModel;
import com.syming.cursedcastle.entity.mob.bat.CCBatEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;

public abstract class CCBatRenderer extends LivingEntityRenderer<CCBatEntity, CCBatModel> {
    public CCBatRenderer(EntityRendererProvider.Context pContext, CCBatModel pModel) {
        super(pContext, pModel, 0.25F);
    }

    @Override
    protected void scale(CCBatEntity pLivingEntity, PoseStack pPoseStack, float pPartialTickTime) {
        pPoseStack.scale(0.45F, 0.45F, 0.45F);
    }

    @Override
    protected boolean shouldShowName(CCBatEntity pEntity) {
        return false;
    }
}
