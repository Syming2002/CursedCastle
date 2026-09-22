package com.syming.cursedcastle.network;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.event.CCBossBar;
import com.syming.cursedcastle.client.gui.ClientMPData;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.function.Supplier;

public record CCMPData(float currentMP) implements CustomPacketPayload {

    public static final Type<CCMPData> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "mp_data"));

    public static final StreamCodec<RegistryFriendlyByteBuf, CCMPData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.FLOAT,
            CCMPData::currentMP,
            CCMPData::new);

    public void execute(IPayloadContext context) {
        context.enqueueWork(() -> {
            ClientMPData.setPlayerMP(this.currentMP());
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
