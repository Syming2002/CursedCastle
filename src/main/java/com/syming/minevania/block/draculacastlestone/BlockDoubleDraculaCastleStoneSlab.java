package com.syming.minevania.block.draculacastlestone;

import com.syming.minevania.tab.CCTabs;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockDoubleDraculaCastleStoneSlab extends BlockDraculaCastleStoneSlab {
    public BlockDoubleDraculaCastleStoneSlab(String name, Material materialIn, BlockSlab half) {
        super(name, materialIn, half);

        setCreativeTab(CCTabs.CREATIVE_TAB);
        setTranslationKey(name);
        setRegistryName(name);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune) {
        return Item.getItemFromBlock(this.half);
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        List<ItemStack> drops = new ArrayList<>();
        drops.add(new ItemStack(Item.getItemFromBlock(this.half), 2));
        return drops;
    }

    @Override
    public boolean isDouble() {
        return true;
    }
}
