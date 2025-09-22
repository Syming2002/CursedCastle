package com.syming.cursedcastle.entity.client.skeleton;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.mob.skeleton.BloodSkeletonEntity;
import com.syming.cursedcastle.entity.mob.skeleton.CCSkeletonEntity;
import com.syming.cursedcastle.entity.mob.skeleton.ThrowingBoneSkeletonEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SkeletonRender extends MobRenderer<CCSkeletonEntity, SkeletonModel> {

    public static final ResourceLocation SKELETON_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/skeleton.png");
    public static final ResourceLocation BLOOD_SKELETON_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/blood_skeleton.png");


    public SkeletonRender(EntityRendererProvider.Context pContext) {
        super(pContext, new SkeletonModel(), 0.5F);
    }

    @Override
    protected float getFlipDegrees(CCSkeletonEntity pLivingEntity) {
        return 0.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(CCSkeletonEntity pEntity) {
        if (pEntity instanceof ThrowingBoneSkeletonEntity) {
            return SKELETON_TEXTURE;
        } else if (pEntity instanceof BloodSkeletonEntity) {
            return BLOOD_SKELETON_TEXTURE;
        }
        return null;
    }
}
