package com.syming.cursedcastle.item.projectile.subweapon;

import com.syming.cursedcastle.event.CCCommonEventForge;
import com.syming.cursedcastle.item.spellbook.SpellBookElementType;
import com.syming.cursedcastle.sound.CCSound;
import com.syming.cursedcastle.entity.projectile.subweapon.DaggerEntity;
import com.syming.cursedcastle.util.SpellBookUtil;
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
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class DaggerItem extends Item {

    private Tier tier;

    public DaggerItem(Tier pTier, Properties pProperties) {
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
        return 18000;
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        if (pLivingEntity instanceof Player player) {
            int loading = getUseDuration(pStack) - pTimeCharged;
            if (loading > 0) {

                    if (SpellBookUtil.isWearingSpellBook(SpellBookElementType.FIRE, player)) {
                        for (int i = 0; i < 8; i++) {
                            DaggerEntity dagger = new DaggerEntity(pLevel, player, pStack, getTier());
                            if (dagger.isAlive()) {
                                double angle = 2 * Math.PI * i / 8;

                                double dx = Math.cos(angle);
                                double dz = Math.sin(angle);

                                double spawnX = player.getX() + dx;
                                double spawnY = player.getY() + player.getEyeHeight();
                                double spawnZ = player.getZ() + dz;

                                pLevel.addFreshEntity(dagger);

                                dagger.setPos(spawnX, spawnY, spawnZ);

                                dagger.shoot(dx,0.0F, dz, 1.5F, 0.2F);
                                dagger.hasImpulse = true;
                            }
                        }

                    } else {
                        if (!pLevel.isClientSide()) {
                        DaggerEntity daggerEntity = new DaggerEntity(player, pLevel, pStack, getTier());
                        daggerEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);

                        daggerEntity.setItem(pStack);
                        pLevel.addFreshEntity(daggerEntity);
                        pLevel.playSound((Player) null, daggerEntity, CCSound.DAGGER_THROW.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                        if (!player.getAbilities().instabuild) {
                            pStack.shrink(1);
                        }
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
