package com.syming.cursedcastle.item.soul;

import com.mojang.blaze3d.platform.InputConstants;
import com.syming.cursedcastle.client.CCKeybinding;
import com.syming.cursedcastle.item.CCItem;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class SoulItem extends Item implements ICurioItem {
    private final Soul soul;

    public SoulItem(Soul pSoul) {
        super(new Properties().stacksTo(8).rarity(pSoul.getRarity()));
        this.soul = pSoul;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity entity = slotContext.entity();

        if (entity instanceof Player player) {
            Level level = player.level();

            if (stack.getItem() instanceof SoulItem soulItem) {
                if (soulItem.getSoul().getSoulType() == SoulType.BULLET) {
                    CuriosApi.getCuriosHelper().findEquippedCurio(CCItem.SOUL_TYPES.get(Soul.BLAZE).get(), player)
                            .ifPresent(slotResult -> {
                                if (soulItem.getSoul() == Soul.BLAZE) {
                                    int count = stack.getCount();

                                    if (CCKeybinding.INSTANCE.BULLET_SOUL_KEY.isDown() && !player.getCooldowns().isOnCooldown(soulItem)) {
                                        launchSmallFireballs(player, count);
                                    }
                                }
                            });
                } else if (soulItem.getSoul().getSoulType() == SoulType.GUARDIAN) {

                    CuriosApi.getCuriosHelper().findEquippedCurio(CCItem.SOUL_TYPES.get(Soul.ENDERMAN).get(), player)
                            .ifPresent(slotResult -> {
                                if (soulItem.getSoul() == Soul.ENDERMAN) {
                                    int count = stack.getCount();
                                    if (CCKeybinding.INSTANCE.GUARDIAN_SOUL_KEY.isDown() && !player.getCooldowns().isOnCooldown(soulItem)) {
                                        teleportSoulOwner(player, count);
                                    }

                                    if (level.isClientSide()) {
                                        for (int i = 0; i < 2; ++i) {
                                            double offsetX = (level.random.nextDouble() - 0.5D) * 0.6D;
                                            double offsetY = level.random.nextDouble();
                                            double offsetZ = (level.random.nextDouble() - 0.5D) * 0.6D;

                                            double x = player.getX() + offsetX;
                                            double y = player.getY() + offsetY + 0.5D;
                                            double z = player.getZ() + offsetZ;

                                            level.addParticle(ParticleTypes.PORTAL, x, y, z, 0, 0, 0);
                                        }
                                    }
                                }
                            });
                } else if (soulItem.getSoul().getSoulType() == SoulType.ENCHANTED) {

                    CuriosApi.getCuriosHelper()
                            .findEquippedCurio(CCItem.SOUL_TYPES.get(Soul.GHOST_DANCER).get(), player)
                            .ifPresent(stringIntegerItemStackImmutableTriple -> {
                                if (soulItem.getSoul() == Soul.GHOST_DANCER) {
                                    int count = stack.getCount();

                                    if (count > 0 && count <= 3) {
                                        addLuckEffect(player, 0);
                                    } else if (count > 3 && count <= 6) {
                                        addLuckEffect(player, 1);
                                    } else if (count > 6) {
                                        addLuckEffect(player, 2);
                                    }
                                }
                            });
                }
            }
        }
    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        if (!slotContext.identifier().equals("ring")) {
            return false;
        }

        return CuriosApi.getCuriosHelper().findEquippedCurio(this, slotContext.entity()).isEmpty();
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    public Soul getSoul() {
        return this.soul;
    }

    private void teleportSoulOwner(Player player, int soulCount) {
        double radius;

        if (soulCount > 0 && soulCount <= 2) {
            radius = 8.0D;
        } else if (soulCount > 2 && soulCount <= 4) {
            radius = 16.0D;
        } else if (soulCount > 4 && soulCount <= 8) {
            radius = 32.0D;
        } else {
            return;
        }

        double d0 = (player.getRandom().nextDouble() - 0.5D) * radius * 2.0D;
        double d1 = (player.getRandom().nextInt(16) - 8);
        double d2 = (player.getRandom().nextDouble() - 0.5D) * radius * 2.0D;

        double targetX = player.getX() + d0;
        double targetY = player.getY() + d1;
        double targetZ = player.getZ() + d2;

        player.randomTeleport(targetX, targetY, targetZ, true);

        player.getCooldowns().addCooldown(this, 200);
    }

    private void addLuckEffect(Player player, int luckAmplifier) {
        player.addEffect(new MobEffectInstance(MobEffects.LUCK, 10, luckAmplifier));
    }

    private void launchSmallFireballs(Player player, int soulCount) {
        Vec3 look = player.getLookAngle().normalize();

        for (int i = 0; i < soulCount; i++) {
            double angle = (Math.PI * 2.0D * i) / soulCount;

            double radius = 1.0D;

            double x = player.getX() + Math.cos(angle) * radius;
            double y = player.getY() + player.getEyeY();
            double z = player.getZ() + Math.sin(angle) * radius;

            SmallFireball fireball = new SmallFireball(
                    player.level(),
                    x,
                    y,
                    z,
                    look.x,
                    look.y,
                    look.z
            );

            fireball.setOwner(player);

            fireball.setPos(fireball.getX(), player.getY(0.5D) + 0.5D, fireball.getZ());
            player.level().addFreshEntity(fireball);

            player.getCooldowns().addCooldown(this, 100);
        }

        player.level().playSound(
                player,
                player.blockPosition(),
                SoundEvents.BLAZE_SHOOT,
                SoundSource.MASTER,
                0.5F,
                1.0F
        );
    }
}
