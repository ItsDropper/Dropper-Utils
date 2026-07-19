package name.dropperutils.client.feature;

import name.dropperutils.client.config.DropperUtilsConfig;

public class DebugHudFeature extends Feature {

    public static final DebugHudFeature INSTANCE = new DebugHudFeature();

    private DebugHudFeature() {
        super("Debug HUD", Category.MISC);
    }


    @Override
    public void onEnable() {
        DropperUtilsConfig.get().debugHud = true;
        DropperUtilsConfig.save();
    }


    @Override
    public void onDisable() {
        DropperUtilsConfig.get().debugHud = false;
        DropperUtilsConfig.save();
    }


    @Override
    public void loadConfig() {
        setEnabled(DropperUtilsConfig.get().debugHud);
    }
}