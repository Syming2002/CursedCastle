package com.syming.castlevaniacraft.block.draculacastlestone;

import com.syming.castlevaniacraft.block.CCBlock;
import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.tab.CCTabs;
import net.minecraft.item.ItemSlab;

public class BlockHalfDraculaCastleStoneSlab extends BlockDraculaCastleStoneSlab {

    public BlockHalfDraculaCastleStoneSlab(String name) {
        super(name);

        setCreativeTab(CCTabs.CREATIVE_TAB);
        setTranslationKey(name);
        setRegistryName(name);

        CCItem.ITEMS.add(new ItemSlab(this, this, CCBlock.DRACULA_CASTLE_STONE_SLAB_DOUBLE).setRegistryName(name));
    }

    @Override
    public boolean isDouble() {
        return false;
    }
}
