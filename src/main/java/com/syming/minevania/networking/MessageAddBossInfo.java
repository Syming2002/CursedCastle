package com.syming.minevania.networking;

import com.syming.minevania.client.gui.MVRenderBossBar;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketUpdateBossInfo;
import net.minecraft.world.BossInfo;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.UUID;

public class MessageAddBossInfo implements IMessage {
    private UUID uuid;
    private int entityID;

    public MessageAddBossInfo() {
    }

    public MessageAddBossInfo(UUID uuid, int entityID) {
        this.uuid = uuid;
        this.entityID = entityID;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.uuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
        this.entityID = ByteBufUtils.readVarInt(buf, 4);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, String.valueOf(this.uuid));
        ByteBufUtils.writeVarInt(buf, this.entityID, 4);
    }

    public static class Handler implements IMessageHandler<MessageAddBossInfo, IMessage> {

        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(MessageAddBossInfo message, MessageContext ctx) {
            if (ctx.side == Side.CLIENT) {
                Minecraft.getMinecraft().addScheduledTask(() -> processMessage(message));
            }
            return null;
        }

        void processMessage(MessageAddBossInfo messageBossInfo) {
            MVRenderBossBar.BOSS_MAP.put(messageBossInfo.uuid, messageBossInfo.entityID);
        }
    }
}
