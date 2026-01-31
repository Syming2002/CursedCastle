package com.syming.cursedcastle.network;

import com.syming.cursedcastle.entity.mob.shade.FrozenShadeEntity;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.soul.Soul;
import com.syming.cursedcastle.item.soul.SoulItem;
import com.syming.cursedcastle.item.whip.IceWhipItem;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.function.Supplier;

public class CCSpawnParticle {

    private final int targetId;

    public CCSpawnParticle(int targetId) {
        this.targetId = targetId;
    }

    public static void encode(CCSpawnParticle msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.targetId);
    }

    public static CCSpawnParticle decode(FriendlyByteBuf buf) {
        return new CCSpawnParticle(buf.readInt());
    }


    public static class Handler {
        public Handler() {
        }

        public static void handle(CCSpawnParticle msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                Minecraft mc = Minecraft.getInstance();
                Level level = mc.level;

                if (level != null) {
                    Entity entity = level.getEntity(msg.targetId);
                    if (entity instanceof FrozenShadeEntity frozenShade) {
                        spawnHearts(frozenShade);
                    }
                }
            });

            ctx.get().setPacketHandled(true);
        }

        public static void spawnHearts(FrozenShadeEntity frozenShadeEntity) {
            for (int i = 0; i < 4; ++i) {
                double d0 = frozenShadeEntity.getRandom().nextGaussian() * 0.02D;
                double d1 = frozenShadeEntity.getRandom().nextGaussian() * 0.02D;
                double d2 = frozenShadeEntity.getRandom().nextGaussian() * 0.02D;
                frozenShadeEntity.level().addParticle(ParticleTypes.HEART, frozenShadeEntity.getRandomX(0.25D), frozenShadeEntity.getRandomY() + 0.25D, frozenShadeEntity.getRandomZ(0.25D), d0, d1, d2);
            }
        }
    }
}
