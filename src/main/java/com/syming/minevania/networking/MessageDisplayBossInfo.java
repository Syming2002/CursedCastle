package com.syming.minevania.networking;

import com.syming.minevania.MinevaniaMod;
import com.syming.minevania.client.gui.MVRenderBossBar;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.UUID;

public class MessageDisplayBossInfo implements IMessage {
    private UUID uuid;
    private int entityID;

    public MessageDisplayBossInfo() {
    }

    public MessageDisplayBossInfo(UUID uuid, int entityID) {
        this.uuid = uuid;
        this.entityID = entityID;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.uuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
        this.entityID = ByteBufUtils.readVarInt(buf, 4);
        new MessageDisplayBossInfo(this.uuid, this.entityID);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, String.valueOf(this.uuid));
        ByteBufUtils.writeVarInt(buf, this.entityID, 4);
    }

    public static class Handler implements IMessageHandler<MessageDisplayBossInfo, IMessage> {

        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(MessageDisplayBossInfo message, MessageContext ctx) {
            final EntityPlayerMP playerMP = (EntityPlayerMP) MinevaniaMod.proxy.getPlayerEntityFromContext(ctx);

            playerMP.getServer().addScheduledTask(
                    new Runnable() {
                        @Override
                        public void run() {
                            MVRenderBossBar.addDraculaBossBar(message.uuid, message.entityID);
                        }
                    }
            );
            return null;
        }
    }
}
