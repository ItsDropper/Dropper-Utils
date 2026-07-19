package name.dropperutils.client.feature;

import name.dropperutils.client.config.DropperUtilsConfig;

public class AnchorOptimizerFeature extends Feature {

    public static final AnchorOptimizerFeature INSTANCE =
            new AnchorOptimizerFeature();

    private AnchorOptimizerFeature() {
        super("Anchor Optimizer", Category.COMBAT);
    }

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public void saveConfig() {
        DropperUtilsConfig.get().anchorOptimizer = isEnabled();
    }

    @Override
    public void loadConfig() {
        setEnabled(DropperUtilsConfig.get().anchorOptimizer);
    }
}