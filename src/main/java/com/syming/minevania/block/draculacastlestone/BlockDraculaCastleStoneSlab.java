package com.syming.minevania.block.draculacastlestone;

import com.syming.minevania.init.CCBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockDraculaCastleStoneSlab extends BlockSlab {

    protected Block half;
    public static final PropertyEnum<StoneVariant> VARIANT = PropertyEnum.<StoneVariant>create("variant", StoneVariant.class);

    public BlockDraculaCastleStoneSlab(String name, Material materialIn, BlockSlab half) {
        super(materialIn);

        this.useNeighborBrightness = !this.isDouble();

        IBlockState state = this.blockState.getBaseState().withProperty(VARIANT, StoneVariant.DEFAULT);
        if (!this.isDouble()) state = state.withProperty(HALF, EnumBlockHalf.BOTTOM);
        this.half = half;
        CCBlock.BLOCKS.add(this);
    }

    @Override
    public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
        return new ItemStack(this.half);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState state = this.blockState.getBaseState().withProperty(VARIANT, StoneVariant.DEFAULT);
        if (!this.isDouble()) state = state.withProperty(HALF, ((meta & 8) != 0) ? EnumBlockHalf.TOP : EnumBlockHalf.BOTTOM);
        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int meta = 0;
        if (!this.isDouble() && state.getValue(HALF) == EnumBlockHalf.TOP) meta |= 8;
        return meta;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        if (!this.isDouble()) return new BlockStateContainer(this, new IProperty[] {VARIANT, HALF});
        else return new BlockStateContainer(this, new IProperty[]{VARIANT});
    }

    @Override
    public String getTranslationKey(int meta) {
        return super.getTranslationKey();
    }

    @Override
    public IProperty<?> getVariantProperty() {
        return VARIANT;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack) {
        return StoneVariant.DEFAULT;
    }

    public static enum StoneVariant implements IStringSerializable {

        DEFAULT("dracula_castle_stonebrick_default");

        private String name;

        StoneVariant(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }
    }
}
