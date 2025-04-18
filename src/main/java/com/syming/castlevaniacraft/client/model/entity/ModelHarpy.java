package com.syming.castlevaniacraft.client.model.entity;

import com.syming.castlevaniacraft.entity.mobs.EntityHarpy;
import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelBox;
import net.minecraft.entity.Entity;

public class ModelHarpy extends CCModel {

    private final AdvancedModelRenderer model_core;
    private final AdvancedModelRenderer head;
    private final AdvancedModelRenderer body;
    private final AdvancedModelRenderer right_wing;
    private final AdvancedModelRenderer left_wing;
    private final AdvancedModelRenderer right_leg;
    private final AdvancedModelRenderer right_thigh;
    private final AdvancedModelRenderer left_leg;
    private final AdvancedModelRenderer left_thigh;

    private ModelAnimator animator;

    public ModelHarpy() {
        textureWidth = 128;
        textureHeight = 128;

        model_core = new AdvancedModelRenderer(this);
        model_core.setRotationPoint(1.0F, 24.0F, -1.5632F);


        head = new AdvancedModelRenderer(this);
        head.setRotationPoint(-1.0F, -40.0F, -3.0F);
        model_core.addChild(head);
        head.cubeList.add(new ModelBox(head, 60, 0, -4.0F, -4.0F, -4.0F, 8, 8, 8, -0.125F, false));

        body = new AdvancedModelRenderer(this);
        body.setRotationPoint(-1.0F, -29.4954F, -0.1974F);
        model_core.addChild(body);
        setRotationAngle(body, 0.4363F, 0.0F, 0.0F);
        body.cubeList.add(new ModelBox(body, 60, 16, -4.0F, -8.0F, -2.0F, 8, 16, 4, 0.0F, false));

        right_wing = new AdvancedModelRenderer(this);
        right_wing.setRotationPoint(-5.7F, -34.5F, -2.775F);
        model_core.addChild(right_wing);
        setRotationAngle(right_wing, 0.0877F, -0.3535F, 2.2286F);
        right_wing.cubeList.add(new ModelBox(right_wing, 0, 66, -2.0401F, -0.4944F, -1.8817F, 3, 16, 4, 0.0F, false));
        right_wing.cubeList.add(new ModelBox(right_wing, 30, 0, -2.0651F, 1.5056F, 1.1183F, 0, 35, 15, 0.0F, false));

        left_wing = new AdvancedModelRenderer(this);
        left_wing.setRotationPoint(3.7F, -34.5F, -2.775F);
        model_core.addChild(left_wing);
        setRotationAngle(left_wing, 0.0877F, 0.3535F, -2.2286F);
        left_wing.cubeList.add(new ModelBox(left_wing, 14, 66, -0.9599F, -0.4944F, -1.8817F, 3, 16, 4, 0.0F, false));
        left_wing.cubeList.add(new ModelBox(left_wing, 0, 0, 2.0651F, 1.5056F, 1.1183F, 0, 35, 15, 0.0F, false));

        right_leg = new AdvancedModelRenderer(this);
        right_leg.setRotationPoint(-3.0F, -14.0F, -2.0F);
        model_core.addChild(right_leg);
        setRotationAngle(right_leg, 0.4363F, 0.0F, 0.0F);
        right_leg.cubeList.add(new ModelBox(right_leg, 64, 36, -2.0F, -1.2943F, -1.8168F, 4, 16, 4, 0.0F, false));

        right_thigh = new AdvancedModelRenderer(this);
        right_thigh.setRotationPoint(0.0F, -3.2943F, 8.1832F);
        right_leg.addChild(right_thigh);
        right_thigh.cubeList.add(new ModelBox(right_thigh, 0, 50, -2.0F, -2.0F, -10.0F, 4, 4, 12, 0.0F, false));

        left_leg = new AdvancedModelRenderer(this);
        left_leg.setRotationPoint(1.0F, -14.0F, -2.0F);
        model_core.addChild(left_leg);
        setRotationAngle(left_leg, 0.4363F, 0.0F, 0.0F);
        left_leg.cubeList.add(new ModelBox(left_leg, 64, 56, -2.0F, -1.2943F, -1.8168F, 4, 16, 4, 0.0F, false));

        left_thigh = new AdvancedModelRenderer(this);
        left_thigh.setRotationPoint(0.0F, -3.2943F, 8.1832F);
        left_leg.addChild(left_thigh);
        left_thigh.cubeList.add(new ModelBox(left_thigh, 32, 50, -2.0F, -2.0F, -10.0F, 4, 4, 12, 0.0F, false));

        this.animator = ModelAnimator.create();
        updateDefaultPose();
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
        EntityHarpy harpy = (EntityHarpy) entity;
        animate(harpy, limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale);
        model_core.render(scale);
    }

    public void animate(IAnimatedEntity entity, float limbSwing, float limbSwingAmount, float ageInTick, float headYaw, float headPitch, float scale) {
        EntityHarpy harpy = (EntityHarpy) entity;
        animator.update(harpy);
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTick, headYaw, headPitch, scale, harpy);

        if (harpy.getAnimation() == EntityHarpy.FEATHER_ATTACK && harpy.isEntityAlive()) {
            animator.setAnimation(EntityHarpy.FEATHER_ATTACK);

            animator.startKeyframe(20);
            this.rotate(animator, right_wing, 51.2193380833F, -7.2766554875F, -68.429738455F);
            this.rotate(animator, left_wing, 51.2193380833F, 7.2766554875F, 68.429738455F);
            animator.endKeyframe();

            animator.startKeyframe(2);
            this.rotate(animator, right_wing, -62.35F, 13.66F, -80.95F);
            this.rotate(animator, left_wing, -62.35F, -13.66F, 80.95F);
            animator.endKeyframe();

            animator.resetKeyframe(13);
        }
        if (harpy.getAnimation() == EntityHarpy.DAGGER_ATTACK && harpy.isEntityAlive()) {
            animator.setAnimation(EntityHarpy.DAGGER_ATTACK);

            animator.startKeyframe(25);
            this.rotate(animator, model_core, -15.0F, 0, 0);
            animator.endKeyframe();

            animator.startKeyframe(7);
            this.rotate(animator, model_core, 360, 0, 0);
            animator.endKeyframe();
        }
    }

    public void setRotationAngle(AdvancedModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);

        resetToDefaultPose();

        this.head.rotateAngleX = headPitch * 0.017453292F;
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;

        EntityHarpy harpy = (EntityHarpy) entity;
        if (harpy.getAnimation() != EntityHarpy.FEATHER_ATTACK) {
            float wingFlapAngle = Math.abs((float) Math.cos(0.275F * ageInTicks)) * 2.5F;

            right_wing.rotateAngleZ = wingFlapAngle;
            left_wing.rotateAngleZ = -wingFlapAngle;
        }
    }
}
