package fr.deltadesnoc.asgaria.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketOpenNormalInventory implements IMessage, IMessageHandler<PacketOpenNormalInventory, IMessage> {
    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    @Override
    public IMessage onMessage(PacketOpenNormalInventory message, MessageContext ctx) {

        WorldServer server = (WorldServer) ctx.getServerHandler().player.world;
        server.addScheduledTask(() -> {
            (ctx.getServerHandler()).player.openContainer.onContainerClosed((ctx.getServerHandler()).player);
            (ctx.getServerHandler()).player.openContainer = (ctx.getServerHandler()).player.inventoryContainer;
        });

        return null;
    }
}
