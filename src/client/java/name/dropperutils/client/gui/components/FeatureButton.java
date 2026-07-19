package name.dropperutils.client.gui.components;

import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class FeatureButton {

    private final Button button;
    private final String name;
    private final BooleanSupplier enabled;


    public FeatureButton(
            int x,
            int y,
            String name,
            Runnable action,
            BooleanSupplier enabled
    ) {
        this.name = name;
        this.enabled = enabled;

        this.button = Button.builder(
                        getText(),
                        b -> {
                            action.run();
                            b.setMessage(getText());
                        }
                )
                .bounds(x, y, 150, 20)
                .build();
    }


    private Component getText() {
        return Component.literal(
                name + ": " +
                        (enabled.getAsBoolean() ? "ON" : "OFF")
        );
    }


    public Button getButton() {
        return button;
    }

    public void tick() {
        button.setMessage(getText());
    }
}