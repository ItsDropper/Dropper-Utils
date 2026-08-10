package name.dropperutils.client.gui.components.settings;

import name.dropperutils.client.gui.ClickGuiScreen;
import net.minecraft.client.gui.GuiGraphics;

public interface Setting {

    void addWidgets(ClickGuiScreen screen, int x, int y);

    void updateWidgets(int x, int y);

    void render(
            GuiGraphics graphics,
            ClickGuiScreen screen,
            int x,
            int y
    );

    void clearWidgets(ClickGuiScreen screen);

    int getHeight();
}