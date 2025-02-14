package com.syming.castlevaniacraft.item.whips.vampirekiller;

import com.syming.castlevaniacraft.item.MVItem;
import com.syming.castlevaniacraft.tab.CCTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.item.ItemStack;

public class ItemVampireKillerYellowStoneAttachment extends ItemVampireKillerWhip {

    public ItemVampireKillerYellowStoneAttachment(String name, float attackDamage, float attackSpeed, float reach) {
        super(attackDamage, attackSpeed, reach);

        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CCTabs.CREATIVE_TAB);
        setContainerItem(MVItem.vampireKiller);

        MVItem.ITEMS.add(this);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        super.hitEntity(stack, target, attacker);
        EntityLightningBolt lightningBolt = new EntityLightningBolt(target.world, target.posX, target.posY, target.posZ, false);
        target.world.spawnEntity(lightningBolt);
        return true;
    }

    @Override
    public float getReach() {
        return 12.0F;
    }
}
