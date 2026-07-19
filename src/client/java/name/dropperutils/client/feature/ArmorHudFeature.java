package name.dropperutils.client.feature;

import name.dropperutils.client.config.DropperUtilsConfig;

public class ArmorHudFeature extends Feature {

    public static final ArmorHudFeature INSTANCE = new ArmorHudFeature();

    private ArmorHudFeature() {
        super("Armor HUD", Category.HUD);
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void saveConfig() {
        DropperUtilsConfig.get().armorHud = isEnabled();
    }

    @Override
    public void loadConfig() {
        setEnabled(DropperUtilsConfig.get().armorHud);
    }
}