package com.syming.cursedcastle.block.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.function.Supplier;

public class CrucifixBlockEntity extends BlockEntity {

    private WoodType woodType;

    public CrucifixBlockEntity(BlockPos pPos, BlockState pBlockState, WoodType pWoodType) {
        super(CCBlockEntity.CRUCIFIX_BLOCK_ENTITY_TYPES.get(pWoodType).get(), pPos, pBlockState);

        this.woodType = pWoodType;
    }

    public <T extends BlockEntity> void tick(Level pLevel, BlockPos pPos, BlockState pState, T pBlockEntityType) {
        if (pLevel.isClientSide()) return;
    }

    public WoodType getWoodType() {
        return this.woodType;
    }
}
