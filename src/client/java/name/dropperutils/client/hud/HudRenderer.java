package name.dropperutils.client.hud;

import name.dropperutils.client.hudeditor.HudManager;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class HudRenderer {

    public static void render(GuiGraphicsExtractor graphics) {
        HudManager.render(graphics);
    }
}