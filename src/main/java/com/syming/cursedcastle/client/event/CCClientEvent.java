package com.syming.cursedcastle.client.event;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.CCKeybinding;
import com.syming.cursedcastle.effect.CCEffect;
import com.syming.cursedcastle.network.CCPacketHandler;
import com.syming.cursedcastle.network.CCSpawnFamiliarBat;
import com.syming.cursedcastle.network.CCSpawnFriendlyZombie;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.MovementInputUpdateEvent;
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

    @SubscribeEvent
    public static void onInputUpdate(MovementInputUpdateEvent event) {
        Player player = event.getEntity();
        if (player.hasEffect(CCEffect.FREEZE.get())) {
            event.getInput().leftImpulse = 0;
            event.getInput().forwardImpulse = 0;
            event.getInput().jumping = false;
            event.getInput().shiftKeyDown = false;
        }
    }

}
