package com.syming.cursedcastle.item.subweapon;

import com.syming.cursedcastle.sound.CCSound;
import com.syming.cursedcastle.world.entity.projectile.subweapon.ThrowingAxeEntity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class ThrowingAxeItem extends Item {

    private Tier tier;

    public ThrowingAxeItem(Tier pTier, Properties pProperties) {
        super(pProperties);
        this.tier = pTier;
    }

    public Tier getTier() {
        return this.tier;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 36000;
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        if (pLivingEntity instanceof Player player) {
            int loading = getUseDuration(pStack) - pTimeCharged;
            if (loading > 10) {
                if (!pLevel.isClientSide) {
                    ThrowingAxeEntity throwingAxeEntity = new ThrowingAxeEntity(player, pLevel, pStack, getTier());
                    throwingAxeEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);

                    throwingAxeEntity.setItem(pStack);
                    pLevel.addFreshEntity(throwingAxeEntity);
                    pLevel.playSound((Player) null, throwingAxeEntity, CCSound.THROWING_AXE_THROW.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                    if (!player.getAbilities().instabuild) {
                        pStack.shrink(1);
                    }
                }
                player.awardStat(Stats.ITEM_USED.get(this));
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack stack = pPlayer.getItemInHand(pHand);
        if (stack.getDamageValue() >= stack.getMaxDamage() - 1) {
            return InteractionResultHolder.fail(stack);
        } else {
            pPlayer.startUsingItem(pHand);
            return InteractionResultHolder.consume(stack);
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pStack.hurtAndBreak(1, pAttacker, (p_43414_) -> p_43414_.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return true;
    }
}
