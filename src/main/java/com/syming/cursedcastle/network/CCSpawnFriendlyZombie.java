package com.syming.cursedcastle.network;

import com.syming.cursedcastle.entity.mob.friendly.ZombieFriendlyEntity;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.soul.Soul;
import com.syming.cursedcastle.item.soul.SoulItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.function.Supplier;

public class CCSpawnFriendlyZombie {

    public CCSpawnFriendlyZombie() {
    }

    public static void encode(CCSpawnFriendlyZombie msg, FriendlyByteBuf buf) {
    }

    public static CCSpawnFriendlyZombie decode(FriendlyByteBuf buf) {
        return new CCSpawnFriendlyZombie();
    }


    public static class Handler {
        public Handler() {
        }

        public static void handle(CCSpawnFriendlyZombie msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                ServerPlayer player = ctx.get().getSender();
                if (player != null) {
                    CuriosApi.getCuriosHelper().findEquippedCurio(CCItem.SOUL_TYPES.get(Soul.ZOMBIE).get(), player).ifPresent(slotResult -> {
                        ItemStack stack = slotResult.getRight();

                        if (((SoulItem) stack.getItem()).getSoul() == Soul.ZOMBIE) {
                            int count = stack.getCount();

                            if (count > 0 && count <= 3) {
                                spawnZombie(player, 1);
                            } else if (count > 3 && count <= 6) {
                                spawnZombie(player, 2);
                            } else if (count > 6 && count <= 9) {
                                spawnZombie(player, 3);
                            }
                        }
                    });
                }
            });
            ctx.get().setPacketHandled(true);
        }

        private static void spawnZombie(ServerPlayer player, int maxZombies) {
            int currentZombies = player.level().getEntitiesOfClass(ZombieFriendlyEntity.class, new AABB(player.blockPosition()).inflate(32)).size();

            if (currentZombies < maxZombies) {
                Vec3 dir = player.getLookAngle();
                double x = player.getX() + dir.x * 1.5D;
                double y = player.getY() + 0.5D;
                double z = player.getZ() + dir.z * 1.5D;

                ZombieFriendlyEntity zombie = new ZombieFriendlyEntity(player.level());
                zombie.setPos(x, y, z);
                player.level().addFreshEntity(zombie);
            }
        }
    }
}
