package com.syming.castlevaniacraft.proxy;

import com.syming.castlevaniacraft.CastlevaniaCraftMod;
import com.syming.castlevaniacraft.block.draculacastlestone.BlockDraculaCastleStoneBrick;
import com.syming.castlevaniacraft.client.gui.CCGui;
import com.syming.castlevaniacraft.client.render.entity.*;
import com.syming.castlevaniacraft.client.render.tile.TileEntityDraculaHeadRenderer;
import com.syming.castlevaniacraft.entity.EntityCrucifix;
import com.syming.castlevaniacraft.entity.EntityLaserBeamTeleportation;
import com.syming.castlevaniacraft.entity.mobs.*;
import com.syming.castlevaniacraft.entity.mobs.boss.*;
import com.syming.castlevaniacraft.entity.mobs.friendly.EntityFriendlyBat;
import com.syming.castlevaniacraft.entity.mobs.friendly.EntityFriendlyZombie;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityFrozenShade;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityHeatShade;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityPoisonShade;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityThunderShade;
import com.syming.castlevaniacraft.entity.projectiles.*;
import com.syming.castlevaniacraft.entity.projectiles.subweapons.*;
import com.syming.castlevaniacraft.entity.projectiles.subweapons.EntityHolyCross;
import com.syming.castlevaniacraft.block.CCBlock;
import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.integration.jer.CCJerIntegration;
import com.syming.castlevaniacraft.item.souls.EnumBulletSoul;
import com.syming.castlevaniacraft.item.souls.EnumGuardianSoul;
import com.syming.castlevaniacraft.particle.ParticlePoisonBreath;
import com.syming.castlevaniacraft.tile.TileEntityDraculaHead;
import com.syming.castlevaniacraft.util.CCReferences;
import com.syming.castlevaniacraft.util.handler.RenderHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClientProxy extends CommonProxy {

    private static int nextParticleID = 48;

    @Override
    public void registerItemRenderer(Item item, int meta, String id)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }

    @Override
    public void registerItemVariantsRenderer(Item item, int meta, String fileName) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(CCReferences.MODID, fileName), "inventory"));
    }


    @SuppressWarnings("deprecation")
    @Override
    public void render() {
        RenderingRegistry.registerEntityRenderingHandler(EntityThrowingAxe.class, new RenderThrowingAxe(Minecraft.getMinecraft().getRenderManager(), Minecraft.getMinecraft().getRenderItem()));
        RenderingRegistry.registerEntityRenderingHandler(EntityHolyCross.class, new RenderHolyCross(Minecraft.getMinecraft().getRenderManager(), Minecraft.getMinecraft().getRenderItem()));

        RenderingRegistry.registerEntityRenderingHandler(EntityFireHolyCross.class, new RenderFireHolyCross(Minecraft.getMinecraft().getRenderManager(), Minecraft.getMinecraft().getRenderItem()));

        RenderingRegistry.registerEntityRenderingHandler(EntityHolyWater.class, new RenderSnowball<EntityHolyWater>(Minecraft.getMinecraft().getRenderManager(), CCItem.holyWater, Minecraft.getMinecraft().getRenderItem()));
        RenderingRegistry.registerEntityRenderingHandler(EntityDagger.class, RenderDagger::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHolyBible.class, new RenderSnowball<EntityHolyBible>(Minecraft.getMinecraft().getRenderManager(), CCItem.holyBible, Minecraft.getMinecraft().getRenderItem()));

        RenderingRegistry.registerEntityRenderingHandler(EntityScytheProjectile.class, new RenderSnowball<EntityScytheProjectile>(Minecraft.getMinecraft().getRenderManager(), CCItem.animScytheProjectile, Minecraft.getMinecraft().getRenderItem()));
        RenderingRegistry.registerEntityRenderingHandler(EntityThrowingBoneProjectile.class, new RenderSnowball<EntityThrowingBoneProjectile>(Minecraft.getMinecraft().getRenderManager(), CCItem.throwingBone, Minecraft.getMinecraft().getRenderItem()));

        RenderingRegistry.registerEntityRenderingHandler(EntityVampireBat.class, RenderVampireBat::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityBloodSkeleton.class, RenderBloodSkeleton::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCatoblepas.class, RenderCatoblepas::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityCrucifix.class, RenderCrucifix::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDracula.class, RenderDracula::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDraculaSecondForm.class, RenderDraculaSecondForm::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFireHydra.class, RenderFireHydra::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFlameDemon.class, RenderFlameDemon::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFleaMan.class, RenderFleaMan::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFriendlyBat.class, RenderFriendlyBat::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFriendlyZombie.class, RenderFriendlyZombie::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityFrozenShade.class, RenderFrozenShade::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGorgon.class, RenderGorgon::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityGrimReaper.class, RenderGrimReaper::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHarpy.class, RenderHarpy::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHarpyFeather.class, RenderHarpyFeather::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHeatShade.class, RenderHeatShade::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityIceBladeProjectile.class, RenderIceBlade::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityLaserBeamTeleportation.class, RenderLaserBeamTeleportation::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMedusa.class, RenderMedusa::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMedusaSnake.class, RenderMedusaSnake::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityPoisonShade.class, RenderPoisonShade::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRusalka.class, RenderRusalka::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityThunderShade.class, RenderThunderShade::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityZephyr.class, RenderZephyr::new);
    }


    @Override
    public void registerModelBakeryVariants() {
        ModelBakery.registerItemVariants(CCItem.bulletSoul,
                new ResourceLocation(CCReferences.MODID, "bullet_soul.zombie_soul"),
                new ResourceLocation(CCReferences.MODID, "bullet_soul.skeleton_soul"),
                new ResourceLocation(CCReferences.MODID, "bullet_soul.axe_armor_soul"));

        ModelBakery.registerItemVariants(CCItem.guardianSoul,
                new ResourceLocation(CCReferences.MODID, "guardian_soul.bat_soul"));
    }

    @Override
    public void registerItemsVariantsRenderer() {
        for (int i = 0; i < EnumBulletSoul.SoulType.values().length; i++) {
            CastlevaniaCraftMod.proxy.registerItemVariantsRenderer(CCItem.bulletSoul, i, "bullet_soul." + EnumBulletSoul.SoulType.values()[i].getName());
        }

        for (int i = 0; i < EnumGuardianSoul.SoulType.values().length; i++) {
            CastlevaniaCraftMod.proxy.registerItemVariantsRenderer(CCItem.guardianSoul, i, "guardian_soul." + EnumGuardianSoul.SoulType.values()[i].getName());
        }

        for (int i = 0; i < BlockDraculaCastleStoneBrick.EnumType.values().length; i++) {
            CastlevaniaCraftMod.proxy.registerItemVariantsRenderer(Item.getItemFromBlock(CCBlock.DRACULA_CASTLE_STONE_BRICK), i, BlockDraculaCastleStoneBrick.EnumType.values()[i].getName());
        }
    }

    @Override
    public EntityPlayer getPlayerEntityFromContext(MessageContext ctx)
    {
        // Note that if you simply return 'Minecraft.getMinecraft().thePlayer',
        // your packets will not work because you will be getting a client
        // player even when you are on the server! Sounds absurd, but it's true.

        // Solution is to double-check side before returning the player:
        return (ctx.side.isClient() ? Minecraft.getMinecraft().player : ctx.getServerHandler().player);
    }

    @Override
    public World getClientWorld() {
        return FMLClientHandler.instance().getClient().world;
    }

    @Override
    public EntityPlayerSP getPlayer() {
        return FMLClientHandler.instance().getClientPlayerEntity();
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        MinecraftForge.EVENT_BUS.register(new CCGui());

        ParticleManager manager = Minecraft.getMinecraft().effectRenderer;
        if (manager != null) {
            manager.registerParticle(nextParticleID++, new ParticlePoisonBreath.Factory());
        }
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        CCJerIntegration.init();

        RenderHandler.registerTileEntityRenderers();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
