package com.syming.cursedcastle.capability.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

public class PlayerData implements INBTSerializable<CompoundTag>, IPlayerData {

    private int holyBeamTicks;
    private boolean isAttackBolt;
    private double playerYPos;

    @Override
    public int getHolyBeamTicks() {
        return this.holyBeamTicks;
    }

    @Override
    public void setHolyBeamTicks(int ticks) {
        this.holyBeamTicks = ticks;
    }

    @Override
    public boolean isBoltAttackEnabled() {
        return this.isAttackBolt;
    }

    @Override
    public void setBoltAttackEnabled(boolean enabled) {
        this.isAttackBolt = enabled;
    }

    @Override
    public double getPlayerYPos() {
        return this.playerYPos;
    }

    @Override
    public void setPlayerYPos(double pos) {
        this.playerYPos = pos;
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("holyBeamTicks", this.getHolyBeamTicks());
        tag.putBoolean("attackBoltEnabled", this.isBoltAttackEnabled());
        tag.putDouble("playerYPos", this.getPlayerYPos());
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag compoundTag) {
        this.setHolyBeamTicks(compoundTag.getInt("holyBeamTicks"));
        this.setBoltAttackEnabled(compoundTag.getBoolean("attackBoltEnabled"));
        this.setPlayerYPos(compoundTag.getDouble("playerYPos"));
    }
}
