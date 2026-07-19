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


    public FeatureSlider(
            int x,
            int y,
            String name,
            double min,
            double max,
            Supplier<Double> valueGetter,
            Consumer<Double> valueSetter
    ) {
        this.name = name;
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
                getText(),
                (current - min) / (max - min)
        ) {
            @Override
            protected void updateMessage() {
                setMessage(getText());
            }

            @Override
            protected void applyValue() {
                double newValue = min + this.value * (max - min);
                valueSetter.accept(newValue);
            }
        };
    }


    private Component getText() {
        return Component.literal(
                name + ": " +
                        String.format("%.1f", valueGetter.get())
        );
    }


    public AbstractSliderButton getSlider() {
        return slider;
    }
}