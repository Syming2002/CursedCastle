package com.syming.castlevaniacraft.entity.ai.dracula;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.syming.castlevaniacraft.entity.mobs.boss.EntityDracula;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nullable;

public class AIFindPlayer extends EntityAINearestAttackableTarget<EntityPlayer> {

    private final EntityDracula dracula;
    /** The player */
    private EntityPlayer player;
    private int aggroTime;
    private int teleportTime;

    public AIFindPlayer(EntityDracula dracula)
    {
        super(dracula, EntityPlayer.class, false);
        this.dracula = dracula;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute()
    {
        double d0 = this.getTargetDistance();
        this.player = this.dracula.world.getNearestAttackablePlayer(this.dracula.posX, this.dracula.posY, this.dracula.posZ, d0, d0, (Function)null, new Predicate<EntityPlayer>()
        {
            public boolean apply(@Nullable EntityPlayer p_apply_1_)
            {
                return p_apply_1_ != null && AIFindPlayer.this.dracula.shouldAttackPlayer(p_apply_1_);
            }
        });
        return this.player != null;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting()
    {
        this.aggroTime = 5;
        this.teleportTime = 0;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    @Override
    public void resetTask()
    {
        this.player = null;
        super.resetTask();
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean shouldContinueExecuting()
    {
        if (this.player != null)
        {
            if (!this.dracula.shouldAttackPlayer(this.player))
            {
                return false;
            }
            else
            {
                this.dracula.faceEntity(this.player, 10.0F, 10.0F);
                return true;
            }
        }
        else
        {
            return this.targetEntity != null && ((EntityPlayer)this.targetEntity).isEntityAlive() ? true : super.shouldContinueExecuting();
        }
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    @Override
    public void updateTask()
    {
        if (this.player != null)
        {
            if (--this.aggroTime <= 0)
            {
                this.targetEntity = this.player;
                this.player = null;
                super.startExecuting();
            }
        }
        else
        {
            if (this.targetEntity != null && this.targetEntity.isEntityAlive() && this.dracula.isEntityAlive())
            {
                if (this.dracula.shouldAttackPlayer((EntityPlayer)this.targetEntity))
                {
                    if (((EntityPlayer)this.targetEntity).getDistanceSq(this.dracula) < 16.0D)
                    {
                        this.dracula.teleportRandomlyNearTarget();
                    }

                    this.teleportTime = 0;
                }
                else if (((EntityPlayer)this.targetEntity).getDistanceSq(this.dracula) > 256.0D && this.teleportTime++ >= 30 && this.dracula.teleportToEntity(this.targetEntity))
                {
                    this.teleportTime = 0;
                }
            }

            super.updateTask();
        }
    }
}
