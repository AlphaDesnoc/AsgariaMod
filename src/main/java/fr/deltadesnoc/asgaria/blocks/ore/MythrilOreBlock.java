package fr.deltadesnoc.asgaria.blocks.ore;

import fr.deltadesnoc.asgaria.blocks.BlockBasic;
import net.minecraft.block.material.Material;

public class MythrilOreBlock extends BlockBasic {

    public MythrilOreBlock(){
        super("mythril_ore", Material.ROCK, 10.0f, 5.0f, "pickaxe", 2);
    }

}
