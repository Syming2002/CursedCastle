package com.syming.cursedcastle.item.spellbook;

import com.syming.cursedcastle.damagetype.CCDamageType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Rarity;

import org.jetbrains.annotations.Nullable;

public enum SpellBookElementType implements StringRepresentable {
    FIRE("fire", CCDamageType.FIRE, Rarity.UNCOMMON),
    ICE("ice", CCDamageType.ICE, Rarity.UNCOMMON),
    BOLT("bolt", CCDamageType.THUNDER, Rarity.RARE),
    WIND("wind", CCDamageType.WIND, Rarity.RARE),
    SUMMONING("summoning", null, Rarity.EPIC);


    private final String name;
    private final ResourceKey<DamageType> resourceKeyDamageType;
    private final Rarity rarity;

    SpellBookElementType(String pName, @Nullable ResourceKey<DamageType> pResourceKeyDamageType, Rarity pRarity) {
        this.name = pName;
        this.resourceKeyDamageType = pResourceKeyDamageType;
        this.rarity = pRarity;
    }

    public ResourceKey<DamageType> getResourceKeyDamageType() {
        return this.resourceKeyDamageType;
    }

    public Rarity getRarity() {
        return this.rarity;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
