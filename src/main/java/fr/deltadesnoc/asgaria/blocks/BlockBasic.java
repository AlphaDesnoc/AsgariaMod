package fr.deltadesnoc.asgaria.blocks;

import fr.deltadesnoc.asgaria.Asgaria;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBasic extends Block{

    public BlockBasic(String name, Material materialIn) {
        super(materialIn);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(Asgaria.asgariaBlocks);
    }

    public BlockBasic(String name, Material materialIn, int resistance) {
        super(materialIn);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(Asgaria.asgariaBlocks);
        setResistance(resistance);
    }

    public BlockBasic(String name, Material materialIn, float resistance, float hardness) {
        super(materialIn);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(Asgaria.asgariaBlocks);
        setResistance(resistance);
        setHardness(hardness);
    }

    public BlockBasic(String name, Material materialIn, float resistance, float hardness, String classTool, int harvestLevel) {
        super(materialIn);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(Asgaria.asgariaBlocks);
        setResistance(resistance);
        setHardness(hardness);
        setHarvestLevel(classTool, harvestLevel);
    }

    public BlockBasic(String name, Material materialIn, float resistance, float hardness, String classTool, int harvestLevel, int lightLevel) {
        super(materialIn);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(Asgaria.asgariaBlocks);
        setResistance(resistance);
        setHardness(hardness);
        setHarvestLevel(classTool, harvestLevel);
        setLightLevel(lightLevel);
    }

    public BlockBasic(String name, Material materialIn, float resistance, float hardness, String classTool, int harvestLevel, int lightLevel, int opacity) {
        super(materialIn);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(Asgaria.asgariaBlocks);
        setResistance(resistance);
        setHardness(hardness);
        setHarvestLevel(classTool, harvestLevel);
        setLightLevel(lightLevel);
        setLightOpacity(opacity);
    }

}
