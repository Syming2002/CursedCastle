package com.syming.cursedcastle.data;

public interface IPlayerData {

    int getHolyBeamTicks();
    void setHolyBeamTicks(int ticks);

    boolean isBoltAttackEnabled();
    void setBoltAttackEnabled(boolean enabled);

    double getPlayerYPos();
    void setPlayerYPos(double pos);
}
