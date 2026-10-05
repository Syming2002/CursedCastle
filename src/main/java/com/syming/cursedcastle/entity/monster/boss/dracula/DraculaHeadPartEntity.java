package com.syming.cursedcastle.entity.monster.boss.dracula;

import com.syming.cursedcastle.network.CCPacketHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.entity.PartEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DraculaHeadPartEntity extends PartEntity<DraculaEntity> {

    public final String name;
    private final EntityDimensions size;

    public DraculaHeadPartEntity(DraculaEntity parent, String name, float pWidth, float pHeight) {
        super(parent);

        this.name = name;
        this.size = EntityDimensions.scalable(pWidth, pHeight);
        refreshDimensions();
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        return this.size;
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (!this.level().isClientSide() && this.getParent() != null && !this.getParent().isInvulnerableTo(pSource)) {
            return this.getParent().hurtHead(this, pSource, pAmount);
        }
        return false;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    public boolean isPickable()
    {
        return this.getParent().isPickable();
    }

    @Override
    public boolean is(Entity entityIn) {
        return this == entityIn || this.getParent() == entityIn;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    public static void assignPartIDs(Entity parent) {
        PartEntity<?>[] parts = parent.getParts();

        for (int i = 0, length = parts.length; i < length; i++) {
            PartEntity<?> part = parts[i];
            part.setId(parent.getId() + i);
        }
    }

    @Override
    public void recreateFromPacket(@NotNull ClientboundAddEntityPacket packet) {
        super.recreateFromPacket(packet);

        assignPartIDs(this);
    }

    @Override
    public void setId(int pId) {
        super.setId(pId + 1);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    @Nullable
    public ItemStack getPickResult() {
        return this.getParent().getPickResult();
    }
}
