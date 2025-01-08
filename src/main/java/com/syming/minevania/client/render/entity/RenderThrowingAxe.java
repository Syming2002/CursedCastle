package com.syming.minevania.client.render.entity;

import com.syming.minevania.entity.projectiles.subweapons.EntityThrowingAxe;
import com.syming.minevania.item.MVItem;
import com.syming.minevania.item.projectiles.subweapons.throwingaxe.ItemThrowingAxe;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderThrowingAxe extends Render<EntityThrowingAxe> {

    private final RenderItem itemRenderer;

    public RenderThrowingAxe(RenderManager manager, RenderItem itemRenderer) {
        super(manager);
        this.itemRenderer = itemRenderer;
    }

    public void doRender(EntityThrowingAxe entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate((float)(this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
        this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

        if (this.renderOutlines)
        {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }

        this.itemRenderer.renderItem(this.getStackToRender(entity), ItemCameraTransforms.TransformType.GROUND);

        if (this.renderOutlines)
        {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public ItemStack getStackToRender(EntityThrowingAxe entityIn) {
        ItemStack stack = entityIn.getItemStack();
        if (stack.getItem() instanceof ItemThrowingAxe itemThrowingAxe) {
            switch (itemThrowingAxe.getMaterial()) {
                case WOOD:
                    return new ItemStack(MVItem.animWoodenThrowingAxe);
                case STONE:
                    return new ItemStack(MVItem.animStoneThrowingAxe);
                case IRON:
                    return new ItemStack(MVItem.animIronThrowingAxe);
                case GOLD:
                    return new ItemStack(MVItem.animGoldenThrowingAxe);
                case DIAMOND:
                    return new ItemStack(MVItem.animDiamondThrowingAxe);
            }
        }
        return stack;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityThrowingAxe entity) {
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }
}
