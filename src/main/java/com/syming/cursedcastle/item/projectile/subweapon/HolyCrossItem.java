package com.syming.cursedcastle.item.projectile.subweapon;

import com.syming.cursedcastle.capability.CCCapabilities;
import com.syming.cursedcastle.capability.data.PlayerData;
import com.syming.cursedcastle.capability.data.mp.PlayerMP;
import com.syming.cursedcastle.entity.projectile.subweapon.FireHolyCrossEntity;
import com.syming.cursedcastle.entity.projectile.subweapon.IceHolyCrossEntity;
import com.syming.cursedcastle.item.CCThrowableItem;
import com.syming.cursedcastle.item.spellbook.SpellBookElementType;
import com.syming.cursedcastle.network.CCPlayerData;
import com.syming.cursedcastle.sound.CCSound;
import com.syming.cursedcastle.util.HolyCrossUtil;
import com.syming.cursedcastle.entity.projectile.subweapon.HolyCrossEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.List;

import static com.syming.cursedcastle.util.SpellBookUtil.isWearingSpellBook;

public class HolyCrossItem extends CCThrowableItem {

    private Tier tier;

    public HolyCrossItem(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
        this.tier = pTier;
    }

    public Tier getTier() {
        return this.tier;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack throwStack = pPlayer.getItemInHand(pUsedHand);
        pLevel.playSound(pPlayer, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), CCSound.HOLY_CROSS_THROW.get(), SoundSource.MASTER, 0.5F, 1.0F);
        PlayerMP playerMP = pPlayer.getData(CCCapabilities.PLAYER_MP);
        PlayerData playerData = pPlayer.getData(CCCapabilities.PLAYER_DATA);
        ItemStack thrownStackCopy = throwStack.copy();
        if (playerMP != null) {
            if (!pLevel.isClientSide()) {
                if (isWearingSpellBook(SpellBookElementType.FIRE, pPlayer)) {
                    if (!pPlayer.isSpectator() && !pPlayer.isCreative()) {
                        if (playerMP.getCurrentMP() >= 50) {
                            playerMP.removeMP(50);
                            FireHolyCrossEntity fireHolyCross = new FireHolyCrossEntity(pLevel, pPlayer);
                            pLevel.addFreshEntity(fireHolyCross);
                        }
                    } else {
                        FireHolyCrossEntity fireHolyCross = new FireHolyCrossEntity(pLevel, pPlayer);
                        pLevel.addFreshEntity(fireHolyCross);
                    }
                } else if (isWearingSpellBook(SpellBookElementType.ICE, pPlayer)) {
                    if (!pPlayer.isSpectator() && !pPlayer.isCreative()) {
                        if (playerMP.getCurrentMP() >= 30) {
                            playerMP.removeMP(30);
                            IceHolyCrossEntity iceHolyCross = new IceHolyCrossEntity(pLevel, pPlayer);
                            pLevel.addFreshEntity(iceHolyCross);
                        }
                    } else {
                        IceHolyCrossEntity iceHolyCross = new IceHolyCrossEntity(pLevel, pPlayer);
                        pLevel.addFreshEntity(iceHolyCross);
                    }
                } else if (isWearingSpellBook(SpellBookElementType.BOLT, pPlayer)) {
                    if (playerData != null) {
                        if (!pPlayer.isSpectator() && !pPlayer.isCreative()) {
                            if (playerMP.getCurrentMP() >= 60) {
                                playerMP.removeMP(60);
                                playerData.setHolyBeamTicks(300);
                                playerData.setBoltAttackEnabled(true);
                                playerData.setPlayerYPos(pPlayer.getY() + 2.0D);
                                PacketDistributor.sendToPlayer((ServerPlayer) pPlayer, new CCPlayerData(playerData.getHolyBeamTicks(), playerData.isBoltAttackEnabled(), playerData.getPlayerYPos()));
                            }
                        } else {

                            playerData.setHolyBeamTicks(300);
                            playerData.setBoltAttackEnabled(true);
                            playerData.setPlayerYPos(pPlayer.getY() + 2.0D);
                            PacketDistributor.sendToPlayer((ServerPlayer) pPlayer, new CCPlayerData(playerData.getHolyBeamTicks(), playerData.isBoltAttackEnabled(), playerData.getPlayerYPos()));
                        }
                    }
                } else {
                    HolyCrossEntity holyCross = new HolyCrossEntity(pPlayer, pLevel, thrownStackCopy, getTier());
                    pLevel.addFreshEntity(holyCross);
                    throwStack.shrink(1);
                }
            }
        }
        return InteractionResultHolder.sidedSuccess(throwStack, pLevel.isClientSide());
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (pStack.isDamageableItem()) {
            pStack.hurtAndBreak(1, pAttacker, EquipmentSlot.MAINHAND);
            pStack.hurtAndBreak(1, pAttacker, EquipmentSlot.OFFHAND);
        }
        return true;
    }

    @Override
    public boolean isDamageable(ItemStack pStack) {
        return !pStack.isEmpty();
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        Level level = context.level();

        if (level != null) {
            HolyCrossUtil.addHolyCrossTooltip(level, stack, tooltipComponents);
        }
    }

    @Override
    @Deprecated
    public int getEnchantmentValue() {
        return 10;
    }

    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return true;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.NONE;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 100;
    }
}
