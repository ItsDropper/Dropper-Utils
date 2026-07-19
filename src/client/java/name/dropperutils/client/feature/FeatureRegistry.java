package name.dropperutils.client.feature;

import java.util.List;

public class FeatureRegistry {

    public static final List<Feature> FEATURES = List.of(
            FullbrightFeature.INSTANCE,
            ZoomFeature.INSTANCE,
            TotemCounterFeature.INSTANCE,
            ArmorHudFeature.INSTANCE,
            SaturationFeature.INSTANCE
    );

    public static void loadConfigs() {
        for (Feature feature : FEATURES) {
            feature.loadConfig();
        }
    }
}
