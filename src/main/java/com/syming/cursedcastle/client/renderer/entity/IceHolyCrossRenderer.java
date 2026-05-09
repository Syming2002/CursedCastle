package com.syming.cursedcastle.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.syming.cursedcastle.entity.projectile.subweapon.FireHolyCrossEntity;
import com.syming.cursedcastle.entity.projectile.subweapon.IceHolyCrossEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.BlockPos;

import static com.syming.cursedcastle.entity.projectile.subweapon.IceHolyCrossEntity.SPIKE_COUNT;

public class IceHolyCrossRenderer extends ThrownItemRenderer<IceHolyCrossEntity> {

    public IceHolyCrossRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public void render(IceHolyCrossEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();

        int spikeCount = pEntity.getEntityData().get(SPIKE_COUNT);

        int lifeProgress = 9 - spikeCount;

        float scale = 2.5F * (lifeProgress / 9.0F);

        if (scale < 0.01F) {
            pPoseStack.popPose();
            return;
        }

        pPoseStack.scale(scale, scale, scale);

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);

        pPoseStack.popPose();
    }
}
