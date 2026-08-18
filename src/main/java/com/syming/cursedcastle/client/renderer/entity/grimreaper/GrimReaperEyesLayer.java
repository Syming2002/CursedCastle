package com.syming.cursedcastle.client.renderer.entity.grimreaper;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.model.GrimReaperModel;
import com.syming.cursedcastle.entity.monster.boss.GrimReaperEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class GrimReaperEyesLayer<T extends GrimReaperEntity> extends EyesLayer<T, GrimReaperModel<T>> {
    private static final RenderType GRIM_REAPER_EYES = RenderType.eyes(new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/grimreaper/grim_reaper_eyes.png"));

    public GrimReaperEyesLayer(RenderLayerParent<T, GrimReaperModel<T>> pRenderer) {
        super(pRenderer);
    }

    @Override
    public RenderType renderType() {
        return GRIM_REAPER_EYES;
    }
}
