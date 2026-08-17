package com.syming.cursedcastle.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.model.ShadeModel;
import com.syming.cursedcastle.entity.mob.shade.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ShadeRenderer extends MobRenderer<ShadeEntity, ShadeModel<ShadeEntity>> {

    public static final ResourceLocation FROZEN_SHADE_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/shade/frozen_shade.png");
    public static final ResourceLocation POISON_SHADE_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/shade/poison_shade.png");
    public static final ResourceLocation THUNDER_SHADE_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/shade/thunder_shade.png");


    public ShadeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ShadeModel<>(pContext.bakeLayer(ShadeModel.SHADE_LAYER)), 0.5F);
    }

    @Override
    protected float getFlipDegrees(ShadeEntity pLivingEntity) {
        return 0.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(ShadeEntity pEntity) {
        if (pEntity instanceof FrozenShadeEntity) {
            return FROZEN_SHADE_TEXTURE;
        } else if (pEntity instanceof PoisonShadeEntity) {
            return POISON_SHADE_TEXTURE;
        } else if (pEntity instanceof ThunderShadeEntity) {
            return THUNDER_SHADE_TEXTURE;
        }
        return null;
    }
}
