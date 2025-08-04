package com.syming.castlevaniacraft.block;

import com.syming.castlevaniacraft.CastlevaniaCraftMod;
import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.item.whip.LeatherWhipItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class CCBlock {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CastlevaniaCraftMod.MOD_ID);

    public static final RegistryObject<Block> DRACULA_CASTLE_COBBLESTONE = registerBlock("dracula_castle_cobblestone",
            () -> new DraculaCastleCobblestoneBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_BLACK)
                            .strength(2.5F, 12.0F)
                            .sound(SoundType.STONE)));

    public static final RegistryObject<Block> DRACULA_CASTLE_STONE = registerBlock("dracula_castle_stone",
            () -> new DraculaCastleStoneBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_BLACK)
                            .strength(2.5F, 12.0F)
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
