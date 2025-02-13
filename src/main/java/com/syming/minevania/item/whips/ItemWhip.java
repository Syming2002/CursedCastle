package com.syming.minevania.item.whips;

import com.google.common.collect.Multimap;
import com.syming.minevania.api.IExtendedReach;
import com.syming.minevania.util.handlers.SoundsHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.UUID;

public abstract class ItemWhip extends Item implements IExtendedReach {

    private static final UUID REACH_MODIFIER = UUID.fromString("5980CE40-252E-4867-8BA7-3EA767259DF8");

    private float attackDamage;
    private float attackSpeed;
    private float reach;

    public ItemWhip(float attackDamage, float attackSpeed, float reach) {
        this.maxStackSize = 1;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.reach = reach;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        attacker.world.playSound((EntityPlayer) attacker, attacker.posX, attacker.posY, attacker.posZ, SoundsHandler.WHIP_IMPACT, SoundCategory.MASTER, 0.5F, 1.0F);
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        if ((double) state.getBlockHardness(worldIn, pos) != 0.0D) {
            stack.damageItem(2, entityLiving);
        }

        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }


    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double) this.attackDamage, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", this.attackSpeed, 0));
            multimap.put(EntityPlayer.REACH_DISTANCE.getName(), new AttributeModifier(REACH_MODIFIER, "Weapon Modifier", this.reach, 0));
        }

        return multimap;
    }

    @Override
    public boolean canDestroyBlockInCreative(World world, BlockPos pos, ItemStack stack, EntityPlayer player) {
        return false;
    }

    public static double getReach(EntityPlayer player) {
        return player.getEntityAttribute(EntityPlayer.REACH_DISTANCE).getAttributeValue();
    }
}
