package com.syming.cursedcastle;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@EventBusSubscriber(modid = CursedCastleMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
    }
}
