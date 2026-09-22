package com.syming.cursedcastle.capability;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.capability.data.IPlayerData;
import com.syming.cursedcastle.capability.data.PlayerData;
import com.syming.cursedcastle.capability.data.mp.PlayerMP;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.IModBusEvent;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class CCCapabilities {

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, CursedCastleMod.MOD_ID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<PlayerMP>> PLAYER_MP = ATTACHMENT_TYPES.register("player_mp", () -> AttachmentType.serializable(PlayerMP::new).build());

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<PlayerData>> PLAYER_DATA = ATTACHMENT_TYPES.register("player_data", () -> AttachmentType.serializable(PlayerData::new).build());

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }

}
