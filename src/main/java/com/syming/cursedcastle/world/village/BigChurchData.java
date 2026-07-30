package com.syming.cursedcastle.world.village;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.LongTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashSet;
import java.util.Set;

public class BigChurchData extends SavedData {
    private static final String DATA_NAME = "big_church_data";
    private final Set<Long> processed = new HashSet<>();

    public static BigChurchData load(CompoundTag compoundTag) {
        BigChurchData data = new BigChurchData();
        ListTag list = compoundTag.getList("villages", ListTag.TAG_LONG);
        for (Tag tag : list) {
            data.processed.add(((LongTag)tag).getAsLong());
        }
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag pCompoundTag) {
        ListTag list = new ListTag();
        for (Long id : this.processed) {
            list.add(LongTag.valueOf(id));
        }
        pCompoundTag.put("villages", list);
        return pCompoundTag;
    }

    public static BigChurchData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
                BigChurchData::load,
                BigChurchData::new,
                DATA_NAME
        );
    }

    public boolean hasVillage(long pId) {
        return this.processed.contains(pId);
    }

    public void markVillage(long pId) {
        this.processed.add(pId);
        setDirty();
    }
}
