package com.syming.cursedcastle.network;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.event.CCBossBar;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.UUID;
import java.util.function.Supplier;

public abstract class CCBossInfoPacket implements CustomPacketPayload {
    protected final UUID bossEvent;
    protected final int entityID;

    public CCBossInfoPacket(UUID bossEvent, int entityID) {
        this.bossEvent = bossEvent;
        this.entityID = entityID;
    }

    public static class Display extends CCBossInfoPacket {

        public static final Type<CCBossInfoPacket.Display> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "add_custom_bossbar"));

        public static final StreamCodec<RegistryFriendlyByteBuf, CCBossInfoPacket.Display> STREAM_CODEC = StreamCodec.composite(
                UUIDUtil.STREAM_CODEC,
                CCBossInfoPacket.Display::getBossEvent,
                ByteBufCodecs.INT,
                CCBossInfoPacket.Display::getEntityID,
                CCBossInfoPacket.Display::new);

        public Display(UUID bossEvent, int entityID) {
            super(bossEvent, entityID);
        }

        public void execute(IPayloadContext context) {
            CCBossBar.BOSS_EVENT.put(this.bossEvent, this.entityID);
        }

        @Override
        public Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }

    public static class Remove extends CCBossInfoPacket {

        public static final Type<CCBossInfoPacket.Remove> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "remove_custom_bossbar"));

        public static final StreamCodec<RegistryFriendlyByteBuf, CCBossInfoPacket.Remove> STREAM_CODEC = StreamCodec.composite(
                UUIDUtil.STREAM_CODEC,
                CCBossInfoPacket.Remove::getBossEvent,
                ByteBufCodecs.INT,
                CCBossInfoPacket.Remove::getEntityID,
                CCBossInfoPacket.Remove::new);

        public Remove(UUID bossEvent, int entityID) {
            super(bossEvent, entityID);
        }

        public void execute(IPayloadContext context) {
            CCBossBar.BOSS_EVENT.remove(this.bossEvent);
        }

        @Override
        public Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }

    public UUID getBossEvent() {
        return this.bossEvent;
    }

    public int getEntityID() {
        return this.entityID;
    }
}
