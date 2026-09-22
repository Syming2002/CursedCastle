package com.syming.cursedcastle.block;

import com.syming.cursedcastle.block.blockentity.CCBlockEntity;
import com.syming.cursedcastle.block.blockentity.CrucifixBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CrucifixBlock extends Block implements EntityBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    public static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    private final WoodType woodType;

    public CrucifixBlock(Properties pProperties, WoodType pWoodType) {
        super(pProperties);


        this.woodType = pWoodType;

        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(HALF, DoubleBlockHalf.LOWER));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public VoxelShape getInteractionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public WoodType getWoodType() {
        return this.woodType;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new CrucifixBlockEntity(pPos, pState, this.woodType);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pBlockEntityType == CCBlockEntity.CRUCIFIX_BLOCK_ENTITY_TYPES.get(this.woodType).get() ? ((pLevel1, pPos, pState1, pBlockEntity) -> ((CrucifixBlockEntity)pBlockEntity).tick(pLevel1, pPos, pState1, pBlockEntity)) : null;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockPos blockpos = pContext.getClickedPos();
        Level level = pContext.getLevel();
        if (blockpos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(blockpos.above()).canBeReplaced(pContext)) {
            return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection()).setValue(HALF, DoubleBlockHalf.LOWER);
        } else {
            return null;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, HALF);
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        pLevel.setBlock(pPos.above(), pState.setValue(HALF, DoubleBlockHalf.UPPER), 3);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
        DoubleBlockHalf doubleblockhalf = pState.getValue(HALF);
        if (pDirection.getAxis() == Direction.Axis.Y && doubleblockhalf == DoubleBlockHalf.LOWER == (pDirection == Direction.UP)) {
            return pNeighborState.is(this) && pNeighborState.getValue(HALF) != doubleblockhalf ? pState.setValue(FACING, pNeighborState.getValue(FACING)) : Blocks.AIR.defaultBlockState();
        } else {
            return doubleblockhalf == DoubleBlockHalf.LOWER && pDirection == Direction.DOWN && !pState.canSurvive(pLevel, pPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pDirection, pNeighborState, pLevel, pPos, pNeighborPos);
        }
    }

    @Override
    public long getSeed(BlockState pState, BlockPos pPos) {
        return Mth.getSeed(pPos.getX(), pPos.below(pState.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pPos.getZ());
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockPos = pPos.below();
        BlockState blockState = pLevel.getBlockState(blockPos);
        return pState.getValue(HALF) == DoubleBlockHalf.LOWER ? blockState.isFaceSturdy(pLevel, blockPos, Direction.UP) : blockState.is(this);
    }

    @Override
    public List<ItemStack> getDrops(BlockState pState, LootParams.Builder pParams) {
        List<ItemStack> drops = new ArrayList<>();


        if (this.woodType != null) {
            if (this.woodType == WoodType.OAK) {
                Block crucifixOakBlock = CCBlock.CRUCIFIX_BLOCK_TYPES.get(WoodType.OAK).get();
                drops.add(new ItemStack(crucifixOakBlock, 1));
            } else if (this.woodType == WoodType.SPRUCE) {
                Block crucifixSpruceBlock = CCBlock.CRUCIFIX_BLOCK_TYPES.get(WoodType.SPRUCE).get();
                drops.add(new ItemStack(crucifixSpruceBlock, 1));
            } else if (this.woodType == WoodType.BIRCH) {
                Block crucifixBirchBlock = CCBlock.CRUCIFIX_BLOCK_TYPES.get(WoodType.BIRCH).get();
                drops.add(new ItemStack(crucifixBirchBlock, 1));
            } else if (this.woodType == WoodType.JUNGLE) {
                Block crucifixJungleBlock = CCBlock.CRUCIFIX_BLOCK_TYPES.get(WoodType.JUNGLE).get();
                drops.add(new ItemStack(crucifixJungleBlock, 1));
            } else if (this.woodType == WoodType.ACACIA) {
                Block crucifixAcaciaBlock = CCBlock.CRUCIFIX_BLOCK_TYPES.get(WoodType.ACACIA).get();
                drops.add(new ItemStack(crucifixAcaciaBlock, 1));
            } else if (this.woodType == WoodType.DARK_OAK) {
                Block crucifixDarkOakBlock = CCBlock.CRUCIFIX_BLOCK_TYPES.get(WoodType.DARK_OAK).get();
                drops.add(new ItemStack(crucifixDarkOakBlock, 1));
            } else if (this.woodType == WoodType.CRIMSON) {
                Block crucifixCrimsonBlock = CCBlock.CRUCIFIX_BLOCK_TYPES.get(WoodType.CRIMSON).get();
                drops.add(new ItemStack(crucifixCrimsonBlock, 1));
            } else if (this.woodType == WoodType.WARPED) {
                Block crucifixWarpedBlock = CCBlock.CRUCIFIX_BLOCK_TYPES.get(WoodType.WARPED).get();
                drops.add(new ItemStack(crucifixWarpedBlock, 1));
            } else if (this.woodType == WoodType.MANGROVE) {
                Block crucifixMangroveBlock = CCBlock.CRUCIFIX_BLOCK_TYPES.get(WoodType.MANGROVE).get();
                drops.add(new ItemStack(crucifixMangroveBlock, 1));
            } else if (this.woodType == WoodType.CHERRY) {
                Block crucifixCherryBlock = CCBlock.CRUCIFIX_BLOCK_TYPES.get(WoodType.CHERRY).get();
                drops.add(new ItemStack(crucifixCherryBlock, 1));
            } else if (this.woodType == WoodType.BAMBOO) {
                Block crucifixBambooBlock = CCBlock.CRUCIFIX_BLOCK_TYPES.get(WoodType.BAMBOO).get();
                drops.add(new ItemStack(crucifixBambooBlock, 1));
            }
        }
        return drops;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if (pState.is(pNewState.getBlock())) {
            super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
            return;
        }

        BlockPos lowerPos = switch (pState.getValue(HALF)) {
            case LOWER -> pPos.above();
            case UPPER -> pPos.below();
        };

        BlockPos upperPos = lowerPos.above();


        removePart(pLevel, lowerPos);
        removePart(pLevel, upperPos);


        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    private void removePart(Level pLevel, BlockPos pPos) {
        BlockState blockState = pLevel.getBlockState(pPos);
        if (blockState.is(this)) {
            pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 3);
        }
    }
}
