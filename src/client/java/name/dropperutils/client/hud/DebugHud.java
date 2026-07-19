package name.dropperutils.client.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import name.dropperutils.client.feature.DebugHudFeature;
import name.dropperutils.client.util.AnchorOptimizer;

public class DebugHud {

    public static void render(GuiGraphics graphics) {

        if (!DebugHudFeature.INSTANCE.isEnabled()) {
            return;
        }



        Minecraft mc = Minecraft.getInstance();

        graphics.drawString(
                mc.font,
                "Tracked: " + AnchorOptimizer.getTrackedAnchors().size(),
                5,
                5,
                0xFFFFFFFF
        );

        graphics.drawString(
                mc.font,
                String.format("Avg Scan: %.3f ms", AnchorOptimizer.getAverageScanTimeMs()),
                5,
                16,
                0xFF00FF00
        );

        graphics.drawString(
                mc.font,
                String.format(
                        "Last Scan: %.3f ms",
                        AnchorOptimizer.getLastScanTimeMs()
                ),
                5,
                27,
                0xFFFFFF00
        );
    }
}