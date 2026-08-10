package name.dropperutils.client.gui.components.settings;

import name.dropperutils.client.gui.ClickGuiScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public interface Setting {

    void addWidgets(
            ClickGuiScreen screen,
            int x,
            int y
    );

    void updateWidgets(
            int x,
            int y
    );

    void render(
            GuiGraphicsExtractor graphics,
            ClickGuiScreen screen,
            int x,
            int y
    );

    void clearWidgets(
            ClickGuiScreen screen
    );

    int getHeight();
}

