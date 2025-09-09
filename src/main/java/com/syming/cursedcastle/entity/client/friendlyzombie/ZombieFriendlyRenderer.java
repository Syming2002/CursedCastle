package com.syming.cursedcastle.entity.client.friendlyzombie;

import com.syming.cursedcastle.CursedCastleMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;

public class ZombieFriendlyRenderer extends ZombieRenderer {
    private static final ResourceLocation FRIENDLY_ZOMBIE_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/friendly_zombie.png");

    public ZombieFriendlyRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(Zombie pEntity) {
        return FRIENDLY_ZOMBIE_TEXTURE;
    }
}
