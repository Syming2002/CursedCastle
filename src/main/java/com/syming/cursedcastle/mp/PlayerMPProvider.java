package com.syming.cursedcastle.mp;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerMPProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerMP> PLAYER_MP = CapabilityManager.get(new CapabilityToken<PlayerMP>() {});

    private PlayerMP playerMP = null;

    private final LazyOptional<PlayerMP> playerMPOptional = LazyOptional.of(this::createPlayerMP);

    private PlayerMP createPlayerMP() {
        if (this.playerMP == null) {
            this.playerMP = new PlayerMP();
        }

        return this.playerMP;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_MP) {
            return playerMPOptional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        createPlayerMP().saveNBTData(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerMP().loadNBTData(nbt);
    }
}
