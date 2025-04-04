package com.syming.castlevaniacraft.event;

import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.networking.CCNetworking;
import com.syming.castlevaniacraft.networking.MessageSyncEventFireballLauncher;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventFireballLauncher {

    @SubscribeEvent
    public void onLeftClickOnBlock(PlayerInteractEvent.LeftClickBlock event) {
        ItemStack stack = event.getItemStack();

        if (!event.getWorld().isRemote) return;

        if (stack.getItem() == CCItem.vampireKillerBulletAttachment) {
            CCNetworking.network.sendToServer(new MessageSyncEventFireballLauncher());
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

        if (!event.getWorld().isRemote) return;

        if (stack.getItem() == CCItem.vampireKillerBulletAttachment) {
            CCNetworking.network.sendToServer(new MessageSyncEventFireballLauncher());
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
