package com.syming.castlevaniacraft.event;

import com.syming.castlevaniacraft.CastlevaniaCraftMod;
import com.syming.castlevaniacraft.network.CCPacketHandler;
import com.syming.castlevaniacraft.world.entity.CCEntity;
import com.syming.castlevaniacraft.world.entity.mob.BloodSkeletonEntity;
import com.syming.castlevaniacraft.world.entity.mob.VampireBatEntity;
import com.syming.castlevaniacraft.world.entity.mob.boss.DraculaEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = CastlevaniaCraftMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CCCommonEvent {


    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            CCPacketHandler.register();
        });
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(CCEntity.BLOOD_SKELETON.get(), BloodSkeletonEntity.createMobAttributes().build());
        event.put(CCEntity.DRACULA.get(), DraculaEntity.createMobAttributes().build());
        event.put(CCEntity.VAMPIRE_BAT.get(), VampireBatEntity.createMobAttributes().build());
    }
}
