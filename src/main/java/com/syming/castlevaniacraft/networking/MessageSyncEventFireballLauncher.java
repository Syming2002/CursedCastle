package com.syming.castlevaniacraft.networking;

import com.syming.castlevaniacraft.item.MVItem;
import com.syming.castlevaniacraft.item.whips.vampirekiller.ItemVampireKillerBulletAttachment;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSyncEventFireballLauncher implements IMessage {

    public MessageSyncEventFireballLauncher() {
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    public static class Handler implements IMessageHandler<MessageSyncEventFireballLauncher, IMessage> {

        @Override
        public IMessage onMessage(MessageSyncEventFireballLauncher message, MessageContext ctx) {
            EntityPlayerMP playerMP = (EntityPlayerMP) ctx.getServerHandler().player;

            playerMP.getServer().addScheduledTask(() -> {
                if (playerMP.getHeldItemMainhand().getItem() == MVItem.vampireKillerBulletAttachment) {
                    launchSmallFireball(playerMP);
                }
            });
            return null;
        }

        private void launchSmallFireball(EntityPlayerMP playerMP) {
            if (!playerMP.world.isRemote) {
                Vec3d look = playerMP.getLookVec();
                double speed = 0.5D;
                double motionX = look.x * speed;
                double motionY = look.y * speed;
                double motionZ = look.z * speed;

                double spawnX = playerMP.posX + look.x * 1.5D;
                double spawnY = playerMP.posY + playerMP.getEyeHeight();
                double spawnZ = playerMP.posZ + look.z * 1.5D;

                EntitySmallFireball smallFireball = new EntitySmallFireball(
                        playerMP.world);

                smallFireball.setPosition(
                        spawnX,
                        spawnY,
                        spawnZ);

                smallFireball.accelerationX = motionX * 0.1D;
                smallFireball.accelerationY = motionY * 0.1D;
                smallFireball.accelerationZ = motionZ * 0.1D;
                playerMP.world.spawnEntity(smallFireball);

                playerMP.getCooldownTracker().setCooldown(playerMP.getHeldItemMainhand().getItem(), 100);
            }
        }
    }
}
