package com.syming.cursedcastle.item.projectile.subweapon;

import com.syming.cursedcastle.sound.CCSound;
import com.syming.cursedcastle.entity.projectile.subweapon.HolyBibleEntity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HolyBibleItem extends Item {
    public HolyBibleItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack throwStack = pPlayer.getItemInHand(pUsedHand);
        pLevel.playSound(pPlayer, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), CCSound.HOLY_CROSS_THROW.get(), SoundSource.MASTER, 0.5F, 1.0F);
        if (!pLevel.isClientSide()) {
            for (int i = 0; i < 40; i++) {
                HolyBibleEntity holyBible = new HolyBibleEntity(pPlayer, pLevel, throwStack);
                holyBible.setPos(pPlayer.getX(), pPlayer.getY() + pPlayer.getEyeHeight() / 2, pPlayer.getZ());
                pLevel.addFreshEntity(holyBible);
                throwStack.shrink(1);
                pPlayer.getCooldowns().addCooldown(this, 150);
            }
        }
        return InteractionResultHolder.sidedSuccess(throwStack, pLevel.isClientSide());
    }
}
