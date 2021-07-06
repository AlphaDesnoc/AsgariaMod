package fr.deltadesnoc.asgaria.creativetabs;

import fr.deltadesnoc.asgaria.init.BlocksInitialisation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class AsgariaBlocks extends CreativeTabs {
    public AsgariaBlocks(String label) {
        super(label);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(BlocksInitialisation.divine_ore);
    }
}
