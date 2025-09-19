package com.syming.cursedcastle;

import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.client.CCKeybinding;
import com.syming.cursedcastle.creativetab.CCCreativeModeTab;
import com.syming.cursedcastle.effect.CCEffect;
import com.syming.cursedcastle.enchantment.CCEnchantment;
import com.syming.cursedcastle.entity.client.bat.familiarbat.FamiliarBatRender;
import com.syming.cursedcastle.entity.client.friendlyzombie.ZombieFriendlyRender;
import com.syming.cursedcastle.entity.client.skeleton.SkeletonRender;
import com.syming.cursedcastle.fluid.CCFluid;
import com.syming.cursedcastle.fluid.CCFluidType;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.loot.CCLootModifier;
import com.syming.cursedcastle.potion.CCPotion;
import com.syming.cursedcastle.recipe.BrewingStandRecipe;
import com.syming.cursedcastle.sound.CCSound;
import com.syming.cursedcastle.entity.CCEntity;
import com.syming.cursedcastle.entity.client.dagger.DaggerRender;
import com.syming.cursedcastle.entity.client.dracula.DraculaRender;
import com.syming.cursedcastle.entity.client.icespike.IceSpikeRender;
import com.syming.cursedcastle.entity.client.shade.ShadeRender;
import com.syming.cursedcastle.entity.client.bat.vampirebat.VampireBatRender;
import com.syming.cursedcastle.entity.villager.CCVillager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.joml.Vector3f;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CursedCastleMod.MOD_ID)
public class CursedCastleMod {

    public static final String MOD_ID = "cursedcastle";

    public CursedCastleMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        CCBlock.register(modEventBus);
        CCCreativeModeTab.register(modEventBus);
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
                EntityRenderers.register(CCEntity.BLOOD_SKELETON.get(), SkeletonRender::new);
                EntityRenderers.register(CCEntity.DRACULA.get(), DraculaRender::new);
                EntityRenderers.register(CCEntity.FAMILIAR_BAT.get(), FamiliarBatRender::new);
                EntityRenderers.register(CCEntity.FIRE_BREATH.get(), NoopRenderer::new);
                EntityRenderers.register(CCEntity.FIRE_PILLAR.get(), NoopRenderer::new);
                EntityRenderers.register(CCEntity.FRIENDLY_ZOMBIE.get(), ZombieFriendlyRender::new);
                EntityRenderers.register(CCEntity.FROZEN_SHADE.get(), ShadeRender::new);
                EntityRenderers.register(CCEntity.HEAT_SHADE.get(), ShadeRender::new);
                EntityRenderers.register(CCEntity.ICE_BREATH.get(), NoopRenderer::new);
                EntityRenderers.register(CCEntity.ICE_SPIKE.get(), IceSpikeRender::new);
                EntityRenderers.register(CCEntity.POISON_BREATH.get(), NoopRenderer::new);
                EntityRenderers.register(CCEntity.POISON_SHADE.get(), ShadeRender::new);
                EntityRenderers.register(CCEntity.SKELETON.get(), SkeletonRender::new);
                EntityRenderers.register(CCEntity.THUNDER_BREATH.get(), NoopRenderer::new);
                EntityRenderers.register(CCEntity.THUNDER_SHADE.get(), ShadeRender::new);
                EntityRenderers.register(CCEntity.VAMPIRE_BAT.get(), VampireBatRender::new);

                for (Tiers tier : Tiers.values()) {
                    EntityRenderers.register(CCEntity.DAGGER_TYPES.get(tier).get(), DaggerRender::new);
                }

                for (Tiers tier : Tiers.values()) {
                    EntityRenderers.register(CCEntity.HOLY_CROSS_TYPES.get(tier).get(), ThrownItemRenderer::new);
                }

                EntityRenderers.register(CCEntity.HOLY_BIBLE.get(), ThrownItemRenderer::new);
                EntityRenderers.register(CCEntity.HOLY_WATER.get(), ThrownItemRenderer::new);

                for (Tiers tier : Tiers.values()) {
                    EntityRenderers.register(CCEntity.THROWING_AXE_TYPES.get(tier).get(), ThrownItemRenderer::new);
                }

                EntityRenderers.register(CCEntity.THROWING_BONE.get(), ThrownItemRenderer::new);

                // Fluids Render
                ItemBlockRenderTypes.setRenderLayer(CCFluid.SOURCE_BLOOD.get(), RenderType.translucent());
                ItemBlockRenderTypes.setRenderLayer(CCFluid.FLOWING_BLOOD.get(), RenderType.translucent());

                Minecraft.getInstance().getItemColors().register((pStack, pTintIndex) -> {
                    if (pStack.getItem() == CCItem.BLOOD_BUCKET.get() && pTintIndex == 1) {
                        return 0xFF701914;
                    }
                    return 0xFFFFFFFF;
                }, CCItem.BLOOD_BUCKET.get());
            });
        }

        @SubscribeEvent
        public static void registerKeys(RegisterKeyMappingsEvent event) {
            event.register(CCKeybinding.INSTANCE.BULLET_SOUL_KEY);
            event.register(CCKeybinding.INSTANCE.GUARDIAN_SOUL_KEY);
        }
    }
}
