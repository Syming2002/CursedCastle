package com.syming.castlevaniacraft.item.subweapon;

import com.syming.castlevaniacraft.world.entity.projectile.subweapon.HolyWaterEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HolyWaterItem extends Item {
    public HolyWaterItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack throwStack = pPlayer.getItemInHand(pUsedHand);
        pLevel.playSound(pPlayer, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.SPLASH_POTION_THROW, SoundSource.MASTER, 0.5F, 1.0F);
        if (!pLevel.isClientSide()) {
            HolyWaterEntity holyWater = new HolyWaterEntity(pPlayer, pLevel, throwStack);
            holyWater.setItem(throwStack);
            holyWater.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 1.0F);
            pLevel.addFreshEntity(holyWater);
            pPlayer.getCooldowns().addCooldown(this, 40);
        }
        pPlayer.awardStat(Stats.ITEM_USED.get(this));
        if (!pPlayer.getAbilities().instabuild) {
            throwStack.shrink(1);
        }
        return InteractionResultHolder.sidedSuccess(throwStack, pLevel.isClientSide());
    }
}
