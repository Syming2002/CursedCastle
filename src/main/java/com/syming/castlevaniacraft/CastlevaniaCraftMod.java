package com.syming.castlevaniacraft;

import com.syming.castlevaniacraft.block.CCBlock;
import com.syming.castlevaniacraft.creativetab.CCCreativeModeTab;
import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.sound.CCSound;
import com.syming.castlevaniacraft.world.entity.CCEntity;
import com.syming.castlevaniacraft.world.entity.client.bloodskeleton.BloodSkeletonRenderer;
import com.syming.castlevaniacraft.world.entity.client.dagger.DaggerRenderer;
import com.syming.castlevaniacraft.world.entity.client.dracula.DraculaRenderer;
import com.syming.castlevaniacraft.world.entity.client.vampirebat.VampireBatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CastlevaniaCraftMod.MOD_ID)
public class CastlevaniaCraftMod {

    public static final String MOD_ID = "castlevaniacraft";

    public CastlevaniaCraftMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        CCBlock.register(modEventBus);
        CCCreativeModeTab.register(modEventBus);
        CCEntity.register(modEventBus);
        CCItem.register(modEventBus);
        CCSound.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                EntityRenderers.register(CCEntity.BLOOD_SKELETON.get(), BloodSkeletonRenderer::new);
                EntityRenderers.register(CCEntity.DRACULA.get(), DraculaRenderer::new);
                EntityRenderers.register(CCEntity.VAMPIRE_BAT.get(), VampireBatRenderer::new);

                for (Tiers tier : Tiers.values()) {
                    EntityRenderers.register(CCEntity.DAGGER_TYPES.get(tier).get(), DaggerRenderer::new);
                }

                for (Tiers tier : Tiers.values()) {
                    EntityRenderers.register(CCEntity.HOLY_CROSS_TYPES.get(tier).get(), ThrownItemRenderer::new);
                }

                EntityRenderers.register(CCEntity.HOLY_BIBLE.get(), ThrownItemRenderer::new);
                EntityRenderers.register(CCEntity.HOLY_WATER.get(), ThrownItemRenderer::new);

                for (Tiers tier : Tiers.values()) {
                    EntityRenderers.register(CCEntity.THROWING_AXE_TYPES.get(tier).get(), ThrownItemRenderer::new);
                }
            });
        }
    }
}
