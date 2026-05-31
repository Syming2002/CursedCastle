package com.syming.cursedcastle.event;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.effect.CCEffect;
import com.syming.cursedcastle.entity.projectile.subweapon.HolyWaterEntity;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.soul.Soul;
import com.syming.cursedcastle.mp.PlayerMP;
import com.syming.cursedcastle.mp.PlayerMPProvider;
import com.syming.cursedcastle.network.CCLuckEffect;
import com.syming.cursedcastle.network.CCMPData;
import com.syming.cursedcastle.network.CCPacketHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Mod.EventBusSubscriber(modid = CursedCastleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CCCommonEventForge {

    private static final List<ScheduledTask> tasks = new ArrayList<>();

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerMP.class);
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(PlayerMPProvider.PLAYER_MP).isPresent()) {
                event.addCapability(new ResourceLocation(CursedCastleMod.MOD_ID, "properties"), new PlayerMPProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerMPProvider.PLAYER_MP).ifPresent(oldMP -> {
                event.getEntity().getCapability(PlayerMPProvider.PLAYER_MP).ifPresent(newMP -> {
                    newMP.copyFrom(oldMP);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.SERVER) {
            event.player.getCapability(PlayerMPProvider.PLAYER_MP).ifPresent(mp -> {
                if (mp.getCurrentMP() < mp.getMaxMP() && event.player.tickCount % 20 == 0) {
                    if (event.player.experienceLevel <= 10) {
                        mp.addMP(1);
                    } else if (event.player.experienceLevel <= 20) {
                        mp.addMP(2);
                    } else if (event.player.experienceLevel <= 30) {
                        mp.addMP(3);
                    } else if (event.player.experienceLevel <= 40) {
                        mp.addMP(4);
                    } else if (event.player.experienceLevel <= 50) {
                        mp.addMP(5);
                    }
                    CCPacketHandler.sendToPlayer(new CCMPData(mp.getCurrentMP()), (ServerPlayer) event.player);
                }
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            serverPlayer.getCapability(PlayerMPProvider.PLAYER_MP).ifPresent(mp -> {
                CCPacketHandler.sendToPlayer(new CCMPData(mp.getCurrentMP()), serverPlayer);
            });
        }
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) return;

        Iterator<ScheduledTask> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            ScheduledTask task = iterator.next();
            task.tickLeft--;
            if (task.tickLeft <= 0) {
                task.runnable.run();
                iterator.remove();
            }
        }
    }

    @SubscribeEvent
    public static void onRainingEvent(TickEvent.LevelTickEvent event) {
        Level level = event.level;

        if (level.isClientSide()) return;

        if (HolyWaterEntity.holyRainTicks > 0) {
            HolyWaterEntity.holyRainTicks--;
        }

        if (HolyWaterEntity.holyRainTicks <= 0) {
            HolyWaterEntity.rainCaster = null;
        }
    }

    @SubscribeEvent
    public static void onRainingEffectEvent(LivingEvent.LivingTickEvent event) {
        LivingEntity living = event.getEntity();

        if (living.level().isClientSide()) return;

        if (living instanceof Monster monster && monster.level().isRainingAt(monster.blockPosition()) && HolyWaterEntity.rainCaster != null) {
            monster.addEffect(new MobEffectInstance(CCEffect.HOLY.get(), HolyWaterEntity.RAINING_COOLDOWN, 1, false, false, true));
        }
    }

    private static class ScheduledTask {
        Runnable runnable;
        int tickLeft;

        ScheduledTask(Runnable runnable, int tickLeft) {
            this.runnable = runnable;
            this.tickLeft = tickLeft;
        }
    }
}
