package name.dropperutils.client.feature;

import name.dropperutils.client.config.DropperUtilsConfig;
import net.minecraft.client.KeyMapping;

public class ZoomFeature extends Feature {

    public static final ZoomFeature INSTANCE = new ZoomFeature();

    private static boolean zooming = false;
    private static int scrollSteps = 0;

    private static int zoomFov;
    private static float zoomSpeed;

    private ZoomFeature() {
        super("Zoom", "Hold the zoom key to zoom the camera. Scroll while held for extra zoom.", Category.VISUAL);
    }

    public void tick(KeyMapping zoomKey) {
        boolean wasZooming = zooming;
        zooming = isEnabled() && zoomKey.isDown();

        if (wasZooming && !zooming) {
            scrollSteps = 0;
        }
    }

    public static boolean isZooming() {
        return zooming;
    }

    public static void scrollZoom(double amount) {
        if (!zooming || amount == 0.0) {
            return;
        }

        // Positive wheel movement zooms in; scrolling down backs out to the base zoom.
        scrollSteps = Math.max(0, Math.min(20, scrollSteps + (amount > 0.0 ? 1 : -1)));
    }

    public static float getZoomMultiplier() {
        return (zoomFov / 70.0f) * (float) Math.pow(0.8, scrollSteps);
    }

    public static int getZoomFov() {
        return zoomFov;
    }

    public static void setZoomFov(int value) {
        zoomFov = Math.max(1, Math.min(value, 70));
        DropperUtilsConfig.get().zoomFov = zoomFov;
        DropperUtilsConfig.save();
    }

    public static float getZoomSpeed() {
        return zoomSpeed;
    }

    public static void setZoomSpeed(float value) {
        zoomSpeed = Math.max(0.01f, Math.min(value, 1.0f));
        DropperUtilsConfig.get().zoomSpeed = zoomSpeed;
        DropperUtilsConfig.save();
    }

    @Override
    public void loadConfig() {
        DropperUtilsConfig config = DropperUtilsConfig.get();
        zoomFov = config.zoomFov;
        zoomSpeed = config.zoomSpeed;
    }
}