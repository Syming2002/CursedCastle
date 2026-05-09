package com.syming.cursedcastle.item.projectile.subweapon;

import com.syming.cursedcastle.entity.projectile.subweapon.FireHolyCrossEntity;
import com.syming.cursedcastle.entity.projectile.subweapon.IceHolyCrossEntity;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.CCThrowableItem;
import com.syming.cursedcastle.item.spellbook.SpellBookElementType;
import com.syming.cursedcastle.mp.PlayerMP;
import com.syming.cursedcastle.mp.PlayerMPProvider;
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
import top.theillusivec4.curios.api.CuriosApi;

import java.util.List;

import static com.syming.cursedcastle.util.SpellBookUtil.isWearingSpellBook;

public class HolyCrossItem extends CCThrowableItem {

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
            ItemStack thrownStackCopy = throwStack.copy();
            if (isWearingSpellBook(SpellBookElementType.FIRE, pPlayer)) {
                FireHolyCrossEntity fireHolyCross = new FireHolyCrossEntity(pLevel, pPlayer);
                pLevel.addFreshEntity(fireHolyCross);
                PlayerMP playerMP = pPlayer.getCapability(PlayerMPProvider.PLAYER_MP).orElseGet(PlayerMP::new);
                playerMP.removeMP(50);
            } else if (isWearingSpellBook(SpellBookElementType.ICE, pPlayer)) {
                IceHolyCrossEntity iceHolyCross = new IceHolyCrossEntity(pLevel, pPlayer);
                pLevel.addFreshEntity(iceHolyCross);
                PlayerMP playerMP = pPlayer.getCapability(PlayerMPProvider.PLAYER_MP).orElseGet(PlayerMP::new);
                playerMP.removeMP(30);
            } else {
                HolyCrossEntity holyCross = new HolyCrossEntity(pPlayer, pLevel, thrownStackCopy, getTier());
                pLevel.addFreshEntity(holyCross);
                throwStack.shrink(1);
            }
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
