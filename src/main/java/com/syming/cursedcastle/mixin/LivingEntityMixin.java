package com.syming.cursedcastle.mixin;

import com.syming.cursedcastle.item.CCItem;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "onClimbable", at = @At("HEAD"), cancellable = true)
    private void spiderClawClimb(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (CuriosApi.getCuriosHelper().findEquippedCurio(CCItem.SPIDER_CLAW.get(), entity).isPresent()) {
            if (entity.horizontalCollision) {
                cir.setReturnValue(true);
            }
        }
    }

}
