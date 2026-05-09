package com.syming.cursedcastle.network;

import com.syming.cursedcastle.client.gui.ClientMPData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CCMPData {

    private float currentMP;

    public CCMPData(float currentMP) {
        this.currentMP = currentMP;
    }

    public CCMPData(FriendlyByteBuf buffer) {
        this.currentMP = buffer.readFloat();
    }

    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeFloat(this.currentMP);
    }

    public boolean handle(Supplier<NetworkEvent.Context> context) {
        NetworkEvent.Context ctx = context.get();
        ctx.enqueueWork(() -> {
            ClientMPData.setPlayerMP(this.currentMP);
        });
        return true;
    }
}
