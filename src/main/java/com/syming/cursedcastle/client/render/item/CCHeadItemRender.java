package com.syming.cursedcastle.client.render.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.syming.cursedcastle.block.head.CCHeadBlock;
import com.syming.cursedcastle.client.render.blockentity.CCHeadBlockEntityRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SkullBlock;

import java.util.Map;

public class CCHeadItemRender extends BlockEntityWithoutLevelRenderer {
    private final Map<SkullBlock.Type, SkullModelBase> skullModelBaseMap;

    public static CCHeadItemRender getInstance() {
        return new CCHeadItemRender(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    public CCHeadItemRender(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
        super(pBlockEntityRenderDispatcher, pEntityModelSet);
        this.skullModelBaseMap = CCHeadBlockEntityRender.createSkullRenderers(pEntityModelSet);
    }

    @Override
    public void renderByItem(ItemStack pStack, ItemDisplayContext pDisplayContext, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        Item item = pStack.getItem();
        if (item instanceof BlockItem blockItem) {
            Block block = blockItem.getBlock();
            if (block instanceof CCHeadBlock ccHeadBlock) {
                SkullBlock.Type type = ccHeadBlock.getType();
                SkullModelBase skullModelBase = this.skullModelBaseMap.get(type);
                RenderType renderType = CCHeadBlockEntityRender.getRenderType(type);
                CCHeadBlockEntityRender.renderSkull(null, 180.0F, 0.0F, pPoseStack, pBuffer, pPackedLight, skullModelBase, renderType);
            }
        }
    }
}
