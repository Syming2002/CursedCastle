package com.syming.castlevaniacraft.event;

import com.syming.castlevaniacraft.util.ReachFixUtil;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class EventClient {

    private Minecraft minecraft = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            ReachFixUtil.getMouseOver(this.minecraft.getRenderPartialTicks());
        }
    }

}
