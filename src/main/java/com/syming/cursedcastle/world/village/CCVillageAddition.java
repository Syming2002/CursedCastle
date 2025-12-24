package com.syming.cursedcastle.world.village;

import com.syming.cursedcastle.CursedCastleMod;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
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

        for (StructureStart start : chunk.getAllStarts().values()) {
            if (!start.isValid()) continue;

            ResourceLocation id = server.registryAccess()
                    .registryOrThrow(Registries.STRUCTURE)
                    .getKey(start.getStructure());

            if (id == null) continue;

            long villageId = start.getBoundingBox().hashCode();
            VillageBigChurchData data = VillageBigChurchData.get(server);
            if (data.hasVillage(villageId)) continue;

            ChurchType type = mapVillageToChurchType(id);
            if (type == null) continue;


            if (server.random.nextFloat() > 0.9F) continue;

            BlockPos center = getVillageCenter(start);
            if (center == null) continue;

            data.markVillage(villageId);
            pendingChurches.add(new PendingChurch(server, center, type));
        }

    }

    @SubscribeEvent
    public static void onWorldTick(TickEvent.LevelTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!(event.level instanceof ServerLevel server)) return;

        StructureTemplate templatePlains = getTemplate(server, "plains");
        StructureTemplate templateDesert = getTemplate(server, "desert");
        StructureTemplate templateSavanna = getTemplate(server, "savanna");
        StructureTemplate templateTaiga = getTemplate(server, "taiga");
        StructureTemplate templateSnowy = getTemplate(server, "snowy");
        StructureTemplate templateJungle = getTemplate(server, "jungle");
        StructureTemplate templateCherry = getTemplate(server, "cherry");
        StructureTemplate templateSwamp = getTemplate(server, "swamp");

        Iterator<PendingChurch> iterator = pendingChurches.iterator();
        while (iterator.hasNext()) {
            PendingChurch pending = iterator.next();
            if (!pending.server.equals(server)) continue;

            if (!areChunksLoaded(server, pending.center, 80)) continue;

            StructureTemplate template = switch (pending.type) {
                case DESERT -> templateDesert;
                case SAVANNA -> templateSavanna;
                case TAIGA -> templateTaiga;
                case SNOWY -> templateSnowy;
                case JUNGLE -> templateJungle;
                case CHERRY -> templateCherry;
                case SWAMP -> templateSwamp;
                default -> templatePlains;
            };

            spawnBuildingNearVillage(server, pending.center, pending.type, template);
            iterator.remove();
        }
    }

    private static StructureTemplate getTemplate(ServerLevel server, String type) {
        return server.getStructureManager()
                .get(new ResourceLocation(CursedCastleMod.MOD_ID,
                        "village/" + type + "/big_church"))
                .orElse(null);
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
        placeBigChurchStructure(level, offset, center, churchType, template);
    }

    private static BlockPos getSurfacePos(ServerLevel level, BlockPos pos, StructureTemplate template) {
        int x = pos.getX();
        int z = pos.getZ();

        int y = level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z);

        while (y > level.getMinBuildHeight()) {
            BlockPos below = new BlockPos(x, y - 1, z);
            BlockState stateBelow = level.getBlockState(below);
            BlockState stateHere = level.getBlockState(new BlockPos(x, y, z));

            if (!stateBelow.isAir() && stateHere.isAir()) {
                break;
            }
            y--;
        }

        return new BlockPos(x, y, z);
    }

    private static void placeBigChurchStructure(ServerLevel level, BlockPos pos, BlockPos villageCenter, ChurchType churchType, StructureTemplate template) {
        StructureProcessorList processorList = level.registryAccess()
                .registryOrThrow(Registries.PROCESSOR_LIST)
                .get(new ResourceLocation(CursedCastleMod.MOD_ID, "big_church"));
        if (processorList == null) return;

        Rotation rotation = getRotationFacingCenter(pos, villageCenter);

        StructurePlaceSettings settings = new StructurePlaceSettings()
                .addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK)
                .setIgnoreEntities(false)
                .setRotation(rotation);

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
                    case PLAINS, SAVANNA, TAIGA, SNOWY, JUNGLE, CHERRY, SWAMP -> support = rand.nextFloat() < 0.5F ? Blocks.MOSSY_COBBLESTONE.defaultBlockState() : Blocks.COBBLESTONE.defaultBlockState();
                    case DESERT -> support = Blocks.SANDSTONE.defaultBlockState();
                }



                replaceAirAndLiquidDownwards(level, start, support);
            }
        }
    }

    private static boolean isFoundationBlock(BlockState state, ChurchType churchType) {
        if (churchType == ChurchType.PLAINS
                || churchType == ChurchType.SAVANNA
                || churchType == ChurchType.TAIGA
                || churchType == ChurchType.SNOWY
                || churchType == ChurchType.JUNGLE
                || churchType == ChurchType.CHERRY
                || churchType == ChurchType.SWAMP) {
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

    private static Rotation getRotationFacingCenter(BlockPos churchPos, BlockPos villageCenter) {
        double dx = villageCenter.getX() - churchPos.getX();
        double dz = villageCenter.getZ() - churchPos.getZ();

        if (Math.abs(dx) > Math.abs(dz)) {
            return dx > 0 ? Rotation.NONE : Rotation.CLOCKWISE_180;
        } else {
            return dz > 0 ? Rotation.CLOCKWISE_90 : Rotation.COUNTERCLOCKWISE_90;
        }
    }

    private static ChurchType mapVillageToChurchType(ResourceLocation id) {
        String path = id.getPath();

        if (id.getNamespace().equals("minecraft") && path.startsWith("village_")) {
            return switch (path) {
                case "village_desert" -> ChurchType.DESERT;
                case "village_savanna" -> ChurchType.SAVANNA;
                case "village_taiga" -> ChurchType.TAIGA;
                case "village_snowy" -> ChurchType.SNOWY;
                default -> ChurchType.PLAINS;
            };
        }

        if (id.getNamespace().equals(RepurposedStructures.MODID) && path.startsWith("village_")) {
            String type = path.substring("village_".length());

            return switch (type) {
                case "jungle" -> ChurchType.JUNGLE;
                case "cherry" -> ChurchType.CHERRY;
                case "swamp" -> ChurchType.SWAMP;
                default -> ChurchType.PLAINS;
            };
        }

        return null;
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

    private enum ChurchType {
        PLAINS,
        DESERT,
        SAVANNA,
        TAIGA,
        SNOWY,
        JUNGLE,
        CHERRY,
        SWAMP
    }
}
