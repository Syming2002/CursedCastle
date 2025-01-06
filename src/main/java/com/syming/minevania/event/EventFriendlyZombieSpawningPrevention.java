package com.syming.minevania.event;

import com.syming.minevania.entity.mobs.friendly.EntityFriendlyZombie;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Iterator;

public class EventFriendlyZombieSpawningPrevention {

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event) {
        if (!event.world.isRemote && event.phase == TickEvent.WorldTickEvent.Phase.START) {
            World world = event.world;
            int currentMobCount = world.countEntities(EntityFriendlyZombie.class);

            // Check if mob count exceeds the limit
            if (currentMobCount > 1) {
                removeNewestMob(world);
            }
        }
    }

    private static void removeNewestMob(World world) {
        Iterator<Entity> iterator = world.loadedEntityList.iterator();
        Entity newestMob = null;
        // Find the newest spawned mob
        while (iterator.hasNext()) {
            Entity entity = iterator.next();
            if (entity instanceof EntityFriendlyZombie && !entity.isDead) {
                if (newestMob == null || entity.ticksExisted < newestMob.ticksExisted) {
                    newestMob = entity;
                }
            }
        }

        if (newestMob != null) {
            newestMob.setDead();
        }
    }

}
