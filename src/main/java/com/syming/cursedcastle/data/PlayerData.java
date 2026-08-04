package com.syming.cursedcastle.data;

public class PlayerData implements IPlayerData {

    private int holyBeamTicks;

    private boolean isAttackBolt;

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
}
