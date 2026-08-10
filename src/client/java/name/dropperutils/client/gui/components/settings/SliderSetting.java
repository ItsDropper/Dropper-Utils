package name.dropperutils.client.gui.components.settings;

import name.dropperutils.client.gui.ClickGuiScreen;
import name.dropperutils.client.gui.components.FeatureSlider;
import net.minecraft.client.gui.GuiGraphics;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class SliderSetting implements Setting {

    private final String name;
    private final double min;
    private final double max;

    private final Supplier<Double> getter;
    private final Consumer<Double> setter;

    private FeatureSlider slider;

    public SliderSetting(
            String name,
            double min,
            double max,
            Supplier<Double> getter,
            Consumer<Double> setter
    ) {
        this.name = name;
        this.min = min;
        this.max = max;
        this.getter = getter;
        this.setter = setter;
    }

    @Override
    public void addWidgets(
            ClickGuiScreen screen,
            int x,
            int y
    ) {

        slider = new FeatureSlider(
                x,
                y,
                name,
                min,
                max,
                getter,
                setter
        );

        screen.addSlider(slider.getSlider());
    }

    @Override
    public void updateWidgets(int x, int y) {

        if (slider != null) {
            slider.setPosition(x, y);
        }
    }

    @Override
    public void render(
            GuiGraphics graphics,
            ClickGuiScreen screen,
            int x,
            int y
    ) {
    }

    @Override
    public void clearWidgets(ClickGuiScreen screen) {

        if (slider != null) {
            screen.removeWidgetPublic(
                    slider.getSlider()
            );

            slider = null;
        }
    }

    @Override
    public int getHeight() {
        return 30;
    }
}