package name.dropperutils.client.feature;

import name.dropperutils.client.config.DropperUtilsConfig;

public class SaturationFeature extends Feature {

    public static final SaturationFeature INSTANCE = new SaturationFeature();

    private SaturationFeature() {
        super("Saturation", Category.HUD);
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }
    @Override
    public void saveConfig() {
        DropperUtilsConfig.get().saturation = isEnabled();
    }

    @Override
    public void loadConfig() {
        setEnabled(DropperUtilsConfig.get().saturation);
    }
}