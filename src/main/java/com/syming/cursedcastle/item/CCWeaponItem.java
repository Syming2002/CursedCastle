package com.syming.cursedcastle.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForgeMod;

import java.util.UUID;

public abstract class CCWeaponItem extends Item {

    public static final ResourceLocation BASE_ATTACK_REACH_ID = ResourceLocation.withDefaultNamespace("base_attack_reach");

    public CCWeaponItem(Properties pProperties) {
        super(pProperties);
    }

    public static ItemAttributeModifiers createAttributes(float attackDamage, float attackSpeed, float attackReach) {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, attackDamage, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, attackSpeed, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(BASE_ATTACK_REACH_ID, attackReach, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .build();
    }

//    @Override
//    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
//        if (entity instanceof Player player) {
//            float reach = (float) player.getAttributeValue(Attributes.ENTITY_INTERACTION_RANGE);
//            double reachSqr = reach * reach;
//
//            Vec3 view = player.getViewVector(1.0F);
//            Vec3 eye = player.getEyePosition(1.0F);
//            Vec3 targetVec = eye.add(view.x * reach, view.y * reach, view.z * reach);
//
//            EntityHitResult result = new EntityHitResult(player, targetVec);
//
//            if (result == null || !(result.getEntity() instanceof LivingEntity)) {
//                return false;
//            }
//
//            LivingEntity target = (LivingEntity) result.getEntity();
//
//            double distanceToTargetSqr = player.distanceToSqr(target);
//
//            boolean hitResult = (result != null ? target : null) != null;
//
//            if (hitResult) {
//                if (target != player) {
//                    if (reachSqr >= distanceToTargetSqr) {
//                        target.hurt(player.damageSources().playerAttack(player), this.attackDamage);
//                    }
//                }
//            }
//        } else {
//            return super.onEntitySwing(stack, entity);
//        }
//        return super.onEntitySwing(stack, entity);
//    }
}
