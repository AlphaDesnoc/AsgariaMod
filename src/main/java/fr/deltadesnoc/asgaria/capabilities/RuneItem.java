package fr.deltadesnoc.asgaria.capabilities;

import net.minecraft.item.ItemStack;

public class RuneItem implements IAsgaria {
    private AsgariaType type;

    public RuneItem(AsgariaType type) {
        this.type = type;
    }

    @Override
    public AsgariaType getAsgariaType(ItemStack paramItemStack) {
        return this.type;
    }
}