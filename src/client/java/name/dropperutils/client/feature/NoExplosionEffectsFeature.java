package name.dropperutils.client.feature;

import name.dropperutils.client.config.DropperUtilsConfig;

public class NoExplosionEffectsFeature extends Feature {

    public static final NoExplosionEffectsFeature INSTANCE =
            new NoExplosionEffectsFeature();

    private NoExplosionEffectsFeature() {
        super("No Explosion Effects", Category.VISUAL);
    }


    @Override
    public void onEnable() {
        DropperUtilsConfig.get().noExplosionEffects = true;
        DropperUtilsConfig.save();
    }


    @Override
    public void onDisable() {
        DropperUtilsConfig.get().noExplosionEffects = false;
        DropperUtilsConfig.save();
    }


    @Override
    public void loadConfig() {
        setEnabled(
                DropperUtilsConfig.get().noExplosionEffects
        );
    }
}