package com.syming.castlevaniacraft.event;

import com.syming.castlevaniacraft.entity.EntityCrucifix;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class EventVillagerCrucifixProtection {

    private static final double CRUCIFIX_RADIUS = 5.0D;
    private static final double MOB_DETECTION_RADIUS = 10.0D;

    @SubscribeEvent
    public void onVillagerUpdate(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntityLiving() instanceof EntityVillager) {
            EntityVillager villager = (EntityVillager) event.getEntityLiving();
            World world = villager.getWorld();

            EntityMob nearestMob = findNearestMob(villager, world);

            if (nearestMob != null) {

                EntityCrucifix nearestCrucifix = findNearestCrucifix(villager, world);

                if (nearestCrucifix != null) {
                    moveVillagerTowardsCrucifix(villager, nearestCrucifix);
                }
            }
        }

        if (event.getEntityLiving() instanceof EntityMob) {
            EntityMob mob = (EntityMob) event.getEntityLiving();

            mob.tasks.addTask(1, new EntityAIAvoidEntity<>(mob, EntityCrucifix.class, 6.0F, 1.2D, 1.4D));
        }
    }

    private EntityMob findNearestMob(EntityVillager villager, World world) {
        List<EntityMob> mobs = world.getEntitiesWithinAABB(EntityMob.class, villager.getEntityBoundingBox().grow(MOB_DETECTION_RADIUS));
        if (!mobs.isEmpty()) {
            return mobs.get(0);
        }
        return null;
    }

    private void moveVillagerTowardsCrucifix(EntityVillager villager, EntityCrucifix nearestCrucifix) {
        BlockPos crucifixPos = new BlockPos(nearestCrucifix.posX, nearestCrucifix.posY, nearestCrucifix.posZ);

        villager.getNavigator().tryMoveToXYZ(crucifixPos.getX(), crucifixPos.getY(), crucifixPos.getZ(), 1.2D);
    }

    private EntityCrucifix findNearestCrucifix(EntityLiving living, World world) {
        List<EntityCrucifix> crucifixList = world.getEntitiesWithinAABB(EntityCrucifix.class, living.getEntityBoundingBox().grow(CRUCIFIX_RADIUS));
        if (!crucifixList.isEmpty()) {
            return crucifixList.get(0);
        }
        return null;
    }
}
