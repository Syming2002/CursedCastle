package com.syming.minevania.networking;

import com.syming.minevania.item.MVItem;
import com.syming.minevania.item.whips.vampirekiller.ItemVampireKillerBulletAttachment;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSyncEventFireballLauncher implements IMessage {

    private int entityId;

    public MessageSyncEventFireballLauncher() {
    }

    public MessageSyncEventFireballLauncher(int entityId) {
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

    public static class Handler implements IMessageHandler<MessageSyncEventFireballLauncher, IMessage> {

        @Override
        public IMessage onMessage(MessageSyncEventFireballLauncher message, MessageContext ctx) {
            EntityPlayerMP playerMP = (EntityPlayerMP) ctx.getServerHandler().player;

            playerMP.getServer().addScheduledTask(
                    new Runnable() {
                        @Override
                        public void run() {

                            Entity entity = playerMP.world.getEntityByID(message.entityId);

                            if (playerMP.getHeldItemMainhand() == null) {
                                return;
                            }
                            if (playerMP.getHeldItemMainhand().getItem() == MVItem.vampireKillerBulletAttachment) {

                                Vec3d smallFireballPosition = new Vec3d(0, playerMP.posY + playerMP.getEyeHeight(), 0);
                                smallFireballPosition = smallFireballPosition.rotateYaw((float) Math.toRadians((-playerMP.rotationYaw - 90)));
                                smallFireballPosition = smallFireballPosition.add(playerMP.getPositionVector()
                                        .rotatePitch((float) Math.toRadians(-playerMP.rotationPitch))
                                        .rotateYaw((float) Math.toRadians(-playerMP.rotationYawHead)));

                                double targetX = entity.posX - playerMP.posX;
                                double targetY = entity.getEntityBoundingBox().minY + (double) (entity.height / 2.0F) - playerMP.posY;
                                double targetZ = entity.posZ - playerMP.posZ;

                                float distance = MathHelper.sqrt(targetX * targetX + targetY * targetY + targetZ * targetZ) * 0.5F;

                                double directionX = targetX / distance;
                                double directionY = targetY / distance;
                                double directionZ = targetZ / distance;

                                EntitySmallFireball smallFireball = new EntitySmallFireball(playerMP.world, smallFireballPosition.x, smallFireballPosition.y, smallFireballPosition.z, directionX * 0.5, directionY * 0.5, directionZ * 0.5);

                                if (!playerMP.world.isRemote) {
                                    playerMP.world.spawnEntity(smallFireball);
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
