package fr.deltadesnoc.asgaria.container;

import fr.deltadesnoc.asgaria.capabilities.AsgariaCapabilities;
import fr.deltadesnoc.asgaria.capabilities.IAsgaria;
import fr.deltadesnoc.asgaria.capabilities.IAsgariaItemHandler;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ContainerPlayerExpanded extends Container {

    public final InventoryCrafting craftMatrix = new InventoryCrafting(this, 2, 2);
    public final InventoryCraftResult craftResult = new InventoryCraftResult();

    public IAsgariaItemHandler rune;

    public boolean isLocalWorld;

    private final EntityPlayer thePlayer;

    private static final EntityEquipmentSlot[] equipmentSlots = new EntityEquipmentSlot[] { EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET };

    public ContainerPlayerExpanded(InventoryPlayer playerInv, boolean arg2, final EntityPlayer player){
        this.isLocalWorld = arg2;
        this.thePlayer = player;
        this.rune = (IAsgariaItemHandler)player.getCapability(AsgariaCapabilities.CAPABILITY_RUNE, null);

        addSlotToContainer((Slot)new SlotCrafting(playerInv.player, this.craftMatrix, (IInventory)this.craftResult, 0, 144, 27));
        for (int j = 0; j < 2; j++) {
            for (int m = 0; m < 2; m++)
                addSlotToContainer(new Slot((IInventory)this.craftMatrix, m + j * 2, 103 + m * 18, 17 + j * 18));
        }
        for (int k = 0; k < 4; k++) {
            final EntityEquipmentSlot slot = equipmentSlots[k];
            addSlotToContainer(new Slot((IInventory)playerInv, 36 + 3 - k, 8, 8 + k * 18) {
                public int getSlotStackLimit() {
                    return 1;
                }

                public boolean isItemValid(ItemStack stack) {
                    return stack.getItem().isValidArmor(stack, slot, (Entity)player);
                }

                public boolean canTakeStack(EntityPlayer playerIn) {
                    ItemStack itemstack = getStack();
                    return (!itemstack.isEmpty() && !playerIn.isCreative() && EnchantmentHelper.hasBindingCurse(itemstack)) ? false : super.canTakeStack(playerIn);
                }

                public String getSlotTexture() {
                    return ItemArmor.EMPTY_SLOT_NAMES[slot.getIndex()];
                }
            });
        }
        addSlotToContainer(new SlotRune(player, rune, 0, 80, 17));
        addSlotToContainer(new SlotRune(player, rune, 1, 80, 35));
        addSlotToContainer(new SlotRune(player, rune, 2, 80, 53));

        int i;
        for (i = 0; i < 3; i++) {
            for (int m = 0; m < 9; m++)
                addSlotToContainer(new Slot((IInventory)playerInv, m + (i + 1) * 9, 8 + m * 18, 84 + i * 18));
        }
        for (i = 0; i < 9; i++)
            addSlotToContainer(new Slot((IInventory)playerInv, i, 8 + i * 18, 142));
        addSlotToContainer(new Slot((IInventory)playerInv, 40, 106, 61) {
            public boolean isItemValid(ItemStack stack) {
                return super.isItemValid(stack);
            }

            public String getSlotTexture() {
                return "minecraft:items/empty_armor_slot_shield";
            }
        });
        onCraftMatrixChanged((IInventory)this.craftMatrix);
    }

    public void onCraftMatrixChanged(IInventory par1IInventory) {
        slotChangedCraftingGrid(this.thePlayer.getEntityWorld(), this.thePlayer, this.craftMatrix, this.craftResult);
    }

    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);
        this.craftResult.clear();
        if (!player.world.isRemote)
            clearContainer(player, player.world, (IInventory)this.craftMatrix);
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return true;
    }

    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            EntityEquipmentSlot entityequipmentslot = EntityLiving.getSlotForItemStack(itemstack);
            int slotShift = this.rune.getSlots();
            if (index == 0) {
                if (!mergeItemStack(itemstack1, 9 + slotShift, 45 + slotShift, true))
                    return ItemStack.EMPTY;
                slot.onSlotChange(itemstack1, itemstack);
            } else if (index >= 1 && index < 5) {
                if (!mergeItemStack(itemstack1, 9 + slotShift, 45 + slotShift, false))
                    return ItemStack.EMPTY;
            } else if (index >= 5 && index < 9) {
                if (!mergeItemStack(itemstack1, 9 + slotShift, 45 + slotShift, false))
                    return ItemStack.EMPTY;
            } else if (index >= 9 && index < 9 + slotShift) {
                if (!mergeItemStack(itemstack1, 9 + slotShift, 45 + slotShift, false))
                    return ItemStack.EMPTY;
            } else if (entityequipmentslot.getSlotType() == EntityEquipmentSlot.Type.ARMOR && !((Slot)this.inventorySlots.get(8 - entityequipmentslot.getIndex())).getHasStack()) {
                int i = 8 - entityequipmentslot.getIndex();
                if (!mergeItemStack(itemstack1, i, i + 1, false))
                    return ItemStack.EMPTY;
            } else if (entityequipmentslot == EntityEquipmentSlot.OFFHAND && !((Slot)this.inventorySlots.get(45 + slotShift)).getHasStack()) {
                if (!mergeItemStack(itemstack1, 45 + slotShift, 46 + slotShift, false))
                    return ItemStack.EMPTY;
            } else if (itemstack.hasCapability(AsgariaCapabilities.CAPABILITY_ITEM_RUNE, null)) {
                IAsgaria rune = (IAsgaria)itemstack.getCapability(AsgariaCapabilities.CAPABILITY_ITEM_RUNE, null);
                for (int runeSlot : rune.getAsgariaType(itemstack).getValidSlots()) {
                    if (rune.canEquip(itemstack1, (EntityLivingBase)this.thePlayer) && !((Slot)this.inventorySlots.get(runeSlot + 9)).getHasStack() &&
                            !mergeItemStack(itemstack1, runeSlot + 9, runeSlot + 10, false))
                        return ItemStack.EMPTY;
                    if (itemstack1.getCount() == 0)
                        break;
                }
            } else if (index >= 9 + slotShift && index < 36 + slotShift) {
                if (!mergeItemStack(itemstack1, 36 + slotShift, 45 + slotShift, false))
                    return ItemStack.EMPTY;
            } else if (index >= 36 + slotShift && index < 45 + slotShift) {
                if (!mergeItemStack(itemstack1, 9 + slotShift, 36 + slotShift, false))
                    return ItemStack.EMPTY;
            } else if (!mergeItemStack(itemstack1, 9 + slotShift, 45 + slotShift, false)) {
                return ItemStack.EMPTY;
            }
            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
            if (itemstack1.getCount() == itemstack.getCount())
                return ItemStack.EMPTY;
            if (itemstack1.isEmpty() && !this.rune.isEventBlocked() && slot instanceof SlotRune && itemstack
                    .hasCapability(AsgariaCapabilities.CAPABILITY_ITEM_RUNE, null))
                ((IAsgaria)itemstack.getCapability(AsgariaCapabilities.CAPABILITY_ITEM_RUNE, null)).onUnequipped(itemstack, (EntityLivingBase)playerIn);
            ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);
            if (index == 0)
                playerIn.dropItem(itemstack2, false);
        }
        return itemstack;
    }

    public boolean canMergeSlot(ItemStack stack, Slot slot) {
        return (slot.inventory != this.craftResult && super.canMergeSlot(stack, slot));
    }

}
