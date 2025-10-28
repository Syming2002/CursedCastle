package com.syming.cursedcastle.block.blockentity;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.block.CCBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CCBlockEntity {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CursedCastleMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<CCHeadBlockEntity>> CC_HEAD = BLOCK_ENTITIES.register("cc_head", () -> BlockEntityType.Builder.of(CCHeadBlockEntity::new,
                    CCBlock.DRACULA_HEAD.get(), CCBlock.DRACULA_WALL_HEAD.get())
            .build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
