package com.syming.cursedcastle.block.blockentity;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.CCBlock;
import com.syming.cursedcastle.block.CrucifixBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CCBlockEntity {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, CursedCastleMod.MOD_ID);

    public static final Supplier<BlockEntityType<CCHeadBlockEntity>> CC_HEAD = BLOCK_ENTITIES.register("cc_head", () -> BlockEntityType.Builder.of(CCHeadBlockEntity::new,
                    CCBlock.DRACULA_HEAD.get(), CCBlock.DRACULA_WALL_HEAD.get())
            .build(null));

    public static final Map<WoodType, Supplier<BlockEntityType<CrucifixBlockEntity>>> CRUCIFIX_BLOCK_ENTITY_TYPES = new HashMap<>();

    static {
        for (WoodType woodType : WoodType.values().toList()) {
            if (woodType != null) {
                if (woodType == WoodType.OAK
                        || woodType == WoodType.SPRUCE
                        || woodType == WoodType.BIRCH
                        || woodType == WoodType.JUNGLE
                        || woodType == WoodType.ACACIA
                        || woodType == WoodType.DARK_OAK
                        || woodType == WoodType.CRIMSON
                        || woodType == WoodType.WARPED
                        || woodType == WoodType.MANGROVE
                        || woodType == WoodType.CHERRY
                        || woodType == WoodType.BAMBOO) {
                    CRUCIFIX_BLOCK_ENTITY_TYPES.put(woodType, BLOCK_ENTITIES.register(woodType.name() + '_' + "crucifix", () -> BlockEntityType.Builder.of(((pPos, pState) -> new CrucifixBlockEntity(pPos, pState, woodType)),
                            CCBlock.CRUCIFIX_BLOCK_TYPES.get(woodType).get()).build(null)));
                }
            }
        }
    }

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
