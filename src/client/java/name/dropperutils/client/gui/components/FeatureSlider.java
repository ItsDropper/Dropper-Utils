package name.dropperutils.client.gui.components;

import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class FeatureSlider {

    private final AbstractSliderButton slider;
    private final String name;
    private final double min;
    private final double max;
    private final Supplier<Double> valueGetter;
    private final Consumer<Double> valueSetter;
    private final String unit;

    public FeatureSlider(
            int x,
            int y,
            String name,
            double min,
            double max,
            String unit,
            Supplier<Double> valueGetter,
            Consumer<Double> valueSetter
    ) {
        this.name = name;
        this.unit = unit;
        this.min = min;
        this.max = max;
        this.valueGetter = valueGetter;
        this.valueSetter = valueSetter;

        double current = valueGetter.get();

        this.slider = new AbstractSliderButton(
                x,
                y,
                150,
                20,
                getText(current),
                (current - min) / (max - min)
        ) {

            @Override
            protected void updateMessage() {
                setMessage(getText(min + this.value * (max - min)));
            }

            @Override
            protected void applyValue() {
                double newValue =
                        min + this.value * (max - min);

                valueSetter.accept(newValue);
            }
        };
    }

    private Component getText(double value) {

        if (unit.equals("%")) {
            return Component.literal(
                    name + ": " + String.format("%.0f%%", value)
            );
        }

        if (!unit.isEmpty()) {
            return Component.literal(
                    name + ": " + String.format("%.0f", value) + " " + unit
            );
        }

        return Component.literal(
                name + ": " + String.format("%.2f", value)
        );
    }

    public AbstractSliderButton getSlider() {
        return slider;
    }

    public void setPosition(int x, int y) {
        slider.setX(x);
        slider.setY(y);
    }
}