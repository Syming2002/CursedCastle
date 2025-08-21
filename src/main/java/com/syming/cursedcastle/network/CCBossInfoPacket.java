package com.syming.cursedcastle.network;

import com.syming.cursedcastle.client.event.DraculaBossBar;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class CCBossInfoPacket {
    protected final UUID bossEvent;
    protected final int entityID;

    public CCBossInfoPacket(UUID bossEvent, int entityID) {
        this.bossEvent = bossEvent;
        this.entityID = entityID;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeUUID(this.bossEvent);
        buf.writeInt(this.entityID);
    }

    public static class Display extends CCBossInfoPacket {

        public Display(UUID bossEvent, int entityID) {
            super(bossEvent, entityID);
        }

        public static Display decode(FriendlyByteBuf buf) {
            UUID bossEvent = buf.readUUID();
            int entityID = buf.readInt();
            return new Display(bossEvent, entityID);
        }

        public void handle(Supplier<NetworkEvent.Context> context) {
            context.get().enqueueWork(() -> execute(context.get().getSender()));
        }

        public void execute(Player player) {
            DraculaBossBar.BOSS_EVENT.put(this.bossEvent, this.entityID);
        }
    }

    public static class Remove extends CCBossInfoPacket {

        public Remove(UUID bossEvent, int entityID) {
            super(bossEvent, entityID);
        }

        public static Remove decode(FriendlyByteBuf buf) {
            UUID bossEvent = buf.readUUID();
            int entityID = buf.readInt();
            return new Remove(bossEvent, entityID);
        }

        public void handle(Supplier<NetworkEvent.Context> context) {
            context.get().enqueueWork(() -> execute(context.get().getSender()));
        }

        public void execute(Player player) {
            DraculaBossBar.BOSS_EVENT.remove(this.bossEvent);
        }
    }
}
