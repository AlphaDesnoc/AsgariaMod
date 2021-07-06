package fr.deltadesnoc.asgaria.event;

import fr.deltadesnoc.asgaria.packets.PacketHandler;
import fr.deltadesnoc.asgaria.packets.PacketOpenPlayerRuneInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class KeyHandler {

    public KeyBinding key = new KeyBinding("keybind.baublesinventory", 48, "key.categories.inventory");

    public KeyHandler() {
        ClientRegistry.registerKeyBinding(this.key);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == Side.SERVER)
            return;
        if (event.phase == TickEvent.Phase.START &&
                this.key.isPressed() && (FMLClientHandler.instance().getClient()).inGameHasFocus)
            PacketHandler.INSTANCE.sendToServer(new PacketOpenPlayerRuneInventory());
    }

}
