package com.syming.castlevaniacraft.block.draculacastlestone;

import com.syming.castlevaniacraft.block.CCBlock;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public abstract class BlockDraculaCastleStoneSlab extends BlockSlab {

    public static final PropertyEnum<DraculaCastleStoneVariant> VARIANT = PropertyEnum.<DraculaCastleStoneVariant>create("variant", DraculaCastleStoneVariant.class);

    public BlockDraculaCastleStoneSlab(String name) {
        super(Material.ROCK);

        IBlockState iblockstate = this.blockState.getBaseState();

        if (!isDouble()) {
            iblockstate = iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
        }

        this.setDefaultState(iblockstate.withProperty(VARIANT, DraculaCastleStoneVariant.DEFAULT));
        CCBlock.BLOCKS.add(this);
    }

    @Override
    public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
        return new ItemStack(CCBlock.DRACULA_CASTLE_STONEBRICK_SLAB_HALF, 1, ((DraculaCastleStoneVariant)state.getValue(VARIANT)).getMetadata());
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(CCBlock.DRACULA_CASTLE_STONEBRICK_SLAB_HALF);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, DraculaCastleStoneVariant.byMetadata(meta & 7));
        if (!isDouble()) {
            iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
        }
        return iblockstate;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | ((DraculaCastleStoneVariant)state.getValue(VARIANT)).getMetadata();

        if (!isDouble() && state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP)
        {
            i |= 8;
        }

        return i;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return this.isDouble() ? new BlockStateContainer(this, new IProperty[] {VARIANT}) : new BlockStateContainer(this, new IProperty[] {HALF, VARIANT});
    }

    /**
     * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
     * returns the metadata of the dropped item based on the old metadata of the block.
     */
    public int damageDropped(IBlockState state)
    {
        return ((DraculaCastleStoneVariant)state.getValue(VARIANT)).getMetadata();
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     * @deprecated call via {@link IBlockState#getMapColor(IBlockAccess,BlockPos)} whenever possible.
     * Implementing/overriding is fine.
     */
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return ((DraculaCastleStoneVariant)state.getValue(VARIANT)).getMapColor();
    }

    /**
     * Returns the slab block name with the type associated with it
     */
    @Override
    public String getTranslationKey(int meta) {
        return super.getTranslationKey();
    }

    @Override
    public IProperty<?> getVariantProperty()
    {
        return VARIANT;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack) {
        return DraculaCastleStoneVariant.byMetadata(stack.getMetadata() & 7);
    }

    public static enum DraculaCastleStoneVariant implements IStringSerializable {

        DEFAULT(0,"dracula_castle_stonebrick_default", MapColor.BLACK);

        private String name;
        private final int meta;
        private static final DraculaCastleStoneVariant[] META_LOOKUP = new DraculaCastleStoneVariant[values().length];
        private final MapColor mapColor;

        DraculaCastleStoneVariant(int meta, String name, MapColor mapColor) {
            this.meta = meta;
            this.name = name;
            this.mapColor = mapColor;
        }

        @Override
        public String getName() {
            return this.name;
        }

        public int getMetadata() {
            return this.meta;
        }

        public MapColor getMapColor()
        {
            return this.mapColor;
        }


        public static DraculaCastleStoneVariant byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        static
        {
            for (DraculaCastleStoneVariant blockstoneslab$enumtype : values())
            {
                META_LOOKUP[blockstoneslab$enumtype.getMetadata()] = blockstoneslab$enumtype;
            }
        }
    }
}
