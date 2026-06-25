package com.syming.cursedcastle.world.village;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.LongTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashSet;
import java.util.Set;

public class VillageBigChurchData extends SavedData {

    private static final String DATA_NAME = "big_church_data";
    private boolean churchGenerated = false;

    public static VillageBigChurchData load(CompoundTag tag) {
        VillageBigChurchData data = new VillageBigChurchData();
        data.churchGenerated = tag.getBoolean("ChurchGenerated");
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putBoolean("ChurchGenerated", this.churchGenerated);
        return tag;
    }

    public static VillageBigChurchData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
                VillageBigChurchData::load,
                VillageBigChurchData::new,
                DATA_NAME
        );
    }

    public boolean hasChurchGenerated() {
        return this.churchGenerated;
    }

    public void setChurchGenerated() {
        this.churchGenerated = true;
        setDirty();
    }
}
