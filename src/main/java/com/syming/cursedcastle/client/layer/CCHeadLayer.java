package com.syming.cursedcastle.client.layer;

import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.syming.cursedcastle.client.renderer.blockentity.CCHeadBlockEntityRender;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.WalkAnimationState;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraft.world.level.block.SkullBlock;

import java.util.Map;

public class CCHeadLayer<T extends LivingEntity, M extends EntityModel<T> & HeadedModel> extends CustomHeadLayer<T, M> {
    private final float scaleX;
    private final float scaleY;
    private final float scaleZ;
    public Map<SkullBlock.Type, SkullModelBase> skullModelBaseMap;
    private final ItemInHandRenderer itemInHandRenderer;

    public CCHeadLayer(RenderLayerParent<T, M> pRenderer, EntityModelSet pModelSet, ItemInHandRenderer pItemInHandRenderer) {
        this(pRenderer, pModelSet, 1.0F, 1.0F, 1.0F, pItemInHandRenderer);
    }

    public CCHeadLayer(RenderLayerParent<T, M> pRenderer, EntityModelSet pModelSet, float pScaleX, float pScaleY, float pScaleZ, ItemInHandRenderer pItemInHandRenderer) {
        super(pRenderer, pModelSet, pScaleX, pScaleY, pScaleZ, pItemInHandRenderer);

        this.scaleX = pScaleX;
        this.scaleY = pScaleY;
        this.scaleZ = pScaleZ;
        this.skullModelBaseMap = CCHeadBlockEntityRender.createSkullRenderers(pModelSet);
        this.itemInHandRenderer = pItemInHandRenderer;
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        ItemStack itemstack = pLivingEntity.getItemBySlot(EquipmentSlot.HEAD);
        if (!itemstack.isEmpty()) {
            Item item = itemstack.getItem();
            pPoseStack.pushPose();
            pPoseStack.scale(this.scaleX, this.scaleY, this.scaleZ);
            boolean flag = pLivingEntity instanceof Villager || pLivingEntity instanceof ZombieVillager;
            if (pLivingEntity.isBaby() && !(pLivingEntity instanceof Villager)) {
                pPoseStack.translate(0.0F, 0.03125F, 0.0F);
                pPoseStack.scale(0.7F, 0.7F, 0.7F);
                pPoseStack.translate(0.0F, 1.0F, 0.0F);
            }

            this.getParentModel().getHead().translateAndRotate(pPoseStack);
            if (item instanceof BlockItem && ((BlockItem)item).getBlock() instanceof AbstractSkullBlock) {
                pPoseStack.scale(1.1875F, -1.1875F, -1.1875F);
                if (flag) {
                    pPoseStack.translate(0.0F, 0.0625F, 0.0F);
                }

                GameProfile gameprofile = null;
                if (itemstack.hasTag()) {
                    CompoundTag compoundtag = itemstack.getTag();
                    assert compoundtag != null;
                    if (compoundtag.contains("SkullOwner", 10)) {
                        gameprofile = NbtUtils.readGameProfile(compoundtag.getCompound("SkullOwner"));
                    }
                }

                pPoseStack.translate(-0.5D, 0.0D, -0.5D);
                SkullBlock.Type type = ((AbstractSkullBlock)((BlockItem)item).getBlock()).getType();
                SkullModelBase skullModelBase = this.skullModelBaseMap.get(type);
                RenderType rendertype = SkullBlockRenderer.getRenderType(type, gameprofile);
                Entity entity = pLivingEntity.getVehicle();
                WalkAnimationState walkanimationstate;
                if (entity instanceof LivingEntity livingentity) {
                    walkanimationstate = livingentity.walkAnimation;
                } else {
                    walkanimationstate = pLivingEntity.walkAnimation;
                }

                float f3 = walkanimationstate.position(pPartialTicks);
                CCHeadBlockEntityRender.renderSkull((Direction)null, 180.0F, f3, pPoseStack, pBuffer, pPackedLight, skullModelBase, rendertype);
            } else {
                label60: {
                    if (item instanceof ArmorItem) {
                        ArmorItem armoritem = (ArmorItem)item;
                        if (armoritem.getEquipmentSlot() == EquipmentSlot.HEAD) {
                            break label60;
                        }
                    }

                    translateToHead(pPoseStack, flag);
                    this.itemInHandRenderer.renderItem(pLivingEntity, itemstack, ItemDisplayContext.HEAD, false, pPoseStack, pBuffer, pPackedLight);
                }
            }

            pPoseStack.popPose();
        }
    }
}
