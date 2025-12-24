//package com.syming.cursedcastle.client.model;
//
//import com.mojang.blaze3d.vertex.PoseStack;
//import com.mojang.blaze3d.vertex.VertexConsumer;
//import com.syming.cursedcastle.CursedCastleMod;
//import com.syming.cursedcastle.entity.CrucifixEntity;
//import net.minecraft.client.model.EntityModel;
//import net.minecraft.client.model.geom.ModelLayerLocation;
//import net.minecraft.client.model.geom.ModelPart;
//import net.minecraft.client.model.geom.PartPose;
//import net.minecraft.client.model.geom.builders.*;
//import net.minecraft.resources.ResourceLocation;
//
//public class CrucifixModel extends EntityModel<CrucifixEntity> {
//
//    public static final ModelLayerLocation CRUCIFIX_LAYER =
//            new ModelLayerLocation(new ResourceLocation(CursedCastleMod.MOD_ID, "crucifix"), "main");
//
//    public final ModelPart crucifix;
//    public final ModelPart base;
//    public final ModelPart beam;
//    public final ModelPart rightBeam;
//    public final ModelPart leftBeam;
//
//    public CrucifixModel(ModelPart pRoot) {
//        this.crucifix = pRoot.getChild("crucifix");
//        this.base = this.crucifix.getChild("base");
//        this.beam = this.crucifix.getChild("beam");
//        this.rightBeam = this.crucifix.getChild("rightBeam");
//        this.leftBeam = this.crucifix.getChild("leftBeam");
//    }
//
//    public static LayerDefinition createBodyLayer() {
//        MeshDefinition mesh = new MeshDefinition();
//        PartDefinition root = mesh.getRoot();
//
//        PartDefinition crucifix = root.addOrReplaceChild(
//                "crucifix",
//                CubeListBuilder.create(),
//                PartPose.offset(0.0F, 0.0F, 0.0F)
//        );
//
//        PartDefinition base = crucifix.addOrReplaceChild(
//                "base",
//                CubeListBuilder.create()
//                        .texOffs(0, 0)
//                        .addBox(-6.0F, 0.5F, -6.0F, 12, 1, 12)
//                        .texOffs(6, 13)
//                        .addBox(-4.0F, -0.5F, -4.0F, 8, 1, 8)
//                        .texOffs(6, 25)
//                        .addBox(-2.0F, -1.5F, -2.0F, 4, 1, 4),
//                PartPose.offset(0.0F, -1.5F, 0.0F)
//        );
//
//        PartDefinition beam = crucifix.addOrReplaceChild(
//                "beam",
//                CubeListBuilder.create()
//                        .texOffs(0, 13)
//                        .addBox(-1.0F, -20.0F, -0.5F, 2, 40, 1),
//                PartPose.offset(0.0F, -23.0F, 0.0F)
//        );
//
//        PartDefinition leftBeam = crucifix.addOrReplaceChild(
//                "leftBeam",
//                CubeListBuilder.create()
//                        .texOffs(24, 22)
//                        .addBox(-4.5F, -1.0F, -0.5F, 8, 2, 1),
//                PartPose.offset(5.5F, -34.0F, 0.0F)
//        );
//
//        PartDefinition rightBeam = crucifix.addOrReplaceChild(
//                "rightBeam",
//                CubeListBuilder.create()
//                        .texOffs(6, 22)
//                        .addBox(-3.5F, -1.0F, -0.5F, 8, 2, 1),
//                PartPose.offset(-5.5F, -34.0F, 0.0F)
//        );
//
//        return LayerDefinition.create(mesh, 64, 64);
//    }
//
//    @Override
//    public void setupAnim(CrucifixEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
//        this.crucifix.yRot = ((float) Math.PI / 180.0F) * pEntity.getBasePose().getY();
//    }
//
//    @Override
//    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
//        this.crucifix.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
//    }
//}
