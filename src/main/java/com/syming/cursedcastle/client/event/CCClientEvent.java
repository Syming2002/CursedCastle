package com.syming.cursedcastle.client.event;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.CCKeybinding;
import com.syming.cursedcastle.effect.CCEffect;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.soul.Soul;
import com.syming.cursedcastle.network.CCPacketHandler;
import com.syming.cursedcastle.network.CCSpawnFamiliarBat;
import com.syming.cursedcastle.network.CCSpawnFriendlyZombie;
import com.syming.cursedcastle.network.CCTeleportEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.MovementInputUpdateEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

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
                CCPacketHandler.sendToServer(new CCTeleportEntity());
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

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Level level = player.level();
        if (player.level().isClientSide() && event.phase == TickEvent.Phase.END) {
            CuriosApi.getCuriosHelper()
                    .findEquippedCurio(CCItem.SOUL_TYPES.get(Soul.ENDERMAN).get(), player)
                    .ifPresent(stringIntegerItemStackImmutableTriple -> {

                    });
        }
    }
}
