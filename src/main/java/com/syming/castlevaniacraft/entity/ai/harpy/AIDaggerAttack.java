package com.syming.castlevaniacraft.entity.ai.harpy;

import com.syming.castlevaniacraft.entity.mobs.EntityHarpy;
import com.syming.castlevaniacraft.entity.projectiles.subweapons.EntityDagger;
import com.syming.castlevaniacraft.item.CCItem;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;

public class AIDaggerAttack extends AnimationAI<EntityHarpy> {

    protected Animation animation;
    protected EntityHarpy harpy;
    protected EntityLivingBase attackTarget;

    public AIDaggerAttack(EntityHarpy entity, Animation animation) {
        super(entity);
        setMutexBits(8);
        this.harpy = entity;
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
        harpy.currentAnim = this;
        attackTarget = harpy.getAttackTarget();
    }

    @Override
    public void resetTask() {
        super.resetTask();
        harpy.currentAnim = null;
    }

    @Override
    public void updateTask() {
        super.updateTask();
        harpy.faceEntity(attackTarget, 30.0F, 30.0F);

        Vec3d daggerPos = new Vec3d(0, 0, 1.0F);
        daggerPos = daggerPos.rotatePitch((float) Math.toRadians(-this.harpy.rotationPitch));
        daggerPos = daggerPos.rotateYaw((float) Math.toRadians(-this.harpy.rotationYaw));
        daggerPos = daggerPos.add(this.harpy.posX, this.harpy.posY + 0.5D, this.harpy.posZ);

        if (harpy.getAnimationTick() == 25 && attackTarget != null) {
            launchDagger(daggerPos.x, daggerPos.y, daggerPos.z, 1.5F);
            System.out.println("Harpy launches dagger");
        }
    }

    private void launchDagger(double posX, double posY, double posZ, double speed) {
        ItemStack woodenStack = new ItemStack(CCItem.woodenDagger);
        ItemStack stoneStack = new ItemStack(CCItem.stoneDagger);
        ItemStack ironStack = new ItemStack(CCItem.ironDagger);
        ItemStack goldenStack = new ItemStack(CCItem.goldenDagger);
        ItemStack diamondStack = new ItemStack(CCItem.diamondDagger);


        if (!this.harpy.world.isRemote) {
            if (this.harpy.getRNG().nextFloat() < 0.33F) {
                EntityDagger dagger = new EntityDagger(harpy.world, harpy, woodenStack, Item.ToolMaterial.WOOD);
                dagger.setPosition(posX, posY, posZ);
                dagger.shoot(harpy, harpy.rotationPitch, harpy.rotationYaw, 0.0F, (float) speed, 0.1F);
                harpy.world.spawnEntity(dagger);
            } else if (this.harpy.getRNG().nextFloat() < 0.45F) {
                EntityDagger dagger = new EntityDagger(harpy.world, harpy, stoneStack, Item.ToolMaterial.STONE);
                dagger.setPosition(posX, posY, posZ);
                dagger.shoot(harpy, harpy.rotationPitch, harpy.rotationYaw, 0.0F, (float) speed, 0.1F);
                harpy.world.spawnEntity(dagger);
            } else if (this.harpy.getRNG().nextFloat() < 0.5F) {
                EntityDagger dagger = new EntityDagger(harpy.world, harpy, ironStack, Item.ToolMaterial.IRON);
                dagger.setPosition(posX, posY, posZ);
                dagger.shoot(harpy, harpy.rotationPitch, harpy.rotationYaw, 0.0F, (float) speed, 0.1F);
                harpy.world.spawnEntity(dagger);
            } else if (this.harpy.getRNG().nextFloat() < 0.40F) {
                EntityDagger dagger = new EntityDagger(harpy.world, harpy, goldenStack, Item.ToolMaterial.GOLD);
                dagger.setPosition(posX, posY, posZ);
                dagger.shoot(harpy, harpy.rotationPitch, harpy.rotationYaw, 0.0F, (float) speed, 0.1F);
                harpy.world.spawnEntity(dagger);
            } else if (this.harpy.getRNG().nextFloat() < 0.25F) {
                EntityDagger dagger = new EntityDagger(harpy.world, harpy, diamondStack, Item.ToolMaterial.DIAMOND);
                dagger.setPosition(posX, posY, posZ);
                dagger.shoot(harpy, harpy.rotationPitch, harpy.rotationYaw, 0.0F, (float) speed, 0.1F);
                harpy.world.spawnEntity(dagger);
            }
        }
    }
}
