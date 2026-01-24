package com.syming.cursedcastle.client.renderer.entity.rusalka;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.model.RusalkaModel;
import com.syming.cursedcastle.entity.mob.boss.RusalkaEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class RusalkaEyesLayer<T extends RusalkaEntity> extends EyesLayer<T, RusalkaModel<T>> {
    private static final RenderType RUSALKA_EYES = RenderType.eyes(new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/rusalka/rusalka_eyes.png"));

    public RusalkaEyesLayer(RenderLayerParent<T, RusalkaModel<T>> pRenderer) {
        super(pRenderer);

        System.out.println("RusalkaEyesLayer");
    }

    @Override
    public RenderType renderType() {
        return RUSALKA_EYES;
    }
}
