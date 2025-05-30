package com.syming.castlevaniacraft.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModelCCHead extends ModelBase {


    public ModelRenderer ccHead;

    public ModelCCHead() {
        this(0, 35, 64, 64);
    }

    public ModelCCHead(int p_i1155_1_, int p_i1155_2_, int p_i1155_3_, int p_i1155_4_) {
        this.textureWidth = p_i1155_3_;
        this.textureHeight = p_i1155_4_;
        this.ccHead = new ModelRenderer(this, p_i1155_1_, p_i1155_2_);
        this.ccHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.ccHead.setRotationPoint(0.0F, 0.0F, 0.0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.ccHead.render(scale);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
        this.ccHead.rotateAngleY = netHeadYaw * 0.017453292F;
        this.ccHead.rotateAngleX = headPitch * 0.017453292F;
    }
}
