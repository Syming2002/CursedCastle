package com.syming.castlevaniacraft;

import com.syming.castlevaniacraft.proxy.CommonProxy;
import com.syming.castlevaniacraft.util.CCReferences;
import com.syming.castlevaniacraft.util.handler.RegistryHandler;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = CCReferences.MODID,
        name = CCReferences.NAME,
        version = CCReferences.VERSION,
        dependencies = "required-after:llibrary@[1.7.9,);" +
                "required-after:baubles@[1.5,);" +
                "required-after:reachfix@[1.0.11,);" +
                "required-after:atlaslib@[1.1.6,);" +
                "required-after:deeperdepths@[1.0.3,);")
public class CastlevaniaCraftMod {

    static
    {
        FluidRegistry.enableUniversalBucket();
    }

    @Mod.Instance
    public static CastlevaniaCraftMod instance;

    @SidedProxy(clientSide = CCReferences.CLIENT_PROXY, serverSide = CCReferences.COMMON_PROXY)
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
