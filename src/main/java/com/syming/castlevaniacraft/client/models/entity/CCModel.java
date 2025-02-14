package com.syming.castlevaniacraft.client.models.entity;

import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelBase;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;

public abstract class CCModel extends AdvancedModelBase {

    public void setRotationAngle(AdvancedModelRenderer AdvancedModelRenderer, float x, float y, float z) {
        AdvancedModelRenderer.rotateAngleX = x;
        AdvancedModelRenderer.rotateAngleY = y;
        AdvancedModelRenderer.rotateAngleZ = z;
    }

    public void rotate(ModelAnimator animator, AdvancedModelRenderer model, float x, float y, float z) {
        animator.rotate(model, (float) Math.toRadians(x), (float) Math.toRadians(y), (float) Math.toRadians(z));
    }

    @Override
    public void walk(AdvancedModelRenderer box, float speed, float degree, boolean invert, float offset, float weight, float walk, float walkAmount) {
        box.walk(speed, (float) Math.toRadians(degree), invert, offset, weight, walk, walkAmount);
    }

    @Override
    public void flap(AdvancedModelRenderer box, float speed, float degree, boolean invert, float offset, float weight, float flap, float flapAmount) {
        box.flap(speed, (float) Math.toRadians(degree), invert, offset, weight, flap, flapAmount);
    }

    @Override
    public void swing(AdvancedModelRenderer box, float speed, float degree, boolean invert, float offset, float weight, float swing, float swingAmount) {
        box.swing(speed, (float) Math.toRadians(degree), invert, offset, weight, swing, swingAmount);
    }

}
