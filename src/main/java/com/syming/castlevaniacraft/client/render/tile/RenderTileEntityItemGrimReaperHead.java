package com.syming.castlevaniacraft.client.render.tile;


import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public class RenderTileEntityItemGrimReaperHead extends TileEntityItemStackRenderer {

    @Override
    public void renderByItem(ItemStack stack, float partialTicks) {
        TileEntityGrimReaperHeadRenderer.INSTANCE.renderGrimReaperHead(
                0.0F, 0.0F, 0.0F,
                EnumFacing.UP,
                180.0F,
                -1
        );
    }

}
