package name.dropperutils.client.gui.components;

import name.dropperutils.client.feature.Category;
import name.dropperutils.client.feature.ZoomFeature;
import name.dropperutils.client.gui.ClickGuiScreen;

public class SliderPanel {

    public static void addSliders(
            ClickGuiScreen screen,
            int x
    ) {

        if (screen.getSelectedCategory() != Category.VISUAL) {
            return;
        }

        screen.addSlider(
                new FeatureSlider(
                        x,
                        225,
                        "Zoom FOV",
                        1,
                        50,
                        () -> (double) ZoomFeature.getZoomFov(),
                        value -> ZoomFeature.setZoomFov(value.intValue())
                ).getSlider()
        );

        screen.addSlider(
                new FeatureSlider(
                        x,
                        250,
                        "Zoom Speed",
                        0.01,
                        0.5,
                        () -> (double) ZoomFeature.getZoomSpeed(),
                        value -> ZoomFeature.setZoomSpeed(value.floatValue())
                ).getSlider()
        );
    }
}