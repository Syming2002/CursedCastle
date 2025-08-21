package com.syming.castlevaniacraft.client.model.entity;

// Made with Blockbench 4.10.2
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports

import com.syming.castlevaniacraft.entity.mobs.shade.EntityShade;
import com.syming.castlevaniacraft.entity.mobs.shade.EntityThunderShade;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.entity.Entity;

public class ModelThunderShade extends ModelShade {

	public ModelThunderShade() {
		super();
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
		super.render(entity, limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale);
	}

	@Override
	public void animate(IAnimatedEntity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
		super.animate(entity, limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);

		EntityThunderShade thunderShade = (EntityThunderShade) entity;

		if (thunderShade.getAnimation() == EntityThunderShade.LIGHTNING_ATTACK_ANIMATION && thunderShade.isEntityAlive()) {
			animator.setAnimation(EntityThunderShade.LIGHTNING_ATTACK_ANIMATION);

			animator.startKeyframe(40);

			this.rotate(animator, right_arm, 0, 0, 50.0F);
			this.rotate(animator, left_arm, 0, 0, -50.0F);
			animator.endKeyframe();
			animator.setStaticKeyframe(20);

			animator.resetKeyframe(40);
		}
	}
}