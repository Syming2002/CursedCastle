package com.syming.castlevaniacraft.world.entity.client.bloodSkeleton;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.syming.castlevaniacraft.CastlevaniaCraftMod;
import com.syming.castlevaniacraft.world.entity.client.dracula.DraculaModel;
import com.syming.castlevaniacraft.world.entity.mob.BloodSkeletonEntity;
import com.syming.castlevaniacraft.world.entity.mob.boss.DraculaEntity;
import corgitaco.enhancedcelestials.EnhancedCelestialsWorldData;
import corgitaco.enhancedcelestials.api.lunarevent.LunarEvent;
import corgitaco.enhancedcelestials.core.EnhancedCelestialsContext;
import corgitaco.enhancedcelestials.lunarevent.LunarForecast;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;

import static corgitaco.enhancedcelestials.api.EnhancedCelestialsRegistry.LUNAR_EVENT_KEY;
import static corgitaco.enhancedcelestials.api.lunarevent.DefaultLunarEvents.BLOOD_MOON;

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
