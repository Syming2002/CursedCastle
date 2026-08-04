package com.syming.cursedcastle.data;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerDataProvider implements ICapabilitySerializable<CompoundTag> {

    public static final Capability<IPlayerData> PLAYER_DATA = CapabilityManager.get(new CapabilityToken<>() {});

    private final PlayerData playerData = new PlayerData();

    private final LazyOptional<IPlayerData> playerDataLazyOptional =LazyOptional.of(() -> this.playerData);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == PLAYER_DATA ? this.playerDataLazyOptional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("HolyBeamTicks", this.playerData.getHolyBeamTicks());
        tag.putBoolean("AttackBoltEnabled", this.playerData.isBoltAttackEnabled());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.playerData.setHolyBeamTicks(nbt.getInt("HolyBeamTicks"));
        this.playerData.setBoltAttackEnabled(nbt.getBoolean("AttackBoltEnabled"));
    }
}
