package com.syming.cursedcastle.data;

public class PlayerData implements IPlayerData {

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
}
