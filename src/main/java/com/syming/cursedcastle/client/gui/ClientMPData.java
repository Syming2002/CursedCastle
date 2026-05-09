package com.syming.cursedcastle.client.gui;

public class ClientMPData {

    private static float playerMP;

    public static float getPlayerMP() {
        return playerMP;
    }

    public static float getMaxPlayerMP() {
        return 500.0F;
    }

    public static void setPlayerMP(float playerMP) {
        ClientMPData.playerMP = playerMP;
    }

}
