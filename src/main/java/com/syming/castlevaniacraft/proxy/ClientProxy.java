package com.syming.castlevaniacraft.proxy;

import com.syming.castlevaniacraft.castlevaniacraftMod;
import com.syming.castlevaniacraft.block.draculacastlestone.BlockDraculaCastleStoneBrick;
import com.syming.castlevaniacraft.client.gui.MVGui;
import com.syming.castlevaniacraft.client.render.entity.RenderDagger;
import com.syming.castlevaniacraft.client.render.entity.RenderHolyCross;
import com.syming.castlevaniacraft.client.render.entity.RenderThrowingAxe;
import com.syming.castlevaniacraft.entity.projectiles.EntityScytheProjectile;
import com.syming.castlevaniacraft.entity.projectiles.EntityThrowingBoneProjectile;
import com.syming.castlevaniacraft.entity.projectiles.subweapons.*;
import com.syming.castlevaniacraft.entity.projectiles.subweapons.EntityHolyCross;
import com.syming.castlevaniacraft.init.CCBlock;
import com.syming.castlevaniacraft.item.MVItem;
import com.syming.castlevaniacraft.integration.jer.CCJerIntegration;
import com.syming.castlevaniacraft.item.souls.EnumBulletSoul;
import com.syming.castlevaniacraft.item.souls.EnumGuardianSoul;
import com.syming.castlevaniacraft.util.MVReferences;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
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
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerItemRenderer(Item item, int meta, String id)
    {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
    }

    @Override
    public void registerItemVariantsRenderer(Item item, int meta, String fileName) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(MVReferences.MODID, fileName), "inventory"));
    }


    @SuppressWarnings("deprecation")
    @Override
    public void render() {
        RenderingRegistry.registerEntityRenderingHandler(EntityThrowingAxe.class, new RenderThrowingAxe(Minecraft.getMinecraft().getRenderManager(), Minecraft.getMinecraft().getRenderItem()));
        RenderingRegistry.registerEntityRenderingHandler(EntityHolyCross.class, new RenderHolyCross(Minecraft.getMinecraft().getRenderManager(), Minecraft.getMinecraft().getRenderItem()));

        RenderingRegistry.registerEntityRenderingHandler(EntityHolyWater.class, new RenderSnowball<EntityHolyWater>(Minecraft.getMinecraft().getRenderManager(), MVItem.holyWater, Minecraft.getMinecraft().getRenderItem()));
        RenderingRegistry.registerEntityRenderingHandler(EntityDagger.class, RenderDagger::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityHolyBible.class, new RenderSnowball<EntityHolyBible>(Minecraft.getMinecraft().getRenderManager(), MVItem.holyBible, Minecraft.getMinecraft().getRenderItem()));

        RenderingRegistry.registerEntityRenderingHandler(EntityScytheProjectile.class, new RenderSnowball<EntityScytheProjectile>(Minecraft.getMinecraft().getRenderManager(), MVItem.animScytheProjectile, Minecraft.getMinecraft().getRenderItem()));
        RenderingRegistry.registerEntityRenderingHandler(EntityThrowingBoneProjectile.class, new RenderSnowball<EntityThrowingBoneProjectile>(Minecraft.getMinecraft().getRenderManager(), MVItem.throwingBone, Minecraft.getMinecraft().getRenderItem()));
    }


    @Override
    public void registerModelBakeryVariants() {
        ModelBakery.registerItemVariants(MVItem.bulletSoul,
                new ResourceLocation(MVReferences.MODID, "bullet_soul.zombie_soul"),
                new ResourceLocation(MVReferences.MODID, "bullet_soul.skeleton_soul"),
                new ResourceLocation(MVReferences.MODID, "bullet_soul.axe_armor_soul"));

        ModelBakery.registerItemVariants(MVItem.guardianSoul,
                new ResourceLocation(MVReferences.MODID, "guardian_soul.bat_soul"));
    }

    @Override
    public void registerItemsVariantsRenderer() {
        for (int i = 0; i < EnumBulletSoul.SoulType.values().length; i++) {
            castlevaniacraftMod.proxy.registerItemVariantsRenderer(MVItem.bulletSoul, i, "bullet_soul." + EnumBulletSoul.SoulType.values()[i].getName());
        }

        for (int i = 0; i < EnumGuardianSoul.SoulType.values().length; i++) {
            castlevaniacraftMod.proxy.registerItemVariantsRenderer(MVItem.guardianSoul, i, "guardian_soul." + EnumGuardianSoul.SoulType.values()[i].getName());
        }

        for (int i = 0; i < BlockDraculaCastleStoneBrick.EnumType.values().length; i++) {
            castlevaniacraftMod.proxy.registerItemVariantsRenderer(Item.getItemFromBlock(CCBlock.DRACULA_CASTLE_STONE_BRICK), i, BlockDraculaCastleStoneBrick.EnumType.values()[i].getName());
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
        MinecraftForge.EVENT_BUS.register(new MVGui());
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        CCJerIntegration.init();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
