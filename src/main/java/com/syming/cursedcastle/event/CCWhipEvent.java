package com.syming.cursedcastle.event;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.effect.CCEffect;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.whip.FlameWhipItem;
import com.syming.cursedcastle.item.whip.vampirekiller.VampireKillerRedStoneTipWhipItem;
import com.syming.cursedcastle.item.whip.vampirekiller.tip.TipType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = CursedCastleMod.MOD_ID)
public class CCWhipEvent {

    @SubscribeEvent
    public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        Player player = event.getEntity();
        Level level = event.getLevel();
        BlockPos blockPos = event.getPos();

        BlockPos firePos = blockPos.relative(event.getFace());

        if (!level.isClientSide()) {
            if (player.getMainHandItem().getItem() instanceof VampireKillerRedStoneTipWhipItem || player.getMainHandItem().getItem() instanceof FlameWhipItem) {
                if (level.getBlockState(firePos).isAir()) {
                    level.setBlock(firePos, Blocks.FIRE.defaultBlockState(), 3);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onWhipBlueStoneTipAttack(AttackEntityEvent event) {
        LivingEntity attacker = event.getEntity();
        if (attacker.getMainHandItem().is(CCItem.VAMPIRE_KILLER_ELEMENTAL_WHIP.get(TipType.BLUE).get())) {
            event.getEntity().addEffect(new MobEffectInstance(CCEffect.FREEZE, 50, 1));
        }
    }
}
