package com.syming.castlevaniacraft.networking;

import com.syming.castlevaniacraft.util.CCReferences;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class MVNetworking {

    public static final String NETWORK_CHANNEL_NAME = CCReferences.MODID;
    public static SimpleNetworkWrapper network;

    public static void registerSimpleNetworking() {
        network = NetworkRegistry.INSTANCE.newSimpleChannel(NETWORK_CHANNEL_NAME);

        int packetId = 0;

        //Server
        network.registerMessage(MessageExtendedReachAttack.Handler.class, MessageExtendedReachAttack.class, packetId++, Side.SERVER);
        network.registerMessage(MessageSyncEventFireballLauncher.Handler.class, MessageSyncEventFireballLauncher.class, packetId++, Side.SERVER);
        network.registerMessage(MessageSyncEventFlameWhipParticle.Handler.class, MessageSyncEventFlameWhipParticle.class, packetId++, Side.SERVER);

        // Client
        network.registerMessage(MessageAddBossInfo.Handler.class, MessageAddBossInfo.class, packetId++, Side.CLIENT);
        network.registerMessage(MessageRemoveBossInfo.Handler.class, MessageRemoveBossInfo.class, packetId++, Side.CLIENT);
    }
}
