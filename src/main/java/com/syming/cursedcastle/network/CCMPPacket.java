package com.syming.cursedcastle.network;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.capability.CCCapabilities;
import com.syming.cursedcastle.capability.data.mp.PlayerMP;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record CCMPPacket() implements CustomPacketPayload {

    public static final Type<CCMPPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "mp_packet"));

    public static final StreamCodec<RegistryFriendlyByteBuf, CCMPPacket> STREAM_CODEC = StreamCodec.unit(new CCMPPacket());

    public void execute(IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (player == null) return;
            PlayerMP playerMP = player.getData(CCCapabilities.PLAYER_MP);

            if (playerMP == null) return;


            if (playerMP.getCurrentMP() < playerMP.getMaxMP()) {
                if (player instanceof ServerPlayer serverPlayer) {
                    PacketDistributor.sendToPlayer(serverPlayer, new CCMPData(playerMP.getCurrentMP()));
                }
            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
