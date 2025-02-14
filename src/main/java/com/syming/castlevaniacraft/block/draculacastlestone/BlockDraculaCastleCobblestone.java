package com.syming.castlevaniacraft.block.draculacastlestone;

import com.syming.castlevaniacraft.init.CCBlock;
import com.syming.castlevaniacraft.item.MVItem;
import com.syming.castlevaniacraft.tab.CCTabs;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class BlockDraculaCastleCobblestone extends Block {
    public BlockDraculaCastleCobblestone(String name) {
        super(Material.ROCK, MapColor.BLACK);
        this.setRegistryName(name);
        this.setTranslationKey(name);

        this.setCreativeTab(CCTabs.CREATIVE_TAB);
        this.setHardness(2.5F);
        this.setResistance(12.0F);
        this.setSoundType(SoundType.STONE);

        CCBlock.BLOCKS.add(this);
        MVItem.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }
}
