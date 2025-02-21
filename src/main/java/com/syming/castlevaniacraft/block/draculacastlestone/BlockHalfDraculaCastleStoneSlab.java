package com.syming.castlevaniacraft.block.draculacastlestone;

import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.tab.CCTabs;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemSlab;

public class BlockHalfDraculaCastleStoneSlab extends BlockDraculaCastleStoneSlab {
    public BlockHalfDraculaCastleStoneSlab(String name, Material materialIn, BlockSlab half, BlockSlab doubleSlab) {
        super(name, materialIn, half);

        setCreativeTab(CCTabs.CREATIVE_TAB);
        setTranslationKey(name);
        setRegistryName(name);

        CCItem.ITEMS.add(new ItemSlab(this, this, doubleSlab).setRegistryName(name));
    }

    @Override
    public boolean isDouble() {
        return false;
    }
}
