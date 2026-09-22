package com.syming.cursedcastle.client.renderer.entity.rusalka;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.model.RusalkaModel;
import com.syming.cursedcastle.entity.monster.boss.RusalkaEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class RusalkaEyesLayer extends EyesLayer<RusalkaEntity, RusalkaModel> {
    private static final RenderType RUSALKA_EYES = RenderType.eyes(ResourceLocation.fromNamespaceAndPath(CursedCastleMod.MOD_ID, "textures/entity/rusalka/rusalka_eyes.png"));

    public RusalkaEyesLayer(RenderLayerParent<RusalkaEntity, RusalkaModel> pRenderer) {
        super(pRenderer);
    }

    @Override
    public RenderType renderType() {
        return RUSALKA_EYES;
    }
}
