package name.dropperutils.client.feature;

import name.dropperutils.client.config.DropperUtilsConfig;

public class PotionEffectsFeature extends Feature {

    public static final PotionEffectsFeature INSTANCE =
            new PotionEffectsFeature();

    private PotionEffectsFeature() {
        super("Potion Effects HUD", Category.HUD);
    }

    @Override
    public void onEnable() {
        DropperUtilsConfig.get().potionEffects = true;
        DropperUtilsConfig.save();
    }

    @Override
    public void onDisable() {
        DropperUtilsConfig.get().potionEffects = false;
        DropperUtilsConfig.save();
    }

    @Override
    public void loadConfig() {
        setEnabled(DropperUtilsConfig.get().potionEffects);
    }
}