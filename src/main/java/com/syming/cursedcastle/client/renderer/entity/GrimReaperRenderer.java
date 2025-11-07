package com.syming.cursedcastle.client.renderer.entity;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.model.GrimReaperModel;
import com.syming.cursedcastle.client.layer.DeathScytheInHandLayer;
import com.syming.cursedcastle.entity.mob.boss.GrimReaperEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GrimReaperRenderer extends MobRenderer<GrimReaperEntity, GrimReaperModel> {
    public static final ResourceLocation GRIM_REAPER_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/grim_reaper.png");

    public GrimReaperRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new GrimReaperModel(), 0.5F);

        addLayer(new DeathScytheInHandLayer(this, pContext.getItemInHandRenderer()));
    }

    @Override
    protected float getFlipDegrees(GrimReaperEntity pLivingEntity) {
        return 0.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(GrimReaperEntity pEntity) {
        return GRIM_REAPER_TEXTURE;
    }
}
