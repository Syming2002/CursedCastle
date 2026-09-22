package com.syming.cursedcastle.block;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.head.CCHeadBlock;
import com.syming.cursedcastle.block.head.CCWallHeadBlock;
import com.syming.cursedcastle.fluid.CCFluid;
import com.syming.cursedcastle.item.CCItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CCBlock {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.Blocks.createBlocks(CursedCastleMod.MOD_ID);

    // Liquid Block

    public static final Supplier<LiquidBlock> BLOOD_BLOCK = registerLiquidBlock("blood_block",
            () -> new LiquidBlock(CCFluid.SOURCE_BLOOD.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));

    public static final Supplier<LiquidBlock> HOLY_WATER_BLOCK = registerLiquidBlock("holy_water_block",
            () -> new LiquidBlock(CCFluid.SOURCE_HOLY_WATER.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).lightLevel(blockState -> 7)));


    // Blocks

    public static final Supplier<Block> CHURCH_ALTAR = registerBlock("church_altar", () -> new ChurchAltarBlock(BlockBehaviour.Properties.of()
            .requiresCorrectToolForDrops()
            .destroyTime(1.0F)));

    public static final Map<WoodType, Supplier<Block>> CRUCIFIX_BLOCK_TYPES = new HashMap<>();

    static {
        for (WoodType woodType : WoodType.values().toList()) {
            if (woodType != null) {
                if (woodType == WoodType.OAK
                        || woodType == WoodType.SPRUCE
                        || woodType == WoodType.BIRCH
                        || woodType == WoodType.JUNGLE
                        || woodType == WoodType.ACACIA
                        || woodType == WoodType.DARK_OAK
                        || woodType == WoodType.CRIMSON
                        || woodType == WoodType.WARPED
                        || woodType == WoodType.MANGROVE
                        || woodType == WoodType.CHERRY
                        || woodType == WoodType.BAMBOO) {
                    CRUCIFIX_BLOCK_TYPES.put(woodType, registerBlock(woodType.name() + '_' + "crucifix",
                            () -> new CrucifixBlock(BlockBehaviour.Properties.of()
                                    .sound(SoundType.WOOD).noOcclusion().destroyTime(1.0F), woodType)));
                }
            }
        }
    }


    public static final Supplier<Block> DRACULA_CASTLE_COBBLESTONE = registerBlock("dracula_castle_cobblestone",
            () -> new DraculaCastleCobblestoneBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_BLACK)
                            .strength(2.0F, 12.0F)
                            .sound(SoundType.STONE)));

    public static final Supplier<Block> DRACULA_CASTLE_STONE = registerBlock("dracula_castle_stone",
            () -> new DraculaCastleStoneBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_BLACK)
                            .strength(2.0F, 12.0F)
                            .sound(SoundType.STONE)));

    public static final Supplier<Block> DRACULA_CASTLE_STONE_BRICKS = registerBlock("dracula_castle_stone_bricks",
            () -> new DraculaCastleStoneBrickBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_BLACK)
                            .strength(2.0F, 12.0F)
                            .sound(SoundType.STONE)));

    public static final Supplier<SlabBlock> DRACULA_CASTLE_STONE_BRICKS_SLAB = registerBlock("dracula_castle_stone_bricks_slab",
            () -> new SlabBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_BLACK)
                            .strength(2.0F, 12.0F)
                            .sound(SoundType.STONE)));

    public static final Supplier<Block> DRACULA_HEAD = BLOCKS.register("dracula_head",
            () -> new CCHeadBlock(CCHeadBlock.CCHeadTypes.DRACULA));


    public static final Supplier<Block> DRACULA_WALL_HEAD = BLOCKS.register("dracula_wall_head",
            () -> new CCWallHeadBlock(CCHeadBlock.CCHeadTypes.DRACULA));

    private static <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> block) {
        Supplier<T> registry = BLOCKS.register(name, block);
        registerItemBlock(name, registry);
        return registry;
    }

    private static <T extends Block> Supplier<T> registerLiquidBlock(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    private static  <T extends Block> Supplier<Item> registerItemBlock(String name, Supplier<T> block) {
        return CCItem.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
