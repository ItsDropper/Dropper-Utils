package name.dropperutils.client.feature;

import name.dropperutils.client.config.DropperUtilsConfig;
import net.minecraft.client.KeyMapping;

public class ZoomFeature extends Feature {

    public static final ZoomFeature INSTANCE = new ZoomFeature();

    private static boolean zooming = false;

    private static float zoomProgress = 0.0f;
    private static float previousZoomProgress = 0.0f;

    private static int zoomFov;
    private static float zoomSpeed;

    private ZoomFeature() {
        super("Zoom", Category.VISUAL);
    }

    public void tick(KeyMapping zoomKey) {
        if (!isEnabled()) {
            zooming = false;
        } else {
            zooming = zoomKey.isDown();
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

    public static float getZoomProgress(float partialTick) {
        return previousZoomProgress
                + (zoomProgress - previousZoomProgress) * partialTick;
    }

    public static float getZoomMultiplier() {
        return zoomFov / 70.0f;
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