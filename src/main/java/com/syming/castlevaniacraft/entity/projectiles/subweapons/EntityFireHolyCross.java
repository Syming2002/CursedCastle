package com.syming.castlevaniacraft.entity.projectiles.subweapons;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityFireHolyCross extends EntityHolyCross {
    public EntityFireHolyCross(World worldIn, EntityLivingBase thrower, ItemStack stack, Item.ToolMaterial material) {
        super(worldIn, thrower, stack, material);
    }

    public EntityFireHolyCross(World worldIn) {
        super(worldIn);
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
                        target.setFire(8);
                        stack.attemptDamageItem(1, this.rand, (EntityPlayerMP) getThrower());
                    }
                }
            }
        }
    }
}
