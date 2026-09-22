package com.syming.cursedcastle.item.projectile.subweapon;

import com.syming.cursedcastle.item.CCThrowableItem;
import com.syming.cursedcastle.sound.CCSound;
import com.syming.cursedcastle.entity.projectile.subweapon.ThrowingAxeEntity;
import com.syming.cursedcastle.util.ThrowingAxeUtil;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ThrowingAxeItem extends CCThrowableItem {

    public ThrowingAxeItem(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 36000;
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        if (pLivingEntity instanceof Player player) {
            int loading = getUseDuration(pStack, pLivingEntity) - pTimeCharged;
            if (loading > 10) {
                if (!pLevel.isClientSide) {
                    ThrowingAxeEntity throwingAxeEntity = new ThrowingAxeEntity(player, pLevel, pStack, getTier());
                    throwingAxeEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);

                    throwingAxeEntity.setItem(pStack);
                    pLevel.addFreshEntity(throwingAxeEntity);
                    pLevel.playSound((Player) null, throwingAxeEntity, SoundEvents.SNOWBALL_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
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
        pStack.hurtAndBreak(1, pAttacker, EquipmentSlot.MAINHAND);
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        Level level = context.level();
        if (level != null) {
            ThrowingAxeUtil.addThrowingAxeTooltip(level, stack, tooltipComponents);
        }
    }

    @Override
    @Deprecated
    public int getEnchantmentValue() {
        return 15;
    }



    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return true;
    }
}
