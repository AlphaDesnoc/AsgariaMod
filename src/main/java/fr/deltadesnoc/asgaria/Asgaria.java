package fr.deltadesnoc.asgaria;

import com.google.common.base.Throwables;
import fr.deltadesnoc.asgaria.capabilities.*;
import fr.deltadesnoc.asgaria.creativetabs.AsgariaArmor;
import fr.deltadesnoc.asgaria.creativetabs.AsgariaBlocks;
import fr.deltadesnoc.asgaria.creativetabs.AsgariaItems;
import fr.deltadesnoc.asgaria.init.BlocksInitialisation;
import fr.deltadesnoc.asgaria.init.ItemsInitialisation;
import fr.deltadesnoc.asgaria.packets.PacketHandler;
import fr.deltadesnoc.asgaria.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.io.File;

@Mod(modid = Constants.MODID, name = Constants.NAME, version = Constants.VERSION, acceptedMinecraftVersions = Constants.MC_VERSION)
public class Asgaria {

    @Mod.Instance(Constants.MODID)
    public static Asgaria instance;

    @SidedProxy(modId = Constants.MODID, clientSide = Constants.CLIENT, serverSide = Constants.SERVER)
    public static CommonProxy proxy;

    public static final CreativeTabs asgariaArmor = new AsgariaArmor("asgariaArmor");
    //public static final CreativeTabs asgariaTools = new AsgariaTools("asgariaTools");
    public static final CreativeTabs asgariaBlocks = new AsgariaBlocks("asgariaBlocks");
    public static final CreativeTabs asgariaItems = new AsgariaItems("asgariaItems");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e){
        if(e.getSide().isClient())
        {
            if(!Minecraft.getMinecraft().mcDataDir.getAbsolutePath().contains("Aromencia") && !Minecraft.getMinecraft().mcDataDir.equals(new File(".")))
            {
                Throwables.propagate(new Exception("Launcher non autorisé"));

            }
        }
        proxy.preInit();
        CapabilityManager.INSTANCE.register(IAsgariaItemHandler.class, new AsgariaCapabilities.CapabilitiesRunes<>(), RuneContainer.class);
        CapabilityManager.INSTANCE
                .register(IAsgaria.class, new AsgariaCapabilities.CapabilityItemBaubleStorage(), () -> new RuneItem(AsgariaType.RUNE));
        proxy.registerEventHandlers();
        PacketHandler.init();
        BlocksInitialisation.init();
        ItemsInitialisation.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e){
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e){
        proxy.postInit();
    }

}
