package com.syming.cursedcastle.block.head;

import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.block.blockentity.CCBlockEntity;
import com.syming.cursedcastle.block.blockentity.CCHeadBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CCHeadBlock extends SkullBlock {

    public CCHeadBlock(Type pType) {
        super(pType, BlockBehaviour.Properties.of().strength(1.0F).noOcclusion());
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new CCHeadBlockEntity(pPos, pState);
    }

    public static enum CCHeadTypes implements SkullBlock.Type {
        DRACULA,
        GRIM_REAPER;

        @Override
        public String getSerializedName() {
            return this.name();
        }
    }
}
