package com.syming.cursedcastle.world.entity.client.shade;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.world.entity.mob.shade.FrozenShadeEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FrozenShadeRenderer extends MobRenderer<FrozenShadeEntity, FrozenShadeModel> {

    public static final ResourceLocation FROZEN_SHADE_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/frozen_shade.png");

    public FrozenShadeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FrozenShadeModel(), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(FrozenShadeEntity pEntity) {
        return FROZEN_SHADE_TEXTURE;
    }
}
