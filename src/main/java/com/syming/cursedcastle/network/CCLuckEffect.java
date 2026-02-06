package com.syming.cursedcastle.network;

import com.syming.cursedcastle.entity.mob.bat.familiar.FamiliarBatEntity;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.soul.Soul;
import com.syming.cursedcastle.item.soul.SoulItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.function.Supplier;

public class CCLuckEffect {

    public CCLuckEffect() {
    }

    public static void encode(CCLuckEffect msg, FriendlyByteBuf buf) {
    }

    public static CCLuckEffect decode(FriendlyByteBuf buf) {
        return new CCLuckEffect();
    }


    public static class Handler {
        public Handler() {
        }

        public static void handle(CCLuckEffect msg, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                ServerPlayer player = ctx.get().getSender();
                if (player != null) {
                    CuriosApi.getCuriosHelper().findEquippedCurio(CCItem.SOUL_TYPES.get(Soul.GHOST_DANCER).get(), player).ifPresent(slotResult -> {
                        ItemStack stack = slotResult.getRight();

                        if (((SoulItem) stack.getItem()).getSoul() == Soul.GHOST_DANCER) {
                            int count = stack.getCount();

                            if (count > 0 && count <= 3) {
                                addLuckEffect(player, 1);
                            } else if (count > 3 && count <= 6) {
                                addLuckEffect(player, 2);
                            } else if (count > 6 && count <= 9) {
                                addLuckEffect(player, 3);
                            }
                        }
                    });
                }
            });
            ctx.get().setPacketHandled(true);
        }

        private static void addLuckEffect(ServerPlayer player, int luck) {
            player.addEffect(new MobEffectInstance(MobEffects.LUCK, 10, luck));
        }
    }
}
