package com.syming.cursedcastle.capability.data.mp;

import com.syming.cursedcastle.capability.data.PlayerData;

public interface IPlayerMP {

    public float getCurrentMP();

    public float getMaxMP();

    public float getMinMP();

    public void addMP(float add);

    public void removeMP(float remove);

    public void copyFrom(PlayerMP source);

}
