package com.syming.cursedcastle.client.event;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.CCKeybinding;
import com.syming.cursedcastle.network.CCPacketHandler;
import com.syming.cursedcastle.network.CCSpawnFamiliarBat;
import com.syming.cursedcastle.network.CCSpawnFriendlyZombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CursedCastleMod.MOD_ID, value = Dist.CLIENT)
public class CCClientEvent {

    @SubscribeEvent
    public static void onKeybindingPressed(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            while (CCKeybinding.INSTANCE.BULLET_SOUL_KEY.consumeClick()) {
                CCPacketHandler.sendToServer(new CCSpawnFriendlyZombie());
            }

            while (CCKeybinding.INSTANCE.GUARDIAN_SOUL_KEY.consumeClick()) {
                CCPacketHandler.sendToServer(new CCSpawnFamiliarBat());
            }
        }
    }

}
