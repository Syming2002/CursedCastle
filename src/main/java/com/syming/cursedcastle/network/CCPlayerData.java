package com.syming.cursedcastle.network;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.capability.CCCapabilities;
import com.syming.cursedcastle.capability.data.IPlayerData;
import com.syming.cursedcastle.capability.data.PlayerData;
import com.syming.cursedcastle.client.gui.ClientMPData;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record CCPlayerData(int holyBeamTicks, boolean isAttackBolt, double playerYPos) implements CustomPacketPayload {

    public static final Type<CCPlayerData> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "player_data"));

    public static final StreamCodec<RegistryFriendlyByteBuf, CCPlayerData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            CCPlayerData::holyBeamTicks,
            ByteBufCodecs.BOOL,
            CCPlayerData::isAttackBolt,
            ByteBufCodecs.DOUBLE,
            CCPlayerData::playerYPos,
            CCPlayerData::new);

    public void execute(IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();

            if (player != null) {
                PlayerData data = player.getData(CCCapabilities.PLAYER_DATA);
                if (data != null) {
                    data.setHolyBeamTicks(holyBeamTicks());
                    data.setBoltAttackEnabled(isAttackBolt());
                    data.setPlayerYPos(playerYPos());
                }
            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
