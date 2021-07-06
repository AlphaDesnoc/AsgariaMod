package fr.deltadesnoc.asgaria.blocks.ore;

        import fr.deltadesnoc.asgaria.blocks.BlockBasic;
        import fr.deltadesnoc.asgaria.init.ItemsInitialisation;
        import net.minecraft.block.material.Material;
        import net.minecraft.block.state.IBlockState;
        import net.minecraft.item.Item;

        import java.util.Random;

public class DivineOreBlock extends BlockBasic {

    public DivineOreBlock(){
        super("divine_ore", Material.ROCK, 10.0f, 5.0f, "pickaxe", 2);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ItemsInitialisation.divine_fragment;
    }
}
