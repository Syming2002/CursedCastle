package com.syming.minevania.entity.ai.zephyr;

import com.syming.minevania.entity.mobs.boss.EntityZephyr;
import com.syming.minevania.entity.projectiles.subweapons.EntityDagger;
import com.syming.minevania.item.CCItem;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;

public class AIDaggerAttack extends AnimationAI<EntityZephyr> {

    protected Animation animation;
    protected EntityZephyr zephyr;
    protected EntityLivingBase attackTarget;

    public AIDaggerAttack(EntityZephyr entity, Animation animation) {
        super(entity);
        setMutexBits(8);
        this.zephyr = entity;
        this.animation = animation;
        this.attackTarget = null;
    }

    @Override
    public Animation getAnimation() {
        return this.animation;
    }

    @Override
    public boolean isAutomatic() {
        return true;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
        zephyr.currentAnim = this;
        attackTarget = zephyr.getAttackTarget();
    }

    @Override
    public void resetTask() {
        super.resetTask();
        zephyr.currentAnim = null;
    }

    @Override
    public void updateTask() {
        super.updateTask();
        zephyr.faceEntity(attackTarget, 30.0F, 30.0F);

        Vec3d daggerPos = new Vec3d(0, 1.275,0);
        daggerPos = daggerPos.rotateYaw((float) Math.toRadians((-zephyr.rotationYaw - 90)));
        daggerPos = daggerPos.add(zephyr.getPositionVector());
        daggerPos = daggerPos.add(new Vec3d(0, 0, 1.5).rotatePitch((float) Math.toRadians(-zephyr.rotationPitch)).rotateYaw((float) Math.toRadians(-zephyr.rotationYawHead)));

        if (zephyr.getAnimationTick() == 24 && attackTarget != null) {
            launchDagger(daggerPos.x, daggerPos.y, daggerPos.z, 1.5F);
            System.out.println("Zephyr launches dagger");
        }
    }

    private void launchDagger(double posX, double posY, double posZ, double speed) {
        ItemStack woodenStack = new ItemStack(CCItem.woodenDagger);
        ItemStack stoneStack = new ItemStack(CCItem.stoneDagger);
        ItemStack ironStack = new ItemStack(CCItem.ironDagger);
        ItemStack goldenStack = new ItemStack(CCItem.goldenDagger);
        ItemStack diamondStack = new ItemStack(CCItem.diamondDagger);


        if (!zephyr.world.isRemote) {
            if (this.zephyr.getRNG().nextFloat() < 0.33F) {
                EntityDagger dagger = new EntityDagger(zephyr.world, zephyr, woodenStack, Item.ToolMaterial.WOOD);
                dagger.setPosition(posX, posY, posZ);
                dagger.shoot(zephyr, zephyr.rotationPitch, zephyr.rotationYaw, 0.0F, (float) speed, 0.2F);
                zephyr.world.spawnEntity(dagger);
            } else if (this.zephyr.getRNG().nextFloat() < 0.45F) {
                EntityDagger dagger = new EntityDagger(zephyr.world, zephyr, stoneStack, Item.ToolMaterial.STONE);
                dagger.setPosition(posX, posY, posZ);
                dagger.shoot(zephyr, zephyr.rotationPitch, zephyr.rotationYaw, 0.0F, (float) speed, 0.2F);
                zephyr.world.spawnEntity(dagger);
            } else if (this.zephyr.getRNG().nextFloat() < 0.5F) {
                EntityDagger dagger = new EntityDagger(zephyr.world, zephyr, ironStack, Item.ToolMaterial.IRON);
                dagger.setPosition(posX, posY, posZ);
                dagger.shoot(zephyr, zephyr.rotationPitch, zephyr.rotationYaw, 0.0F, (float) speed, 0.2F);
                zephyr.world.spawnEntity(dagger);
            } else if (this.zephyr.getRNG().nextFloat() < 0.40F) {
                EntityDagger dagger = new EntityDagger(zephyr.world, zephyr, goldenStack, Item.ToolMaterial.GOLD);
                dagger.setPosition(posX, posY, posZ);
                dagger.shoot(zephyr, zephyr.rotationPitch, zephyr.rotationYaw, 0.0F, (float) speed, 0.2F);
                zephyr.world.spawnEntity(dagger);
            } else if (this.zephyr.getRNG().nextFloat() < 0.25F) {
                EntityDagger dagger = new EntityDagger(zephyr.world, zephyr, diamondStack, Item.ToolMaterial.DIAMOND);
                dagger.setPosition(posX, posY, posZ);
                dagger.shoot(zephyr, zephyr.rotationPitch, zephyr.rotationYaw, 0.0F, (float) speed, 0.2F);
                zephyr.world.spawnEntity(dagger);
            }
        }
    }
}
