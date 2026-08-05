package com.syming.cursedcastle.mixin.client;

import com.sun.jna.platform.win32.OaIdl;
import com.syming.cursedcastle.data.PlayerData;
import com.syming.cursedcastle.data.PlayerDataProvider;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerModel.class)
public abstract class PlayerModelMixin<T extends LivingEntity> extends HumanoidModel<T> {

    @Shadow
    @Final
    public ModelPart rightSleeve;

    @Shadow
    @Final
    public ModelPart leftSleeve;

    @Shadow
    @Final
    public ModelPart rightPants;

    @Shadow
    @Final
    public ModelPart leftPants;

    static {
        System.out.println("PlayerModelMixin registered");
    }

    public PlayerModelMixin(ModelPart pRoot) {
        super(pRoot);
    }

    @Inject(method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V", at = @At("TAIL"))
    public void setupBoltAttackAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch, CallbackInfo ci) {
        if (pEntity instanceof Player player) {
            player.getCapability(PlayerDataProvider.PLAYER_DATA).ifPresent(data -> {
               if (data.isBoltAttackEnabled()) {
                   this.rightArm.zRot = rotlerpRad(0.45F, this.rightArm.zRot, Mth.PI / 6.0F);
                   this.leftArm.zRot = rotlerpRad(0.45F, this.leftArm.zRot, -Mth.PI / 6.0F);

                   this.rightSleeve.zRot = rotlerpRad(0.45F, this.rightArm.zRot, Mth.PI / 6.0F);
                   this.leftSleeve.zRot = rotlerpRad(0.45F, this.leftArm.zRot, -Mth.PI / 6.0F);

                   this.rightLeg.zRot = rotlerpRad(0.45F, this.rightLeg.zRot, Mth.PI / 6.0F);
                   this.leftLeg.zRot = rotlerpRad(0.45F, this.leftLeg.zRot, -Mth.PI / 6.0F);

                   this.rightPants.zRot = rotlerpRad(0.45F, this.rightLeg.zRot, Mth.PI / 6.0F);
                   this.leftPants.zRot = rotlerpRad(0.45F, this.leftLeg.zRot, -Mth.PI / 6.0F);
               }
            });
        }
    }
}
