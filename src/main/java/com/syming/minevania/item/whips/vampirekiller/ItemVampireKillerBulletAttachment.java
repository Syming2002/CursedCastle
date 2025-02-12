package com.syming.minevania.item.whips.vampirekiller;

import com.syming.minevania.item.MVItem;
import com.syming.minevania.tab.CCTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemVampireKillerBulletAttachment extends ItemVampireKillerWhip {


    public ItemVampireKillerBulletAttachment(String name, float attackDamage, float attackSpeed, float reach) {
        super(attackDamage, attackSpeed, reach);

        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);
        setContainerItem(MVItem.vampireKiller);

        MVItem.ITEMS.add(this);
    }

    @Override
    public float getReach() {
        return 12.0F;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.isRemote) {
            Vec3d look = playerIn.getLookVec();
            EntitySmallFireball smallFireball = new EntitySmallFireball(worldIn, playerIn, 0.5D, 0.5D, 0.5D);
            smallFireball.setPosition(playerIn.posX + look.x * 1.5D, playerIn.posY + playerIn.getEyeHeight() + look.y * 1.5D, playerIn.posZ + look.z * 1.5D);
            smallFireball.accelerationX = look.x * 0.5D;
            smallFireball.accelerationY = look.y * 0.5D;
            smallFireball.accelerationZ = look.z * 0.5D;
            playerIn.getCooldownTracker().setCooldown(this, 100);
            worldIn.spawnEntity(smallFireball);
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
}
