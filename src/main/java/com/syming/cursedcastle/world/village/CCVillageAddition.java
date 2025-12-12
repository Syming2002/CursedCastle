package com.syming.cursedcastle.world.village;

import com.syming.cursedcastle.CursedCastleMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.*;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.event.level.ChunkTicketLevelUpdatedEvent;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = CursedCastleMod.MOD_ID)
public class CCVillageAddition {

    @SubscribeEvent
    public static void onChunkLoad(ChunkEvent event) {
        Level level = (Level) event.getLevel();

        if (level == null) return;

        if (level.isClientSide()) return;
        if (!(level instanceof ServerLevel server)) return;
        if (!(event.getChunk() instanceof LevelChunk chunk)) return;

        Structure plainsVillageStructure =
                server.registryAccess()
                        .registryOrThrow(Registries.STRUCTURE)
                        .get(BuiltinStructures.VILLAGE_PLAINS);

        if (plainsVillageStructure == null) return;

        StructureStart start = chunk.getStartForStructure(plainsVillageStructure);

        if (start == null || !start.isValid()) return;

        long villageId = start.getBoundingBox().hashCode();

        VillageBigChurchData data = VillageBigChurchData.get(server);

        if (data.hasVillage(villageId)) {
            return;
        }

        data.markVillage(villageId);

        if (server.random.nextFloat() > 0.9F) return;

        BlockPos center = getVillageCenter(start);
        if (center == null) return;

        server.getServer().tell(new TickTask(0, () -> spawnBuildingNearVillage(server, center)));

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

    private static void spawnBuildingNearVillage(ServerLevel level, BlockPos center) {
        RandomSource rand = level.random;

        int radius = rand.nextIntBetweenInclusive(80, 80);
        double angle = rand.nextDouble() * Math.PI * 2;

        int dx = (int)(Math.cos(angle) * radius);
        int dz = (int)(Math.sin(angle) * radius);

        BlockPos raw = center.offset(dx, -1, dz);
        int y = level.getHeight(Heightmap.Types.WORLD_SURFACE, raw.getX(), raw.getZ());
        BlockPos pos = new BlockPos(raw.getX(), y - 1, raw.getZ());

        placeBigChurchStructure(level, pos);
    }

    private static void placeBigChurchStructure(ServerLevel level, BlockPos pos) {

        StructureTemplate template = level.getStructureManager()
                .get(new ResourceLocation(CursedCastleMod.MOD_ID, "big_church"))
                .orElse(null);

        StructureProcessorList processorList = level.registryAccess()
                .registryOrThrow(Registries.PROCESSOR_LIST)
                .get(new ResourceLocation(CursedCastleMod.MOD_ID, "big_church"));

        if (template == null) return;


        if (processorList == null) return;

        StructurePlaceSettings settings = new StructurePlaceSettings()
                .addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK)
                .setIgnoreEntities(false)
                .addProcessor(processorList.list().get(0));

        Vec3i size = template.getSize();
        int width = size.getX();
        int depth = size.getZ();

        int y1 = level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pos.getX(), pos.getZ());
        int y2 = level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pos.getX() + width, pos.getZ());
        int y3 = level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pos.getX(), pos.getZ() + depth);
        int y4 = level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pos.getX() + width, pos.getZ() + depth);

        int finalY = Math.max(Math.max(y1, y2), Math.max(y3, y4));

        int templateMinY = template.getBoundingBox(settings, new BlockPos(0, 0, 0)).minY();

        BlockPos correctedPos = new BlockPos(pos.getX(), finalY - templateMinY, pos.getZ());

        template.placeInWorld(level, correctedPos, correctedPos, settings, level.random, 2);

        buildFoundation(level, correctedPos, template);
    }

    private static void buildFoundation(ServerLevel level, BlockPos origin, StructureTemplate template) {

        Vec3i size = template.getSize();
        int width = size.getX();
        int height = size.getY();
        int depth = size.getZ();

        RandomSource rand = level.random;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < depth; z++) {

                    BlockPos blockPos = origin.offset(x, y, z);
                    BlockState block = level.getBlockState(blockPos);

                    if (block.is(Blocks.COBBLESTONE) || block.is(Blocks.MOSSY_COBBLESTONE) || block.is(Blocks.COBBLESTONE_STAIRS) || block.is(Blocks.MOSSY_COBBLESTONE_STAIRS)) {

                        BlockPos below = blockPos.below();

                        while (below.getY() > level.getMinBuildHeight()) {

                            BlockState stateBelow = level.getBlockState(below);

                            if (!stateBelow.isAir() && !stateBelow.is(Blocks.CAVE_AIR)) {
                                break;
                            }

                            BlockState support = rand.nextFloat() < 0.5F
                                    ? Blocks.MOSSY_COBBLESTONE.defaultBlockState()
                                    : Blocks.COBBLESTONE.defaultBlockState();

                            level.setBlock(below, support, 2);
                            below = below.below();
                        }
                    }
                }
            }
        }
    }
}
