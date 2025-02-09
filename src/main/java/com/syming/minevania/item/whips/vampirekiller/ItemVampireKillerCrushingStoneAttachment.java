package com.syming.minevania.item.whips.vampirekiller;

import com.google.common.collect.Sets;
import com.syming.minevania.item.MVItem;
import com.syming.minevania.item.whips.ItemWhipTool;
import com.syming.minevania.tab.CCTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import java.util.Set;

public class ItemVampireKillerCrushingStoneAttachment extends ItemWhipTool {

//    private ItemStackHandler handler;

    public static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(
            Blocks.ACTIVATOR_RAIL,
            Blocks.COAL_ORE,
            Blocks.COBBLESTONE,
            Blocks.DETECTOR_RAIL,
            Blocks.DIAMOND_BLOCK,
            Blocks.DIAMOND_ORE,
            Blocks.DOUBLE_STONE_SLAB,
            Blocks.GOLDEN_RAIL,
            Blocks.GOLD_BLOCK,
            Blocks.GOLD_ORE,
            Blocks.ICE,
            Blocks.IRON_BLOCK,
            Blocks.IRON_ORE,
            Blocks.LAPIS_BLOCK,
            Blocks.LAPIS_ORE,
            Blocks.LIT_REDSTONE_ORE,
            Blocks.MOSSY_COBBLESTONE,
            Blocks.NETHERRACK,
            Blocks.PACKED_ICE,
            Blocks.RAIL,
            Blocks.REDSTONE_ORE,
            Blocks.SANDSTONE,
            Blocks.RED_SANDSTONE,
            Blocks.STONE,
            Blocks.STONE_SLAB,
            Blocks.STONE_BUTTON,
            Blocks.STONE_PRESSURE_PLATE,
            Blocks.PLANKS,
            Blocks.BOOKSHELF,
            Blocks.LOG,
            Blocks.LOG2,
            Blocks.CHEST,
            Blocks.PUMPKIN,
            Blocks.LIT_PUMPKIN,
            Blocks.MELON_BLOCK,
            Blocks.LADDER,
            Blocks.WOODEN_BUTTON,
            Blocks.WOODEN_PRESSURE_PLATE,
            Blocks.CLAY,
            Blocks.DIRT,
            Blocks.FARMLAND,
            Blocks.GRASS,
            Blocks.GRAVEL,
            Blocks.MYCELIUM,
            Blocks.SAND,
            Blocks.SNOW,
            Blocks.SNOW_LAYER,
            Blocks.SOUL_SAND,
            Blocks.GRASS_PATH,
            Blocks.CONCRETE_POWDER);

    public ItemVampireKillerCrushingStoneAttachment(String name, float attackDamage, float attackSpeed, float efficiency) {
        super(attackDamage, attackSpeed, efficiency, EFFECTIVE_ON);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);
        setContainerItem(MVItem.vampireKiller);

        MVItem.ITEMS.add(this);
    }

    @Override
    public boolean canHarvestBlock(IBlockState blockIn) {
        Block block = blockIn.getBlock();

        //Shovel
        if (block == Blocks.SNOW_LAYER || block == Blocks.SNOW) {
            return true;
        }

        //Pickaxe
        if (block == Blocks.OBSIDIAN) {
            return true;
        } else if (block == Blocks.DIAMOND_BLOCK || block == Blocks.DIAMOND_ORE) {
            return true;
        } else if (block == Blocks.EMERALD_ORE || block == Blocks.EMERALD_BLOCK) {
            return true;
        } else if (block == Blocks.GOLD_BLOCK || block == Blocks.GOLD_ORE) {
            return true;
        } else if (block == Blocks.IRON_BLOCK || block == Blocks.IRON_ORE) {
            return true;
        } else if (block == Blocks.LAPIS_BLOCK || block == Blocks.LAPIS_ORE) {
            return true;
        } else if (block == Blocks.REDSTONE_ORE || block == Blocks.LIT_REDSTONE_ORE) {
            return true;
        }
        Material material = blockIn.getMaterial();

        if (material == Material.ROCK) {
            return true;
        } else if (material == Material.IRON) {
            return true;
        }
        return material == Material.ANVIL;
    }

    @Override
    public float getReach() {
        return 12.0F;
    }
}
