package com.syming.cursedcastle.capability.data.mp;

import com.syming.cursedcastle.capability.data.PlayerData;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

public class PlayerMP implements INBTSerializable<CompoundTag>, IPlayerMP {

    private float currentMP;
    private final float minMP = 0.0F;
    private final float maxMP = 500.0F;

    @Override
    public float getCurrentMP() {
        return this.currentMP;
    }

    @Override
    public float getMaxMP() {
        return this.maxMP;
    }

    @Override
    public float getMinMP() {
        return this.minMP;
    }

    @Override
    public void addMP(float add) {
        this.currentMP = Math.min(this.currentMP + add, this.maxMP);
    }

    @Override
    public void removeMP(float remove) {
        this.currentMP = Math.max(this.currentMP - remove, this.minMP);
    }

    @Override
    public void copyFrom(PlayerMP source) {
        this.currentMP = source.getCurrentMP();
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        tag.putFloat("currentMP", this.currentMP);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag compoundTag) {
        this.currentMP = compoundTag.getFloat("currentMP");
    }
}
