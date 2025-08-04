package com.syming.castlevaniacraft.event;

import com.syming.castlevaniacraft.CastlevaniaCraftMod;
import com.syming.castlevaniacraft.world.entity.client.dagger.DaggerModel;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CastlevaniaCraftMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CCClientEvent {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(DaggerModel.LAYER_LOCATION, DaggerModel::createBodyLayer);
    }
}
