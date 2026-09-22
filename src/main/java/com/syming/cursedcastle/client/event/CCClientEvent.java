package com.syming.cursedcastle.client.event;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.CCKeybinding;
import com.syming.cursedcastle.effect.CCEffect;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.soul.Soul;
import com.syming.cursedcastle.network.CCSpawnFamiliarBat;
import com.syming.cursedcastle.network.CCSpawnFriendlyZombie;
import com.syming.cursedcastle.network.CCTeleportEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.MovementInputUpdateEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import top.theillusivec4.curios.api.CuriosApi;

@EventBusSubscriber(modid = CursedCastleMod.MOD_ID, value = Dist.CLIENT)
public class CCClientEvent {

    @SubscribeEvent
    public static void onKeybindingPressed(ClientTickEvent.Pre event) {
        while (CCKeybinding.INSTANCE.BULLET_SOUL_KEY.consumeClick()) {
            PacketDistributor.sendToServer(new CCSpawnFriendlyZombie());
        }

        while (CCKeybinding.INSTANCE.GUARDIAN_SOUL_KEY.consumeClick()) {
            PacketDistributor.sendToServer(new CCSpawnFamiliarBat());
            PacketDistributor.sendToServer(new CCTeleportEntity());
        }
    }

    @SubscribeEvent
    public static void onInputUpdate(MovementInputUpdateEvent event) {
        Player player = event.getEntity();
        if (player.hasEffect(CCEffect.FREEZE)) {
            event.getInput().leftImpulse = 0;
            event.getInput().forwardImpulse = 0;
            event.getInput().jumping = false;
            event.getInput().shiftKeyDown = false;
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();
        Level level = player.level();
        if (level.isClientSide()) {
            CuriosApi.getCuriosHelper()
                    .findEquippedCurio(CCItem.SOUL_TYPES.get(Soul.ENDERMAN).get(), player)
                    .ifPresent(stringIntegerItemStackImmutableTriple -> {

                    });
        }
    }
}
