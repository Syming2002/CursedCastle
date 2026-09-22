package com.syming.cursedcastle.event;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.capability.CCCapabilities;
import com.syming.cursedcastle.capability.data.PlayerData;
import com.syming.cursedcastle.capability.data.mp.PlayerMP;
import com.syming.cursedcastle.damagetype.CCDamageType;
import com.syming.cursedcastle.effect.CCEffect;
import com.syming.cursedcastle.entity.monster.CCFlyingMonster;
import com.syming.cursedcastle.entity.projectile.subweapon.HolyWaterEntity;
import com.syming.cursedcastle.entity.villager.CCVillager;
import com.syming.cursedcastle.network.CCMPData;
import com.syming.cursedcastle.network.CCPlayerData;
import com.syming.cursedcastle.potion.CCPotion;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@EventBusSubscriber(modid = CursedCastleMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class CCCommonEventForge {

    private static final List<ScheduledTask> tasks = new ArrayList<>();

//    @SubscribeEvent
//    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
//        event.registerEntity(CCCapabilities.PLAYER_DATA, EntityType.PLAYER, (entity, context) -> new PlayerData());
//        event.registerEntity(CCCapabilities.PLAYER_MP, EntityType.PLAYER, (entity, context) -> new PlayerMP());
//    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        Player oldPlayer = event.getOriginal();
        Player newPlayer = event.getEntity();

        Level oldPlayerLevel = oldPlayer.level();
        Level newPlayerLevel = newPlayer.level();

        PlayerMP oldPlayerMP = oldPlayer.getData(CCCapabilities.PLAYER_MP);
        PlayerMP newPlayerMP = newPlayer.getData(CCCapabilities.PLAYER_MP);

        PlayerData oldPlayerData = oldPlayer.getData(CCCapabilities.PLAYER_DATA);
        PlayerData newPlayerData = newPlayer.getData(CCCapabilities.PLAYER_DATA);

        if (event.isWasDeath()) {
            if (newPlayerMP != null && oldPlayerMP != null) {
                newPlayerMP.copyFrom(oldPlayerMP);

            }
            if (newPlayerData != null && oldPlayerData != null) {
                newPlayerData.setHolyBeamTicks(oldPlayerData.getHolyBeamTicks());
                newPlayerData.setBoltAttackEnabled(oldPlayerData.isBoltAttackEnabled());
                newPlayerData.setPlayerYPos(oldPlayerData.getPlayerYPos());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();
        PlayerMP playerMP = player.getData(CCCapabilities.PLAYER_MP);
        PlayerData playerData = player.getData(CCCapabilities.PLAYER_DATA);

        if (player instanceof ServerPlayer serverPlayer){
            if (playerMP != null) {
                if (playerMP.getCurrentMP() < playerMP.getMaxMP() && serverPlayer.tickCount % 20 == 0 && (!serverPlayer.isCreative() && !serverPlayer.isSpectator())) {
                    if (serverPlayer.experienceLevel <= 10) {
                        playerMP.addMP(1);
                    } else if (serverPlayer.experienceLevel <= 20) {
                        playerMP.addMP(2);
                    } else if (serverPlayer.experienceLevel <= 30) {
                        playerMP.addMP(3);
                    } else if (serverPlayer.experienceLevel <= 40) {
                        playerMP.addMP(4);
                    } else if (serverPlayer.experienceLevel <= 50) {
                        playerMP.addMP(5);
                    }
                    PacketDistributor.sendToPlayer(serverPlayer, new CCMPData(playerMP.getCurrentMP()));
                }
            }

            if (playerData != null) {
                if (playerData.isBoltAttackEnabled()) {

                    double playerX = player.getX();
                    double playerY = playerData.getPlayerYPos();
                    double playerZ = player.getZ();

                    player.setNoGravity(true);

                    PacketDistributor.sendToPlayer(serverPlayer, new CCPlayerData(playerData.getHolyBeamTicks(), playerData.isBoltAttackEnabled(), playerData.getPlayerYPos()));

                    if (player.getY() < playerY) {
                        ((ServerPlayer) player).teleportTo(playerX, Math.min(player.getY() + 0.25D, playerY), playerZ);
                    }


                    List<LivingEntity> livingEntities = player.level().getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(2.0D), (living) -> living != player);

                    for (LivingEntity living : livingEntities) {
                        if (living instanceof Monster || living instanceof CCFlyingMonster) {
                            living.hurt(CCDamageType.causeThunderDamage(living), 10.0F);
                        }
                    }

                    if (playerData.getHolyBeamTicks() > 0) {
                        PacketDistributor.sendToPlayer(serverPlayer, new CCPlayerData(playerData.getHolyBeamTicks(), playerData.isBoltAttackEnabled(), playerData.getPlayerYPos()));
                        playerData.setHolyBeamTicks(playerData.getHolyBeamTicks() - 1);
                    }

                    if (playerData.getHolyBeamTicks() <= 0) {
                        player.setNoGravity(false);
                        player.setDeltaMovement(player.getDeltaMovement());
                        playerData.setBoltAttackEnabled(false);
                        PacketDistributor.sendToPlayer(serverPlayer, new CCPlayerData(playerData.getHolyBeamTicks(), playerData.isBoltAttackEnabled(), playerData.getPlayerYPos()));
                        playerData.setHolyBeamTicks(300);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        PlayerMP playerMP = player.getData(CCCapabilities.PLAYER_MP);
        PlayerData playerData = player.getData(CCCapabilities.PLAYER_DATA);

        if (playerMP != null && player instanceof ServerPlayer serverPlayer) {
            PacketDistributor.sendToPlayer(serverPlayer, new CCMPData(playerMP.getCurrentMP()));
        }

        if (playerData != null && player instanceof ServerPlayer serverPlayer) {
            PacketDistributor.sendToPlayer(serverPlayer, new CCPlayerData(playerData.getHolyBeamTicks(), playerData.isBoltAttackEnabled(), playerData.getPlayerYPos()));
        }
    }

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Pre event) {
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
    public static void onRainingEvent(LevelTickEvent.Pre event) {
        Level level = event.getLevel();

        if (level.isClientSide()) return;

        if (HolyWaterEntity.holyRainTicks > 0) {
            HolyWaterEntity.holyRainTicks--;
        }

        if (HolyWaterEntity.holyRainTicks <= 0) {
            HolyWaterEntity.rainCaster = null;
        }
    }

    @SubscribeEvent
    public static void onRainingEffectEvent(LivingDamageEvent.Pre event) {
        LivingEntity living = event.getEntity();

        if (living.level().isClientSide()) return;

        if (living instanceof Monster monster && monster.level().isRainingAt(monster.blockPosition()) && HolyWaterEntity.rainCaster != null) {
            monster.addEffect(new MobEffectInstance(CCEffect.HOLY, HolyWaterEntity.RAINING_COOLDOWN, 1, false, false, true));
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
                    if (CCVillager.LEADER_PRIEST_POI != null) {
                        return poiTypeHolder.is(CCVillager.LEADER_PRIEST_POI);
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

    @SubscribeEvent
    public static void addCCBrewingRecipes(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addStartMix(Items.PACKED_ICE, CCPotion.FREEZE);
        builder.addMix(CCPotion.FREEZE, Items.REDSTONE, CCPotion.LONG_FREEZE);

        builder.addStartMix(Items.ENCHANTED_GOLDEN_APPLE, CCPotion.HOLY);
        builder.addMix(CCPotion.HOLY, Items.REDSTONE, CCPotion.LONG_HOLY);
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
