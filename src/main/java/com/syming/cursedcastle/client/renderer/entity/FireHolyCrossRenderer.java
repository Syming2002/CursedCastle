package com.syming.cursedcastle.client.renderer.entity;

import com.syming.cursedcastle.entity.projectile.subweapon.FireHolyCrossEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.BlockPos;

public class FireHolyCrossRenderer extends ThrownItemRenderer<FireHolyCrossEntity> {

    public FireHolyCrossRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    protected int getBlockLightLevel(FireHolyCrossEntity pEntity, BlockPos pPos) {
        return 7;
    }
}
