package com.syming.cursedcastle.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;

import java.util.UUID;

public abstract class CCWeaponItem extends Item {

    public static final UUID BASE_ATTACK_REACH_UUID = UUID.fromString("2a2af586-d6d1-46fe-8557-7e449f6de5eb");
    protected float attackDamage;
    protected float attackSpeed;
    protected float reach;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public CCWeaponItem(Properties pProperties, int pAttackDamageModifier, float pAttackSpeedModifier, float pReachModifier) {
        super(pProperties);

        this.attackDamage = (float) pAttackDamageModifier;
        this.attackSpeed = pAttackSpeedModifier;
        this.reach = pReachModifier;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)this.attackSpeed, AttributeModifier.Operation.ADDITION));
        if (ForgeMod.ENTITY_REACH.isPresent()) {
            builder.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(BASE_ATTACK_REACH_UUID, "Weapon modifier", this.reach, AttributeModifier.Operation.ADDITION));
        }
        this.defaultModifiers = builder.build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        return pEquipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(pEquipmentSlot);
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        if (entity instanceof Player player) {
            float reach = (float) player.getAttributeValue(ForgeMod.ENTITY_REACH.get());
            double reachSqr = reach * reach;

            Vec3 view = player.getViewVector(1.0F);
            Vec3 eye = player.getEyePosition(1.0F);
            Vec3 targetVec = eye.add(view.x * reach, view.y * reach, view.z * reach);

            EntityHitResult result = new EntityHitResult(player, targetVec);

            if (result == null || !(result.getEntity() instanceof LivingEntity)) {
                return false;
            }

            LivingEntity target = (LivingEntity) result.getEntity();

            double distanceToTargetSqr = player.distanceToSqr(target);

            boolean hitResult = (result != null ? target : null) != null;

            if (hitResult) {
                if (target != player) {
                    if (reachSqr >= distanceToTargetSqr) {
                        target.hurt(player.damageSources().playerAttack(player), this.attackDamage);
                    }
                }
            }
        } else {
            return super.onEntitySwing(stack, entity);
        }
        return super.onEntitySwing(stack, entity);
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public float getAttackSpeed() {
        return this.attackSpeed;
    }
}
