package com.syming.cursedcastle.client.renderer.entity;

import com.syming.cursedcastle.CursedCastleMod;
import com.syming.cursedcastle.client.model.CCZombieModel;
import com.syming.cursedcastle.entity.monster.zombie.CCZombieEntity;
import com.syming.cursedcastle.entity.monster.zombie.FireZombieEntity;
import com.syming.cursedcastle.entity.monster.zombie.FrostZombieEntity;
import com.syming.cursedcastle.entity.monster.zombie.friendly.FriendlyZombieEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CCZombieRenderer extends MobRenderer<CCZombieEntity, CCZombieModel<CCZombieEntity>> {

    public static final ResourceLocation FIRE_ZOMBIE_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/zombie/fire_zombie.png");
    public static final ResourceLocation FROST_ZOMBIE_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/zombie/frost_zombie.png");
    private static final ResourceLocation FRIENDLY_ZOMBIE_TEXTURE = new ResourceLocation(CursedCastleMod.MOD_ID, "textures/entity/friendly_zombie.png");

    public CCZombieRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new CCZombieModel<>(pContext.bakeLayer(CCZombieModel.CC_ZOMBIE_LAYER)), 0.5F);
    }


    @Override
    public ResourceLocation getTextureLocation(CCZombieEntity pEntity) {
        if (pEntity instanceof FireZombieEntity) {
            return FIRE_ZOMBIE_TEXTURE;
        } else if (pEntity instanceof FrostZombieEntity) {
            return FROST_ZOMBIE_TEXTURE;
        } else if (pEntity instanceof FriendlyZombieEntity) {
            return FRIENDLY_ZOMBIE_TEXTURE;
        }
        return null;
    }
}
