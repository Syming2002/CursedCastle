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
    private final Set<Long> processed = new HashSet<>();

    public static VillageBigChurchData load(CompoundTag tag) {
        VillageBigChurchData data = new VillageBigChurchData();

        ListTag list = tag.getList("villages", Tag.TAG_LONG);
        for (Tag t : list) {
            data.processed.add(((LongTag)t).getAsLong());
        }

        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        ListTag list = new ListTag();
        for (Long id : processed) {
            list.add(LongTag.valueOf(id));
        }
        tag.put("villages", list);
        return tag;
    }

    public static VillageBigChurchData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
                VillageBigChurchData::load,
                VillageBigChurchData::new,
                DATA_NAME
        );
    }

    public boolean hasVillage(long id) {
        return processed.contains(id);
    }

    public void markVillage(long id) {
        processed.add(id);
        setDirty();
    }

}
