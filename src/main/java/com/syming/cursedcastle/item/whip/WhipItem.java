package com.syming.cursedcastle.item.whip;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.syming.cursedcastle.enchantment.CCEnchantment;
import com.syming.cursedcastle.item.CCWeaponItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

import java.util.UUID;

public abstract class WhipItem extends CCWeaponItem {

    public WhipItem(Properties pProperties, int pAttackDamageModifier, float pAttackSpeedModifier, float pReachModifier) {
        super(pProperties, pAttackDamageModifier, pAttackSpeedModifier, pReachModifier);
    }

    @Override
    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return !pPlayer.isCreative();
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pStack.hurtAndBreak(1, pAttacker, (p_43296_) -> p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return true;
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        return false;
    }

    @Override
    public boolean isCorrectToolForDrops(BlockState pBlock) {
        return false;
    }



    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return ToolActions.DEFAULT_SWORD_ACTIONS.contains(toolAction);
    }

    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return true;
    }

    @Override
    public int getEnchantmentValue(ItemStack stack) {
        return 12;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment == Enchantments.SHARPNESS
                || enchantment == Enchantments.SMITE
                || enchantment == Enchantments.BANE_OF_ARTHROPODS
                || enchantment == Enchantments.FIRE_ASPECT
                || enchantment == Enchantments.MOB_LOOTING
                || enchantment == Enchantments.KNOCKBACK
                || enchantment == Enchantments.UNBREAKING
                || enchantment == CCEnchantment.BLOOD_SKELETON_KILLER.get()
                || super.canApplyAtEnchantingTable(stack, enchantment);
    }
}
