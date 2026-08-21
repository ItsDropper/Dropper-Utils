package name.dropperutils.client.gui.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;

public class SettingsButton extends AbstractWidget {

    private final Runnable action;

    public SettingsButton(
            int x,
            int y,
            Runnable action
    ) {
        super(
                x,
                y,
                20,
                28,
                Component.literal("Settings")
        );

        this.action = action;
    }

    @Override
    protected void extractWidgetRenderState(
            GuiGraphicsExtractor graphics,
            int mouseX,
            int mouseY,
            float partialTick
    ) {
        boolean hovered = isHovered();

        int background = hovered
                ? 0xFF303038
                : 0xFF202024;

        graphics.fill(
                getX(),
                getY(),
                getX() + width,
                getY() + height,
                background
        );

        int arrowColor = hovered
                ? 0xFFFFFFFF
                : 0xFFAAAAAA;

        graphics.text(
                Minecraft.getInstance().font,
                ">",
                getX() + 7,
                getY() + 9,
                arrowColor
        );
    }

    @Override
    public boolean mouseClicked(
            MouseButtonEvent event,
            boolean bl
    ) {
        if (!isActive()) {
            return false;
        }

        if (isMouseOver(event.x(), event.y())) {

            playDownSound(
                    Minecraft.getInstance()
                            .getSoundManager()
            );

            action.run();

            return true;
        }

        return false;
    }

    @Override
    protected void updateWidgetNarration(
            NarrationElementOutput narration
    ) {
        narration.add(
                NarratedElementType.TITLE,
                Component.literal(
                        "Open feature settings"
                )
        );
    }
}