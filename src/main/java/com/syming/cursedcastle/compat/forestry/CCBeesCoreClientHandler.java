package com.syming.cursedcastle.compat.forestry;

import forestry.api.client.IClientModuleHandler;
import forestry.apiculture.features.ApicultureItems;
import forestry.core.models.ClientManager;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class CCBeesCoreClientHandler implements IClientModuleHandler {

    @Override
    public void registerEvents(IEventBus modBus) {
        modBus.addListener(CCBeesCoreClientHandler::registerItemColors);
    }

    private static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register(ClientManager.FORESTRY_ITEM_COLOR,
                ApicultureItems.BEE_LARVAE.item(),
                ApicultureItems.BEE_DRONE.item(),
                ApicultureItems.BEE_PRINCESS.item(),
                ApicultureItems.BEE_QUEEN.item());
        event.register(ClientManager.FORESTRY_ITEM_COLOR, CCBeesApicultureItems.CC_BEES_COMB.itemArray());
    }
}
