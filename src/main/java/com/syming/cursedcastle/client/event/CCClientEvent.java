package com.syming.cursedcastle.client.event;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.world.entity.client.dagger.DaggerModel;
import com.syming.cursedcastle.world.entity.client.icespike.IceSpikeModel;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CursedCastleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CCClientEvent {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(DaggerModel.LAYER_LOCATION, DaggerModel::createBodyLayer);
        event.registerLayerDefinition(IceSpikeModel.LAYER_LOCATION, IceSpikeModel::createBodyLayer);
    }
}
