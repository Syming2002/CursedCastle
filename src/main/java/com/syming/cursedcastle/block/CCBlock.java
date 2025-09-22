package com.syming.cursedcastle.block;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.fluid.CCFluid;
import com.syming.cursedcastle.item.CCItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class CCBlock {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CursedCastleMod.MOD_ID);

    public static final RegistryObject<LiquidBlock> BLOOD_BLOCK = registerBlock("blood_block",
            () -> new LiquidBlock(CCFluid.SOURCE_BLOOD, BlockBehaviour.Properties.copy(Blocks.WATER)));

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
