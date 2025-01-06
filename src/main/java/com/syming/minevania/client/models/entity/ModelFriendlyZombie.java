package com.syming.minevania.client.models.entity;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.entity.Entity;

public class ModelFriendlyZombie extends ModelZombie {

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

}
