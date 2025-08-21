package com.syming.castlevaniacraft.util.handler;


import com.syming.castlevaniacraft.CastlevaniaCraftMod;
import com.syming.castlevaniacraft.DelayedTaskScheduler;
import com.syming.castlevaniacraft.advancement.CCAdvancement;
import com.syming.castlevaniacraft.api.*;
import com.syming.castlevaniacraft.block.CCBlock;
import com.syming.castlevaniacraft.entity.CCEntity;
import com.syming.castlevaniacraft.event.*;
import com.syming.castlevaniacraft.fluid.CCFluid;
import com.syming.castlevaniacraft.init.*;
import com.syming.castlevaniacraft.integration.forestry.genetics.CCBeesFlowers;
import com.syming.castlevaniacraft.integration.forestry.recipes.SqueezerRecipes;
import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.integration.forestry.bees.CCBeeDefinition;
import com.syming.castlevaniacraft.integration.forestry.items.CCCombItem;
import com.syming.castlevaniacraft.integration.forestry.items.CCDropItem;
import com.syming.castlevaniacraft.integration.forestry.recipes.CentrifugeRecipes;
import com.syming.castlevaniacraft.item.projectiles.subweapons.ItemDagger;
import com.syming.castlevaniacraft.item.projectiles.subweapons.holycross.ItemAnimNetheriteHolyCross;
import com.syming.castlevaniacraft.item.projectiles.subweapons.holycross.ItemAnimWoodenHolyCross;
import com.syming.castlevaniacraft.item.projectiles.subweapons.holycross.ItemHolyCross;
import com.syming.castlevaniacraft.item.projectiles.subweapons.throwingaxe.ItemAnimDiamondThrowingAxe;
import com.syming.castlevaniacraft.item.projectiles.subweapons.throwingaxe.ItemAnimNetheriteThrowingAxe;
import com.syming.castlevaniacraft.item.projectiles.subweapons.throwingaxe.ItemThrowingAxe;
import com.syming.castlevaniacraft.keybind.CCKeybind;
import com.syming.castlevaniacraft.networking.CCNetworking;
import com.syming.castlevaniacraft.recipe.CraftingRecipes;
import com.syming.castlevaniacraft.util.ModLoadedUtils;
import com.syming.castlevaniacraft.world.gen.structure.CCWorldGenerator;
import com.syming.castlevaniacraft.world.gen.structure.cemetery.CemeteryCreationHandler;
import com.syming.castlevaniacraft.world.gen.structure.church.BigChurchCreationHandler;

import forestry.api.core.ForestryAPI;
import forestry.core.items.IColoredItem;

import git.jbredwards.nether_api.mod.NetherAPI;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import static com.unseen.nb.init.NBSoundTypes.NETHERITE;

@Mod.EventBusSubscriber
public class RegistryHandler {

