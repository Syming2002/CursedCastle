package com.syming.minevania.util.handlers;


import com.syming.minevania.MinevaniaMod;
import com.syming.minevania.api.*;
import com.syming.minevania.entity.CCEntity;
import com.syming.minevania.event.*;
import com.syming.minevania.init.*;
import com.syming.minevania.item.MVItem;
import com.syming.minevania.integration.forestry.bees.CCBeeDefinition;
import com.syming.minevania.integration.forestry.items.CCCombItem;
import com.syming.minevania.integration.forestry.items.CCDropItem;
import com.syming.minevania.integration.forestry.recipes.CentrifugeRecipes;
import com.syming.minevania.keybind.SoulKeyBind;
import com.syming.minevania.networking.MVNetworking;
import com.syming.minevania.recipe.CraftingRecipes;
import com.syming.minevania.util.ModLoadedUtils;
import com.syming.minevania.world.gen.structure.CCWorldGenerator;
import com.syming.minevania.world.gen.structure.cemetery.CemeteryCreationHandler;
import com.syming.minevania.world.gen.structure.church.BigChurchCreationHandler;

import forestry.api.core.ForestryAPI;
import forestry.core.items.IColoredItem;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
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

@Mod.EventBusSubscriber
public class RegistryHandler {

    private static IForgeRegistry<Item> itemRegistry;
    public static CCCombItem COMBS;
    public static CCDropItem DROPS;

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        itemRegistry = event.getRegistry();
        event.getRegistry().registerAll(MVItem.ITEMS.toArray(new Item[0]));
        if (ModLoadedUtils.Forestry.isModLoaded()) {
            itemRegistry.register(DROPS);
            itemRegistry.register(COMBS);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelRegister(ModelRegistryEvent event)
    {
        RenderHandler.registerEntityRenders();
        RenderHandler.registerCustomMeshesAndStates();

        for (Item item : MVItem.ITEMS) {
            MinevaniaMod.proxy.registerItemRenderer(item, 0, "inventory");
        }

        RenderHandler.registerItemRenders();

        for(Block block : CCBlock.BLOCKS) {
            MinevaniaMod.proxy.registerItemRenderer(Item.getItemFromBlock(block), 0, "inventory");
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
        EventExtendedReach extendedReach = new EventExtendedReach();
        EventFireballLauncher fireballLauncher = new EventFireballLauncher();
        EventFriendlyZombieSpawningPrevention friendlyZombieSpawningPrevention = new EventFriendlyZombieSpawningPrevention();
        EventImmobileEntityLiving immobileEntityLiving = new EventImmobileEntityLiving();
        EventVanillaMobLootTableModifier vanillaMobLootTableModifier = new EventVanillaMobLootTableModifier();
        EventVillagerCrucifixProtection villagerCrucifixProtection = new EventVillagerCrucifixProtection();

        MinecraftForge.EVENT_BUS.register(extendedReach);
        MinecraftForge.EVENT_BUS.register(fireballLauncher);
        MinecraftForge.EVENT_BUS.register(friendlyZombieSpawningPrevention);
        MinecraftForge.EVENT_BUS.register(immobileEntityLiving);
        MinecraftForge.EVENT_BUS.register(vanillaMobLootTableModifier);
        MinecraftForge.EVENT_BUS.register(villagerCrucifixProtection);

        // Keybindings
        ClientRegistry.registerKeyBinding(SoulKeyBind.bulletKey);
        ClientRegistry.registerKeyBinding(SoulKeyBind.guardianKey);
    }

    public static void preInitRegistries(FMLPreInitializationEvent event) {
        MinevaniaMod.proxy.preInit(event);
        MinevaniaMod.proxy.render();
        CCEntity.registerEntities();
        registerEvent();
        FluidInit.registerFluids();
        MVNetworking.registerSimpleNetworking();

//        NetworkRegistry.INSTANCE.registerGuiHandler(MinevaniaMod.instance, new GuiHandler());

        CCLog.init(event.getModLog());

        if (ModLoadedUtils.Forestry.isModLoaded()) {
            DROPS = new CCDropItem();
            COMBS = new CCCombItem();
        }
    }

    public static void initRegistries(FMLInitializationEvent event) {
        MinevaniaMod.proxy.init(event);
        MinevaniaMod.proxy.render();
        MinevaniaMod.proxy.registerModelBakeryVariants();
        SoundsHandler.registerSounds();
        CraftingRecipes.init();
        BigChurchCreationHandler.registerBigChurchComponents();
        CemeteryCreationHandler.registerCemeteryComponents();
        VillagerRegistry.instance().registerVillageCreationHandler(WorldGenInit.BIG_CHURCH_HANDLER);
        VillagerRegistry.instance().registerVillageCreationHandler(WorldGenInit.CEMETERY_HANDLER);

        GameRegistry.registerWorldGenerator(new CCWorldGenerator(), 0);


        if (event.getSide() == Side.CLIENT) {
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
                if (stack.getItem() instanceof IColoredItem coloredItem) {
                    return coloredItem.getColorFromItemstack(stack, tintIndex);
                }
                return 0xFFFFFF;
            }, DROPS, COMBS);
        }

        VillagerInit.init();
    }

    public static void postInitRegistries(FMLPostInitializationEvent event) {
        MinevaniaMod.proxy.postInit(event);
        if (ModLoadedUtils.Forestry.isModLoaded()) {
            CCBeeDefinition.doInit();
        }
        CentrifugeRecipes.register();

    }

    public static void serverRegistries(FMLServerStartingEvent event) {

    }
}
