package com.syming.cursedcastle.network;

import com.syming.cursedcastle.mp.PlayerMP;
import com.syming.cursedcastle.mp.PlayerMPProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CCMPPacket {

    public CCMPPacket() {
    }

    public CCMPPacket(FriendlyByteBuf buf) {
    }

    public void toBytes(FriendlyByteBuf buf) {

    }


    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            ServerLevel level = player.serverLevel();
            PlayerMP playerMP = player.getCapability(PlayerMPProvider.PLAYER_MP).orElseGet(PlayerMP::new);

            if (playerMP.getCurrentMP() < playerMP.getMaxMP()) {
                player.getCapability(PlayerMPProvider.PLAYER_MP).ifPresent(mp -> {
                    mp.addMP(5);
                    CCPacketHandler.sendToPlayer(new CCMPData(mp.getCurrentMP()), player);
                });
            }
        });

        return true;
    }
}
