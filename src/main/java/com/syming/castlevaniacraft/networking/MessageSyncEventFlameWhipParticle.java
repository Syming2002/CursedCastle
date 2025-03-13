package com.syming.castlevaniacraft.networking;

import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.item.whips.ItemWhip;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSyncEventFlameWhipParticle implements IMessage {

    public MessageSyncEventFlameWhipParticle() {
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    public static class Handler implements IMessageHandler<MessageSyncEventFlameWhipParticle, IMessage> {

        @Override
        public IMessage onMessage(MessageSyncEventFlameWhipParticle message, MessageContext ctx) {
            EntityPlayerMP playerMP = (EntityPlayerMP) ctx.getServerHandler().player;

            playerMP.getServer().addScheduledTask(() -> {
                if (playerMP.getHeldItemMainhand().getItem() == CCItem.flameWhip) {

                }
            });
            return null;
        }
    }
}
