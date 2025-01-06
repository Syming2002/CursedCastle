package com.syming.minevania.entity.ai.rusalka;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

import static com.syming.minevania.entity.ai.RandomWaterPositionGenerator.getWaterPosition;

public class AIEntityOnWaterWanderAvoidGround extends EntityAIWander {

    protected final float probability;

    public AIEntityOnWaterWanderAvoidGround(EntityCreature creature, double speedIn) {
        this(creature, speedIn, 0.001F);
    }

    public AIEntityOnWaterWanderAvoidGround(EntityCreature creature, double speedIn, float probability) {
        super(creature, speedIn);
        this.probability = probability;
    }

    @Override
    @Nullable
    protected Vec3d getPosition() {
        if (!entity.isInWater()) {
            return getWaterPosition(this.entity, 15, 7);
        }
        else
        {
            return this.entity.getRNG().nextFloat() >= this.probability ? getWaterPosition(this.entity, 10, 7) : null;
        }
    }


}
