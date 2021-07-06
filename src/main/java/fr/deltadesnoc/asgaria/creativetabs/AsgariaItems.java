package fr.deltadesnoc.asgaria.creativetabs;

import fr.deltadesnoc.asgaria.init.ItemsInitialisation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class AsgariaItems extends CreativeTabs {
    public AsgariaItems(String label) {
        super(label);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ItemsInitialisation.divine_fragment);
    }
}
