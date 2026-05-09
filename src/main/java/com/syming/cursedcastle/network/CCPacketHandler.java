package com.syming.cursedcastle.network;

import com.syming.cursedcastle.CursedCastleMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class CCPacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(CursedCastleMod.MOD_ID, "main"),
            () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals
    );

    private static int id;

    public static synchronized void register() {
        INSTANCE.messageBuilder(CCSpawnParticle.class, id++)
                .encoder(CCSpawnParticle::encode)
                .decoder(CCSpawnParticle::decode)
                .consumerMainThread(CCSpawnParticle.Handler::handle)
                .add();

        INSTANCE.messageBuilder(CCBossInfoPacket.Display.class, id++)
                .encoder(CCBossInfoPacket.Display::encode)
                .decoder(CCBossInfoPacket.Display::decode)
                .consumerMainThread(CCBossInfoPacket.Display::handle)
                .add();

        INSTANCE.messageBuilder(CCBossInfoPacket.Remove.class, id++)
                .encoder(CCBossInfoPacket.Remove::encode)
                .decoder(CCBossInfoPacket.Remove::decode)
                .consumerMainThread(CCBossInfoPacket.Remove::handle)
                .add();

        INSTANCE.messageBuilder(CCMessageHurtMultiPart.class, id++)
                .encoder(CCMessageHurtMultiPart::write)
                .decoder(CCMessageHurtMultiPart::read)
                .consumerMainThread(CCMessageHurtMultiPart.Handler::handle)
                .add();

        INSTANCE.messageBuilder(CCSpawnFriendlyZombie.class, id++)
                .encoder(CCSpawnFriendlyZombie::encode)
                .decoder(CCSpawnFriendlyZombie::decode)
                .consumerMainThread(CCSpawnFriendlyZombie.Handler::handle)
                .add();

        INSTANCE.messageBuilder(CCSpawnFamiliarBat.class, id++)
                .encoder(CCSpawnFamiliarBat::encode)
                .decoder(CCSpawnFamiliarBat::decode)
                .consumerMainThread(CCSpawnFamiliarBat.Handler::handle)
                .add();

        INSTANCE.messageBuilder(CCTeleportEntity.class, id++)
                .encoder(CCTeleportEntity::encode)
                .decoder(CCTeleportEntity::decode)
                .consumerMainThread(CCTeleportEntity.Handler::handle)
                .add();

        INSTANCE.messageBuilder(CCLuckEffect.class, id++)
                .encoder(CCLuckEffect::encode)
                .decoder(CCLuckEffect::decode)
                .consumerMainThread(CCLuckEffect.Handler::handle)
                .add();

        INSTANCE.messageBuilder(CCMPPacket.class, id++)
                .encoder(CCMPPacket::toBytes)
                .decoder(CCMPPacket::new)
                .consumerMainThread(CCMPPacket::handle)
                .add();

        INSTANCE.messageBuilder(CCMPData.class, id++)
                .encoder(CCMPData::toBytes)
                .decoder(CCMPData::new)
                .consumerMainThread(CCMPData::handle)
                .add();
    }

    public static void sendToPlayer(Object message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static void sendToServer(Object message) {
        INSTANCE.sendToServer(message);
    }

    public static void sendToEntity(Object message, Entity targetEntity) {
        INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> targetEntity), message);
    }

}
