package com.syming.minevania.entity.util;

import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class PathNavigateOnWater extends PathNavigateGround {
    private boolean shouldAvoidGround;

    public PathNavigateOnWater(EntityLiving entityIn, World worldIn) {
        super(entityIn, worldIn);
    }

    @Override
    protected PathFinder getPathFinder() {
        this.nodeProcessor = new WalkNodeProcessor();
        return new PathFinder(this.nodeProcessor);
    }

//    @Override
//    protected Vec3d getEntityPosition() {
//        return new Vec3d(this.entity.posX, (double) this.getPathablePosY(), this.entity.posZ);
//    }

//    @Override
//    protected boolean canNavigate() {
//        return this.isInLiquid() && this.getCanSwim() || this.entity.isRiding();
//    }
//
//    @Override
//    protected boolean isDirectPathBetweenPoints(Vec3d posVec31, Vec3d posVec32, int sizeX, int sizeY, int sizeZ) {
//        int i = MathHelper.floor(posVec31.x);
//        int j = MathHelper.floor(posVec31.z);
//        double d0 = posVec32.x - posVec31.x;
//        double d1 = posVec32.z - posVec31.z;
//        double d2 = d0 * d0 + d1 * d1;
//
//        if (d2 < 1.0E-8D) {
//            return false;
//        } else {
//            double d3 = 1.0D / Math.sqrt(d2);
//            d0 = d0 * d3;
//            d1 = d1 * d3;
//            sizeX = sizeX + 2;
//            sizeZ = sizeZ + 2;
//
//            if (!this.isSafeToStandAt(i, (int) posVec31.y, j, sizeX, sizeY, sizeZ, posVec31, d0, d1)) {
//                return false;
//            } else {
//                sizeX = sizeX - 2;
//                sizeZ = sizeZ - 2;
//                double d4 = 1.0D / Math.abs(d0);
//                double d5 = 1.0D / Math.abs(d1);
//                double d6 = (double) i - posVec31.x;
//                double d7 = (double) j - posVec31.z;
//
//                if (d0 >= 0.0D) {
//                    ++d6;
//                }
//
//                if (d1 >= 0.0D) {
//                    ++d7;
//                }
//
//                d6 = d6 / d0;
//                d7 = d7 / d1;
//                int k = d0 < 0.0D ? -1 : 1;
//                int l = d1 < 0.0D ? -1 : 1;
//                int i1 = MathHelper.floor(posVec32.x);
//                int j1 = MathHelper.floor(posVec32.z);
//                int k1 = i1 - i;
//                int l1 = j1 - j;
//
//                while (k1 * k > 0 || l1 * l > 0) {
//                    if (d6 < d7) {
//                        d6 += d4;
//                        i += k;
//                        k1 = i1 - i;
//                    } else {
//                        d7 += d5;
//                        j += l;
//                        l1 = j1 - j;
//                    }
//
//                    if (!this.isSafeToStandAt(i, (int) posVec31.y, j, sizeX, sizeY, sizeZ, posVec31, d0, d1)) {
//                        return false;
//                    }
//                }
//
//                return true;
//            }
//        }
//    }

    /**
     * Sets a new path. If it's diferent from the old path. Checks to adjust path for sun avoiding, and stores start
     * coords. Args : path, speed
     */
    @Override
    public boolean setPath(@Nullable Path pathentityIn, double speedIn) {
//        this.removeGroundPath();
        return super.setPath(pathentityIn, speedIn);
    }

//    /**
//     * Returns true when an entity could stand at a position, including solid blocks under the entire entity.
//     */
//    private boolean isSafeToStandAt(int x, int y, int z, int sizeX, int sizeY, int sizeZ, Vec3d vec31, double p_179683_8_, double p_179683_10_) {
//        int i = x - sizeX / 2;
//        int j = z - sizeZ / 2;
//
//        if (!this.isPositionClear(i, y, j, sizeX, sizeY, sizeZ, vec31, p_179683_8_, p_179683_10_)) {
//            return false;
//        } else {
//            for (int k = i; k < i + sizeX; ++k) {
//                for (int l = j; l < j + sizeZ; ++l) {
//                    double d0 = (double) k + 0.5D - vec31.x;
//                    double d1 = (double) l + 0.5D - vec31.z;
//
//                    if (d0 * p_179683_8_ + d1 * p_179683_10_ >= 0.0D) {
//                        PathNodeType pathnodetype = this.nodeProcessor.getPathNodeType(this.world, k, y - 1, l, this.entity, sizeX, sizeY, sizeZ, false, false);
//
//                        if (pathnodetype == PathNodeType.WATER) {
//                            return true;
//                        }
//
//                        if (pathnodetype == PathNodeType.LAVA) {
//                            return false;
//                        }
//
//                        if (pathnodetype == PathNodeType.OPEN) {
//                            return false;
//                        }
//
//                        pathnodetype = this.nodeProcessor.getPathNodeType(this.world, k, y, l, this.entity, sizeX, sizeY, sizeZ, false, false);
//                        float f = this.entity.getPathPriority(pathnodetype);
//
//                        if (f < 0.0F || f >= 8.0F) {
//                            return false;
//                        }
//
//                        if (pathnodetype == PathNodeType.DAMAGE_FIRE || pathnodetype == PathNodeType.DANGER_FIRE || pathnodetype == PathNodeType.DAMAGE_OTHER) {
//                            return false;
//                        }
//                    }
//                }
//            }
//
//            return true;
//        }
//    }

//    /**
//     * Returns true if an entity does not collide with any solid blocks at the position.
//     */
//    private boolean isPositionClear(int x, int y, int z, int sizeX, int sizeY, int sizeZ, Vec3d p_179692_7_, double p_179692_8_, double p_179692_10_) {
//        for (BlockPos blockpos : BlockPos.getAllInBox(new BlockPos(x, y, z), new BlockPos(x + sizeX - 1, y + sizeY - 1, z + sizeZ - 1))) {
//            double d0 = (double) blockpos.getX() + 0.5D - p_179692_7_.x;
//            double d1 = (double) blockpos.getZ() + 0.5D - p_179692_7_.z;
//
//            if (d0 * p_179692_8_ + d1 * p_179692_10_ >= 0.0D) {
//                Block block = this.world.getBlockState(blockpos).getBlock();
//
//                if (!block.isPassable(this.world, blockpos)) {
//                    return false;
//                }
//            }
//        }
//
//        return true;
//    }

    @Override
    public boolean canEntityStandOnPos(BlockPos pos) {
        return this.world.getBlockState(pos.down()).getBlock() == Blocks.WATER || this.world.getBlockState(pos.down()).getBlock() == Blocks.FLOWING_WATER;
    }

//    public boolean getCanSwim() {
//        return this.nodeProcessor.getCanSwim();
//    }
//
//    /**
//     * Gets the safe pathing Y position for the entity depending on if it can path swim or not
//     */
//    private int getPathablePosY() {
//        if (this.entity.isInWater() && this.getCanSwim()) {
//            int i = (int) this.entity.getEntityBoundingBox().minY;
////            Block block = this.world.getBlockState(new BlockPos(MathHelper.floor(this.entity.posX), i, MathHelper.floor(this.entity.posZ))).getBlock();
//            Material material = this.world.getBlockState(new BlockPos(MathHelper.floor(this.entity.posX), i, MathHelper.floor(this.entity.posZ))).getMaterial();
//            int j = 0;
//
//            while (material == Material.WATER) {
//                ++i;
//                material = this.world.getBlockState(new BlockPos(MathHelper.floor(this.entity.posX), i, MathHelper.floor(this.entity.posZ))).getMaterial();
//                ++j;
//
//                if (j > 16) {
//                    return (int) this.entity.getEntityBoundingBox().minY;
//                }
//            }
//
//            return i;
//        } else {
//            return (int) (this.entity.getEntityBoundingBox().minY + 0.5D);
//        }
//    }
//
//    private void removeGroundPath() {
//        if (this.shouldAvoidGround) {
//            if (this.world.getBlockState(new BlockPos(MathHelper.floor(this.entity.posX), (int) (this.entity.getEntityBoundingBox().minY + 0.5D), MathHelper.floor(this.entity.posZ))).getMaterial() == Material.WATER) {
//                return;
//            }
//
//            if (this.currentPath != null) {
//                for (int i = 0; i < this.currentPath.getCurrentPathLength(); ++i) {
//                    PathPoint pathpoint = this.currentPath.getPathPointFromIndex(i);
//
//                    if (this.world.getBlockState(new BlockPos(pathpoint.x, pathpoint.y, pathpoint.z)).getMaterial() == Material.WATER) {
//                        this.currentPath.setCurrentPathLength(i - 1);
//                        return;
//                    }
//                }
//            }
//        }
//    }
//
//    public void setAvoidGround(boolean avoidGround) {
//        this.shouldAvoidGround = avoidGround;
//    }
}
