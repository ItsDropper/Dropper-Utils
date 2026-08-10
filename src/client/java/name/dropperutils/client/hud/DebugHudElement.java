package name.dropperutils.client.hud;

import name.dropperutils.client.config.DropperUtilsConfig;
import name.dropperutils.client.feature.DebugHudFeature;
import name.dropperutils.client.feature.HudEditorFeature;
import name.dropperutils.client.hudeditor.HudElement;
import name.dropperutils.client.util.AnchorStats;
import name.dropperutils.client.util.AnchorTracker;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class DebugHudElement extends HudElement {

    public DebugHudElement() {
        super(
                DropperUtilsConfig.get().debugHudX,
                DropperUtilsConfig.get().debugHudY
        );

        scale = DropperUtilsConfig.get().debugHudScale;
    }

    @Override
    protected void renderElement(GuiGraphicsExtractor graphics) {

        if (!DebugHudFeature.INSTANCE.isEnabled()
                && !HudEditorFeature.INSTANCE.isEnabled()) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();

        graphics.text(
                mc.font,
                "Tracked: " + AnchorTracker.getTrackedAnchors().size(),
                0,
                0,
                0xFFFFFFFF,
                false
        );

        graphics.text(
                mc.font,
                String.format(
                        "Avg Scan: %.3f ms",
                        AnchorStats.getAverageScanTimeMs()
                ),
                0,
                11,
                0xFF00FF00,
                false
        );

        graphics.text(
                mc.font,
                String.format(
                        "Last Scan: %.3f ms",
                        AnchorStats.getLastScanTimeMs()
                ),
                0,
                22,
                0xFFFFFF00,
                false
        );

        if (HudEditorFeature.INSTANCE.isEnabled()) {
            graphics.fill(
                    -2,
                    -2,
                    getWidth(),
                    getHeight(),
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

    @Override
    public void setScale(float value) {
        scale = Math.max(
                0.25f,
                Math.min(value, 3.0f)
        );

        DropperUtilsConfig.get().debugHudScale = scale;
        DropperUtilsConfig.save();
    }
}

