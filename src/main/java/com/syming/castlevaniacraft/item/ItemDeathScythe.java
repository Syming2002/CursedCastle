package com.syming.castlevaniacraft.item;

import com.google.common.collect.Multimap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

import java.util.Random;

public class ItemDeathScythe extends Item {

    private final float attackDamage;
    private Random random = new Random();

    public ItemDeathScythe(String name) {
        this.setRegistryName(name);
        this.setTranslationKey(name);
        this.setMaxStackSize(1);
        this.setMaxDamage(600);

        this.attackDamage = 5.0F;

        CCItem.ITEMS.add(this);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        if (this.random.nextFloat() < 0.1F) {
            target.attackEntityFrom(DamageSource.GENERIC, Float.MAX_VALUE);
        } else {
            target.attackEntityFrom(DamageSource.GENERIC, 5.0F);
        }
        return true;
    }

    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.attackDamage, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -3.6D, 0));
        }

        return multimap;
    }
}
