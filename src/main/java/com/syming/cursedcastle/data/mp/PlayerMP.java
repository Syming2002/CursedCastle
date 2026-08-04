package com.syming.cursedcastle.data.mp;

import net.minecraft.nbt.CompoundTag;

public class PlayerMP {
    private float currentMP;
    private final float minMP = 0.0F;
    private final float maxMP = 500.0F;


    public float getCurrentMP() {
        return this.currentMP;
    }

    public float getMaxMP() {
        return this.maxMP;
    }

    public float getMinMP() {
        return this.minMP;
    }

    public void addMP(float add) {
        this.currentMP = Math.min(this.currentMP + add, this.maxMP);
    }

    public void removeMP(float remove) {
        this.currentMP = Math.max(this.currentMP - remove, this.minMP);
    }

    public void copyFrom(PlayerMP source) {
        this.currentMP = source.currentMP;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putFloat("currentMP", this.currentMP);
    }

    public void loadNBTData(CompoundTag nbt) {
        this.currentMP = nbt.getFloat("currentMP");
    }

}
