package com.syming.cursedcastle.block.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class CrucifixBlockEntity extends BlockEntity implements BlockEntityTicker<CrucifixBlockEntity> {

    private WoodType woodType;

    public CrucifixBlockEntity(BlockPos pPos, BlockState pBlockState, WoodType pWoodType) {
        super(CCBlockEntity.CRUCIFIX_BLOCK_ENTITY_TYPES.get(pWoodType).get(), pPos, pBlockState);

        this.woodType = pWoodType;
    }

    @Override
    public BlockEntityType<?> getType() {
        return CCBlockEntity.CRUCIFIX_BLOCK_ENTITY_TYPES.get(this.woodType).get();
    }

    public WoodType getWoodType() {
        return this.woodType;
    }

    @Override
    public void tick(Level pLevel, BlockPos pPos, BlockState pState, CrucifixBlockEntity pBlockEntity) {

    }
}
