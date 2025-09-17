package com.syming.cursedcastle.network;

import com.syming.cursedcastle.entity.mob.bat.familiar.FamiliarBatEntity;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.soul.Soul;
import com.syming.cursedcastle.item.soul.SoulGuardianItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.function.Supplier;

public class CCSpawnFamiliarBat {

    public CCSpawnFamiliarBat() {
    }

    public static void encode(CCSpawnFamiliarBat msg, FriendlyByteBuf buf) {
    }

    public static CCSpawnFamiliarBat decode(FriendlyByteBuf buf) {
        return new CCSpawnFamiliarBat();
    }


    public static class Handler {
        public Handler() {
        }

        public static void handle(CCSpawnFamiliarBat msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                ServerPlayer player = ctx.get().getSender();
                if (player != null) {
                    CuriosApi.getCuriosHelper().findEquippedCurio(CCItem.BAT_SOUL.get(), player).ifPresent(slotResult -> {
                        ItemStack stack = slotResult.getRight();

                        if (((SoulGuardianItem) stack.getItem()).getSoul() == Soul.BAT) {
                            int count = stack.getCount();

                            if (count > 0 && count <= 3) {
                                spawnFamiliarBat(player, 1);
                            } else if (count > 3 && count <= 6) {
                                spawnFamiliarBat(player, 2);
                            } else if (count > 6 && count <= 9) {
                                spawnFamiliarBat(player, 3);
                            }
                        }
                    });
                }
            });
            ctx.get().setPacketHandled(true);
        }

        private static void spawnFamiliarBat(ServerPlayer player, int maxBats) {
            int currentBat = player.level().getEntitiesOfClass(FamiliarBatEntity.class, new AABB(player.blockPosition()).inflate(32)).size();

            if (currentBat < maxBats) {
                Vec3 dir = player.getLookAngle();
                double x = player.getX() + dir.x * -2.0D;
                double y = player.getY() + 0.5D;
                double z = player.getZ() + dir.z * -2.0D;

                FamiliarBatEntity familiarBat = new FamiliarBatEntity(player.level());
                familiarBat.setPosRaw(x, y, z);
                player.level().addFreshEntity(familiarBat);
            }
        }
    }

}
