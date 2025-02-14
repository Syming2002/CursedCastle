package com.syming.castlevaniacraft.networking;

import com.syming.castlevaniacraft.castlevaniacraftMod;
import com.syming.castlevaniacraft.api.IExtendedReach;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageExtendedReachAttack implements IMessage {
    private int entityId;

    public MessageExtendedReachAttack() {

    }

    public MessageExtendedReachAttack(int entityId) {
        this.entityId = entityId;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.entityId = ByteBufUtils.readVarInt(buf, 4);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeVarInt(buf, this.entityId, 4);
    }

    public static class Handler implements IMessageHandler<MessageExtendedReachAttack, IMessage> {

        @Override
        public IMessage onMessage(MessageExtendedReachAttack message, MessageContext ctx) {
            final EntityPlayerMP playerMP = (EntityPlayerMP) castlevaniacraftMod.proxy.getPlayerEntityFromContext(ctx);

            playerMP.getServer().addScheduledTask(
                    new Runnable() {
                        @Override
                        public void run() {
                            Entity entity = playerMP.world.getEntityByID(message.entityId);

                            if (playerMP.getHeldItemMainhand() == null) {
                                return;
                            }
                            if (playerMP.getHeldItemMainhand().getItem() instanceof IExtendedReach) {
                                IExtendedReach extendedReach = (IExtendedReach) playerMP.getHeldItemMainhand().getItem();

                                double distanceSq = playerMP.getDistanceSq(entity);

                                double reachSq = extendedReach.getReach() * extendedReach.getReach();

                                if (reachSq >= distanceSq) {
                                    playerMP.attackTargetEntityWithCurrentItem(entity);
                                }
                            }
                            return;
                        }
                    }
            );
            return null;
        }
    }
}
