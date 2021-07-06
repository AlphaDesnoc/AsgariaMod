package fr.deltadesnoc.asgaria.armor;

import fr.deltadesnoc.asgaria.Asgaria;
import fr.deltadesnoc.asgaria.Constants;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.util.EnumHelper;

import javax.annotation.Nonnull;

public class FarmArmor extends ItemArmor implements ISpecialArmor {

    public static final ArmorMaterial farmArmor = EnumHelper.addArmorMaterial("farm", Constants.MODID + "farm_armor", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);

    public FarmArmor(String name, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(farmArmor, renderIndexIn, equipmentSlotIn);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(Asgaria.asgariaArmor);
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, @Nonnull ItemStack armor, DamageSource source, double damage, int slot) {
        switch(slot) {
            case 3:
                return new ArmorProperties(1, 0.05, 10);
            case 2:
                return new ArmorProperties(0, 0.2, 15);
            case 1:
                return new ArmorProperties(0, 0.3, 18);
            case 0:
                return new ArmorProperties(1, 0.04, 6);
            default: //Nothing, to disable eclipse warning
                return new ArmorProperties(0, 0, 0);
        }
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, @Nonnull ItemStack armor, int slot) {
        switch(slot) {
            case 3: //Helmet
                return 2;
            case 2: //Chestplate
                return 4;
            case 1: //Leggings
                return 3;
            case 0: //Boots
                return 2;
            default: //Nothing, to disable eclipse warning
                return 0;
        }
    }

    @Override
    public void damageArmor(EntityLivingBase entity, @Nonnull ItemStack stack, DamageSource source, int damage, int slot) {
        stack.damageItem(damage / 2, entity);
        if(stack.getItemDamage() >= stack.getMaxDamage()) {
            stack.setCount(stack.getCount() - 1);
        }
    }
}
