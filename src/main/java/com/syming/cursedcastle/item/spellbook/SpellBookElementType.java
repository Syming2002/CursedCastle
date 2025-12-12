package com.syming.cursedcastle.item.spellbook;

import com.syming.cursedcastle.datagen.CCDatapackBuiltinEntriesProvider;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Rarity;

import org.jetbrains.annotations.Nullable;

public enum SpellBookElementType {
    FIRE("fire", CCDatapackBuiltinEntriesProvider.FIRE, Rarity.UNCOMMON),
    ICE("ice", CCDatapackBuiltinEntriesProvider.ICE, Rarity.UNCOMMON),
    BOLT("bolt", CCDatapackBuiltinEntriesProvider.THUNDER, Rarity.RARE),
    WIND("wind", CCDatapackBuiltinEntriesProvider.WIND, Rarity.RARE),
    SUMMONING("summoning", null, Rarity.EPIC);


    private final String name;
    private final ResourceKey<DamageType> resourceKeyDamageType;
    private final Rarity rarity;

    SpellBookElementType(String pName, @Nullable ResourceKey<DamageType> pResourceKeyDamageType, Rarity pRarity) {
        this.name = pName;
        this.resourceKeyDamageType = pResourceKeyDamageType;
        this.rarity = pRarity;
    }

    public String getName() {
        return this.name;
    }

    public ResourceKey<DamageType> getResourceKeyDamageType() {
        return this.resourceKeyDamageType;
    }

    public Rarity getRarity() {
        return this.rarity;
    }
}
