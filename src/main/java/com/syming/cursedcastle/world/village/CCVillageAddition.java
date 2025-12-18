package com.syming.cursedcastle.world.village;

import com.syming.cursedcastle.CursedCastleMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@Mod.EventBusSubscriber(modid = CursedCastleMod.MOD_ID)
public class CCVillageAddition {

    private static final List<PendingChurch> pendingChurches = new ArrayList<>();

    @SubscribeEvent
    public static void onChunkLoad(ChunkEvent.Load event) {
        Level level = (Level) event.getLevel();

        if (level == null || level.isClientSide()) return;
        if (!(level instanceof ServerLevel server)) return;
        if (!(event.getChunk() instanceof LevelChunk chunk)) return;

        StructureSet village =
                server.registryAccess()
                        .registryOrThrow(Registries.STRUCTURE_SET)
                        .get(BuiltinStructureSets.VILLAGES);
        if (village == null) return;

        StructureStart start = null;
        Structure matchedVillage = null;
        for (StructureSet.StructureSelectionEntry entry : village.structures()) {
            Structure structure = entry.structure().value();
            StructureStart candidate = chunk.getStartForStructure(structure);
            if (candidate != null && candidate.isValid()) {
                start = candidate;
                matchedVillage = structure;
                break;
            }
        }

        if (matchedVillage == null || start == null || !start.isValid()) return;

        long villageId = start.getBoundingBox().hashCode();
        VillageBigChurchData data = VillageBigChurchData.get(server);
        if (data.hasVillage(villageId)) return;
        data.markVillage(villageId);

        if (server.random.nextFloat() > 0.9F) return;

        BlockPos center = getVillageCenter(start);
        if (center == null) return;

        ResourceLocation id = server.registryAccess()
                .registryOrThrow(Registries.STRUCTURE)
                .getKey(matchedVillage);

        if (BuiltinStructures.VILLAGE_PLAINS.location().equals(id)) {
            pendingChurches.add(new PendingChurch(server, center, ChurchType.PLAINS));
        } else if (BuiltinStructures.VILLAGE_DESERT.location().equals(id)) {
            pendingChurches.add(new PendingChurch(server, center, ChurchType.DESERT));
        }
    }

    @SubscribeEvent
    public static void onWorldTick(TickEvent.LevelTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!(event.level instanceof ServerLevel server)) return;

        StructureTemplate templatePlains = server.getStructureManager()
                .get(new ResourceLocation(CursedCastleMod.MOD_ID, "village/plains/big_church"))
                .orElse(null);


        StructureTemplate templateDesert = server.getStructureManager()
                .get(new ResourceLocation(CursedCastleMod.MOD_ID, "village/desert/big_church"))
                .orElse(null);

