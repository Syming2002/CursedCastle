package com.syming.castlevaniacraft.entity.mobs.shade;

import com.syming.castlevaniacraft.entity.ai.shade.AIRepulseAttack;
import com.syming.castlevaniacraft.entity.mobs.CCEntityMob;

import net.ilexiconn.llibrary.server.animation.Animation;
import net.ilexiconn.llibrary.server.animation.AnimationAI;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class EntityShade extends CCEntityMob implements IAnimatedEntity {

    protected Animation animation = NO_ANIMATION;

    public static final Animation REPULSE_ATTACK_ANIMATION = Animation.create(180);
    protected int repulseAttackCooldown = 0;
    public static final int REPULSE_ATTACK_COOLDOWN = 150;

    protected int animationTick;

    protected static final Animation[] ANIMATIONS = {REPULSE_ATTACK_ANIMATION};


    public AnimationAI currentAnim;

    public EntityShade(World worldIn) {
        super(worldIn);

        this.setSize(1.33F, 2.0F);
        this.experienceValue = (int) 1.36;
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(2, new AIRepulseAttack(this, REPULSE_ATTACK_ANIMATION));
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<EntityVillager>(this, EntityVillager.class, true));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    }

    protected boolean isRepulseAnimation() {
        return animation == REPULSE_ATTACK_ANIMATION;
    }


}
