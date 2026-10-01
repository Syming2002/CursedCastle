package com.syming.cursedcastle.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.layer.CCSkeletonTintLayer;
import com.syming.cursedcastle.client.model.CCSkeletonModel;
import com.syming.cursedcastle.entity.monster.skeleton.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class CCSkeletonRenderer extends MobRenderer<CCSkeletonEntity, CCSkeletonModel> {

    public static final ResourceLocation TRANSPARENCY = ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "textures/misc/transparency.png");

    public CCSkeletonRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new CCSkeletonModel(pContext.bakeLayer(CCSkeletonModel.CC_SKELETON_LAYER)), 0.5F);

        addLayer(new CCSkeletonTintLayer(this));
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
        return TRANSPARENCY;
    }
}
