package com.syming.castlevaniacraft.client.model.entity;

// Made with Blockbench 4.10.2
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports

import com.syming.castlevaniacraft.entity.mobs.shade.EntityPoisonShade;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelBox;
import net.minecraft.entity.Entity;

public class ModelPoisonShade extends ModelShade {

	public ModelPoisonShade() {
		super();
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
		super.render(entity, limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale);
	}

	public void animate(IAnimatedEntity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
		super.animate(entity, limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
	}
}