    private static IForgeRegistry<Item> itemRegistry;
    public static CCCombItem COMBS;
    public static CCDropItem DROPS;
    public static Item.ToolMaterial NETHERITE = EnumHelper.addToolMaterial("netherite", 4, 2031, 10.0F, 4.0F, 15);
    public static ItemAnimNetheriteHolyCross animNetheriteHolyCross;
    public static ItemAnimNetheriteThrowingAxe animNetheriteThrowingAxe;
    public static ItemDagger netheriteDagger;
    public static ItemHolyCross netheriteHolyCross;
    public static ItemThrowingAxe netheriteThrowingAxe;


    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        itemRegistry = event.getRegistry();
        event.getRegistry().registerAll(CCItem.ITEMS.toArray(new Item[0]));
        if (ModLoadedUtils.Forestry.isModLoaded()) {
            itemRegistry.register(DROPS);
            itemRegistry.register(COMBS);
        }
        if (ModLoadedUtils.NetherBackport.isModLoaded()) {
            animNetheriteHolyCross = new ItemAnimNetheriteHolyCross("anim_netherite_holy_cross");
            itemRegistry.register(animNetheriteHolyCross);
            animNetheriteThrowingAxe = new ItemAnimNetheriteThrowingAxe("anim_netherite_throwing_axe");
            itemRegistry.register(animNetheriteThrowingAxe);
            netheriteDagger = new ItemDagger("netherite_dagger", NETHERITE);
            itemRegistry.register(netheriteDagger);
            netheriteHolyCross = new ItemHolyCross("netherite_holy_cross", NETHERITE);
            itemRegistry.register(netheriteHolyCross);
            netheriteThrowingAxe = new ItemThrowingAxe("netherite_throwing_axe", NETHERITE);
            itemRegistry.register(netheriteThrowingAxe);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelRegister(ModelRegistryEvent event)
    {
        RenderHandler.registerEntityRenders();
        RenderHandler.registerCustomMeshesAndStates();

        for (Item item : CCItem.ITEMS) {
            CastlevaniaCraftMod.proxy.registerItemRenderer(item, 0, "inventory");
        }

        RenderHandler.registerItemRenders();

        for(Block block : CCBlock.BLOCKS) {
            CastlevaniaCraftMod.proxy.registerItemRenderer(Item.getItemFromBlock(block), 0, "inventory");
        }

        if (ModLoadedUtils.Forestry.isModLoaded()) {
            DROPS.registerModel(DROPS, ForestryAPI.modelManager);
            COMBS.registerModel(COMBS, ForestryAPI.modelManager);
        }
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
//        CCBlock.getBlocks();
        event.getRegistry().registerAll(CCBlock.BLOCKS.toArray(new Block[0]));
    }

    @SubscribeEvent
    public static void registerEnchant(RegistryEvent.Register<Enchantment> event) {

    }

    public static void registerEvent() {
        // Events

        DelayedTaskScheduler delayedTaskScheduler = new DelayedTaskScheduler();
        EventClient client = new EventClient();
        EventCommon common = new EventCommon();
        EventEntityNotMoving noJumping = new EventEntityNotMoving();
        EventExtendedReach extendedReach = new EventExtendedReach();
        EventFireballLauncher fireballLauncher = new EventFireballLauncher();
        EventFlameWhipParticle flameWhipParticle = new EventFlameWhipParticle();
        EventFriendlyZombieSpawningPrevention friendlyZombieSpawningPrevention = new EventFriendlyZombieSpawningPrevention();
        EventImmobileEntityLiving immobileEntityLiving = new EventImmobileEntityLiving();

        EventVanillaMobLootTableModifier vanillaMobLootTableModifier = new EventVanillaMobLootTableModifier();
        EventVillagerCrucifixProtection villagerCrucifixProtection = new EventVillagerCrucifixProtection();

        MinecraftForge.EVENT_BUS.register(delayedTaskScheduler);
        MinecraftForge.EVENT_BUS.register(client);
        MinecraftForge.EVENT_BUS.register(common);
        MinecraftForge.EVENT_BUS.register(noJumping);
        MinecraftForge.EVENT_BUS.register(extendedReach);
        MinecraftForge.EVENT_BUS.register(fireballLauncher);
        MinecraftForge.EVENT_BUS.register(flameWhipParticle);
        MinecraftForge.EVENT_BUS.register(friendlyZombieSpawningPrevention);
        MinecraftForge.EVENT_BUS.register(immobileEntityLiving);
        MinecraftForge.EVENT_BUS.register(vanillaMobLootTableModifier);
        MinecraftForge.EVENT_BUS.register(villagerCrucifixProtection);

        // Keybindings
        ClientRegistry.registerKeyBinding(CCKeybind.bulletKey);
        ClientRegistry.registerKeyBinding(CCKeybind.guardianKey);
    }

    public static void preInitRegistries(FMLPreInitializationEvent event) {
        CastlevaniaCraftMod.proxy.preInit(event);
        CastlevaniaCraftMod.proxy.render();
        CCEntity.registerEntities();
        registerEvent();
        CCFluid.registerFluids();
        CCNetworking.registerSimpleNetworking();

        WorldGenInit.initStructure();

        CCLog.init(event.getModLog());

        if (ModLoadedUtils.Forestry.isModLoaded()) {
            DROPS = new CCDropItem();
            COMBS = new CCCombItem();
        }

        CastlevaniaCraftMod.proxy.registerTileEntities();
    }

    public static void initRegistries(FMLInitializationEvent event) {
        CastlevaniaCraftMod.proxy.init(event);
        CastlevaniaCraftMod.proxy.render();
        CastlevaniaCraftMod.proxy.registerModelBakeryVariants();
        SoundsHandler.registerSounds();
        CraftingRecipes.init();
        BigChurchCreationHandler.registerBigChurchComponents();
        CemeteryCreationHandler.registerCemeteryComponents();
        VillagerRegistry.instance().registerVillageCreationHandler(WorldGenInit.BIG_CHURCH_HANDLER);
        VillagerRegistry.instance().registerVillageCreationHandler(WorldGenInit.CEMETERY_HANDLER);

        CCAdvancement.init();

        GameRegistry.registerWorldGenerator(new CCWorldGenerator(), 0);

        if (ModLoadedUtils.Forestry.isModLoaded()) {
            if (event.getSide() == Side.CLIENT) {
                Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
                    if (stack.getItem() instanceof IColoredItem coloredItem) {
                        return coloredItem.getColorFromItemstack(stack, tintIndex);
                    }
                    return 0xFFFFFF;
                }, DROPS, COMBS);
            }
            CCBeesFlowers.doInit();
        }

        VillagerInit.init();
    }

    public static void postInitRegistries(FMLPostInitializationEvent event) {
        CastlevaniaCraftMod.proxy.postInit(event);
        if (ModLoadedUtils.Forestry.isModLoaded()) {
            CCBeeDefinition.doInit();
            CentrifugeRecipes.register();
            SqueezerRecipes.register();
        }
        NetworkRegistry.INSTANCE.registerGuiHandler(CastlevaniaCraftMod.instance, new GuiHandler());
    }

    public static void serverRegistries(FMLServerStartingEvent event) {

    }
}
