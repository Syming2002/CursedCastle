package com.syming.cursedcastle.item.subweapon;

import com.syming.cursedcastle.sound.CCSound;
import com.syming.cursedcastle.util.HolyCrossUtil;
import com.syming.cursedcastle.entity.projectile.subweapon.HolyCrossEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HolyCrossItem extends Item {

    private Tier tier;

    public HolyCrossItem(Tier pTier, Properties pProperties) {
        super(pProperties);
        this.tier = pTier;
    }

    public Tier getTier() {
        return this.tier;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack throwStack = pPlayer.getItemInHand(pUsedHand);
        pLevel.playSound(pPlayer, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), CCSound.HOLY_CROSS_THROW.get(), SoundSource.MASTER, 0.5F, 1.0F);
        if (!pLevel.isClientSide()) {
            HolyCrossEntity holyCross = new HolyCrossEntity(pPlayer, pLevel, throwStack, getTier());
            holyCross.setItem(throwStack);
            pLevel.addFreshEntity(holyCross);
            throwStack.shrink(1);
        }
        return InteractionResultHolder.sidedSuccess(throwStack, pLevel.isClientSide());
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (pStack.isDamageableItem()) {
            pStack.hurt(1, pAttacker.getRandom(), (ServerPlayer) pAttacker);
        }
        return true;
    }

    @Override
    public boolean isDamageable(ItemStack pStack) {
        return !pStack.isEmpty();
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        HolyCrossUtil.addHolyCrossTooltip(pStack, getTier(), pTooltip);
    }

    @Override
    @Deprecated
    public int getEnchantmentValue() {
        return 10;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment == Enchantments.SHARPNESS
                || enchantment == Enchantments.SMITE
                || enchantment == Enchantments.BANE_OF_ARTHROPODS
                || enchantment == Enchantments.FIRE_ASPECT
                || enchantment == Enchantments.MOB_LOOTING
                || enchantment == Enchantments.KNOCKBACK
                || enchantment == Enchantments.UNBREAKING;
    }

    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return true;
    }

}
