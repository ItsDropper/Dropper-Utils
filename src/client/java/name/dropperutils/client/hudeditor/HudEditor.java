package name.dropperutils.client.hudeditor;

import name.dropperutils.client.feature.HudEditorFeature;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class HudEditor {

    public static void render(GuiGraphicsExtractor graphics) {

        if (!HudEditorFeature.INSTANCE.isEnabled()) {
            return;
        }

        HudManager.render(graphics);
    }
}