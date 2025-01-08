package com.syming.minevania;

import com.syming.minevania.proxy.CommonProxy;
import com.syming.minevania.util.MVReferences;
import com.syming.minevania.util.handlers.RegistryHandler;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = MVReferences.MODID,
        name = MVReferences.NAME,
        version = MVReferences.VERSION,
        dependencies = "required-after:llibrary@[1.7.9,);" + "required-after:baubles@[1.5,);")
public class MinevaniaMod {

    static
    {
        FluidRegistry.enableUniversalBucket();
    }

    @Mod.Instance
    public static com.syming.minevania.MinevaniaMod instance;

    @SidedProxy(clientSide = MVReferences.CLIENT_PROXY, serverSide = MVReferences.COMMON_PROXY)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        RegistryHandler.preInitRegistries(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        RegistryHandler.initRegistries(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        RegistryHandler.postInitRegistries(event);
    }

    @Mod.EventHandler
    public void serverInit(FMLServerStartingEvent event)
    {
        RegistryHandler.serverRegistries(event);
    }


}
