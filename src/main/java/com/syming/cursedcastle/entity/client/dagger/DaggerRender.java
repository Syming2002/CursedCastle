package com.syming.cursedcastle.entity.client.dagger;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.item.subweapon.DaggerItem;
import com.syming.cursedcastle.entity.projectile.subweapon.DaggerEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

public class DaggerRender extends EntityRenderer<DaggerEntity> {

    public static final ResourceLocation WOODEN_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/wooden_dagger.png");
    public static final ResourceLocation STONE_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/stone_dagger.png");
    public static final ResourceLocation IRON_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/iron_dagger.png");
    public static final ResourceLocation GOLDEN_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/golden_dagger.png");
    public static final ResourceLocation DIAMOND_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/diamond_dagger.png");
    public static final ResourceLocation NETHERITE_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/netherite_dagger.png");

    protected DaggerModel model;

    public DaggerRender(EntityRendererProvider.Context pContext) {
        super(pContext);

        this.model = new DaggerModel<>(pContext.bakeLayer(DaggerModel.LAYER_LOCATION));
        this.shadowRadius = 0.25F;
    }

    @Override
    public void render(DaggerEntity daggerEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
//        pPoseStack.translate(daggerEntity.getX() - daggerEntity.xo * pPartialTick,
//                daggerEntity.getY() - daggerEntity.yo * pPartialTick,
//                daggerEntity.getZ() - daggerEntity.zo * pPartialTick);

        pPoseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(pPartialTick, daggerEntity.yRotO, daggerEntity.getYRot()) - 90.0F));
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(pPartialTick, daggerEntity.xRotO, daggerEntity.getXRot())));

        VertexConsumer vertexConsumer = ItemRenderer.getFoilBufferDirect(pBuffer, this.model.renderType(getTextureLocation(daggerEntity)), false, false);


        this.model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        pPoseStack.popPose();
        super.render(daggerEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);

    }

    @Override
    public ResourceLocation getTextureLocation(DaggerEntity daggerEntity) {
        ItemStack stack = daggerEntity.getPickupItem();
        if (stack.getItem() instanceof DaggerItem daggerItem) {
            Tier daggerItemTier = daggerItem.getTier();
            if (daggerItemTier == Tiers.WOOD) {
                return WOODEN_TEXTURE;
            } else if (daggerItemTier == Tiers.STONE) {
                return STONE_TEXTURE;
            } else if (daggerItemTier == Tiers.IRON) {
                return IRON_TEXTURE;
            } else if (daggerItemTier == Tiers.GOLD) {
                return GOLDEN_TEXTURE;
            } else if (daggerItemTier == Tiers.DIAMOND) {
                return DIAMOND_TEXTURE;
            } else if (daggerItemTier == Tiers.NETHERITE) {
                return NETHERITE_TEXTURE;
            }
        }
        return WOODEN_TEXTURE;
    }
}
