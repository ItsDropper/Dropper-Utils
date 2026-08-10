package name.dropperutils.client.hudeditor;

import name.dropperutils.client.feature.HudEditorFeature;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class HudEditorScreen extends Screen {

    public HudEditorScreen() {
        super(Component.literal("HUD Editor"));
    }

    @Override
    public void extractRenderState(
            GuiGraphicsExtractor graphics,
            int mouseX,
            int mouseY,
            float delta
    ) {
        Minecraft mc = Minecraft.getInstance();

        graphics.text(
                mc.font,
                "Mouse: " + mouseX + ", " + mouseY,
                5,
                5,
                0xFFFFFF
        );

        graphics.fill(
                0,
                0,
                this.width,
                this.height,
                0x66000000
        );

        HudMouseHandler.updateHover(mouseX, mouseY);

        HudManager.render(graphics);

        HudElement hovered = HudMouseHandler.getHovered();

        if (hovered != null) {

            graphics.text(
                    mc.font,
                    "Drag to move",
                    mouseX + 10,
                    mouseY + 10,
                    0xFFFFFFFF
            );

            graphics.text(
                    mc.font,
                    "Click to customize",
                    mouseX + 10,
                    mouseY + 22,
                    0xFFFFFFFF
            );
        }

        HudSettingsPanel.render(graphics, mouseX, mouseY);
    }

    @Override
    public boolean mouseClicked(
            net.minecraft.client.input.MouseButtonEvent event,
            boolean doubleClick
    ) {
        double mouseX = event.x();
        double mouseY = event.y();

        if (HudSettingsPanel.mouseClicked(mouseX, mouseY)) {
            return true;
        }

        HudElement hovered = HudMouseHandler.getHovered();

        if (hovered != null) {
            HudMouseHandler.startDragging(
                    hovered,
                    mouseX,
                    mouseY
            );

            return true;
        }

        return false;
    }

    @Override
    public boolean mouseReleased(
            net.minecraft.client.input.MouseButtonEvent event
    ) {
        HudElement hovered = HudMouseHandler.getHovered();

        boolean moved = HudMouseHandler.hasMoved();

        HudMouseHandler.release();
        HudSettingsPanel.mouseReleased();

        if (!moved && hovered != null) {
            HudSettingsPanel.setSelected(hovered);
        }

        return true;
    }

    @Override
    public boolean mouseDragged(
            net.minecraft.client.input.MouseButtonEvent event,
            double dragX,
            double dragY
    ) {
        double mouseX = event.x();
        double mouseY = event.y();

        if (HudSettingsPanel.mouseDragged(mouseX, mouseY)) {
            return true;
        }

        if (event.button() == 0) {
            HudMouseHandler.drag(mouseX, mouseY);
        }

        return true;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}