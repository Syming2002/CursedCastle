package com.syming.cursedcastle.item.projectile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class ScytheProjectileItem extends Item {
    public ScytheProjectileItem() {
        super(new Properties().rarity(Rarity.UNCOMMON));
    }
}
