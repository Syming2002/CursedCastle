package com.syming.castlevaniacraft.event;

import com.syming.castlevaniacraft.world.gen.structure.cave.RusalkaCave;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class EventCommon {

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END && !event.world.isRemote && event.world instanceof WorldServer) {
            RusalkaCave.processQueue((WorldServer) event.world);
        }
    }

}
