package com.syming.cursedcastle.block;

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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ChurchAltarBlock extends Block {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<TripleBlockPart> PART = EnumProperty.create("part", TripleBlockPart.class);
    public static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public ChurchAltarBlock(Properties pProperties) {
        super(pProperties);

        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(PART, TripleBlockPart.LEFT));
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

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Level level = pContext.getLevel();
        BlockPos pos = pContext.getClickedPos();

        Direction facing = pContext.getHorizontalDirection();
        Direction left = facing.getCounterClockWise();
        Direction right = facing.getClockWise();

        if (level.getBlockState(pos.relative(left)).canBeReplaced(pContext)
                && level.getBlockState(pos.relative(right)).canBeReplaced(pContext)) {
            return defaultBlockState()
                    .setValue(FACING, facing)
                    .setValue(PART, TripleBlockPart.MIDDLE);
        }

        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, PART);
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
        Direction facing = pState.getValue(FACING);

        Direction left = facing.getCounterClockWise();
        Direction right = facing.getClockWise();

        pLevel.setBlock(pPos.relative(left), pState.setValue(PART, TripleBlockPart.LEFT), 3);

        pLevel.setBlock(pPos.relative(right), pState.setValue(PART, TripleBlockPart.RIGHT), 3);
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
        Direction facing = pState.getValue(FACING);
        TripleBlockPart part = pState.getValue(PART);

        Direction left = facing.getCounterClockWise();
        Direction right = facing.getClockWise();

        switch (part) {
            case LEFT -> {
                if (pDirection == left) {
                    if (!pNeighborState.is(this) || pNeighborState.getValue(PART) != TripleBlockPart.MIDDLE)
                        return Blocks.AIR.defaultBlockState();
                }
            }

            case MIDDLE -> {
                if (pDirection == left && pDirection == right) {
                    if ((pNeighborState.getValue(PART) != TripleBlockPart.LEFT && pNeighborState.getValue(PART) != TripleBlockPart.RIGHT))
                        return Blocks.AIR.defaultBlockState();
                }
            }

            case RIGHT -> {
                if (pDirection == right) {
                    if (!pNeighborState.is(this) || pNeighborState.getValue(PART) != TripleBlockPart.MIDDLE)
                        return Blocks.AIR.defaultBlockState();
                }
            }
        }

        return super.updateShape(pState, pDirection, pNeighborState, pLevel, pPos, pNeighborPos);
    }

    @Override
    public long getSeed(BlockState pState, BlockPos pPos) {
        Direction facing = pState.getValue(FACING);

        BlockPos center = switch (pState.getValue(PART)) {
            case LEFT -> pPos.relative(facing.getClockWise());
            case MIDDLE -> pPos;
            case RIGHT -> pPos.relative(facing.getCounterClockWise());
        };

        return Mth.getSeed(center.getX(), center.getY(), center.getZ());
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockPos = pPos.below();
        BlockState blockState = pLevel.getBlockState(blockPos);
        return pState.getValue(PART) == TripleBlockPart.MIDDLE ? blockState.isFaceSturdy(pLevel, blockPos, Direction.UP) : blockState.is(this);
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if (pState.is(pNewState.getBlock())) {
            super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
            return;
        }

        Direction facing = pState.getValue(FACING);
        Direction left = facing.getCounterClockWise();
        Direction right = facing.getClockWise();

        BlockPos middlePos = switch (pState.getValue(PART)) {
            case LEFT -> pPos.relative(right);
            case MIDDLE -> pPos;
            case RIGHT -> pPos.relative(left);
        };

        BlockPos leftPos = middlePos.relative(left);
        BlockPos rightPos = middlePos.relative(right);

        removePart(pLevel, leftPos);
        removePart(pLevel, middlePos);
        removePart(pLevel, rightPos);


        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    @Override
    public List<ItemStack> getDrops(BlockState pState, LootParams.Builder pParams) {
        List<ItemStack> churchAltarDrops = new ArrayList<>();

        Block churchAltar = CCBlock.CHURCH_ALTAR.get();

        churchAltarDrops.add(new ItemStack(churchAltar, 1));

        return churchAltarDrops;
    }

    private void removePart(Level pLevel, BlockPos pPos) {
        BlockState blockState = pLevel.getBlockState(pPos);
        if (blockState.is(this)) {
            pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 3);
        }
    }
}
