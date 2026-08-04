package com.syming.cursedcastle.network;

import com.syming.cursedcastle.data.IPlayerData;
import com.syming.cursedcastle.data.PlayerData;
import com.syming.cursedcastle.data.PlayerDataProvider;
import com.syming.cursedcastle.item.CCItem;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CCPlayerData {

    private int holyBeamTicks;
    private boolean isAttackBolt;

    public CCPlayerData(IPlayerData playerData) {
        this.holyBeamTicks = playerData.getHolyBeamTicks();
        this.isAttackBolt = playerData.isBoltAttackEnabled();
    }

    public CCPlayerData(FriendlyByteBuf buffer) {
        this.holyBeamTicks = buffer.readInt();
        this.isAttackBolt = buffer.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeInt(this.holyBeamTicks);
        buffer.writeBoolean(this.isAttackBolt);
    }

    public static void handle(CCPlayerData msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;

            if (player != null) {
                IPlayerData data = player.getCapability(PlayerDataProvider.PLAYER_DATA).orElseGet(PlayerData::new);

                data.setHolyBeamTicks(msg.holyBeamTicks);
                data.setBoltAttackEnabled(msg.isAttackBolt);

            }
        });
        ctx.get().setPacketHandled(true);
    }
}
