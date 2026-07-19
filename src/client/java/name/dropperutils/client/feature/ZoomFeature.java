package name.dropperutils.client.feature;

import name.dropperutils.client.config.DropperUtilsConfig;
import net.minecraft.client.KeyMapping;

public class ZoomFeature extends Feature {

    public static final ZoomFeature INSTANCE = new ZoomFeature();

    private static boolean zooming = false;

    private static int zoomFov;
    private static float zoomSpeed;

    private ZoomFeature() {
        super("Zoom", Category.VISUAL);
    }

    public void tick(KeyMapping zoomKey) {

        if (!isEnabled()) {
            zooming = false;
            return;
        }

        zooming = zoomKey.isDown();
    }

    public static boolean isZooming() {
        return zooming;
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