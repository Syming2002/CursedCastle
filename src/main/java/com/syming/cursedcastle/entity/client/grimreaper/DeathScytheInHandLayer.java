package com.syming.cursedcastle.entity.client.grimreaper;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.syming.cursedcastle.entity.mob.boss.GrimReaperEntity;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DeathScytheInHandLayer extends ItemInHandLayer<GrimReaperEntity, GrimReaperModel> {
    private final ItemInHandRenderer itemInHandRenderer;

    public DeathScytheInHandLayer(RenderLayerParent<GrimReaperEntity, GrimReaperModel> pRenderer, ItemInHandRenderer pItemInHandRenderer) {
        super(pRenderer, pItemInHandRenderer);

        this.itemInHandRenderer = pItemInHandRenderer;
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, GrimReaperEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        ItemStack stack = pLivingEntity.getMainHandItem();
        if (!stack.isEmpty()) {
            pPoseStack.pushPose();

            translateToHand(pPoseStack);

            pPoseStack.mulPose(Axis.XP.rotationDegrees(-25.0F));
            pPoseStack.mulPose(Axis.YP.rotationDegrees(25.0F));
            pPoseStack.mulPose(Axis.ZP.rotationDegrees(-100.0F));

            pPoseStack.translate(-0.6F, 0.2F, -0.1F);

            pPoseStack.scale(1.25F,1.25F, 1.25F);

            this.itemInHandRenderer.renderItem(pLivingEntity, stack, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, false, pPoseStack, pBuffer, pPackedLight);

            pPoseStack.popPose();
        }
    }

    protected void translateToHand(PoseStack pPoseStack) {
        getParentModel().rightArm.translateAndRotate(pPoseStack);
    }
}
