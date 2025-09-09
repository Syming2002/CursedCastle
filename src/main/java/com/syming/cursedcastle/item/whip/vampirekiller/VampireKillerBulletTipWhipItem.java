package com.syming.cursedcastle.item.whip.vampirekiller;

import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.whip.WhipItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class VampireKillerBulletTipWhipItem extends WhipItem {
    public VampireKillerBulletTipWhipItem(Properties pProperties, int pAttackDamageModifier, float pAttackSpeedModifier, float pReachModifier) {
        super(pProperties, pAttackDamageModifier, pAttackSpeedModifier, pReachModifier);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack pStack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack pStack) {
        return pStack.isDamaged() ? ItemStack.EMPTY : new ItemStack(CCItem.VAMPIRE_KILLER_WHIP.get());
    }

    @Override
    public void onCraftedBy(ItemStack pStack, Level pLevel, Player pPlayer) {
        if (!pStack.isDamaged()) {
            super.onCraftedBy(pStack, pLevel, pPlayer);
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack heldItem = pPlayer.getItemInHand(pUsedHand);
        if (heldItem.is(CCItem.VAMPIRE_KILLER_BULLET_TIP_WHIP.get())) {
            if (!pLevel.isClientSide()) {
                SmallFireball smallFireball = new SmallFireball(EntityType.SMALL_FIREBALL, pLevel);
                Vec3 lookVec = pPlayer.getLookAngle();
                Vec3 position = pPlayer.position().add(lookVec.scale(1.5F));

                smallFireball.setPos(position.x, position.y + pPlayer.getEyeHeight(), position.z);
                smallFireball.setDeltaMovement(lookVec.x * 1.5F, lookVec.y * 1.5F, lookVec.z * 1.5F);

                pLevel.addFreshEntity(smallFireball);

                pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.BLAZE_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F);

                pPlayer.getCooldowns().addCooldown(this, 60);

                System.out.println("Fireball is launching");
            }
        }
        return InteractionResultHolder.sidedSuccess(heldItem, pLevel.isClientSide());
    }
}
