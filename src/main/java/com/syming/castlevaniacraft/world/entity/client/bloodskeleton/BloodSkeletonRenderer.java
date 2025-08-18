package com.syming.castlevaniacraft.world.entity.client.bloodskeleton;

import com.mojang.blaze3d.vertex.PoseStack;
import com.syming.castlevaniacraft.CastlevaniaCraftMod;
import com.syming.castlevaniacraft.world.entity.mob.BloodSkeletonEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BloodSkeletonRenderer extends MobRenderer<BloodSkeletonEntity, BloodSkeletonModel> {
    public static final ResourceLocation BLOOD_SKELETON_TEXTURE = new ResourceLocation(CastlevaniaCraftMod.MOD_ID, "textures/entity/blood_skeleton.png");


    public BloodSkeletonRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BloodSkeletonModel(), 0.5F);
    }

    @Override
    public void render(BloodSkeletonEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    protected float getFlipDegrees(BloodSkeletonEntity pLivingEntity) {
        return 0.0F;
    }


    @Override
    public ResourceLocation getTextureLocation(BloodSkeletonEntity draculaEntity) {
        return BLOOD_SKELETON_TEXTURE;
    }
}
