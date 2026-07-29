package name.dropperutils.client.feature;

import name.dropperutils.client.config.DropperUtilsConfig;

public class HudEditorFeature extends Feature {

    public static final HudEditorFeature INSTANCE =
            new HudEditorFeature();


    private HudEditorFeature() {
        super("HUD Editor", Category.MISC);
    }


    @Override
    public void onEnable() {
        DropperUtilsConfig.get().hudEditor = true;
        DropperUtilsConfig.save();
    }


    @Override
    public void onDisable() {
        DropperUtilsConfig.get().hudEditor = false;
        DropperUtilsConfig.save();
    }


    @Override
    public void loadConfig() {
        setEnabled(DropperUtilsConfig.get().hudEditor);
    }
}