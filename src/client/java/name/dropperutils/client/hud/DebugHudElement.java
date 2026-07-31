package name.dropperutils.client.hud;

import name.dropperutils.client.feature.DebugHudFeature;
import name.dropperutils.client.feature.HudEditorFeature;
import name.dropperutils.client.hudeditor.HudElement;
import name.dropperutils.client.util.AnchorOptimizer;
import name.dropperutils.client.util.AnchorStats;
import name.dropperutils.client.config.DropperUtilsConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

public class DebugHudElement extends HudElement {

    public DebugHudElement() {
        super(
                DropperUtilsConfig.get().debugHudX,
                DropperUtilsConfig.get().debugHudY
        );
    }


    @Override
    public void render(GuiGraphics graphics) {

        if (!DebugHudFeature.INSTANCE.isEnabled()
                && !HudEditorFeature.INSTANCE.isEnabled()) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();

        graphics.drawString(
                mc.font,
                "Tracked: " + AnchorOptimizer.getTrackedAnchors().size(),
                getX(),
                getY(),
                0xFFFFFFFF
        );

        graphics.drawString(
                mc.font,
                String.format(
                        "Avg Scan: %.3f ms",
                        AnchorStats.getAverageScanTimeMs()
                ),
                getX(),
                getY() + 11,
                0xFF00FF00
        );

        graphics.drawString(
                mc.font,
                String.format(
                        "Last Scan: %.3f ms",
                        AnchorStats.getLastScanTimeMs()
                ),
                getX(),
                getY() + 22,
                0xFFFFFF00
        );


        if (HudEditorFeature.INSTANCE.isEnabled()) {
            graphics.fill(
                    getX() - 2,
                    getY() - 2,
                    getX() + getWidth(),
                    getY() + getHeight(),
                    0x55FFFFFF
            );
        }
    }


    @Override
    public int getWidth() {
        return 120;
    }


    @Override
    public int getHeight() {
        return 35;
    }

    @Override
    public void savePosition() {

        DropperUtilsConfig.get().debugHudX = xPercent;
        DropperUtilsConfig.get().debugHudY = yPercent;

        DropperUtilsConfig.save();
    }
}