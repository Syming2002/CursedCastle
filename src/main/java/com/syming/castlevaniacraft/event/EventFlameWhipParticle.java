package com.syming.castlevaniacraft.event;

import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.item.whips.ItemWhip;
import com.syming.castlevaniacraft.networking.CCNetworking;
import com.syming.castlevaniacraft.networking.MessageSyncEventFlameWhipParticle;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;

import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventFlameWhipParticle {

    @SubscribeEvent
    public void onLeftClickOnBlock(PlayerInteractEvent.LeftClickBlock event) {
        ItemStack stack = event.getItemStack();
        EntityPlayer player = event.getEntityPlayer();

        if (stack.getItem() == CCItem.flameWhip) {
            if (player.world.isRemote) {
                Vec3d look = player.getLookVec();
                double reach = ItemWhip.getReach(player);
                double stepSize = 0.5D;
                double maxSteps = reach / stepSize;

                for (int i = 0; i <= maxSteps; i++) {
                    double factor = i * stepSize;
                    double spawnX = player.posX + look.x * factor;
                    double spawnY = player.posY + player.getEyeHeight() + look.y * factor;
                    double spawnZ = player.posZ + look.z * factor;
                    player.world.spawnParticle(EnumParticleTypes.FLAME, spawnX, spawnY, spawnZ, 0.0D, 0.0D, 0.0D);
                }
            }


            CCNetworking.network.sendToServer(new MessageSyncEventFlameWhipParticle());
            event.getWorld().playSound(event.getEntityPlayer(),
                    event.getEntityPlayer().posX,
                    event.getEntityPlayer().posY,
                    event.getEntityPlayer().posZ,
                    SoundEvents.ENTITY_BLAZE_SHOOT,
                    SoundCategory.MASTER,
                    1.0F,
                    0.5F);

        }
    }

    @SubscribeEvent
    public void onLeftClickEmpty(PlayerInteractEvent.LeftClickEmpty event) {
        ItemStack stack = event.getItemStack();
        EntityPlayer player = event.getEntityPlayer();

        if (stack.getItem() == CCItem.flameWhip) {
            if (player.world.isRemote) {
                Vec3d look = player.getLookVec();
                double reach = ItemWhip.getReach(player);
                double stepSize = 0.5D;
                double maxSteps = reach / stepSize;

                for (int i = 0; i <= maxSteps; i++) {
                    double factor = i * stepSize;
                    double spawnX = player.posX + look.x * factor;
                    double spawnY = player.posY + player.getEyeHeight() + look.y * factor;
                    double spawnZ = player.posZ + look.z * factor;
                    player.world.spawnParticle(EnumParticleTypes.FLAME, spawnX, spawnY, spawnZ, 0.0D, 0.0D, 0.0D);
                }
            }


            CCNetworking.network.sendToServer(new MessageSyncEventFlameWhipParticle());
            event.getWorld().playSound(event.getEntityPlayer(),
                    event.getEntityPlayer().posX,
                    event.getEntityPlayer().posY,
                    event.getEntityPlayer().posZ,
                    SoundEvents.ENTITY_BLAZE_SHOOT,
                    SoundCategory.MASTER,
                    1.0F,
                    0.5F);

        }
    }
}
