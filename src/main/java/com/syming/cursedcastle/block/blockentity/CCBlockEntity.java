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
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class CCBlockEntity {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CursedCastleMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<CCHeadBlockEntity>> CC_HEAD = BLOCK_ENTITIES.register("cc_head", () -> BlockEntityType.Builder.of(CCHeadBlockEntity::new,
                    CCBlock.DRACULA_HEAD.get(), CCBlock.DRACULA_WALL_HEAD.get())
            .build(null));

    public static final Map<WoodType, RegistryObject<BlockEntityType<CrucifixBlockEntity>>> CRUCIFIX_BLOCK_ENTITY_TYPES = new HashMap<>();

    static {
        for (WoodType woodType : WoodType.values().toList()) {
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

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
