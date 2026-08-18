package com.syming.cursedcastle.network;

import com.syming.cursedcastle.api.ISyncHurtMultipart;
import com.syming.cursedcastle.entity.monster.arachne.ArachneEntity;
import com.syming.cursedcastle.entity.monster.boss.dracula.DraculaEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.entity.PartEntity;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CCMessageHurtMultiPart {

    public int part;
    public int parent;
    public float damage;
    public String damageType;

    public CCMessageHurtMultiPart(int part, int parent, float damage) {
        this.part = part;
        this.parent = parent;
        this.damage = damage;
        this.damageType = "";
    }

    public CCMessageHurtMultiPart(int part, int parent, float damage, String damageType) {
        this.part = part;
        this.parent = parent;
        this.damage = damage;
        this.damageType = damageType;
    }

    public CCMessageHurtMultiPart() {
    }

    public static CCMessageHurtMultiPart read(FriendlyByteBuf buf) {
        return new CCMessageHurtMultiPart(buf.readInt(), buf.readInt(), buf.readFloat(), buf.readUtf());
    }

    public static void write(CCMessageHurtMultiPart message, FriendlyByteBuf buf) {
        buf.writeInt(message.part);
        buf.writeInt(message.parent);
        buf.writeFloat(message.damage);
        buf.writeUtf(message.damageType);
    }

    public static void handle(CCMessageHurtMultiPart message, Supplier<NetworkEvent.Context> context) {
        context.get().setPacketHandled(true);
        context.get().enqueueWork(() -> {
            Player player = context.get().getSender();
            if (context.get().getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                player = Minecraft.getInstance().player;
            }

            if (player != null) {
                if (player.level() != null) {
                    PartEntity<?> part = (PartEntity<?>) player.level().getEntity(message.part);
                    Entity parent = player.level().getEntity(message.parent);
                    Registry<DamageType> registry = player.level().registryAccess().registry(Registries.DAMAGE_TYPE).get();
                    DamageType dmg = registry.get(new ResourceLocation(message.damageType));
                    if (dmg != null) {
                        Holder<DamageType> holder = registry.getHolder(registry.getId(dmg)).orElseGet(null);
                        if (holder != null) {
                            DamageSource source = new DamageSource(registry.getHolder(registry.getId(dmg)).get());
                            if (part instanceof ISyncHurtMultipart && parent instanceof LivingEntity) {
                                ((ISyncHurtMultipart) part).onServerMultipartAttack((LivingEntity) parent, source, message.damage);
                            }
                            if (part != null && parent != null && parent.isMultipartEntity() && parent instanceof DraculaEntity dracula) {
                                dracula.hurtHead(part, source, message.damage);
                            } else if (part != null && parent != null && parent.isMultipartEntity() && parent instanceof ArachneEntity arachne) {
                                arachne.hurtHumanBody(part, source, message.damage);
                            }
                        }
                    }
                }
            }
        });
    }
}
