package com.syming.cursedcastle;

import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.block.blockentity.CCBlockEntity;
import com.syming.cursedcastle.block.head.CCHeadBlock;
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
import com.syming.cursedcastle.potion.CCPotion;
import com.syming.cursedcastle.recipe.BrewingStandRecipe;
import com.syming.cursedcastle.sound.CCSound;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;

import javax.swing.text.html.parser.Entity;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CursedCastleMod.MOD_ID)
public class CursedCastleMod {

    public static final String MOD_ID = "cursedcastle";



    public CursedCastleMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        CCBlock.register(modEventBus);
        CCBlockEntity.register(modEventBus);
        CCCreativeTab.register(modEventBus);
        CCEffect.register(modEventBus);
        CCEnchantment.register(modEventBus);
        CCEntity.register(modEventBus);
        CCFluid.register(modEventBus);
        CCFluidType.register(modEventBus);
        CCItem.register(modEventBus);
        CCLootModifier.register(modEventBus);
        CCPotion.register(modEventBus);
        CCSound.register(modEventBus);
        CCVillager.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
//        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            BrewingStandRecipe.registerRecipes();
        });

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                EntityRenderers.register(CCEntity.ARACHNE.get(), ArachneRenderer::new);
                EntityRenderers.register(CCEntity.BLOOD_SKELETON.get(), SkeletonRenderer::new);
                EntityRenderers.register(CCEntity.CLEAR_BONE_SKELETON.get(), SkeletonRenderer::new);
                EntityRenderers.register(CCEntity.COBWEB_PROJECTILE.get(), NoopRenderer::new);
                EntityRenderers.register(CCEntity.DRACULA.get(), DraculaRenderer::new);
                EntityRenderers.register(CCEntity.FAMILIAR_BAT.get(), FamiliarBatRenderer::new);
                EntityRenderers.register(CCEntity.FIRE_ZOMBIE.get(), FireZombieRenderer::new);
                EntityRenderers.register(CCEntity.FIRE_BREATH.get(), NoopRenderer::new);
                EntityRenderers.register(CCEntity.FIRE_PILLAR.get(), NoopRenderer::new);
                EntityRenderers.register(CCEntity.FRIENDLY_ZOMBIE.get(), ZombieFriendlyRenderer::new);
                EntityRenderers.register(CCEntity.FROST_ZOMBIE.get(), FrostZombieRenderer::new);
                EntityRenderers.register(CCEntity.FROZEN_SHADE.get(), ShadeRenderer::new);
                EntityRenderers.register(CCEntity.GOLDEN_SKELETON.get(), SkeletonRenderer::new);
                EntityRenderers.register(CCEntity.GRIM_REAPER.get(), GrimReaperRenderer::new);
                EntityRenderers.register(CCEntity.HARPY.get(), HarpyRenderer::new);
                EntityRenderers.register(CCEntity.HARPY_FEATHER.get(), HarpyFeatherRenderer::new);
                EntityRenderers.register(CCEntity.HEAT_SHADE.get(), HeatShadeRenderer::new);
                EntityRenderers.register(CCEntity.ICE_BREATH.get(), NoopRenderer::new);
                EntityRenderers.register(CCEntity.ICE_SPIKE.get(), IceSpikeRenderer::new);
                EntityRenderers.register(CCEntity.MEDUSA.get(), MedusaRenderer::new);
                EntityRenderers.register(CCEntity.POISON_BREATH.get(), NoopRenderer::new);
                EntityRenderers.register(CCEntity.POISON_SHADE.get(), ShadeRenderer::new);
                EntityRenderers.register(CCEntity.RUSALKA.get(), RusalkaRenderer::new);
                EntityRenderers.register(CCEntity.THROWING_BONE_SKELETON.get(), SkeletonRenderer::new);
                EntityRenderers.register(CCEntity.THUNDER_BREATH.get(), NoopRenderer::new);
                EntityRenderers.register(CCEntity.THUNDER_SHADE.get(), ShadeRenderer::new);
                EntityRenderers.register(CCEntity.VAMPIRE_BAT.get(), VampireBatRenderer::new);

                EntityRenderers.register(CCEntity.FIRE_HOLY_CROSS.get(), FireHolyCrossRenderer::new);

                EntityRenderers.register(CCEntity.ICE_HOLY_CROSS.get(), IceHolyCrossRenderer::new);


                for (RegistryObject<EntityType<?>> entityThrownItem : CCEntity.ENTITY_TYPES.getEntries()) {



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
            event.registerLayerDefinition(DaggerModel.DAGGER_LAYER, DaggerModel::createBodyLayer);
            event.registerLayerDefinition(DraculaModel.DRACULA_LAYER, DraculaModel::createBodyLayer);
            event.registerLayerDefinition(FamiliarBatModel.FAMILIAR_BAT_LAYER, FamiliarBatModel::createBodyLayer);
            event.registerLayerDefinition(GrimReaperModel.GRIM_REAPER_LAYER, GrimReaperModel::createBodyLayer);
            event.registerLayerDefinition(HarpyFeatherModel.HARPY_FEATHER_LAYER, HarpyFeatherModel::createBodyLayer);
            event.registerLayerDefinition(IceSpikeModel.ICE_SPIKE_LAYER, IceSpikeModel::createBodyLayer);
            event.registerLayerDefinition(VampireBatModel.VAMPIRE_BAT_LAYER, VampireBatModel::createBodyLayer);

            event.registerLayerDefinition(CCHeadBlockEntityRender.DRACULA_HEAD_LAYER, DraculaHeadModel::createDraculaHeadLayer);
        }

        @SubscribeEvent
        public static void registerCCHeadLayers(EntityRenderersEvent.AddLayers event) {
            if (event.getSkin("default") instanceof PlayerRenderer playerRenderer) {
                playerRenderer.addLayer(new CCHeadLayer<>(playerRenderer, Minecraft.getInstance().getEntityModels(), Minecraft.getInstance().getEntityRenderDispatcher().getItemInHandRenderer()));
            }

            if (event.getSkin("slim") instanceof PlayerRenderer playerRenderer) {
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
