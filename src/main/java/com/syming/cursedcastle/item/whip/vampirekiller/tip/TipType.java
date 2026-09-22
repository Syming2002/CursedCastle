package com.syming.cursedcastle.item.whip.vampirekiller.tip;

import com.syming.cursedcastle.damagetype.CCDamageType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.Nullable;

public enum TipType implements StringRepresentable {
    BLUE("blue", CCDamageType.ICE),
    BULLET("bullet", null),
    CRUSHING("crushing", null),
    GREEN("green", CCDamageType.WIND),
    RED("red", CCDamageType.FIRE),
    YELLOW("yellow", CCDamageType.THUNDER);

    private final String name;
    private final ResourceKey<DamageType> resourceKeyDamageType;

    TipType(String pName, @Nullable ResourceKey<DamageType> pResourceKeyDamageType) {
        this.name = pName;
        this.resourceKeyDamageType = pResourceKeyDamageType;
    }

    public ResourceKey<DamageType> getResourceKeyDamageType() {
        return this.resourceKeyDamageType;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}


