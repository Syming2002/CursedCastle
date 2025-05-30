package com.syming.castlevaniacraft.client.render.entity;

import com.syming.castlevaniacraft.client.model.entity.ModelGrimReaper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;

public class CCLayerHeldItemDeathScythe implements LayerRenderer<EntityLivingBase> {
    protected final RenderLivingBase<?> livingEntityRenderer;

    public CCLayerHeldItemDeathScythe(RenderLivingBase<?> livingEntityRendererIn) {
        this.livingEntityRenderer = livingEntityRendererIn;
    }

    @Override
    public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        ItemStack itemstack = entitylivingbaseIn.getHeldItemMainhand();

        if (!itemstack.isEmpty()) {
            GlStateManager.pushMatrix();

            this.renderHeldItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, EnumHandSide.RIGHT);

            GlStateManager.popMatrix();
        }
    }

    private void renderHeldItem(EntityLivingBase entityLivingBase, ItemStack stack, ItemCameraTransforms.TransformType camera, EnumHandSide side) {
        if (!stack.isEmpty()) {
            GlStateManager.pushMatrix();

            // Forge: moved this call down, fixes incorrect offset while sneaking.
            this.translateToHand(side);
            GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);

            GlStateManager.translate(0.0F, 0.2F, -0.6F);

            GlStateManager.rotate((float) Math.toDegrees(40.9384), 0F, 0F, 1F);
            GlStateManager.rotate((float) Math.toDegrees(4.4638), 0F, 1F, 0F);
            GlStateManager.rotate((float) Math.toDegrees(-165.665), 1F, 0F, 0F);

            GlStateManager.scale(1.25F, 1.25F, 1.25F);

            Minecraft.getMinecraft().getItemRenderer().renderItemSide(entityLivingBase, stack, camera, false);
            GlStateManager.popMatrix();
        }
    }

    protected void translateToHand(EnumHandSide side) {
        ((ModelGrimReaper) this.livingEntityRenderer.getMainModel()).postRenderArm(0.0625F, side);
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
