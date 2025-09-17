package com.syming.cursedcastle.event;

import com.github.alexthe666.citadel.repack.jaad.aac.syntax.PCE;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.effect.CCEffect;
import com.syming.cursedcastle.item.CCItem;
import com.syming.cursedcastle.item.whip.FlameWhipItem;
import com.syming.cursedcastle.item.whip.vampirekiller.VampireKillerRedStoneTipWhipItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CursedCastleMod.MOD_ID)
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
    public static void onWhipBlueStoneTipAttack(LivingAttackEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity attacker) {
            if (attacker.getMainHandItem().is(CCItem.VAMPIRE_KILLER_BLUE_STONE_TIP_WHIP.get())) {
                event.getEntity().addEffect(new MobEffectInstance(CCEffect.FREEZE.get(), 50,1));
                System.out.println("Entity Living is frozen");
            }
        }
    }
}
