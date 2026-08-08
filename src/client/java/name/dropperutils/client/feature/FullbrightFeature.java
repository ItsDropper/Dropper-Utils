package name.dropperutils.client.feature;

import name.dropperutils.client.config.DropperUtilsConfig;

public class FullbrightFeature extends Feature {

    public static final FullbrightFeature INSTANCE = new FullbrightFeature();

    private FullbrightFeature() {
        super("Fullbright", Category.VISUAL);
    }

    @Override
    public void onEnable() {
        DropperUtilsConfig.get().fullbright = true;
        DropperUtilsConfig.save();
    }

    @Override
    public void onDisable() {
        DropperUtilsConfig.get().fullbright = false;
        DropperUtilsConfig.save();
    }
}

