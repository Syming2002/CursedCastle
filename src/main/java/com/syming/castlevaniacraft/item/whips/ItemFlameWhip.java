package com.syming.castlevaniacraft.item.whips;

import com.syming.castlevaniacraft.api.IElementalDamage;
import com.syming.castlevaniacraft.damagesource.CCDamageSource;
import com.syming.castlevaniacraft.item.CCItem;
import com.syming.castlevaniacraft.tab.CCTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;

public class ItemFlameWhip extends ItemWhip implements IElementalDamage {

    public ItemFlameWhip(String name, float attackDamage, float attackSpeed, float reach) {
        super(attackDamage, attackSpeed, reach);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);

        CCItem.ITEMS.add(this);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        super.hitEntity(stack, target, attacker);

        target.setFire((int) 2.5);
        getElementalDamageSource(stack, target, attacker);
        return true;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        super.onLeftClickEntity(stack, player, entity);
        player.world.playSound(player,
                player.posX,
                player.posY,
                player.posZ,
                SoundEvents.ENTITY_BLAZE_SHOOT,
                SoundCategory.MASTER,
                1.0F,
                0.5F);
        if (player.world.isRemote) {
            Vec3d look = player.getLookVec();
            double reach = ItemWhip.getReach(player);
            double stepSize = 0.5D;
            double maxSteps = reach / stepSize;

            for (int i = 0; i <= maxSteps; i++) {
                double factor = i * stepSize;
                double spawnX = player.posX + look.x * factor;
                double spawnY = player.posY + player.getEyeHeight() + look.y * factor;
                double spawnZ = player.posZ + look.z * factor;
                player.world.spawnParticle(EnumParticleTypes.FLAME, spawnX, spawnY, spawnZ, 0.0D, 0.0D, 0.0D);
            }
        }
        return false;
    }


    @Override
    public DamageSource getElementalDamageSource(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        return CCDamageSource.getFireDamage();
    }

    @Override
    public float getReach() {
        return 12.0F;
    }
}
