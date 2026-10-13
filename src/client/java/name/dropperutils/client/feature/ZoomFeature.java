package name.dropperutils.client.feature;

import name.dropperutils.client.config.DropperUtilsConfig;
import net.minecraft.client.KeyMapping;

public class ZoomFeature extends Feature {

    public static final ZoomFeature INSTANCE = new ZoomFeature();

    private static boolean zooming = false;
    private static int scrollSteps = 0;

    private static float zoomProgress = 0.0f;
    private static float previousZoomProgress = 0.0f;

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

        previousZoomProgress = zoomProgress;
        float target = zooming ? 1.0f : 0.0f;
        zoomProgress += (target - zoomProgress) * zoomSpeed;

        if (Math.abs(target - zoomProgress) < 0.001f) {
            zoomProgress = target;
        }
    }

    public static boolean isZooming() {
        return zooming;
    }

    public static void scrollZoom(double amount) {
        if (!zooming || amount == 0.0) {
            return;
        }

        int direction = amount > 0.0 ? 1 : -1;
        scrollSteps = Math.max(0, Math.min(30, scrollSteps + direction));
    }

    public static float getZoomProgress(float partialTick) {
        return previousZoomProgress
                + (zoomProgress - previousZoomProgress) * partialTick;
    }

    public static float getZoomMultiplier(float baseFov) {
        if (baseFov <= 0.0f) {
            return 1.0f;
        }

        float targetFov = Math.max(1.0f, zoomFov) * (float) Math.pow(0.65, scrollSteps);
        return Math.max(0.005f, Math.min(1.0f, targetFov / baseFov));
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