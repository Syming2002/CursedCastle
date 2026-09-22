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

public record CCSpawnFamiliarBat() implements CustomPacketPayload {

    public static final Type<CCSpawnFamiliarBat> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "spawn_familiar_bat"));

    public static final StreamCodec<RegistryFriendlyByteBuf, CCSpawnFamiliarBat> STREAM_CODEC = StreamCodec.unit(new CCSpawnFamiliarBat());

    public void execute(IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (player != null) {
                CuriosApi.getCuriosHelper().findEquippedCurio(CCItem.SOUL_TYPES.get(Soul.BAT).get(), player).ifPresent(slotResult -> {
                    ItemStack stack = slotResult.getRight();

                    if (((SoulItem) stack.getItem()).getSoul() == Soul.BAT) {
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
    }

    private static void spawnFamiliarBat(Player player, int maxBats) {
        int currentBat = player.level().getEntitiesOfClass(FamiliarBatEntity.class, new AABB(player.blockPosition()).inflate(32)).size();

        if (currentBat < maxBats) {
            Vec3 dir = player.getLookAngle();
            double x = player.getX() + dir.x * -2.0D;
            double y = player.getY() + 0.5D;
            double z = player.getZ() + dir.z * -2.0D;

            FamiliarBatEntity familiarBat = new FamiliarBatEntity(player.level());
            familiarBat.setPos(x, y, z);
            player.level().addFreshEntity(familiarBat);
        }
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
