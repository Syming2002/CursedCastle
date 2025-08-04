package com.syming.castlevaniacraft.handler;

import com.syming.castlevaniacraft.CastlevaniaCraftMod;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.KeybindContents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.swing.text.JTextComponent;

@Mod.EventBusSubscriber(modid = CastlevaniaCraftMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeHandler {

    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event) {
    }
}
