package com.syming.minevania.event;

import com.syming.minevania.item.MVItem;
import com.syming.minevania.networking.MVNetworking;
import com.syming.minevania.networking.MessageSyncEventFireballLauncher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventFireballLauncher {

    @SubscribeEvent
    public void onLeftClick(PlayerInteractEvent.LeftClickEmpty event) {
        EntityPlayer player = event.getEntityPlayer();
        Entity entity = event.getEntity();
        World world = event.getWorld();
        ItemStack stack = event.getItemStack();

        Vec3d smallFireballPosition = new Vec3d(0, player.posY + player.getEyeHeight(), 0);
        smallFireballPosition = smallFireballPosition.rotateYaw((float) Math.toRadians((-player.rotationYaw - 90)));
        smallFireballPosition = smallFireballPosition.add(player.getPositionVector()
                .rotatePitch((float) Math.toRadians(-player.rotationPitch))
                .rotateYaw((float) Math.toRadians(-player.rotationYawHead)));

        double targetX = entity.posX - player.posX;
        double targetY = entity.getEntityBoundingBox().minY + (double) (entity.height / 2.0F) - player.posY;
        double targetZ = entity.posZ - player.posZ;

        float distance = MathHelper.sqrt(targetX * targetX + targetY * targetY + targetZ * targetZ) * 0.5F;

        double directionX = targetX / distance;
        double directionY = targetY / distance;
        double directionZ = targetZ / distance;

        EntitySmallFireball smallFireball = new EntitySmallFireball(player.world, smallFireballPosition.x, smallFireballPosition.y, smallFireballPosition.z, directionX * 0.5, directionY * 0.5, directionZ * 0.5);

        if (stack.getItem() == MVItem.vampireKillerBulletAttachment) {
            if (!world.isRemote) {
                MVNetworking.network.sendToServer(new MessageSyncEventFireballLauncher(entity.getEntityId()));
                world.spawnEntity(smallFireball);
            }
        }
    }

}
