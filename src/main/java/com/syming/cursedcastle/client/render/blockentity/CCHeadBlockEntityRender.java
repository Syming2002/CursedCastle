package com.syming.cursedcastle.client.render.blockentity;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.blockentity.CCHeadBlockEntity;
import com.syming.cursedcastle.block.head.CCHeadBlock;
import com.syming.cursedcastle.block.head.CCWallHeadBlock;
import com.syming.cursedcastle.entity.client.dracula.DraculaHeadModel;
import net.minecraft.Util;
import net.minecraft.client.model.PiglinHeadModel;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.dragon.DragonHeadModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

import static net.minecraft.client.renderer.blockentity.SkullBlockRenderer.SKIN_BY_TYPE;
import static net.minecraft.client.renderer.blockentity.SkullBlockRenderer.getRenderType;

@OnlyIn(Dist.CLIENT)
public class CCHeadBlockEntityRender implements BlockEntityRenderer<CCHeadBlockEntity> {

    public static final Map<SkullBlock.Type, ResourceLocation> SKIN_BY_TYPE = Util.make(Maps.newHashMap(), (p_261388_) -> {
        p_261388_.put(CCHeadBlock.CCHeadTypes.DRACULA, new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/dracula.png"));
    });
    public static final ModelLayerLocation DRACULA_HEAD_LAYER = new ModelLayerLocation(new ResourceLocation(CursedCastleMod.MOD_ID, "dracula_head"), "main");
    private final Map<SkullBlock.Type, SkullModelBase> modelByType;

    public CCHeadBlockEntityRender(BlockEntityRendererProvider.Context pContext) {
        this.modelByType = createSkullRenderers(pContext.getModelSet());

    }

    public static Map<SkullBlock.Type, SkullModelBase> createSkullRenderers(EntityModelSet entityModelSet) {
        ImmutableMap.Builder<SkullBlock.Type, SkullModelBase> builder = ImmutableMap.builder();
        builder.put(SkullBlock.Types.SKELETON, new SkullModel(entityModelSet.bakeLayer(ModelLayers.SKELETON_SKULL)));
        builder.put(SkullBlock.Types.WITHER_SKELETON, new SkullModel(entityModelSet.bakeLayer(ModelLayers.WITHER_SKELETON_SKULL)));
        builder.put(SkullBlock.Types.PLAYER, new SkullModel(entityModelSet.bakeLayer(ModelLayers.PLAYER_HEAD)));
        builder.put(SkullBlock.Types.ZOMBIE, new SkullModel(entityModelSet.bakeLayer(ModelLayers.ZOMBIE_HEAD)));
        builder.put(SkullBlock.Types.CREEPER, new SkullModel(entityModelSet.bakeLayer(ModelLayers.CREEPER_HEAD)));
        builder.put(SkullBlock.Types.DRAGON, new DragonHeadModel(entityModelSet.bakeLayer(ModelLayers.DRAGON_SKULL)));
        builder.put(SkullBlock.Types.PIGLIN, new PiglinHeadModel(entityModelSet.bakeLayer(ModelLayers.PIGLIN_HEAD)));

        builder.put(CCHeadBlock.CCHeadTypes.DRACULA, new DraculaHeadModel(entityModelSet.bakeLayer(DRACULA_HEAD_LAYER)));
        return builder.build();
    }

    @Override
    public void render(CCHeadBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        if (pBlockEntity.getBlockState().getBlock() instanceof CCHeadBlock || pBlockEntity.getBlockState().getBlock() instanceof CCWallHeadBlock) {
            if (pBlockEntity.getLevel() != null) {
                if (pBlockEntity.getLevel().isClientSide()) {
                    float animation = pBlockEntity.getAnimation(pPartialTick);
                    BlockState blockState = pBlockEntity.getBlockState();
                    boolean isWallSkull = blockState.getBlock() instanceof WallSkullBlock;
                    Direction direction = isWallSkull ? blockState.getValue(WallSkullBlock.FACING) : null;
                    int rotation = isWallSkull ? RotationSegment.convertToSegment(direction.getOpposite()) : blockState.getValue(SkullBlock.ROTATION);
                    float rotationInDegrees = RotationSegment.convertToDegrees(rotation);
                    SkullBlock.Type type = ((AbstractSkullBlock) blockState.getBlock()).getType();
                    SkullModelBase skullModelBase = this.modelByType.get(type);
                    RenderType renderType = getRenderType(type);
                    renderSkull(direction, rotationInDegrees, animation, pPoseStack, pBuffer, pPackedLight, skullModelBase, renderType);
                    System.out.println("Render Dracula Head Block");
                }
            }
        }
    }

    public static void renderSkull(@Nullable Direction pDirection, float pRotation, float pAnimation, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, SkullModelBase pSkullModel, RenderType pRenderType) {
        pPoseStack.pushPose();

        if (pDirection == null) {
            pPoseStack.translate(0.5F, 0.0F, 0.5F);
        } else {
            float horizontalTranslation = 0.25F;
            float verticalTranslation = 0.25F;

            pPoseStack.translate(0.5F - (float)pDirection.getStepX() * horizontalTranslation, verticalTranslation,
                    0.5F - (float)pDirection.getStepZ() * horizontalTranslation);
        }

        pPoseStack.scale(-1.0F, -1.0F, 1.0F);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(pRenderType);
        pSkullModel.setupAnim(pAnimation, pRotation, 0.0F);
        if (pSkullModel instanceof DraculaHeadModel draculaHeadModel) {
            draculaHeadModel.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        } else {
            pSkullModel.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
        pPoseStack.popPose();
    }

    public static RenderType getRenderType(SkullBlock.Type pType) {
        return RenderType.entityCutoutNoCullZOffset(SKIN_BY_TYPE.get(pType));
    }
}
