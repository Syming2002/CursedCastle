package com.syming.minevania.entity.ai;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityCreature;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class RandomWaterPositionGenerator {


    /**
     * used to store a driection when the user passes a point to move towards or away from. WARNING: NEVER THREAD SAFE.
     * MULTIPLE findTowards and findAway calls, will share this var
     */
    private static Vec3d staticVector = Vec3d.ZERO;

    /**
     * finds a random target within par1(x,z) and par2 (y) blocks
     */
    @Nullable
    public static Vec3d findRandomTarget(EntityCreature entitycreatureIn, int xz, int y)
    {
        return findRandomTargetBlock(entitycreatureIn, xz, y, (Vec3d)null);
    }

    /**
     * finds a random target within par1(x,z) and par2 (y) blocks in the direction of the point par3
     */
    @Nullable
    public static Vec3d findRandomTargetBlockTowards(EntityCreature entitycreatureIn, int xz, int y, Vec3d targetVec3)
    {
        staticVector = targetVec3.subtract(entitycreatureIn.posX, entitycreatureIn.posY, entitycreatureIn.posZ);
        return findRandomTargetBlock(entitycreatureIn, xz, y, staticVector);
    }

    /**
     * finds a random target within par1(x,z) and par2 (y) blocks in the reverse direction of the point par3
     */
    @Nullable
    public static Vec3d findRandomTargetBlockAwayFrom(EntityCreature entitycreatureIn, int xz, int y, Vec3d targetVec3)
    {
        staticVector = (new Vec3d(entitycreatureIn.posX, entitycreatureIn.posY, entitycreatureIn.posZ)).subtract(targetVec3);
        return findRandomTargetBlock(entitycreatureIn, xz, y, staticVector);
    }

    /**
     * searches 10 blocks at random in a within par1(x,z) and par2 (y) distance, ignores those not in the direction of
     * par3Vec3, then points to the tile for which creature.getBlockPathWeight returns the highest number
     */
    @Nullable
    private static Vec3d findRandomTargetBlock(EntityCreature entitycreatureIn, int xz, int y, @Nullable Vec3d targetVec3)
    {
        return generateRandomWaterPos(entitycreatureIn, xz, y, targetVec3, true);
    }

    @Nullable
    public static Vec3d getWaterPosition(EntityCreature creature, int range, int verticalRange) {
        return generateRandomWaterPos(creature, range, verticalRange, (Vec3d) null, false);
    }

    @Nullable
    private static Vec3d generateRandomWaterPos(EntityCreature creature, int range, int verticalRange, @Nullable Vec3d targetVec3d, boolean b)
    {
        Random random = creature.getRNG();
        PathNavigate pathnavigate = creature.getNavigator();

        boolean flag;

        if (creature.hasHome())
        {
            double d0 = creature.getHomePosition().distanceSq((double) MathHelper.floor(creature.posX), (double)MathHelper.floor(creature.posY), (double)MathHelper.floor(creature.posZ)) + 4.0D;
            double d1 = (double)(creature.getMaximumHomeDistance() + (float)range);
            flag = d0 < d1 * d1;
        }
        else
        {
            flag = false;
        }

        boolean flag1 = false;
        float f = -99999.0F;
        int k1 = 0;
        int i = 0;
        int j = 0;

        for (int k = 0; k < 10; ++k)
        {
            int l = random.nextInt(2 * range + 1) - range;
            int i1 = random.nextInt(2 * verticalRange + 1) - verticalRange;
            int j1 = random.nextInt(2 * range + 1) - range;

            if (targetVec3d == null || (double)l * targetVec3d.x + (double)j1 * targetVec3d.z >= 0.0D)
            {
                if (creature.hasHome() && range > 1)
                {
                    BlockPos blockpos = creature.getHomePosition();

                    if (creature.posX > (double)blockpos.getX())
                    {
                        l -= random.nextInt(range / 2);
                    }
                    else
                    {
                        l += random.nextInt(range / 2);
                    }

                    if (creature.posZ > (double)blockpos.getZ())
                    {
                        j1 -= random.nextInt(range / 2);
                    }
                    else
                    {
                        j1 += random.nextInt(range / 2);
                    }
                }

                BlockPos blockpos1 = new BlockPos((double)l + creature.posX, (double)i1 + creature.posY - 1, (double)j1 + creature.posZ);

                if ((!flag || creature.isWithinHomeDistanceFromPosition(blockpos1)) && pathnavigate.canEntityStandOnPos(blockpos1))
                {
                    if (!b)
                    {
                        blockpos1 = moveAboveWater(blockpos1, creature);

                        if (isSolidBlockDestination(blockpos1, creature))
                        {
                            continue;
                        }
                    }

                    float f1 = creature.getBlockPathWeight(blockpos1);

                    if (f1 > f)
                    {
                        f = f1;
                        k1 = l;
                        i = i1;
                        j = j1;
                        flag1 = true;
                    }
                }
            }
        }

        if (flag1)
        {
            return new Vec3d((double)k1 + creature.posX, (double)i + creature.posY, (double)j + creature.posZ);
        }
        else
        {
            return null;
        }
    }

    private static BlockPos moveAboveWater(BlockPos pos, EntityCreature creature)
    {
        if (creature.world.getBlockState(pos).getMaterial() == Material.WATER)
        {
            return pos;
        }
        else
        {
            BlockPos blockpos;

            for (blockpos = pos.up(); blockpos.getY() < creature.world.getHeight() && creature.world.getBlockState(blockpos).getMaterial() == Material.WATER; blockpos = blockpos.up())
            {
                ;
            }

            return blockpos;
        }
    }

    private static boolean isSolidBlockDestination(BlockPos pos, EntityCreature creature)
    {
        return creature.world.getBlockState(pos).getMaterial().isSolid();
    }

}
