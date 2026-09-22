package com.syming.cursedcastle.network;


import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.monster.bat.familiar.FamiliarBatEntity;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.soul.Soul;
import com.syming.cursedcastle.item.soul.SoulItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.function.Supplier;

public record CCTeleportEntity() implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<CCTeleportEntity> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "teleport_entity"));

    public static final StreamCodec<RegistryFriendlyByteBuf, CCTeleportEntity> STREAM_CODEC = StreamCodec.unit(new CCTeleportEntity());

    public void execute(IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (player != null) {
                CuriosApi.getCuriosHelper().findEquippedCurio(CCItem.SOUL_TYPES.get(Soul.ENDERMAN).get(), player).ifPresent(slotResult -> {
                    ItemStack stack = slotResult.getRight();

                    if (((SoulItem) stack.getItem()).getSoul() == Soul.ENDERMAN) {
                        int count = stack.getCount();

                        if (count > 0 && count <= 3) {
                            teleportPlayer(player, 4);
                        } else if (count > 3 && count <= 6) {
                            teleportPlayer(player, 8);
                        } else if (count > 6 && count <= 9) {
                            teleportPlayer(player, 16);
                        }
                    }
                });
            }
        });
    }

    private static void teleportPlayer(Player player, double radius) {
        player.randomTeleport(player.getX() + radius, player.getY(), player.getZ() + radius, true);
    }

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
