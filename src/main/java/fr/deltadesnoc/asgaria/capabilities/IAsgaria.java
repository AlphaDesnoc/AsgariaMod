package fr.deltadesnoc.asgaria.capabilities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public interface IAsgaria {
    AsgariaType getAsgariaType(ItemStack paramItemStack);

    default void onWornTick(ItemStack itemstack, EntityLivingBase player) {}

    default void onEquipped(ItemStack itemstack, EntityLivingBase player) {}

    default void onUnequipped(ItemStack itemstack, EntityLivingBase player) {}

    default boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    default boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    default boolean willAutoSync(ItemStack itemstack, EntityLivingBase player) {
        return false;
    }
}
