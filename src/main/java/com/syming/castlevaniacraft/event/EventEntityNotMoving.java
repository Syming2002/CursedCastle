package com.syming.castlevaniacraft.event;

import com.syming.castlevaniacraft.entity.EntityStoneBreath;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.UUID;

public class EventEntityNotMoving {

    @SubscribeEvent
    public void onHitOnEntityJump(LivingEvent.LivingJumpEvent event) {
        EntityLivingBase hitEntity = event.getEntityLiving();
        UUID id = hitEntity.getUniqueID();
        if (hitEntity != null && EntityStoneBreath.affectedEntities.containsKey(id)) {
            int timeLeft = EntityStoneBreath.affectedEntities.get(id) - 1;

            if (timeLeft <= 0) {
                EntityStoneBreath.affectedEntities.remove(id);
            } else {
                hitEntity.setJumping(false);
                hitEntity.jumpMovementFactor = 0;
                hitEntity.motionY = 0;
                hitEntity.velocityChanged = true;
            }
        }
    }

    @SubscribeEvent
    public void onHitOnEntityMove(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase hitEntity = event.getEntityLiving();
        UUID id = hitEntity.getUniqueID();
        if (hitEntity != null && EntityStoneBreath.affectedEntities.containsKey(id)) {
            int timeLeft = EntityStoneBreath.affectedEntities.get(id) - 1;

            if (timeLeft <= 0) {
                EntityStoneBreath.affectedEntities.remove(id);
            } else {
                EntityStoneBreath.affectedEntities.put(id, timeLeft);
                hitEntity.setVelocity(0, 0, 0);
                hitEntity.velocityChanged = true;
            }
        }
    }
}
