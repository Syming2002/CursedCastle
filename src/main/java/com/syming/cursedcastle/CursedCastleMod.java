package com.syming.cursedcastle;

import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.block.blockentity.CCBlockEntity;
import com.syming.cursedcastle.block.head.CCHeadBlock;
import com.syming.cursedcastle.capability.CCCapabilities;
import com.syming.cursedcastle.client.CCKeybinding;
import com.syming.cursedcastle.client.layer.CCHeadLayer;
import com.syming.cursedcastle.client.model.*;
import com.syming.cursedcastle.client.renderer.blockentity.CCHeadBlockEntityRender;
import com.syming.cursedcastle.client.renderer.entity.*;
import com.syming.cursedcastle.client.renderer.entity.grimreaper.GrimReaperRenderer;
import com.syming.cursedcastle.client.renderer.entity.rusalka.RusalkaRenderer;
import com.syming.cursedcastle.creativetab.CCCreativeTab;
import com.syming.cursedcastle.effect.CCEffect;
import com.syming.cursedcastle.enchantment.CCEnchantment;
import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.villager.CCVillager;
import com.syming.cursedcastle.fluid.CCFluid;
import com.syming.cursedcastle.fluid.CCFluidType;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.soul.Soul;
import com.syming.cursedcastle.item.soul.SoulItem;
import com.syming.cursedcastle.loot.CCLootModifier;
import com.syming.cursedcastle.network.*;
import com.syming.cursedcastle.potion.CCPotion;
import com.syming.cursedcastle.sound.CCSound;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Tiers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Supplier;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CursedCastleMod.MOD_ID)
public class CursedCastleMod {

    public static final String MOD_ID = "cursedcastle";


    public CursedCastleMod(ModContainer modContainer, IEventBus modEventBus) {

        CCBlock.register(modEventBus);
        CCBlockEntity.register(modEventBus);
        CCCapabilities.register(modEventBus);
        CCCreativeTab.register(modEventBus);
        CCEffect.register(modEventBus);
        CCEntity.register(modEventBus);
        CCFluid.register(modEventBus);
        CCFluidType.register(modEventBus);
        CCItem.register(modEventBus);
        CCLootModifier.register(modEventBus);
        CCPotion.register(modEventBus);
        CCSound.register(modEventBus);
        CCVillager.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerPackets);

        NeoForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    public void registerPackets(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar payloadRegistrar = event.registrar("1").optional();

        // Client
        payloadRegistrar.playToClient(CCBossInfoPacket.Display.TYPE, CCBossInfoPacket.Display.STREAM_CODEC, CCBossInfoPacket.Display::execute);
        payloadRegistrar.playToClient(CCBossInfoPacket.Remove.TYPE, CCBossInfoPacket.Remove.STREAM_CODEC, CCBossInfoPacket.Remove::execute);
        payloadRegistrar.playToClient(CCMPData.TYPE, CCMPData.STREAM_CODEC, CCMPData::execute);

        // Server
        payloadRegistrar.playToServer(CCMPPacket.TYPE, CCMPPacket.STREAM_CODEC, CCMPPacket::execute);
        payloadRegistrar.playToServer(CCSpawnFamiliarBat.TYPE, CCSpawnFamiliarBat.STREAM_CODEC, CCSpawnFamiliarBat::execute);
        payloadRegistrar.playToServer(CCSpawnFriendlyZombie.TYPE, CCSpawnFriendlyZombie.STREAM_CODEC, CCSpawnFriendlyZombie::execute);
        payloadRegistrar.playToServer(CCTeleportEntity.TYPE, CCTeleportEntity.STREAM_CODEC, CCTeleportEntity::execute);
        payloadRegistrar.playToServer(CCSpawnParticle.TYPE, CCSpawnParticle.STREAM_CODEC, CCSpawnParticle::execute);

        // Both
        payloadRegistrar.playBidirectional(CCPlayerData.TYPE, CCPlayerData.STREAM_CODEC, CCPlayerData::execute);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                EntityRenderers.register(CCEntity.ARACHNE.get(), ArachneRenderer::new);
                EntityRenderers.register(CCEntity.BLOOD_SKELETON.get(), CCSkeletonRenderer::new);
                EntityRenderers.register(CCEntity.CLEAR_BONE_SKELETON.get(), CCSkeletonRenderer::new);
                EntityRenderers.register(CCEntity.COBWEB_PROJECTILE.get(), NoopRenderer::new);
                EntityRenderers.register(CCEntity.DRACULA.get(), DraculaRenderer::new);
                EntityRenderers.register(CCEntity.FAMILIAR_BAT.get(), FamiliarBatRenderer::new);
                EntityRenderers.register(CCEntity.FIRE_ZOMBIE.get(), CCZombieRenderer::new);
                EntityRenderers.register(CCEntity.FIRE_BREATH.get(), NoopRenderer::new);
                EntityRenderers.register(CCEntity.FIRE_PILLAR.get(), NoopRenderer::new);
                EntityRenderers.register(CCEntity.FRIENDLY_ZOMBIE.get(), CCZombieRenderer::new);
                EntityRenderers.register(CCEntity.FROST_ZOMBIE.get(), CCZombieRenderer::new);
                EntityRenderers.register(CCEntity.FROZEN_SHADE.get(), ShadeRenderer::new);
                EntityRenderers.register(CCEntity.GOLDEN_SKELETON.get(), CCSkeletonRenderer::new);
                EntityRenderers.register(CCEntity.GRIM_REAPER.get(), GrimReaperRenderer::new);
                EntityRenderers.register(CCEntity.HARPY.get(), HarpyRenderer::new);
                EntityRenderers.register(CCEntity.HARPY_FEATHER.get(), HarpyFeatherRenderer::new);
                EntityRenderers.register(CCEntity.HEAT_SHADE.get(), HeatShadeRenderer::new);
                EntityRenderers.register(CCEntity.ICE_BREATH.get(), NoopRenderer::new);
                EntityRenderers.register(CCEntity.ICE_SPIKE.get(), IceSpikeRenderer::new);
                EntityRenderers.register(CCEntity.MEDUSA.get(), MedusaRenderer::new);
                EntityRenderers.register(CCEntity.MEDUSA_SNAKE.get(), MedusaSnakeRenderer::new);
                EntityRenderers.register(CCEntity.POISON_BREATH.get(), NoopRenderer::new);
                EntityRenderers.register(CCEntity.POISON_SHADE.get(), ShadeRenderer::new);
                EntityRenderers.register(CCEntity.RUSALKA.get(), RusalkaRenderer::new);
                EntityRenderers.register(CCEntity.THROWING_BONE_SKELETON.get(), CCSkeletonRenderer::new);
                EntityRenderers.register(CCEntity.THUNDER_BREATH.get(), NoopRenderer::new);
                EntityRenderers.register(CCEntity.THUNDER_SHADE.get(), ShadeRenderer::new);
                EntityRenderers.register(CCEntity.VAMPIRE_BAT.get(), VampireBatRenderer::new);

                EntityRenderers.register(CCEntity.FIRE_HOLY_CROSS.get(), FireHolyCrossRenderer::new);

                EntityRenderers.register(CCEntity.ICE_HOLY_CROSS.get(), IceHolyCrossRenderer::new);


                for (DeferredHolder<EntityType<?>, ? extends EntityType<?>> entityThrownItem : CCEntity.ENTITY_TYPES.getEntries()) {


                    for (Tiers tier : Tiers.values()) {
                        if (entityThrownItem.get() == CCEntity.DAGGER_TYPES.get(tier).get()) {
                            EntityRenderers.register(CCEntity.DAGGER_TYPES.get(tier).get(), DaggerRenderer::new);
                        }

                        if (entityThrownItem.get() == CCEntity.HOLY_CROSS_TYPES.get(tier).get()) {
                            EntityRenderers.register(CCEntity.HOLY_CROSS_TYPES.get(tier).get(), ThrownItemRenderer::new);
                        }


                        if (entityThrownItem.get() == CCEntity.THROWING_AXE_TYPES.get(tier).get()) {
                            EntityRenderers.register(CCEntity.THROWING_AXE_TYPES.get(tier).get(), ThrownItemRenderer::new);
                        }

                        if (entityThrownItem.get() == CCEntity.HOLY_BIBLE.get()) {
                            EntityRenderers.register(CCEntity.HOLY_BIBLE.get(), ThrownItemRenderer::new);
                        }

                        if (entityThrownItem.get() == CCEntity.HOLY_WATER.get()) {
                            EntityRenderers.register(CCEntity.HOLY_WATER.get(), ThrownItemRenderer::new);
                        }

                        if (entityThrownItem.get() == CCEntity.THROWING_BONE.get()) {
                            EntityRenderers.register(CCEntity.THROWING_BONE.get(), ThrownItemRenderer::new);
                        }

                        if (entityThrownItem.get() == CCEntity.THROWING_BLOOD_BONE.get()) {
                            EntityRenderers.register(CCEntity.THROWING_BLOOD_BONE.get(), ThrownItemRenderer::new);
                        }

                        if (entityThrownItem.get() == CCEntity.THROWING_GOLDEN_BONE.get()) {
                            EntityRenderers.register(CCEntity.THROWING_GOLDEN_BONE.get(), ThrownItemRenderer::new);
                        }

                        if (entityThrownItem.get() == CCEntity.SCYTHE_PROJECTILE.get()) {
                            EntityRenderers.register(CCEntity.SCYTHE_PROJECTILE.get(), ThrownItemRenderer::new);
                        }
                    }
                }

                EntityRenderers.register(CCEntity.HOLY_FIRE.get(), NoopRenderer::new);

                Minecraft.getInstance().getItemColors().register((pStack, pTintIndex) -> {
                            if ((pStack.getItem() == CCItem.BLOOD_BUCKET.get() || pStack.getItem() == CCBlock.BLOOD_BLOCK.get().asItem()) && pTintIndex == 1) {
                                return 0xFF701914;
                            }
                            if (pStack.getItem() == CCItem.HOLY_WATER_BUCKET.get() && pTintIndex == 1) {
                                return 0xFF6783BD;
                            }
                            return 0xFFFFFFFF;
                        }, CCItem.BLOOD_BUCKET.get(), CCBlock.BLOOD_BLOCK.get().asItem(),
                        CCItem.HOLY_WATER_BUCKET.get());
            });
        }

        @SubscribeEvent
        public static void registerBlockRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(CCBlockEntity.CC_HEAD.get(), CCHeadBlockEntityRender::new);
        }

        @SubscribeEvent
        public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(ArachneModel.ARACHNE_LAYER, ArachneModel::createBodyLayer);
            event.registerLayerDefinition(CCSkeletonModel.CC_SKELETON_LAYER, CCSkeletonModel::createBodyLayer);
            event.registerLayerDefinition(CCZombieModel.CC_ZOMBIE_LAYER, CCZombieModel::createBodyLayer);
            event.registerLayerDefinition(DaggerModel.DAGGER_LAYER, DaggerModel::createBodyLayer);
            event.registerLayerDefinition(DraculaModel.DRACULA_LAYER, DraculaModel::createBodyLayer);
            event.registerLayerDefinition(FamiliarBatModel.FAMILIAR_BAT_LAYER, FamiliarBatModel::createBodyLayer);
            event.registerLayerDefinition(GrimReaperModel.GRIM_REAPER_LAYER, GrimReaperModel::createBodyLayer);
            event.registerLayerDefinition(HarpyModel.HARPY_LAYER, HarpyModel::createBodyLayer);
            event.registerLayerDefinition(HarpyFeatherModel.HARPY_FEATHER_LAYER, HarpyFeatherModel::createBodyLayer);
            event.registerLayerDefinition(MedusaModel.MEDUSA_LAYER, MedusaModel::createBodyLayer);
            event.registerLayerDefinition(MedusaSnakeModel.MEDUSA_SNAKE_LAYER, MedusaSnakeModel::createBodyLayer);
            event.registerLayerDefinition(ShadeModel.SHADE_LAYER, ShadeModel::createBodyLayer);
            event.registerLayerDefinition(IceSpikeModel.ICE_SPIKE_LAYER, IceSpikeModel::createBodyLayer);
            event.registerLayerDefinition(RusalkaModel.RUSALKA_LAYER, RusalkaModel::createBodyLayer);
            event.registerLayerDefinition(VampireBatModel.VAMPIRE_BAT_LAYER, VampireBatModel::createBodyLayer);

            event.registerLayerDefinition(CCHeadBlockEntityRender.DRACULA_HEAD_LAYER, DraculaHeadModel::createDraculaHeadLayer);
        }

        @SubscribeEvent
        public static void registerCCHeadLayers(EntityRenderersEvent.AddLayers event) {
            if (event.getSkin(PlayerSkin.Model.WIDE) instanceof PlayerRenderer playerRenderer) {
                playerRenderer.addLayer(new CCHeadLayer<>(playerRenderer, Minecraft.getInstance().getEntityModels(), Minecraft.getInstance().getEntityRenderDispatcher().getItemInHandRenderer()));
            }

            if (event.getSkin(PlayerSkin.Model.SLIM) instanceof PlayerRenderer playerRenderer) {
                playerRenderer.addLayer(new CCHeadLayer<>(playerRenderer, Minecraft.getInstance().getEntityModels(), Minecraft.getInstance().getEntityRenderDispatcher().getItemInHandRenderer()));
            }
        }

        @SubscribeEvent
        public static void createSkullModels(EntityRenderersEvent.CreateSkullModels event) {
            event.registerSkullModel(CCHeadBlock.CCHeadTypes.DRACULA, new DraculaHeadModel(event.getEntityModelSet().bakeLayer(CCHeadBlockEntityRender.DRACULA_HEAD_LAYER)));
        }

        @SubscribeEvent
        public static void registerKeys(RegisterKeyMappingsEvent event) {
            event.register(CCKeybinding.INSTANCE.BULLET_SOUL_KEY);
            event.register(CCKeybinding.INSTANCE.GUARDIAN_SOUL_KEY);
        }

        @SubscribeEvent
        public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
            for (Soul soul : Soul.values()) {
                SoulItem soulItem = CCItem.SOUL_TYPES.get(soul).get();
                event.register((pStack, pTintIndex) -> pTintIndex == 0 ? soul.getColor().getRGB() : 0xFFFFFFFF, soulItem);
            }
        }
    }
}
