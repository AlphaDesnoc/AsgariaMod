package fr.deltadesnoc.asgaria.utils;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;

import java.util.Map;

public class Methodes {

    private void removeEnchant(ItemStack stack, Enchantment ench) {
        Map<Enchantment, Integer> ech = EnchantmentHelper.getEnchantments(stack);
        if(ech.containsKey(ench)) {
            ech.remove(ench);
        }
        EnchantmentHelper.setEnchantments(ech, stack);
    }


}
