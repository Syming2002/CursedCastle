//package com.syming.cursedcastle.client.renderer.entity;
//
//import com.mojang.blaze3d.vertex.PoseStack;
//import com.mojang.blaze3d.vertex.VertexConsumer;
//import com.mojang.math.Axis;
//import com.syming.cursedcastle.client.model.CrucifixModel;
//import com.syming.cursedcastle.entity.CrucifixEntity;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.MultiBufferSource;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.client.renderer.entity.EntityRendererProvider;
//import net.minecraft.client.renderer.entity.LivingEntityRenderer;
//import net.minecraft.client.renderer.entity.layers.RenderLayer;
//import net.minecraft.client.renderer.texture.OverlayTexture;
//import net.minecraft.core.Direction;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.util.Mth;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.Pose;
//import net.minecraft.world.level.block.state.properties.WoodType;
//import org.jetbrains.annotations.Nullable;
//
//import static net.minecraft.world.level.block.state.properties.WoodType.*;
//
//public class CrucifixRenderer extends LivingEntityRenderer<CrucifixEntity, CrucifixModel> {
//    private static final ResourceLocation OAK_CRUCIFIX_TEXTURE = new ResourceLocation("minecraft", "textures/block/oak_planks.png");
//    private static final ResourceLocation SPRUCE_CRUCIFIX_TEXTURE = new ResourceLocation("minecraft", "textures/block/spruce_planks.png");
//    private static final ResourceLocation BIRCH_CRUCIFIX_TEXTURE = new ResourceLocation("minecraft", "textures/block/birch_planks.png");
//    private static final ResourceLocation ACACIA_CRUCIFIX_TEXTURE = new ResourceLocation("minecraft", "textures/block/acacia_planks.png");
//    private static final ResourceLocation CHERRY_CRUCIFIX_TEXTURE = new ResourceLocation("minecraft", "textures/block/cherry_planks.png");
//    private static final ResourceLocation JUNGLE_CRUCIFIX_TEXTURE = new ResourceLocation("minecraft", "textures/block/jungle_planks.png");
//    private static final ResourceLocation DARK_OAK_CRUCIFIX_TEXTURE = new ResourceLocation("minecraft", "textures/block/dark_oak_planks.png");
//    private static final ResourceLocation CRIMSON_CRUCIFIX_TEXTURE = new ResourceLocation("minecraft", "textures/block/crimson_planks.png");
//    private static final ResourceLocation WARPED_CRUCIFIX_TEXTURE = new ResourceLocation("minecraft", "textures/block/warped_planks.png");
//    private static final ResourceLocation MANGROVE_CRUCIFIX_TEXTURE = new ResourceLocation("minecraft", "textures/block/mangrove_planks.png");
//    private static final ResourceLocation BAMBOO_CRUCIFIX_TEXTURE = new ResourceLocation("minecraft", "textures/block/bamboo_planks.png");
//
//    private static final ResourceLocation COBBLESTONE_BASE_TEXTURE = new ResourceLocation("minecraft", "textures/block/cobblestone.png");
//
//    public CrucifixRenderer(EntityRendererProvider.Context pContext) {
//        super(pContext, new CrucifixModel(pContext.bakeLayer(CrucifixModel.CRUCIFIX_LAYER)), 0.0F);
//    }
//
//    @Override
//    public void render(CrucifixEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
//        pPoseStack.pushPose();
//
//        this.setupRotations(pEntity, pPoseStack, pEntity.tickCount, pEntityYaw, pPartialTicks);
//        pPoseStack.translate(0.0F, 0.0F, 0.0F);
//        pPoseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
//
//        VertexConsumer baseConsumer = pBuffer.getBuffer(
//                RenderType.entityCutout(COBBLESTONE_BASE_TEXTURE)
//        );
//        this.model.base.render(
//                pPoseStack,
//                baseConsumer,
//                pPackedLight,
//                OverlayTexture.NO_OVERLAY
//        );
//
//        VertexConsumer beamConsumer = pBuffer.getBuffer(
//                RenderType.entityCutout(getTextureLocation(pEntity))
//        );
//        this.model.beam.render(
//                pPoseStack,
//                beamConsumer,
//                pPackedLight,
//                OverlayTexture.NO_OVERLAY
//        );
//
//        this.model.leftBeam.render(
//                pPoseStack,
//                beamConsumer,
//                pPackedLight,
//                OverlayTexture.NO_OVERLAY
//        );
//
//        this.model.rightBeam.render(
//                pPoseStack,
//                beamConsumer,
//                pPackedLight,
//                OverlayTexture.NO_OVERLAY
//        );
//        pPoseStack.popPose();
//    }
//
//    @Override
//    protected void setupRotations(CrucifixEntity pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
//        pPoseStack.mulPose(Axis.YP.rotationDegrees(180.0F - pRotationYaw));
//        float f = (float)(pEntityLiving.level().getGameTime() - pEntityLiving.lastHit) + pPartialTicks;
//        if (f < 5.0F) {
//            pPoseStack.mulPose(Axis.YP.rotationDegrees(Mth.sin(f / 1.5F * (float) Math.PI) * 3.0F));
//        }
//    }
//
//    @Override
//    public ResourceLocation getTextureLocation(CrucifixEntity pEntity) {
//        WoodType woodType = pEntity.getWoodType();
//        if (woodType == OAK) {
//            return OAK_CRUCIFIX_TEXTURE;
//        } else if (woodType == SPRUCE) {
//            return SPRUCE_CRUCIFIX_TEXTURE;
//        } else if (woodType == BIRCH) {
//            return BIRCH_CRUCIFIX_TEXTURE;
//        } else if (woodType == ACACIA) {
//            return ACACIA_CRUCIFIX_TEXTURE;
//        } else if (woodType == CHERRY) {
//            return CHERRY_CRUCIFIX_TEXTURE;
//        } else if (woodType == JUNGLE) {
//            return JUNGLE_CRUCIFIX_TEXTURE;
//        } else if (woodType == DARK_OAK) {
//            return DARK_OAK_CRUCIFIX_TEXTURE;
//        } else if (woodType == CRIMSON) {
//            return CRIMSON_CRUCIFIX_TEXTURE;
//        } else if (woodType == WARPED) {
//            return WARPED_CRUCIFIX_TEXTURE;
//        } else if (woodType == MANGROVE) {
//            return MANGROVE_CRUCIFIX_TEXTURE;
//        } else if (woodType == BAMBOO) {
//            return BAMBOO_CRUCIFIX_TEXTURE;
//        }
//        return null;
//    }
//}
