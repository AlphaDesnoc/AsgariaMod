package fr.deltadesnoc.asgaria.packets;

import fr.deltadesnoc.asgaria.Asgaria;
import fr.deltadesnoc.asgaria.capabilities.AsgariaAPI;
import fr.deltadesnoc.asgaria.capabilities.IAsgariaItemHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSync implements IMessage {
    int playerId;

    byte slot = 0;

    ItemStack bauble;

    public PacketSync() {}

    public PacketSync(EntityPlayer p, int slot, ItemStack bauble) {
        this.slot = (byte)slot;
        this.bauble = bauble;
        this.playerId = p.getEntityId();
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.playerId);
        buffer.writeByte(this.slot);
        ByteBufUtils.writeItemStack(buffer, this.bauble);
    }

    public void fromBytes(ByteBuf buffer) {
        this.playerId = buffer.readInt();
        this.slot = buffer.readByte();
        this.bauble = ByteBufUtils.readItemStack(buffer);
    }

    public static class Handler implements IMessageHandler<PacketSync, IMessage> {
        public IMessage onMessage(final PacketSync message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(new Runnable() {
                public void run() {
                    World world = Asgaria.proxy.getClientWorld();
                    if (world == null)
                        return;
                    Entity p = world.getEntityByID(message.playerId);
                    if (p != null && p instanceof EntityPlayer) {
                        IAsgariaItemHandler handler = AsgariaAPI.getRuneHandler((EntityPlayer)p);
                        handler.setStackInSlot(message.slot, message.bauble);
                    }
                }
            });
            return null;
        }
    }
}