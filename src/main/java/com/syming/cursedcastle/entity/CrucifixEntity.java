//package com.syming.cursedcastle.entity;
//
//import com.google.common.collect.ImmutableList;
//import com.syming.cursedcastle.item.CCItem;
//import com.syming.cursedcastle.item.CrucifixItem;
//import net.minecraft.core.Rotations;
//import net.minecraft.core.particles.BlockParticleOption;
//import net.minecraft.core.particles.ParticleTypes;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.nbt.ListTag;
//import net.minecraft.nbt.Tag;
//import net.minecraft.network.syncher.EntityDataAccessor;
//import net.minecraft.network.syncher.EntityDataSerializers;
//import net.minecraft.network.syncher.SynchedEntityData;
//import net.minecraft.server.level.ServerLevel;
//import net.minecraft.sounds.SoundEvents;
//import net.minecraft.tags.DamageTypeTags;
//import net.minecraft.world.damagesource.DamageSource;
//import net.minecraft.world.damagesource.DamageTypes;
//import net.minecraft.world.entity.*;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.entity.projectile.AbstractArrow;
//import net.minecraft.world.entity.vehicle.AbstractMinecart;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraft.world.level.block.state.properties.WoodType;
//import net.minecraft.world.level.gameevent.GameEvent;
//
//import java.util.List;
//import java.util.function.Predicate;
//
//import static net.minecraft.world.level.block.state.properties.WoodType.*;
//
//public class CrucifixEntity extends LivingEntity {
//
//    private static final Rotations DEFAULT_POSE = new Rotations(0.0F, 0.0F, 0.0F);
//    private static final EntityDataAccessor<Rotations> DATA_POSE = SynchedEntityData.defineId(CrucifixEntity.class, EntityDataSerializers.ROTATIONS);
//    private static final EntityDataAccessor<Integer> WOOD_TYPE = SynchedEntityData.defineId(CrucifixEntity.class, EntityDataSerializers.INT);
//    public static final List<WoodType> VANILLA_WOOD = List.of(OAK, SPRUCE, BIRCH, ACACIA, CHERRY, JUNGLE, DARK_OAK, CRIMSON, WARPED, MANGROVE, BAMBOO);
//
//    private Rotations basePose = DEFAULT_POSE;
//    public long lastHit;
//
//    private static final Predicate<Entity> RIDABLE_MINECARTS = (p_31582_) -> {
//        return p_31582_ instanceof AbstractMinecart && ((AbstractMinecart)p_31582_).getMinecartType() == AbstractMinecart.Type.RIDEABLE;
//    };
//
//    public CrucifixEntity(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
//        super(pEntityType, pLevel);
//    }
//
//    public CrucifixEntity(WoodType pWoodType, Level pLevel) {
//        super(CCEntity.CRUCIFIX_WOOD_TYPE.get(pWoodType).get(), pLevel);
//
//        setWoodType(pWoodType);
//    }
//
//    @Override
//    protected void defineSynchedData() {
//        super.defineSynchedData();
//        this.entityData.define(DATA_POSE, DEFAULT_POSE);
//        this.entityData.define(WOOD_TYPE, CrucifixEntity.getId(OAK));
//    }
//
//    @Override
//    public void tick() {
//        super.tick();
//
//        Rotations basePose = this.entityData.get(DATA_POSE);
//        if (!this.basePose.equals(basePose)) {
//            setBasePose(basePose);
//        }
//    }
//
//    @Override
//    public void setYBodyRot(float pOffset) {
//        this.yBodyRotO = this.yRotO = pOffset;
//        this.yHeadRotO = this.yHeadRot = pOffset;
//    }
//
//    @Override
//    public void setYHeadRot(float pRotation) {
//        this.yBodyRotO = this.yRotO = pRotation;
//        this.yHeadRotO = this.yHeadRot = pRotation;
//    }
//
//    @Override
//    public Iterable<ItemStack> getArmorSlots() {
//        return ImmutableList.of(ItemStack.EMPTY);
//    }
//
//    @Override
//    public ItemStack getItemBySlot(EquipmentSlot pSlot) {
//        return ItemStack.EMPTY;
//    }
//
//    @Override
//    public void setItemSlot(EquipmentSlot pSlot, ItemStack pStack) {
//
//    }
//
//    @Override
//    public HumanoidArm getMainArm() {
//        return null;
//    }
//
//    @Override
//    public void addAdditionalSaveData(CompoundTag pCompound) {
//        super.addAdditionalSaveData(pCompound);
//
//        pCompound.put("Pose", writePos());
//        pCompound.putInt("WoodType", this.entityData.get(WOOD_TYPE));
//    }
//
//    @Override
//    public void readAdditionalSaveData(CompoundTag pCompound) {
//        super.readAdditionalSaveData(pCompound);
//
//        CompoundTag compoundTag = pCompound.getCompound("Pose");
//
//        if (pCompound.contains("WoodType", Tag.TAG_STRING)) {
//            this.entityData.set(WOOD_TYPE, pCompound.getInt("WoodType"));
//        }
//
//        readPose(compoundTag);
//    }
//
//    @Override
//    public boolean isPushable() {
//        return false;
//    }
//
//    @Override
//    protected void pushEntities() {
//        List<Entity> list = this.level().getEntities(this, this.getBoundingBox(), RIDABLE_MINECARTS);
//
//        for(int i = 0; i < list.size(); ++i) {
//            Entity entity = list.get(i);
//            if (this.distanceToSqr(entity) <= 0.2D) {
//                entity.push(this);
//            }
//        }
//    }
//
//    @Override
//    public void handleEntityEvent(byte pId) {
//        if (pId == 32) {
//            if (this.level().isClientSide) {
//                this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.ARMOR_STAND_HIT, this.getSoundSource(), 0.3F, 1.0F, false);
//                this.lastHit = this.level().getGameTime();
//            }
//        } else {
//            super.handleEntityEvent(pId);
//        }
//    }
//
//    @Override
//    public boolean canCollideWith(Entity pEntity) {
//        if (pEntity instanceof Mob mob) {
//            mob.setSecondsOnFire(5);
//        }
//        return false;
//    }
//
//    @Override
//    public boolean hurt(DamageSource pSource, float pAmount) {
//        if (this.level().isClientSide()) return false;
//
//        if (!this.isRemoved()) {
//            if (pSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
//                this.discard();
//                return false;
//            } else if (!this.isInvulnerableTo(pSource)) {
//                if (pSource.is(DamageTypeTags.IS_EXPLOSION)) {
//                    this.brokenByAnything(pSource);
//                    this.discard();
//                    return false;
//                } else if (pSource.is(DamageTypeTags.IGNITES_ARMOR_STANDS)) {
//                    if (this.isOnFire()) {
//                        this.causeDamage(pSource, 0.15F);
//                    } else {
//                        this.setSecondsOnFire(5);
//                    }
//
//                    return false;
//                } else if (pSource.is(DamageTypeTags.BURNS_ARMOR_STANDS) && this.getHealth() > 0.5F) {
//                    this.causeDamage(pSource, 4.0F);
//                    return false;
//                } else {
//                    boolean flag = pSource.getDirectEntity() instanceof AbstractArrow;
//                    boolean flag1 = flag && ((AbstractArrow)pSource.getDirectEntity()).getPierceLevel() > 0;
//                    boolean flag2 = "player".equals(pSource.getMsgId());
//                    if (!flag2 && !flag) {
//                        return false;
//                    } else {
//                        Entity entity = pSource.getEntity();
//                        if (entity instanceof Player) {
//                            Player player = (Player)entity;
//                            if (!player.getAbilities().mayBuild) {
//                                return false;
//                            }
//                        }
//
//                        if (pSource.isCreativePlayer()) {
//                            this.playBrokenSound();
//                            this.showBreakingParticles();
//                            this.discard();
//                            return flag1;
//                        } else {
//                            long i = this.level().getGameTime();
//                            if (i - this.lastHit > 5L && !flag) {
//                                this.level().broadcastEntityEvent(this, (byte)32);
//                                this.gameEvent(GameEvent.ENTITY_DAMAGE, pSource.getEntity());
//                                this.lastHit = i;
//                            } else {
//                                this.brokenByPlayer(pSource);
//                                this.showBreakingParticles();
//                                this.discard();
//                            }
//
//                            return true;
//                        }
//                    }
//                }
//            } else {
//                return false;
//            }
//        } else {
//            return false;
//        }
//    }
//
//    private void dropBlock() {
//        this.playBrokenSound();
//        if (this.getWoodType() == OAK) {
//            Block.popResource(this.level(), this.blockPosition(), new ItemStack(CCItem.CRUCIFIX_TYPES.get(OAK).get()));
//        } else if (this.getWoodType() == SPRUCE) {
//            Block.popResource(this.level(), this.blockPosition(), new ItemStack(CCItem.CRUCIFIX_TYPES.get(SPRUCE).get()));
//        } else if (this.getWoodType() == BIRCH) {
//            Block.popResource(this.level(), this.blockPosition(), new ItemStack(CCItem.CRUCIFIX_TYPES.get(BIRCH).get()));
//        } else if (this.getWoodType() == ACACIA) {
//            Block.popResource(this.level(), this.blockPosition(), new ItemStack(CCItem.CRUCIFIX_TYPES.get(ACACIA).get()));
//        } else if (this.getWoodType() == CHERRY) {
//            Block.popResource(this.level(), this.blockPosition(), new ItemStack(CCItem.CRUCIFIX_TYPES.get(CHERRY).get()));
//        } else if (this.getWoodType() == JUNGLE) {
//            Block.popResource(this.level(), this.blockPosition(), new ItemStack(CCItem.CRUCIFIX_TYPES.get(JUNGLE).get()));
//        } else if (this.getWoodType() == DARK_OAK) {
//            Block.popResource(this.level(), this.blockPosition(), new ItemStack(CCItem.CRUCIFIX_TYPES.get(DARK_OAK).get()));
//        } else if (this.getWoodType() == CRIMSON) {
//            Block.popResource(this.level(), this.blockPosition(), new ItemStack(CCItem.CRUCIFIX_TYPES.get(CRIMSON).get()));
//        } else if (this.getWoodType() == WARPED) {
//            Block.popResource(this.level(), this.blockPosition(), new ItemStack(CCItem.CRUCIFIX_TYPES.get(WARPED).get()));
//        } else if (this.getWoodType() == MANGROVE) {
//            Block.popResource(this.level(), this.blockPosition(), new ItemStack(CCItem.CRUCIFIX_TYPES.get(MANGROVE).get()));
//        } else if (this.getWoodType() == BAMBOO) {
//            Block.popResource(this.level(), this.blockPosition(), new ItemStack(CCItem.CRUCIFIX_TYPES.get(BAMBOO).get()));
//        }
//    }
//
//    private void playBrokenSound() {
//        this.level().playSound((Player) null, this.getX(), this.getY(), this.getZ(), SoundEvents.ARMOR_STAND_BREAK, getSoundSource(), 1.0F, 1.0F);
//    }
//
//    private void readPose(CompoundTag compoundTag) {
//        ListTag listTag = compoundTag.getList("Base", 5);
//        setBasePose(listTag.isEmpty() ? DEFAULT_POSE : new Rotations(listTag));
//    }
//
//    private CompoundTag writePos() {
//        CompoundTag compoundTag = new CompoundTag();
//
//        if (basePose != DEFAULT_POSE) {
//            compoundTag.put("Base", this.basePose.save());
//        }
//
//        return compoundTag;
//    }
//
//    public void setBasePose(Rotations pBasePose) {
//        this.basePose = pBasePose;
//        this.entityData.set(DATA_POSE, pBasePose);
//    }
//
//    public Rotations getBasePose() {
//        return this.basePose;
//    }
//
//    private void brokenByPlayer(DamageSource pDamageSource) {
//        dropBlock();
//        this.brokenByAnything(pDamageSource);
//    }
//
//    private void brokenByAnything(DamageSource pDamageSource) {
//        this.playBrokenSound();
//        this.dropAllDeathLoot(pDamageSource);
//    }
//
//    private void showBreakingParticles() {
//        if (this.level() instanceof ServerLevel) {
//            if (this.getWoodType() == OAK) {
//                ((ServerLevel) this.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.OAK_PLANKS.defaultBlockState()), this.getX(), this.getY(0.6666666666666666D), this.getZ(), 10, (double) (this.getBbWidth() / 4.0F), (double) (this.getBbHeight() / 4.0F), (double) (this.getBbWidth() / 4.0F), 0.05D);
//            } else if (this.getWoodType() == SPRUCE) {
//                ((ServerLevel) this.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.SPRUCE_PLANKS.defaultBlockState()), this.getX(), this.getY(0.6666666666666666D), this.getZ(), 10, (double) (this.getBbWidth() / 4.0F), (double) (this.getBbHeight() / 4.0F), (double) (this.getBbWidth() / 4.0F), 0.05D);
//            } else if (this.getWoodType() == BIRCH) {
//                ((ServerLevel) this.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.BIRCH_PLANKS.defaultBlockState()), this.getX(), this.getY(0.6666666666666666D), this.getZ(), 10, (double) (this.getBbWidth() / 4.0F), (double) (this.getBbHeight() / 4.0F), (double) (this.getBbWidth() / 4.0F), 0.05D);
//            } else if (this.getWoodType() == ACACIA) {
//                ((ServerLevel) this.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.ACACIA_PLANKS.defaultBlockState()), this.getX(), this.getY(0.6666666666666666D), this.getZ(), 10, (double) (this.getBbWidth() / 4.0F), (double) (this.getBbHeight() / 4.0F), (double) (this.getBbWidth() / 4.0F), 0.05D);
//            } else if (this.getWoodType() == CHERRY) {
//                ((ServerLevel) this.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.CHERRY_PLANKS.defaultBlockState()), this.getX(), this.getY(0.6666666666666666D), this.getZ(), 10, (double) (this.getBbWidth() / 4.0F), (double) (this.getBbHeight() / 4.0F), (double) (this.getBbWidth() / 4.0F), 0.05D);
//            } else if (this.getWoodType() == JUNGLE) {
//                ((ServerLevel) this.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.JUNGLE_PLANKS.defaultBlockState()), this.getX(), this.getY(0.6666666666666666D), this.getZ(), 10, (double) (this.getBbWidth() / 4.0F), (double) (this.getBbHeight() / 4.0F), (double) (this.getBbWidth() / 4.0F), 0.05D);
//            } else if (this.getWoodType() == DARK_OAK) {
//                ((ServerLevel) this.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.DARK_OAK_PLANKS.defaultBlockState()), this.getX(), this.getY(0.6666666666666666D), this.getZ(), 10, (double) (this.getBbWidth() / 4.0F), (double) (this.getBbHeight() / 4.0F), (double) (this.getBbWidth() / 4.0F), 0.05D);
//            } else if (this.getWoodType() == CRIMSON) {
//                ((ServerLevel) this.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.CRIMSON_PLANKS.defaultBlockState()), this.getX(), this.getY(0.6666666666666666D), this.getZ(), 10, (double) (this.getBbWidth() / 4.0F), (double) (this.getBbHeight() / 4.0F), (double) (this.getBbWidth() / 4.0F), 0.05D);
//            } else if (this.getWoodType() == WARPED) {
//                ((ServerLevel) this.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.WARPED_PLANKS.defaultBlockState()), this.getX(), this.getY(0.6666666666666666D), this.getZ(), 10, (double) (this.getBbWidth() / 4.0F), (double) (this.getBbHeight() / 4.0F), (double) (this.getBbWidth() / 4.0F), 0.05D);
//            } else if (this.getWoodType() == MANGROVE) {
//                ((ServerLevel) this.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.MANGROVE_PLANKS.defaultBlockState()), this.getX(), this.getY(0.6666666666666666D), this.getZ(), 10, (double) (this.getBbWidth() / 4.0F), (double) (this.getBbHeight() / 4.0F), (double) (this.getBbWidth() / 4.0F), 0.05D);
//            } else if (this.getWoodType() == BAMBOO) {
//                ((ServerLevel) this.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.BAMBOO_PLANKS.defaultBlockState()), this.getX(), this.getY(0.6666666666666666D), this.getZ(), 10, (double) (this.getBbWidth() / 4.0F), (double) (this.getBbHeight() / 4.0F), (double) (this.getBbWidth() / 4.0F), 0.05D);
//            }
//        }
//    }
//
//    private void causeDamage(DamageSource pDamageSource, float pAmount) {
//        float f = this.getHealth();
//        f -= pAmount;
//        if (f <= 0.5F) {
//            this.brokenByAnything(pDamageSource);
//            this.kill();
//        } else {
//            this.setHealth(f);
//            this.gameEvent(GameEvent.ENTITY_DAMAGE, pDamageSource.getEntity());
//        }
//
//    }
//
//    public static int getId(WoodType type) {
//        int idx = VANILLA_WOOD.indexOf(type);
//        return Math.max(idx, 0);
//    }
//
//    public static WoodType byId(int id) {
//        if (id < 0 || id >= VANILLA_WOOD.size()) {
//            return WoodType.OAK;
//        }
//        return VANILLA_WOOD.get(id);
//    }
//
//    public WoodType getWoodType() {
//        return CrucifixEntity.byId(this.entityData.get(WOOD_TYPE));
//    }
//
//    public void setWoodType(WoodType type) {
//        this.entityData.set(WOOD_TYPE, CrucifixEntity.getId(type));
//    }
//}
