package com.syming.castlevaniacraft.client.render.entity;

import com.syming.castlevaniacraft.entity.projectiles.subweapons.EntityThrowingAxe;
import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.item.projectiles.subweapons.throwingaxe.ItemThrowingAxe;
import com.syming.castlevaniacraft.util.handler.RegistryHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

import static com.syming.castlevaniacraft.util.handler.RegistryHandler.NETHERITE;

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
            if (itemThrowingAxe.getMaterial() == Item.ToolMaterial.WOOD) {
                return new ItemStack(CCItem.animWoodenThrowingAxe);
            } else if (itemThrowingAxe.getMaterial() == Item.ToolMaterial.STONE) {
                return new ItemStack(CCItem.animStoneThrowingAxe);
            } else if (itemThrowingAxe.getMaterial() == Item.ToolMaterial.IRON) {
                return new ItemStack(CCItem.animIronThrowingAxe);
            } else if (itemThrowingAxe.getMaterial() == Item.ToolMaterial.GOLD) {
                return new ItemStack(CCItem.animGoldenThrowingAxe);
            } else if (itemThrowingAxe.getMaterial() == Item.ToolMaterial.DIAMOND) {
                return new ItemStack(CCItem.animDiamondThrowingAxe);
            } else if (itemThrowingAxe.getMaterial() == NETHERITE) {
                return new ItemStack(RegistryHandler.animNetheriteThrowingAxe);
            }
        }
        return stack;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityThrowingAxe entity) {
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }
}
