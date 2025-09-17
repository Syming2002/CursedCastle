package com.syming.cursedcastle.client.event;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.client.dagger.DaggerModel;
import com.syming.cursedcastle.entity.client.bat.familiarbat.FamiliarBatModel;
import com.syming.cursedcastle.entity.client.icespike.IceSpikeModel;
import com.syming.cursedcastle.entity.client.bat.vampirebat.VampireBatModel;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CursedCastleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CCClientEventMod {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(DaggerModel.LAYER_LOCATION, DaggerModel::createBodyLayer);
        event.registerLayerDefinition(FamiliarBatModel.FAMILIAR_BAT_LAYER, FamiliarBatModel::createBodyLayer);
        event.registerLayerDefinition(IceSpikeModel.LAYER_LOCATION, IceSpikeModel::createBodyLayer);
        event.registerLayerDefinition(VampireBatModel.VAMPIRE_BAT_LAYER, VampireBatModel::createBodyLayer);
    }
}
