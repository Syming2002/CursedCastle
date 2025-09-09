package com.syming.cursedcastle;

import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.client.CCKeybinding;
import com.syming.cursedcastle.creativetab.CCCreativeModeTab;
import com.syming.cursedcastle.entity.client.familiarbat.FamiliarBatRenderer;
import com.syming.cursedcastle.entity.client.friendlyzombie.ZombieFriendlyRenderer;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.loot.CCLootModifier;
import com.syming.cursedcastle.sound.CCSound;
import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.client.bloodskeleton.BloodSkeletonRenderer;
import com.syming.cursedcastle.entity.client.dagger.DaggerRenderer;
import com.syming.cursedcastle.entity.client.dracula.DraculaRenderer;
import com.syming.cursedcastle.entity.client.icespike.IceSpikeRender;
import com.syming.cursedcastle.entity.client.shade.ShadeRenderer;
import com.syming.cursedcastle.entity.client.vampirebat.VampireBatRenderer;
import com.syming.cursedcastle.entity.villager.CCVillager;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
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
@Mod(CursedCastleMod.MOD_ID)
public class CursedCastleMod {

    public static final String MOD_ID = "cursedcastle";

    public CursedCastleMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        CCBlock.register(modEventBus);
        CCCreativeModeTab.register(modEventBus);
        CCEntity.register(modEventBus);
        CCItem.register(modEventBus);
        CCLootModifier.register(modEventBus);
        CCSound.register(modEventBus);
        CCVillager.register(modEventBus);

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
                EntityRenderers.register(CCEntity.FAMILIAR_BAT.get(), FamiliarBatRenderer::new);
                EntityRenderers.register(CCEntity.FIRE_BREATH.get(), NoopRenderer::new);
                EntityRenderers.register(CCEntity.FIRE_PILLAR.get(), NoopRenderer::new);
                EntityRenderers.register(CCEntity.FRIENDLY_ZOMBIE.get(), ZombieFriendlyRenderer::new);
                EntityRenderers.register(CCEntity.FROZEN_SHADE.get(), ShadeRenderer::new);
                EntityRenderers.register(CCEntity.HEAT_SHADE.get(), ShadeRenderer::new);
                EntityRenderers.register(CCEntity.ICE_BREATH.get(), NoopRenderer::new);
                EntityRenderers.register(CCEntity.ICE_SPIKE.get(), IceSpikeRender::new);
                EntityRenderers.register(CCEntity.POISON_BREATH.get(), NoopRenderer::new);
                EntityRenderers.register(CCEntity.POISON_SHADE.get(), ShadeRenderer::new);
                EntityRenderers.register(CCEntity.THUNDER_BREATH.get(), NoopRenderer::new);
                EntityRenderers.register(CCEntity.THUNDER_SHADE.get(), ShadeRenderer::new);
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

        @SubscribeEvent
        public static void registerKeys(RegisterKeyMappingsEvent event) {
            event.register(CCKeybinding.INSTANCE.BULLET_SOUL_KEY);
            event.register(CCKeybinding.INSTANCE.GUARDIAN_SOUL_KEY);
        }
    }
}
