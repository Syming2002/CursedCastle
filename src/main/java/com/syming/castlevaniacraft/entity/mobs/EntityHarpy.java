package com.syming.castlevaniacraft.entity.mobs;

import com.syming.castlevaniacraft.api.IElementalWeapon;
import com.syming.castlevaniacraft.damagesource.CCDamageSource;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityHarpy extends CCEntityMob implements IAnimatedEntity, EntityFlying {

    private Animation animation = NO_ANIMATION;
    public static final Animation FEATHER_ATTACK = Animation.create(35);
    private static final Animation[] ANIMATIONS = {FEATHER_ATTACK};
    private int animationTick;
    public AnimationAI currentAnim;

    public EntityHarpy(World worldIn) {
        super(worldIn);
    }

    @Override
    public boolean hasWeakness(DamageSource source, float damageAmount) {
        EntityPlayer player = (EntityPlayer) getAttackingEntity();
        if (player != null) {
            System.out.println("player is not null");
            ItemStack stack = player.getHeldItemMainhand();
            if (stack != null && stack.getItem() instanceof IElementalWeapon elementalWeapon) {
                System.out.println("item implements IElementalWeapon");
                if (elementalWeapon != null) {
                    source = elementalWeapon.getElementalDamageSource(stack, getAttackTarget(), getAttackingEntity());
                    System.out.println("source damage detected");
                    return source == CCDamageSource.getIceDamage();
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasStrength(DamageSource source, float damageAmount) {
        EntityPlayer player = (EntityPlayer) getAttackingEntity();
        if (player != null) {
            ItemStack stack = player.getHeldItemMainhand();
            if (stack != null && stack.getItem() instanceof IElementalWeapon elementalWeapon) {
                if (elementalWeapon != null) {
                    source = elementalWeapon.getElementalDamageSource(stack, getAttackTarget(), getAttackingEntity());
                    System.out.println("source damage detected");
                    return source == CCDamageSource.getWindDamage();
                }
            }
        }
        return false;
    }

    @Override
    public int getAnimationTick() {
        return this.animationTick;
    }

    @Override
    public void setAnimationTick(int tick) {
        this.animationTick = tick;
    }

    @Override
    public Animation getAnimation() {
        return this.animation;
    }

    @Override
    public void setAnimation(Animation animation) {
        if (animation == NO_ANIMATION) {
            onAnimationFinish(this.animation);
            setAnimationTick(0);
        }
        this.animation = animation;
    }

    protected void onAnimationFinish(Animation animation) {

    }

    @Override
    public Animation[] getAnimations() {
        return ANIMATIONS;
    }
}
