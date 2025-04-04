package com.syming.castlevaniacraft.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityAlchemyTable extends TileEntity implements IInventory {

    private NonNullList<ItemStack> alchemyTableItemStacks = NonNullList.<ItemStack>withSize(9, ItemStack.EMPTY);

    public TileEntityAlchemyTable() {
    }

    @Override
    public int getSizeInventory() {
        return this.alchemyTableItemStacks.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : this.alchemyTableItemStacks) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.alchemyTableItemStacks.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.alchemyTableItemStacks, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.alchemyTableItemStacks, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.alchemyTableItemStacks.set(index, stack);
        markDirty();
    }

    @Override
    public int getInventoryStackLimit() {
        return this.alchemyTableItemStacks.size();
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        if(this.world.getTileEntity(this.pos) != this)
        {
            return false;
        }
        else
        {
            return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return index < 8;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        this.alchemyTableItemStacks.clear();
    }

    @Override
    public String getName() {
        return new TextComponentTranslation("container.alchemy_table").getUnformattedText();
    }

    @Override
    public ITextComponent getDisplayName() {
        return (ITextComponent)(this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName(), new Object[0]));
    }

    @Override
    public boolean hasCustomName() {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        ItemStackHelper.loadAllItems(compound, this.alchemyTableItemStacks);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        ItemStackHelper.saveAllItems(compound, this.alchemyTableItemStacks);
        return compound;
    }
}
