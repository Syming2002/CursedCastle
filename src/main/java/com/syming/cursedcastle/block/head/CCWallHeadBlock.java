package com.syming.cursedcastle.block.head;

import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.block.blockentity.CCBlockEntity;
import com.syming.cursedcastle.block.blockentity.CCHeadBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CCWallHeadBlock extends WallSkullBlock {

    public CCWallHeadBlock(CCHeadBlock.CCHeadTypes pTypes) {
        super(pTypes, BlockBehaviour.Properties.of().strength(1.0F).noOcclusion());
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new CCHeadBlockEntity(pPos, pState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (pLevel.isClientSide()) {
            boolean flag = pState.is(CCBlock.DRACULA_WALL_HEAD.get());
            if (flag) {
                return createTickerHelper(pBlockEntityType, CCBlockEntity.CC_HEAD.get(),
                        CCHeadBlockEntity::animation);
            }
        }
        return null;
    }
}
