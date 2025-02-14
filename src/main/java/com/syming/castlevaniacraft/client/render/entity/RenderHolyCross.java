package com.syming.castlevaniacraft.client.render.entity;

import com.syming.castlevaniacraft.entity.projectiles.subweapons.EntityHolyCross;
import com.syming.castlevaniacraft.item.MVItem;
import com.syming.castlevaniacraft.item.projectiles.subweapons.holycross.ItemHolyCross;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderHolyCross extends Render<EntityHolyCross> {

    private final RenderItem itemRenderer;

    public RenderHolyCross(RenderManager manager, RenderItem itemRenderer) {
        super(manager);
        this.itemRenderer = itemRenderer;
    }

    @Override
    public void doRender(EntityHolyCross entity, double x, double y, double z, float entityYaw, float partialTicks) {
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

    public ItemStack getStackToRender(EntityHolyCross entityIn) {
        ItemStack stack = entityIn.getItemStack();
        if (stack.getItem() instanceof ItemHolyCross itemHolyCross) {
            switch (itemHolyCross.getMaterial()) {
                case WOOD:
                    return new ItemStack(MVItem.animWoodenHolyCross);
                case STONE:
                    return new ItemStack(MVItem.animStoneHolyCross);
                case IRON:
                    return new ItemStack(MVItem.animIronHolyCross);
                case GOLD:
                    return new ItemStack(MVItem.animGoldenHolyCross);
                case DIAMOND:
                    return new ItemStack(MVItem.animDiamondHolyCross);
            }
        }
        return stack;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityHolyCross entity) {
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }
}
