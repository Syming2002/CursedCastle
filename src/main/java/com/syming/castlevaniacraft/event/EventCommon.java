package com.syming.castlevaniacraft.event;

import com.syming.castlevaniacraft.advancement.CCAdvancement;
import com.syming.castlevaniacraft.entity.mobs.boss.EntityGrimReaper;
import com.syming.castlevaniacraft.world.gen.structure.cave.RusalkaCave;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class EventCommon {

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END && !event.world.isRemote && event.world instanceof WorldServer) {
            RusalkaCave.processQueue((WorldServer) event.world);
        }
    }

    @SubscribeEvent
    public void onGrimReaperDeath(LivingDeathEvent event) {
        if (event.getEntityLiving() instanceof EntityGrimReaper) {
            if (event.getSource().getImmediateSource() instanceof EntityPlayerMP playerMP) {
                CCAdvancement.GRIM_REAPER_DEATH.trigger(playerMP);
            }
        }
    }

}
