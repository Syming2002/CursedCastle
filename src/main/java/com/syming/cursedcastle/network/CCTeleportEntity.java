package com.syming.cursedcastle.network;


import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.soul.Soul;
import com.syming.cursedcastle.item.soul.SoulItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.function.Supplier;

public class CCTeleportEntity {

    public CCTeleportEntity() {
    }

    public static void encode(CCTeleportEntity msg, FriendlyByteBuf buf) {
    }

    public static CCTeleportEntity decode(FriendlyByteBuf buf) {
        return new CCTeleportEntity();
    }


    public static class Handler {
        public Handler() {
        }

        public static void handle(CCTeleportEntity msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                ServerPlayer player = ctx.get().getSender();
                if (player != null) {
                    CuriosApi.getCuriosHelper().findEquippedCurio(CCItem.SOUL_TYPES.get(Soul.ENDERMAN).get(), player).ifPresent(slotResult -> {
                        ItemStack stack = slotResult.getRight();

                        if (((SoulItem) stack.getItem()).getSoul() == Soul.ENDERMAN) {
                            int count = stack.getCount();
                            teleportSoulOwner(player, count);
                        }
                    });
                }
            });
            ctx.get().setPacketHandled(true);
        }

        private static void teleportSoulOwner(ServerPlayer player, int soulCount) {
            double radius;

            if (soulCount <= 4) {
                radius = 4.0D;
            } else if (soulCount <= 8) {
                radius = 8.0D;
            } else if (soulCount <= 16) {
                radius = 16.0D;
            } else {
                return;
            }

            double d0 = (player.getRandom().nextDouble() - 0.5D) * radius * 2.0D;
            double d1 = (player.getRandom().nextInt(16) - 8);
            double d2 = (player.getRandom().nextDouble() - 0.5D) * radius * 2.0D;

            double targetX = player.getX() + d0;
            double targetY = player.getY() + d1;
            double targetZ = player.getZ() + d2;

            player.randomTeleport(targetX, targetY, targetZ, true);
        }
    }
}
