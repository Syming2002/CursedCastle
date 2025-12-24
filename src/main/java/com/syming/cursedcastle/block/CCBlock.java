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
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CCBlock {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CursedCastleMod.MOD_ID);

    // Liquid Block

    public static final RegistryObject<LiquidBlock> BLOOD_BLOCK = registerBlock("blood_block",
            () -> new LiquidBlock(CCFluid.SOURCE_BLOOD, BlockBehaviour.Properties.copy(Blocks.WATER)));

    public static final RegistryObject<LiquidBlock> HOLY_WATER_BLOCK = registerBlock("holy_water_block",
            () -> new LiquidBlock(CCFluid.SOURCE_HOLY_WATER, BlockBehaviour.Properties.copy(Blocks.WATER).lightLevel(blockState -> 7)));


    // Blocks

    public static final Map<WoodType, RegistryObject<Block>> CRUCIFIX_BLOCK_TYPES = new HashMap<>();

    static {
        for (WoodType woodType : WoodType.values().toList()) {
            CRUCIFIX_BLOCK_TYPES.put(woodType, registerBlock(woodType.name() + '_' + "crucifix",
                    () -> new CrucifixBlock(BlockBehaviour.Properties.of()
                            .sound(SoundType.WOOD).noOcclusion(), woodType)));
        }
    }


    public static final RegistryObject<Block> DRACULA_CASTLE_COBBLESTONE = registerBlock("dracula_castle_cobblestone",
            () -> new DraculaCastleCobblestoneBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_BLACK)
                            .strength(2.0F, 12.0F)
                            .sound(SoundType.STONE)));

    public static final RegistryObject<Block> DRACULA_CASTLE_STONE = registerBlock("dracula_castle_stone",
            () -> new DraculaCastleStoneBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_BLACK)
                            .strength(2.0F, 12.0F)
                            .sound(SoundType.STONE)));

    public static final RegistryObject<Block> DRACULA_CASTLE_STONE_BRICKS = registerBlock("dracula_castle_stone_bricks",
            () -> new DraculaCastleStoneBrickBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_BLACK)
                            .strength(2.0F, 12.0F)
                            .sound(SoundType.STONE)));

    public static final RegistryObject<SlabBlock> DRACULA_CASTLE_STONE_BRICKS_SLAB = registerBlock("dracula_castle_stone_bricks_slab",
            () -> new SlabBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_BLACK)
                            .strength(2.0F, 12.0F)
                            .sound(SoundType.STONE)));

    public static final RegistryObject<Block> DRACULA_HEAD = BLOCKS.register("dracula_head",
            () -> new CCHeadBlock(CCHeadBlock.CCHeadTypes.DRACULA));


    public static final RegistryObject<Block> DRACULA_WALL_HEAD = BLOCKS.register("dracula_wall_head",
            () -> new CCWallHeadBlock(CCHeadBlock.CCHeadTypes.DRACULA));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> registry = BLOCKS.register(name, block);
        registerItemBlock(name, registry);
        return registry;
    }

    private static  <T extends Block> RegistryObject<Item> registerItemBlock(String name, RegistryObject<T> block) {
        return CCItem.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