        Iterator<PendingChurch> iterator = pendingChurches.iterator();
        while (iterator.hasNext()) {
            PendingChurch pending = iterator.next();
            if (!pending.server.equals(server)) continue;

            boolean allLoaded = areChunksLoaded(server, pending.center, 80);
            if (!allLoaded) continue;

            switch (pending.type) {
                case PLAINS -> {
                    spawnBuildingNearVillage(server, pending.center, pending.type, templatePlains);
                    System.out.println("Structure big church " + pending.type + "has spawned at: " + pending.center);
                }
                case DESERT -> {
                    spawnBuildingNearVillage(server, pending.center, pending.type, templateDesert);
                    System.out.println("Structure big church " + pending.type + "has spawned at: " + pending.center);
                }
            }

            iterator.remove();
        }
    }

    private static class PendingChurch {
        final ServerLevel server;
        final BlockPos center;
        final ChurchType type;

        PendingChurch(ServerLevel server, BlockPos center, ChurchType type) {
            this.server = server;
            this.center = center;
            this.type = type;
        }
    }

    private static BlockPos getVillageCenter(StructureStart start) {
        int x = 0, y = 0, z = 0, n = 0;
        for (StructurePiece piece : start.getPieces()) {
            BoundingBox box = piece.getBoundingBox();
            x += (box.minX() + box.maxX()) / 2;
            y += (box.minY() + box.maxY()) / 2;
            z += (box.minZ() + box.maxZ()) / 2;
            n++;
        }
        return n == 0 ? null : new BlockPos(x / n, y / n, z / n);
    }

    private static void spawnBuildingNearVillage(ServerLevel level, BlockPos center, ChurchType churchType, StructureTemplate template) {
        if (template == null) return;

        int radius = 80;
        long seed = center.asLong();
        double angle = (seed % 360) * (Math.PI / 180.0);

        int dx = (int) Math.round(Math.cos(angle) * radius);
        int dz = (int) Math.round(Math.sin(angle) * radius);

        BlockPos offset = center.offset(dx, 0, dz);
        placeBigChurchStructure(level, offset, churchType, template);
    }

    private static BlockPos getSurfacePos(ServerLevel level, BlockPos pos, StructureTemplate template) {
        Vec3i size = template.getSize();
        int maxY = level.getMinBuildHeight();
        for (int x = 0; x < size.getX(); x++) {
            for (int z = 0; z < size.getZ(); z++) {
                int y = level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pos.getX() + x, pos.getZ() + z);
                if (y > maxY) maxY = y;
            }
        }
        return new BlockPos(pos.getX(), maxY, pos.getZ());
    }

    private static void placeBigChurchStructure(ServerLevel level, BlockPos pos, ChurchType churchType, StructureTemplate template) {
        StructureProcessorList processorList = level.registryAccess()
                .registryOrThrow(Registries.PROCESSOR_LIST)
                .get(new ResourceLocation(CursedCastleMod.MOD_ID, "big_church"));
        if (processorList == null) return;

        StructurePlaceSettings settings = new StructurePlaceSettings()
                .addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK)
                .setIgnoreEntities(false)
                .setRotation(Rotation.getRandom(level.random));

        for (StructureProcessor processor : processorList.list()) settings.addProcessor(processor);

        BlockPos correctedPos = getSurfacePos(level, pos, template);
        template.placeInWorld(level, correctedPos, correctedPos, settings, level.random, 2);
        buildFoundation(level, correctedPos, churchType, template, settings);
    }

    private static void buildFoundation(ServerLevel level, BlockPos origin, ChurchType churchType, StructureTemplate template, StructurePlaceSettings settings) {
        Vec3i size = template.getSize();
        RandomSource rand = level.random;

        for (int x = 0; x < size.getX(); x++) {
            for (int z = 0; z < size.getZ(); z++) {

                BlockPos lowestBlockPos = null;
                BlockState lowestBlockState = null;

                for (int y = 0; y < size.getY(); y++) {
                    BlockPos local = new BlockPos(x, y, z);
                    BlockPos worldPos = StructureTemplate
                            .calculateRelativePosition(settings, local)
                            .offset(origin);

                    BlockState state = level.getBlockState(worldPos);

                    if (!state.isAir()) {
                        lowestBlockPos = worldPos;
                        lowestBlockState = state;
                        break;
                    }
                }

                if (lowestBlockPos == null) continue;

                if (!isFoundationBlock(lowestBlockState, churchType)) continue;

                BlockPos start = lowestBlockPos.below();

                BlockState support = null;

                switch (churchType) {
                    case PLAINS -> support = rand.nextFloat() < 0.5F ? Blocks.MOSSY_COBBLESTONE.defaultBlockState() : Blocks.COBBLESTONE.defaultBlockState();
                    case DESERT -> support = Blocks.SANDSTONE.defaultBlockState();
                }



                replaceAirAndLiquidDownwards(level, start, support);
            }
        }
    }

    private static boolean isFoundationBlock(BlockState state, ChurchType churchType) {
        if (churchType == ChurchType.PLAINS) {
            return state.is(Blocks.COBBLESTONE) || state.is(Blocks.MOSSY_COBBLESTONE) || state.is(Blocks.COBBLESTONE_STAIRS) || state.is(Blocks.MOSSY_COBBLESTONE_STAIRS);
        } else if (churchType == ChurchType.DESERT) {
            return state.is(Blocks.SANDSTONE) || state.is(Blocks.SMOOTH_SANDSTONE_STAIRS);
        }
        return false;
    }

    private static boolean areChunksLoaded(ServerLevel server, BlockPos center, int radius) {
        int chunkRadius = (radius / 16) + 1;
        int centerChunkX = center.getX() >> 4;
        int centerChunkZ = center.getZ() >> 4;

        for (int dx = -chunkRadius; dx <= chunkRadius; dx++) {
            for (int dz = -chunkRadius; dz <= chunkRadius; dz++) {
                if (!server.getChunkSource().hasChunk(centerChunkX + dx, centerChunkZ + dz)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void replaceAirAndLiquidDownwards(ServerLevel level, BlockPos startPos, BlockState blockState) {
        BlockPos.MutableBlockPos pos = startPos.mutable();

        while (pos.getY() > level.getMinBuildHeight()) {
            BlockState current = level.getBlockState(pos);

            if (!current.isAir() && !current.getFluidState().isSource() && !current.canBeReplaced()) {
                break;
            }

            level.setBlock(pos, blockState, 2);
            pos.move(0, -1, 0);
        }
    }

    private enum ChurchType {
        PLAINS,
        DESERT
    }
}
