package com.syming.castlevaniacraft.event;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventImmobileEntityLiving {

    @SubscribeEvent
    public void onHitOnEntity(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntityLiving() instanceof EntityLiving) {
            EntityLiving living = (EntityLiving) event.getEntityLiving();
            if (living.isAIDisabled()) {
                int ticksLeft = living.getEntityData().getInteger("NoAITicks");
                if (ticksLeft <= 0) {
                    living.setNoAI(false);
                } else {
                    living.getEntityData().setInteger("NoAITicks", ticksLeft - 1);
                }
            }
        } else if (event.getEntityLiving() instanceof EntityPlayer player) {
            if (player.motionX == 0.0D && player.motionY == 0.0D && player.motionZ == 0.0D) {
                int ticksLeft = player.getEntityData().getInteger("PlayerCannotMove");
                if (ticksLeft <= 0) {
                    player.setVelocity(player.motionX, player.motionY, player.motionZ);
                    player.setJumping(true);
                } else {
                    player.getEntityData().setInteger("PlayerCannotMove", ticksLeft - 1);
                }
            }
        }
    }
}
