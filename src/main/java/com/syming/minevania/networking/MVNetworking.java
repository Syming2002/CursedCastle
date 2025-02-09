package com.syming.minevania.networking;

import com.syming.minevania.util.MVReferences;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class MVNetworking {

    public static final String NETWORK_CHANNEL_NAME = MVReferences.MODID;
    public static SimpleNetworkWrapper network;

    public static void registerSimpleNetworking() {
        network = NetworkRegistry.INSTANCE.newSimpleChannel(NETWORK_CHANNEL_NAME);

        int packetId = 0;

        network.registerMessage(MessageExtendedReachAttack.Handler.class, MessageExtendedReachAttack.class, packetId++, Side.SERVER);
        network.registerMessage(MessageSyncEventFireballLauncher.Handler.class, MessageSyncEventFireballLauncher.class, packetId++, Side.SERVER);
        network.registerMessage(MessageDisplayBossInfo.Handler.class, MessageDisplayBossInfo.class, packetId++, Side.SERVER);
        network.registerMessage(MessageRemoveBossInfo.Handler.class, MessageRemoveBossInfo.class, packetId++, Side.SERVER);
    }
}
