package com.syming.castlevaniacraft.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.ArrayList;
import java.util.List;

public class DraculaCastleStoneBlock extends Block {
    public DraculaCastleStoneBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        List<ItemStack> draculaStoneDrops = new ArrayList<>();
        List<ItemStack> draculaCobblestoneDrops = new ArrayList<>();

        Block draculaStone = CCBlock.DRACULA_CASTLE_STONE.get();
        Block draculaCobblestone = CCBlock.DRACULA_CASTLE_COBBLESTONE.get();

        draculaCobblestoneDrops.add(new ItemStack(draculaCobblestone, 1));
        draculaStoneDrops.add(new ItemStack(draculaStone, 1));

        return state == CCBlock.DRACULA_CASTLE_STONE.get().defaultBlockState() ? draculaCobblestoneDrops : draculaStoneDrops;
    }
}
