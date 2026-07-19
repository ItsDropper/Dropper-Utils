package name.dropperutils.client.feature;

import name.dropperutils.client.config.DropperUtilsConfig;

public class LowFireFeature extends Feature {

    public static final LowFireFeature INSTANCE =
            new LowFireFeature();

    private LowFireFeature() {
        super("No Fire Overlay", Category.VISUAL);
    }

    @Override
    public void onEnable() {
        DropperUtilsConfig.get().lowFire = true;
        DropperUtilsConfig.save();
    }

    @Override
    public void onDisable() {
        DropperUtilsConfig.get().lowFire = false;
        DropperUtilsConfig.save();
    }

    @Override
    public void loadConfig() {
        setEnabled(DropperUtilsConfig.get().lowFire);
    }
}