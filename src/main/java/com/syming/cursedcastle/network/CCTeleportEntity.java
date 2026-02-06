package com.syming.cursedcastle.network;


import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.soul.Soul;
import com.syming.cursedcastle.item.soul.SoulItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.function.Supplier;

public class CCTeleportEntity {

    public CCTeleportEntity() {
    }

    public static void encode(CCTeleportEntity msg, FriendlyByteBuf buf) {
    }

    public static CCTeleportEntity decode(FriendlyByteBuf buf) {
        return new CCTeleportEntity();
    }


    public static class Handler {
        public Handler() {
        }

        public static void handle(CCTeleportEntity msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                ServerPlayer player = ctx.get().getSender();
                if (player != null) {

                }
            });
            ctx.get().setPacketHandled(true);
        }


    }
}
