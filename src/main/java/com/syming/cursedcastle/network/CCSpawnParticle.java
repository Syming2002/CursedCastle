package com.syming.cursedcastle.network;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.monster.shade.FrozenShadeEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ParticleUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record CCSpawnParticle(int entityId) implements CustomPacketPayload {

    public static final Type<CCSpawnParticle> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "spawn_particle"));

    public static final StreamCodec<RegistryFriendlyByteBuf, CCSpawnParticle> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            CCSpawnParticle::entityId,
            CCSpawnParticle::new);

    public void execute(IPayloadContext context) {
        context.enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();
            Level level = mc.level;

            if (level != null) {
                Entity entity = level.getEntity(this.entityId());
                if (entity instanceof FrozenShadeEntity frozenShade) {
                    spawnParticles(frozenShade, ParticleTypes.HEART);
                }
            }
        });
    }

    public static void spawnParticles(FrozenShadeEntity frozenShadeEntity, SimpleParticleType simpleParticleType) {
        for (int i = 0; i < 4; ++i) {
            double d0 = frozenShadeEntity.getRandom().nextGaussian() * 0.02D;
            double d1 = frozenShadeEntity.getRandom().nextGaussian() * 0.02D;
            double d2 = frozenShadeEntity.getRandom().nextGaussian() * 0.02D;
            frozenShadeEntity.level().addParticle(simpleParticleType, frozenShadeEntity.getRandomX(0.25D), frozenShadeEntity.getRandomY() + 0.25D, frozenShadeEntity.getRandomZ(0.25D), d0, d1, d2);
        }
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
