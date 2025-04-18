package com.syming.castlevaniacraft.entity.projectiles.subweapons;

import com.syming.castlevaniacraft.api.IElementalWeapon;
import com.syming.castlevaniacraft.damagesource.CCDamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;

public class EntityFireHolyCross extends EntityHolyCross implements IElementalWeapon {

    private boolean spinning = false;
    private final int maxFireballs = 1;

    public EntityFireHolyCross(World worldIn, EntityLivingBase thrower, ItemStack stack, Item.ToolMaterial material) {
        super(worldIn, thrower, stack, material);
    }

    public EntityFireHolyCross(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        Entity hitEntity = result.entityHit;
        ItemStack stack = getItemStack();
        if (!this.world.isRemote && getThrower() != null) {
            if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
                if (!stack.isEmpty()) {
                    entityDropItem(stack, 0.5F);
                    stack.attemptDamageItem(1, this.rand, getThrower() instanceof EntityPlayerMP ? (EntityPlayerMP) getThrower() : null);
                    setDead();
                    System.out.println("Holy Cross has hit a block");

                } else {
                    System.out.println("Stack is Empty");
                }
            } else if (result.typeOfHit == RayTraceResult.Type.ENTITY) {
                if (hitEntity != null) {
                    if (hitEntity == getThrower()) {
                        if (getThrower() instanceof EntityPlayer player) {
                            if (stack.getMaxDamage() - stack.getItemDamage() > 0) {
                                player.inventory.addItemStackToInventory(stack);
                                this.world.playSound(player, posX, posY, posZ, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.MASTER, 0.5F, 1.0F);
                                setDead();
                                System.out.println("Holy Cross is back in inventory");
                            }
                        }
                    } else if (hitEntity instanceof EntityLiving target && hitEntity != getThrower()) {
                        target.setFire(5);
                        stack.attemptDamageItem(1, this.rand, (EntityPlayerMP) getThrower());
                    }
                }
            }
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        ItemStack stack = getItemStack();

        if (!this.world.isRemote) {
            this.ticksExisted++;

            if (this.ticksExisted >= 10) {
                this.motionX = 0;
                this.motionY = 0;
                this.motionZ = 0;
                this.velocityChanged = true;
                this.spinning = true;
            }

            if (this.ticksExisted >= 50) {
                shootRotatingFireballs();
                setDead();
            }
        }

        if (this.world.isRemote) {
            double offsetX = (this.rand.nextDouble() - 0.5D) * 0.5D;
            double offsetY = this.rand.nextDouble() * 0.02D;
            double offsetZ = (this.rand.nextDouble() - 0.5D) * 0.5D;

            double particleX = this.posX + offsetX;
            double particleY = this.posY + offsetY;
            double particleZ = this.posZ + offsetZ;

            double motionY = 0.02D + this.rand.nextDouble() * 0.02D;

            this.world.spawnParticle(EnumParticleTypes.FLAME, particleX, particleY, particleZ, 0.0D, motionY, 0.0D);
        }
    }

    private void shootRotatingFireballs() {
        double angle = (Math.PI / 3);

        double x1 = Math.cos(angle);
        double z1 = Math.sin(angle);

        double x2 = -x1;
        double z2 = -z1;

        shootFireball(x1, 0, z1);
        shootFireball(x2, 0, z2);

        this.world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.HOSTILE, 1.0F, 1.0F);
        setDead();
    }

    private void shootFireball(double x, double y, double z) {
        EntitySmallFireball fireball = new EntitySmallFireball(this.world, Objects.requireNonNull(getThrower()), x, y, z);

        fireball.setPosition(this.posX, this.posY + 0.5, this.posZ);
        this.world.spawnEntity(fireball);
    }

    @Override
    public DamageSource getElementalDamageSource(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        return CCDamageSource.getFireDamage();
    }
}
