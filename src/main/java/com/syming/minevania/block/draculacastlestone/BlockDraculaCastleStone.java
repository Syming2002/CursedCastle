package com.syming.minevania.block.draculacastlestone;

import com.syming.minevania.init.CCBlock;
import com.syming.minevania.item.MVItem;
import com.syming.minevania.tab.CCTabs;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import java.util.Random;

public class BlockDraculaCastleStone extends Block {

    public BlockDraculaCastleStone(String name) {
        super(Material.ROCK, MapColor.BLACK);

        this.setTranslationKey(name);
        this.setRegistryName(name);
        this.setCreativeTab(CCTabs.CREATIVE_TAB);

        this.setHardness(2.5F);
        this.setResistance(12.0F);
        this.setSoundType(SoundType.STONE);

        CCBlock.BLOCKS.add(this);
        MVItem.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return state == CCBlock.DRACULA_CASTLE_STONE.getDefaultState() ? Item.getItemFromBlock(CCBlock.DRACULA_CASTLE_COBBLESTONE) : Item.getItemFromBlock(CCBlock.DRACULA_CASTLE_STONE);
    }
}
