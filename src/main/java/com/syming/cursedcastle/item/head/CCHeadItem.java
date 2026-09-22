package com.syming.cursedcastle.item.head;

import com.syming.cursedcastle.client.renderer.item.CCHeadItemRender;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class CCHeadItem extends StandingAndWallBlockItem {
    public CCHeadItem(Block pBlock, Block pWallBlock, Direction pAttachmentDirection) {
        super(pBlock, pWallBlock, new Properties().rarity(Rarity.RARE), pAttachmentDirection);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return CCHeadItemRender.getInstance();
            }
        });
    }
}
