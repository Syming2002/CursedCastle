package com.syming.castlevaniacraft.block.draculacastlestone;

import com.syming.castlevaniacraft.block.CCBlock;
import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.itemblock.ItemBlockDraculaCastleStoneBrick;
import com.syming.castlevaniacraft.tab.CCTabs;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;

public class BlockDraculaCastleStoneBrick extends Block {

    public static final PropertyEnum<BlockDraculaCastleStoneBrick.EnumType> VARIANT = PropertyEnum.<BlockDraculaCastleStoneBrick.EnumType>create("variant", BlockDraculaCastleStoneBrick.EnumType.class);
    public static final int DEFAULT_META = BlockDraculaCastleStoneBrick.EnumType.DEFAULT.getMetadata();
    public static final int CRACKED_META = BlockDraculaCastleStoneBrick.EnumType.CRACKED.getMetadata();
    public static final int CHISELED_META = BlockDraculaCastleStoneBrick.EnumType.CHISELED.getMetadata();

    public BlockDraculaCastleStoneBrick(String name) {
        super(Material.ROCK, MapColor.BLACK);

        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockDraculaCastleStoneBrick.EnumType.DEFAULT));
        this.setCreativeTab(CCTabs.CREATIVE_TAB);

        this.setHardness(2.5F);
        this.setResistance(12.0F);
        this.setSoundType(SoundType.STONE);

        this.setTranslationKey(name);
        this.setRegistryName(name);

        CCBlock.BLOCKS.add(this);
        CCItem.ITEMS.add(new ItemBlockDraculaCastleStoneBrick(this, new String[]{"dracula_castle_default", "dracula_castle_cracked", "dracula_castle_chiseled"}).setRegistryName(this.getRegistryName()));

    }

    /**
     * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
     * returns the metadata of the dropped item based on the old metadata of the block.
     */
    public int damageDropped(IBlockState state)
    {
        return ((BlockDraculaCastleStoneBrick.EnumType)state.getValue(VARIANT)).getMetadata();
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for (BlockDraculaCastleStoneBrick.EnumType blockstonebrick$enumtype : BlockDraculaCastleStoneBrick.EnumType.values())
        {
            items.add(new ItemStack(this, 1, blockstonebrick$enumtype.getMetadata()));
        }
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockDraculaCastleStoneBrick.EnumType.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((BlockDraculaCastleStoneBrick.EnumType)state.getValue(VARIANT)).getMetadata();
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }

    public static enum EnumType implements IStringSerializable
    {
        DEFAULT(0, "dracula_castle_stonebrick_default", "dracula_castle_stonebrick_default"),
        CRACKED(1, "dracula_castle_stonebrick_cracked", "dracula_castle_stonebrick_cracked"),
        CHISELED(2, "dracula_castle_stonebrick_chiseled", "dracula_castle_stonebrick_chiseled");

        private static final BlockDraculaCastleStoneBrick.EnumType[] META_LOOKUP = new BlockDraculaCastleStoneBrick.EnumType[values().length];
        private final int meta;
        private final String name;
        private final String translationKey;

        private EnumType(int meta, String name, String unlocalizedName)
        {
            this.meta = meta;
            this.name = name;
            this.translationKey = unlocalizedName;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        public String toString()
        {
            return this.name;
        }

        public static BlockDraculaCastleStoneBrick.EnumType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        @Override
        public String getName()
        {
            return this.name;
        }

        public String getTranslationKey()
        {
            return this.translationKey;
        }

        static
        {
            for (BlockDraculaCastleStoneBrick.EnumType blockstonebrick$enumtype : values())
            {
                META_LOOKUP[blockstonebrick$enumtype.getMetadata()] = blockstonebrick$enumtype;
            }
        }
    }
}
