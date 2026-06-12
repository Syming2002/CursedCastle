package com.syming.cursedcastle.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.syming.cursedcastle.client.model.GrimReaperModel;
import com.syming.cursedcastle.entity.mob.boss.GrimReaperEntity;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DeathScytheInHandLayer extends RenderLayer<GrimReaperEntity, GrimReaperModel<GrimReaperEntity>> {
    private final ItemInHandRenderer itemInHandRenderer;

    public DeathScytheInHandLayer(RenderLayerParent<GrimReaperEntity, GrimReaperModel<GrimReaperEntity>> pRenderer, ItemInHandRenderer pItemInHandRenderer) {
        super(pRenderer);

        this.itemInHandRenderer = pItemInHandRenderer;
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, GrimReaperEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        ItemStack stack = pLivingEntity.getMainHandItem();
        if (!stack.isEmpty()) {
            pPoseStack.pushPose();
            this.renderArmWithDeathScythe(pLivingEntity, stack, ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, HumanoidArm.RIGHT, pPoseStack, pBuffer, pPackedLight);
            pPoseStack.popPose();
        }
    }

    protected void renderArmWithDeathScythe(LivingEntity pLivingEntity, ItemStack pItemStack, ItemDisplayContext pDisplayContext, HumanoidArm pArm, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        if (pItemStack.isEmpty()) {
            return;
        }

        pPoseStack.pushPose();

        translateToHand(pArm, pPoseStack);

        pPoseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));

        pPoseStack.mulPose(Axis.YP.rotationDegrees(180.0F));

        pPoseStack.translate(0.0F, 0.2F, -0.6F);

        pPoseStack.mulPose(Axis.ZP.rotationDegrees((float)Math.toDegrees(40.9384)));

        pPoseStack.mulPose(Axis.YP.rotationDegrees((float)Math.toDegrees(4.4638)));

        pPoseStack.mulPose(Axis.XP.rotationDegrees((float)Math.toDegrees(-165.665)));

        pPoseStack.scale(1.25F, 1.25F, 1.25F);

        this.itemInHandRenderer.renderItem(pLivingEntity, pItemStack, pDisplayContext, false, pPoseStack, pBuffer, pPackedLight);

        pPoseStack.popPose();
    }

    private void translateToHand(HumanoidArm pArm, PoseStack pPoseStack) {
        getParentModel().translateToHand(pArm, pPoseStack);
    }
}
