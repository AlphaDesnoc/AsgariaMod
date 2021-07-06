package fr.deltadesnoc.asgaria.client.gui;

import fr.deltadesnoc.asgaria.container.ContainerPlayerExpanded;
import fr.deltadesnoc.asgaria.proxy.ClientProxy;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.io.IOException;

public class GuiPlayerExpanded extends InventoryEffectRenderer {
    public static final ResourceLocation background = new ResourceLocation("asgaria", "textures/gui/expanded_inventory.png");

    private float oldMouseX;

    private float oldMouseY;

    public GuiPlayerExpanded(EntityPlayer player) {
        super((Container)new ContainerPlayerExpanded(player.inventory, !(player.getEntityWorld()).isRemote, player));
        this.allowUserInput = true;
    }

    private void resetGuiLeft() {
        this.guiLeft = (this.width - this.xSize) / 2;
    }

    public void updateScreen() {
        ((ContainerPlayerExpanded)this.inventorySlots).rune.setEventBlock(false);
        updateActivePotionEffects();
        resetGuiLeft();
    }

    public void initGui() {
        this.buttonList.clear();
        super.initGui();
        resetGuiLeft();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        this.oldMouseX = mouseX;
        this.oldMouseY = mouseY;
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(background);
        int k = this.guiLeft;
        int l = this.guiTop;
        drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        for (int i1 = 0; i1 < this.inventorySlots.inventorySlots.size(); i1++) {
            Slot slot = this.inventorySlots.inventorySlots.get(i1);
            if (slot.getHasStack() && slot.getSlotStackLimit() == 1)
                drawTexturedModalRect(k + slot.xPos, l + slot.yPos, 200, 0, 16, 16);
        }
        GuiInventory.drawEntityOnScreen(k + 51, l + 75, 30, (k + 51) - this.oldMouseX, (l + 75 - 50) - this.oldMouseY, (EntityLivingBase)this.mc.player);
    }

    protected void actionPerformed(GuiButton button) {
        if (button.id == 0);
        if (button.id == 1)
            this.mc.displayGuiScreen((GuiScreen)new GuiStats((GuiScreen)this, this.mc.player.getStatFileWriter()));
    }

    protected void keyTyped(char par1, int par2) throws IOException {
        if (par2 == ClientProxy.KEY_RUNES.getKeyCode()) {
            this.mc.player.closeScreen();
        } else {
            super.keyTyped(par1, par2);
        }
    }

    public void displayNormalInventory() {
        GuiInventory gui = new GuiInventory((EntityPlayer)this.mc.player);
        ReflectionHelper.setPrivateValue(GuiInventory.class, gui, this.oldMouseX, new String[] { "oldMouseX", "field_147048_u" });
        ReflectionHelper.setPrivateValue(GuiInventory.class, gui, this.oldMouseY, new String[] { "oldMouseY", "field_147047_v" });
        this.mc.displayGuiScreen((GuiScreen)gui);
    }
}
