package com.syming.cursedcastle.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.model.MedusaModel;
import com.syming.cursedcastle.entity.monster.boss.MedusaEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EnderDragonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

public class MedusaRenderer extends MobRenderer<MedusaEntity, MedusaModel> {
    private static final ResourceLocation MEDUSA_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/medusa.png");

    public MedusaRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new MedusaModel(pContext.bakeLayer(MedusaModel.MEDUSA_LAYER)), 0.75F);
    }

    @Override
    public void render(MedusaEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);

        if (pEntity.getTarget() != null && pEntity.getTarget().isAlive()) {
            if (pEntity.getAnimation() == MedusaEntity.STARE_ATTACK_ANIMATION) {
                if (pEntity.stareAttackAnimationTick > 55 && pEntity.stareAttackAnimationTick <= 75) {
                    Vec3 mobHead = pEntity.getPosition(pPartialTicks)
                            .add(0.0D, pEntity.getEyeHeight(), 0.0D);

                    Vec3 targetHead = pEntity.getTarget().getPosition(pPartialTicks)
                            .add(0.0D, pEntity.getTarget().getEyeHeight(), 0.0D);

                    Vec3 beam = targetHead.subtract(mobHead);

                    pPoseStack.pushPose();

                    EnderDragonRenderer.renderCrystalBeams(
                            (float) beam.x,
                            (float) beam.y,
                            (float) beam.z,
                            pPartialTicks,
                            pEntity.tickCount,
                            pPoseStack,
                            pBuffer,
                            pPackedLight
                    );

                    pPoseStack.popPose();
                }
            }
        }
    }

    @Override
    public ResourceLocation getTextureLocation(MedusaEntity pEntity) {
        return MEDUSA_TEXTURE;
    }
}
