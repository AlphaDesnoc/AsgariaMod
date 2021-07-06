package fr.deltadesnoc.asgaria.capabilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class RuneContainer extends ItemStackHandler implements IAsgariaItemHandler {
    private static final int RUNE_SLOTS = 3;

    private boolean[] changed = new boolean[3];

    private boolean blockEvents = false;

    private EntityLivingBase player;

    public RuneContainer() {
        super(3);
    }

    public void setSize(int size) {
        if (size < 3)
            size = 3;
        super.setSize(size);
        boolean[] old = this.changed;
        this.changed = new boolean[size];
        for (int i = 0; i < old.length && i < this.changed.length; i++)
            this.changed[i] = old[i];
    }

    public boolean isItemValidForSlot(int slot, ItemStack stack, EntityLivingBase player) {
        if (stack == null || stack.isEmpty() || !stack.hasCapability(AsgariaCapabilities.CAPABILITY_ITEM_RUNE, null))
            return false;
        IAsgaria rune = (IAsgaria)stack.getCapability(AsgariaCapabilities.CAPABILITY_ITEM_RUNE, null);
        return (rune.canEquip(stack, player) && rune.getAsgariaType(stack).hasSlot(slot));
    }

    public void setStackInSlot(int slot, ItemStack stack) {
        if (stack == null || stack.isEmpty() || isItemValidForSlot(slot, stack, this.player))
            super.setStackInSlot(slot, stack);
    }

    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        if (!isItemValidForSlot(slot, stack, this.player))
            return stack;
        return super.insertItem(slot, stack, simulate);
    }

    public boolean isEventBlocked() {
        return this.blockEvents;
    }

    public void setEventBlock(boolean blockEvents) {
        this.blockEvents = blockEvents;
    }

    protected void onContentsChanged(int slot) {
        setChanged(slot, true);
    }

    public boolean isChanged(int slot) {
        if (this.changed == null)
            this.changed = new boolean[getSlots()];
        return this.changed[slot];
    }

    public void setChanged(int slot, boolean change) {
        if (this.changed == null)
            this.changed = new boolean[getSlots()];
        this.changed[slot] = change;
    }

    public void setPlayer(EntityLivingBase player) {
        this.player = player;
    }
}
