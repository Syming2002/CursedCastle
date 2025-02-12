package com.syming.minevania.util;

import com.syming.minevania.item.whips.ItemWhip;
import meldexun.reachfix.config.ReachFixConfig;
import meldexun.reachfix.util.BoundingBoxUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Modified code based on ReachFix
 * Credit to https://github.com/Meldexun/ReachFix
 */

public class ReachFixUtil {

    /**
     * Identical to getMouseOver from Reach Fix
     */
    public static void getMouseOver(float partialTicks) {
        Minecraft mc = Minecraft.getMinecraft();
        Entity viewEntity = mc.getRenderViewEntity();

        if (viewEntity == null) {
            return;
        }
        if (mc.player == null) {
            return;
        }
        if (mc.world == null) {
            return;
        }

        mc.profiler.startSection("pick");

        RayTraceResult target = pointedObject(viewEntity, mc.player, EnumHand.MAIN_HAND, mc.world, partialTicks);
        mc.objectMouseOver = target;
        mc.entityRenderer.pointedEntity = target.entityHit;
        mc.pointedEntity = target.entityHit;
        mc.profiler.endSection();
    }

    /**
     * Identical to pointedObject from Reach Fix except using getReach from ItemWhip
     */
    public static RayTraceResult pointedObject(Entity viewEntity, EntityPlayer player, EnumHand hand, World world, float partialTicks) {
        Vec3d start = viewEntity.getPositionEyes(partialTicks);
        Vec3d look = viewEntity.getLook(partialTicks);
        double reach = ItemWhip.getReach(player);
        Vec3d end = start.add(look.scale(reach));
        RayTraceResult pointedBlock = world.rayTraceBlocks(start, end, false, false, false);
        RayTraceResult pointedEntity = getPointedEntity(viewEntity, world, start, end, partialTicks);

        if (!isNullOrMiss(pointedBlock)) {
            if (!isNullOrMiss(pointedEntity)) {
                double distBlock = start.squareDistanceTo(pointedBlock.hitVec);
                double distEntity = start.squareDistanceTo(pointedEntity.hitVec);
                if (distBlock < distEntity) {
                    if (distBlock < reach * reach) {
                        return pointedBlock;
                    }
                } else if (distEntity < reach * reach) {
                    return pointedEntity;
                }
            } else if (start.squareDistanceTo(pointedBlock.hitVec) < reach * reach) {
                return pointedBlock;
            }
        } else if (!isNullOrMiss(pointedEntity) && start.squareDistanceTo(pointedEntity.hitVec) < reach * reach) {
            return pointedEntity;
        }

        return new RayTraceResult(RayTraceResult.Type.MISS, end, null, new BlockPos(end));
    }

    /**
     * Identical to getPointedEntity form ReachFix
     */
    @Nullable
    private static RayTraceResult getPointedEntity(Entity viewEntity, World world, Vec3d start, Vec3d end, float partialTicks) {
        AxisAlignedBB aabb = new AxisAlignedBB(start, end).grow(1.0D);
        Entity lowestRidingEntity = viewEntity.getLowestRidingEntity();
        List<Entity> possibleEntities = world.getEntitiesInAABBexcluding(viewEntity, aabb, entity -> {
            if (!EntitySelectors.NOT_SPECTATING.apply(entity)) {
                return false;
            }
            return entity.canBeCollidedWith();
        });

        RayTraceResult result = null;
        Entity pointedEntity = null;
        double min = Double.MAX_VALUE;
        for (Entity entity : possibleEntities) {
            AxisAlignedBB entityAabb = BoundingBoxUtil.getInteractionBoundingBox(entity, partialTicks);
            if (lowestRidingEntity == entity.getLowestRidingEntity() && !entity.canRiderInteract()) {
                if (ReachFixConfig.getInstance().forceInteractionInsideVehicles && entityAabb.contains(start)) {
                    return new RayTraceResult(entity, start);
                }
                continue;
            }

            if (entityAabb.contains(start)) {
                return new RayTraceResult(entity, start);
            }

            RayTraceResult rtr = entityAabb.calculateIntercept(start, end);
            if (isNullOrMiss(rtr)) {
                continue;
            }

            double dist = start.squareDistanceTo(rtr.hitVec);
            if (dist < min) {
                result = rtr;
                pointedEntity = entity;
                min = dist;
            }
        }

        if (isNullOrMiss(result)) {
            return null;
        }

        return new RayTraceResult(pointedEntity, result.hitVec);
    }

    /**
     * Identical to isNullOrMiss form ReachFix
     */
    private static boolean isNullOrMiss(RayTraceResult rayTraceResult) {
        return rayTraceResult == null || rayTraceResult.typeOfHit == RayTraceResult.Type.MISS;
    }

}
