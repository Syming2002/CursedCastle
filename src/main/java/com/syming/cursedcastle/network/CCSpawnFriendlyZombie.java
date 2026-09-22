package com.syming.cursedcastle.network;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.monster.zombie.friendly.FriendlyZombieEntity;
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

public record CCSpawnFriendlyZombie() implements CustomPacketPayload {

    public static final Type<CCSpawnFriendlyZombie> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "spawn_friendly_zombie"));

    public static final StreamCodec<RegistryFriendlyByteBuf, CCSpawnFriendlyZombie> STREAM_CODEC = StreamCodec.unit(
            new CCSpawnFriendlyZombie());

    public void execute(IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
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
    }

    private static void spawnZombie(Player player, int maxZombies) {
        int currentZombies = player.level().getEntitiesOfClass(FriendlyZombieEntity.class, new AABB(player.blockPosition()).inflate(32)).size();

        if (currentZombies < maxZombies) {
            Vec3 dir = player.getLookAngle();
            double x = player.getX() + dir.x * 1.5D;
            double y = player.getY() + 0.5D;
            double z = player.getZ() + dir.z * 1.5D;

            FriendlyZombieEntity zombie = new FriendlyZombieEntity(player.level());
            zombie.setPos(x, y, z);
            player.level().addFreshEntity(zombie);
        }
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
