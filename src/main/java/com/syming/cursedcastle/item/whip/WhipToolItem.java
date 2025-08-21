package com.syming.cursedcastle.item.whip;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;

public abstract class WhipToolItem extends WhipItem {

    protected final float speed;

    public WhipToolItem(Properties pProperties, int pAttackDamageModifier, float pAttackSpeedModifier, float pReachModifier, float pSpeed) {
        super(pProperties, pAttackDamageModifier, pAttackSpeedModifier, pReachModifier);
        this.speed = pSpeed;
    }
    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        return this.speed;
    }

    @Override
    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return !pPlayer.isCreative();
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        if (!pLevel.isClientSide && pState.getDestroySpeed(pLevel, pPos) != 0.0F) {
            pStack.hurtAndBreak(1, pEntityLiving, (p_40992_) -> p_40992_.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        }

        return true;
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return !state.is(Tags.Blocks.OBSIDIAN);
    }
}
