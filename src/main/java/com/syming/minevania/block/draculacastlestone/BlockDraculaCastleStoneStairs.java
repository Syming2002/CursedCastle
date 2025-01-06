package com.syming.minevania.block.draculacastlestone;

import com.syming.minevania.init.CCBlock;
import com.syming.minevania.item.CCItem;
import com.syming.minevania.tab.CCTabs;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;

public class BlockDraculaCastleStoneStairs extends BlockStairs {
    public BlockDraculaCastleStoneStairs(String name, IBlockState modelState) {
        super(modelState);

        this.setCreativeTab(CCTabs.CREATIVE_TAB);

        this.setHardness(2.5F);
        this.setResistance(12.0F);
        this.setSoundType(SoundType.STONE);

        this.setTranslationKey(name);
        this.setRegistryName(name);

        CCBlock.BLOCKS.add(this);
        CCItem.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }
}
