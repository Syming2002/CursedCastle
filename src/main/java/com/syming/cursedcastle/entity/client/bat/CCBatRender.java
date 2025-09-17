package com.syming.cursedcastle.entity.client.bat;

import com.mojang.blaze3d.vertex.PoseStack;
import com.syming.cursedcastle.entity.mob.bat.CCBatEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;

public abstract class CCBatRender extends LivingEntityRenderer<CCBatEntity, CCBatModel> {
    public CCBatRender(EntityRendererProvider.Context pContext, CCBatModel pModel) {
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
