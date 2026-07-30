package com.syming.cursedcastle.event;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.CrucifixBlock;
import com.syming.cursedcastle.effect.CCEffect;
import com.syming.cursedcastle.entity.projectile.subweapon.HolyWaterEntity;
import com.syming.cursedcastle.entity.villager.CCVillager;
import com.syming.cursedcastle.mp.PlayerMP;
import com.syming.cursedcastle.mp.PlayerMPProvider;
import com.syming.cursedcastle.network.CCMPData;
import com.syming.cursedcastle.network.CCPacketHandler;
import com.syming.cursedcastle.tag.CCTag;
import com.syming.cursedcastle.world.village.CCVillage;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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

        BlockPos crucifixPos = findNearestCrucifix(living);

        if (living instanceof Villager villager) {
            Level level = villager.level();
            Monster nearestMonster = findNearestMonster(villager, level);
            if (nearestMonster != null) {

                if (crucifixPos != null) {
                    villagerMoveToCrucifixBlock(villager, crucifixPos);
                }
            }
        }
        if (living instanceof Monster monster) {

            if (crucifixPos != null) {
                monsterMoveAwayFromCrucifixBlock(monster, crucifixPos);
            }
        }
    }

    private static Monster findNearestMonster(Villager villager, Level level) {
        List<Monster> monsters = level.getNearbyEntities(Monster.class, TargetingConditions.DEFAULT, villager, villager.getBoundingBox().inflate(10.0D));
        if (monsters.isEmpty()) return null;
        return monsters.get(0);
    }

    @Nullable
    private static BlockPos findNearestCrucifix(LivingEntity living) {
        if (living.level().isClientSide()) return null;

        ServerLevel level = (ServerLevel) living.level();
        PoiManager poiManager = level.getPoiManager();

        Optional<BlockPos> nearest = poiManager.findClosest(
                poiTypeHolder -> {
                    if (CCVillager.LEADER_PRIEST_POI.getKey() != null) {
                        return poiTypeHolder.is(CCVillager.LEADER_PRIEST_POI.getKey());
                    }
                    return false;
                },
                living.blockPosition(),
                16,
                PoiManager.Occupancy.ANY
        );

        return nearest.orElse(null);
    }

    private static void villagerMoveToCrucifixBlock(Villager villager, BlockPos pos) {
        villager.getNavigation().moveTo(pos.getX(), pos.getY(), pos.getZ(), 0.5D);
    }

    private static void monsterMoveAwayFromCrucifixBlock(Monster monster, BlockPos pos) {
        Vec3 away = DefaultRandomPos.getPosAway(monster, 4, 2, Vec3.atCenterOf(pos));
        if (away != null) {
            monster.getNavigation().moveTo(away.x, away.y, away.z, 1.2D);
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
