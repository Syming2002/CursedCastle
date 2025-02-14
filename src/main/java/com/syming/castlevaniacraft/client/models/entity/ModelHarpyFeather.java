package com.syming.castlevaniacraft.client.models.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHarpyFeather extends ModelBase {

    private final ModelRenderer model_core;
    private final ModelRenderer feather;

    public ModelHarpyFeather() {
        textureWidth = 64;
        textureHeight = 64;

        model_core = new ModelRenderer(this);
        model_core.setRotationPoint(0.5F, 24.0F, 0.5F);


        feather = new ModelRenderer(this);
        feather.setRotationPoint(0.0F, 0.0F, 0.0F);
        model_core.addChild(feather);
        feather.cubeList.add(new ModelBox(feather, 32, 17, -1.0F, -1.0F, -7.0F, 1, 1, 12, 0.0F, false));
        feather.cubeList.add(new ModelBox(feather, 0, 17, -1.0F, -2.0F, -8.0F, 1, 1, 15, 0.0F, false));
        feather.cubeList.add(new ModelBox(feather, 0, 0, -1.0F, -3.0F, -8.0F, 1, 1, 16, 0.0F, false));
        feather.cubeList.add(new ModelBox(feather, 32, 30, -1.0F, -4.0F, -6.0F, 1, 1, 12, 0.0F, false));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        model_core.render(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

}
