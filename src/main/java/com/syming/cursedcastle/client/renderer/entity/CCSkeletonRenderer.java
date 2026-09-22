package com.syming.cursedcastle.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.model.CCSkeletonModel;
import com.syming.cursedcastle.entity.monster.skeleton.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class CCSkeletonRenderer extends MobRenderer<CCSkeletonEntity, CCSkeletonModel> {

    public static final ResourceLocation SKELETON_TEXTURE = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/skeleton/skeleton.png");

    /*
    * Vanilla Skeleton base texture but with colored tint texture and alpha modification
    * */
    public static final ResourceLocation BLOOD_SKELETON_TEXTURE = ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID,"textures/entity/skeleton/blood_skeleton.png");
    public static final ResourceLocation GOLDEN_SKELETON_TEXTURE = ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID,"textures/entity/skeleton/golden_skeleton.png");
    public static final ResourceLocation CLEAR_BONE_SKELETON_INVULNERABLE_TEXTURE = ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID,"textures/entity/skeleton/clear_bone_skeleton_invulnerable.png");


    public CCSkeletonRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new CCSkeletonModel(pContext.bakeLayer(CCSkeletonModel.CC_SKELETON_LAYER)), 0.5F);
    }

    @Override
    public void render(CCSkeletonEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    protected float getFlipDegrees(CCSkeletonEntity pLivingEntity) {
        return 0.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(CCSkeletonEntity pEntity) {
        if (pEntity instanceof BloodSkeletonEntity) {
            return BLOOD_SKELETON_TEXTURE;
        } else if (pEntity instanceof GoldenSkeletonEntity) {
            return GOLDEN_SKELETON_TEXTURE;
        } else if (pEntity instanceof ClearBoneSkeletonEntity clearBoneSkeleton) {
            if (clearBoneSkeleton.isClearBoneInvulnerable()) {
                return CLEAR_BONE_SKELETON_INVULNERABLE_TEXTURE;
            }
            return SKELETON_TEXTURE;
        }
        return SKELETON_TEXTURE;
    }

    @Override
    protected @Nullable RenderType getRenderType(CCSkeletonEntity pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
        if (pLivingEntity instanceof ClearBoneSkeletonEntity clearBoneSkeleton) {
            if (clearBoneSkeleton.isClearBoneInvulnerable()) {
                return RenderType.entityTranslucent(CLEAR_BONE_SKELETON_INVULNERABLE_TEXTURE);
            }
        }
        return super.getRenderType(pLivingEntity, pBodyVisible, pTranslucent, pGlowing);
    }
}
