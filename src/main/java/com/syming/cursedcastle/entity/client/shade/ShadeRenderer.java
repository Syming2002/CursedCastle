package com.syming.cursedcastle.entity.client.shade;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.entity.mob.shade.*;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ShadeRenderer extends MobRenderer<ShadeEntity, ShadeModel> {

    public static final ResourceLocation FROZEN_SHADE_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/frozen_shade.png");
    public static final ResourceLocation HEAT_SHADE_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/heat_shade.png");
    public static final ResourceLocation POISON_SHADE_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/poison_shade.png");
    public static final ResourceLocation THUNDER_SHADE_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/thunder_shade.png");


    public ShadeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ShadeModel(), 0.5F);
    }

    @Override
    protected float getFlipDegrees(ShadeEntity pLivingEntity) {
        return 0.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(ShadeEntity pEntity) {
        if (pEntity instanceof FrozenShadeEntity) {
            return FROZEN_SHADE_TEXTURE;
        } else if (pEntity instanceof HeatShadeEntity) {
            return HEAT_SHADE_TEXTURE;
        } else if (pEntity instanceof PoisonShadeEntity) {
            return POISON_SHADE_TEXTURE;
        } else if (pEntity instanceof ThunderShadeEntity) {
            return THUNDER_SHADE_TEXTURE;
        }
        return null;
    }
}
