package name.dropperutils.client.gui.components.settings;

import name.dropperutils.client.feature.Feature;
import name.dropperutils.client.feature.FullbrightFeature;
import name.dropperutils.client.feature.LowFireFeature;
import name.dropperutils.client.feature.ZoomFeature;
import name.dropperutils.client.config.DropperUtilsConfig;

import java.util.ArrayList;
import java.util.List;

public class SettingRegistry {

    public static List<Setting> getSettings(Feature feature) {

        List<Setting> settings = new ArrayList<>();

        if (feature == ZoomFeature.INSTANCE) {

            settings.add(
                    new SliderSetting(
                            "Zoom FOV",
                            1,
                            50,
                            () -> (double) ZoomFeature.getZoomFov(),
                            value -> ZoomFeature.setZoomFov(
                                    value.intValue()
                            )
                    )
            );

            settings.add(
                    new SliderSetting(
                            "Zoom Speed",
                            0.01,
                            0.5,
                            () -> (double) ZoomFeature.getZoomSpeed(),
                            value -> ZoomFeature.setZoomSpeed(
                                    value.floatValue()
                            )
                    )
            );
        }

        if (feature == LowFireFeature.INSTANCE) {
            settings.add(
                    new SliderSetting(
                            "Fire Height",
                            0,
                            100,
                            () -> 100.0 - (
                                    DropperUtilsConfig.get().lowFireHeight * 100.0
                            ),
                            value -> {
                                DropperUtilsConfig.get().lowFireHeight =
                                        1.0f - (value.floatValue() / 100.0f);

                                DropperUtilsConfig.save();
                            }
                    )
            );
        }

        if (feature == FullbrightFeature.INSTANCE) {

            settings.add(
                    new SliderSetting(
                            "Brightness",
                            0,
                            100,
                            () -> (double) FullbrightFeature.getBrightness(),
                            value -> FullbrightFeature.setBrightness(
                                    value.floatValue()
                            )
                    )
            );
        }

        return settings;
    }
}

