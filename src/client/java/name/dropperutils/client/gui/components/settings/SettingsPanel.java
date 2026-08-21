package name.dropperutils.client.gui.components.settings;

import name.dropperutils.client.feature.Feature;
import name.dropperutils.client.gui.ClickGuiScreen;
import name.dropperutils.client.gui.components.CloseButton;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class SettingsPanel {

    private final ClickGuiScreen screen;

    private Feature feature;

    private final int width = 190;
    private final int height = 150;

    private float animation = 0.0f;
    private boolean closing = false;

    private static final float ANIMATION_SPEED = 0.18f;

    private int startX;
    private int startY;

    private CloseButton closeButton;

    private final List<AbstractWidget> buttons = new ArrayList<>();
    private List<Setting> settings = new ArrayList<>();

    public SettingsPanel(ClickGuiScreen screen) {
        this.screen = screen;
    }

    public void open(Feature feature, int buttonX, int buttonY) {

        clearWidgets();

        this.feature = feature;
        this.startX = buttonX;
        this.startY = buttonY;

        this.animation = 0.0f;
        this.closing = false;

        settings = SettingRegistry.getSettings(feature);


        createWidgets();
    }

    public void close() {
        closing = true;
    }

    public boolean isOpen() {
        return feature != null;
    }

    private void createWidgets() {

        if (feature == null) {
            return;
        }

        int x = getX();
        int y = 70;

        closeButton = new CloseButton(
                x + width - 28,
                y + 8,
                this::close
        );

        screen.addButton(closeButton);
        buttons.add(closeButton);

        int settingY = y + 55;


        for (Setting setting : settings) {


            setting.addWidgets(
                    screen,
                    x + 20,
                    settingY
            );

            settingY += setting.getHeight();
        }
    }

    public void clearWidgets() {

        for (AbstractWidget button : buttons) {
            screen.removeWidgetPublic(button);
        }

        buttons.clear();

        for (Setting setting : settings) {
            setting.clearWidgets(screen);
        }

        closeButton = null;
    }

    public void tick() {

        if (feature == null) {
            return;
        }

        if (closing) {

            animation -= ANIMATION_SPEED;

            if (animation <= 0.0f) {
                animation = 0.0f;

                clearWidgets();

                feature = null;
                closing = false;

                return;
            }

        } else {

            animation += (1.0f - animation) * 0.22f;

            if (animation > 0.999f) {
                animation = 1.0f;
            }
        }

        updateWidgets();
    }

    private int getX() {

        float eased = easeOutCubic(animation);

        int targetX = startX + 25;

        return (int) (
                startX +
                        (targetX - startX) * eased
        );
    }

    private void updateWidgets() {

        int x = getX();
        int y = 70;

        if (closeButton != null) {
            closeButton.setX(x + width - 28);
            closeButton.setY(y + 8);
        }

        int settingY = y + 55;

        for (Setting setting : settings) {
            setting.updateWidgets(
                    x + 20,
                    settingY
            );

            settingY += setting.getHeight();
        }
    }

    public void render(
            GuiGraphics graphics,
            int mouseX,
            int mouseY
    ) {

        if (feature == null) {
            return;
        }


        int x = getX();
        int y = 70;

        // Connection from the feature's settings button

        // Soft shadow
        graphics.fill(
                x - 4,
                y - 4,
                x + width + 4,
                y + height + 4,
                0x44000000
        );

        // Outer border
        graphics.fill(
                x - 1,
                y - 1,
                x + width + 1,
                y + height + 1,
                0xFF44444A
        );

        // Main panel
        graphics.fill(
                x,
                y,
                x + width,
                y + height,
                0xFF18181C
        );

        // Accent line
        graphics.fill(
                x,
                y,
                x + width,
                y + 2,
                0xFF6677FF
        );

        // Title
        graphics.drawString(
                screen.getFont(),
                feature.getName() + " Settings",
                x + 15,
                y + 15,
                0xFFFFFFFF
        );

        // Close button
        int closeX = x + width - 30;
        int closeY = y + 8;

        boolean hovered =
                mouseX >= closeX &&
                        mouseX <= closeX + 20 &&
                        mouseY >= closeY &&
                        mouseY <= closeY + 20;

        graphics.fill(
                closeX,
                closeY,
                closeX + 20,
                closeY + 20,
                hovered
                        ? 0xFF303038
                        : 0xFF202024
        );

        graphics.drawCenteredString(
                screen.getFont(),
                "×",
                closeX + 10,
                closeY + 5,
                0xFFFFFFFF
        );


        // Divider
        graphics.fill(
                x + 15,
                y + 35,
                x + width - 15,
                y + 36,
                0xFF38383F
        );
    }

    public void closeImmediately() {
        clearWidgets();

        feature = null;
        closing = false;
        animation = 0.0f;
    }

    private float easeOutCubic(float value) {
        float inverse = 1.0f - value;
        return 1.0f - inverse * inverse * inverse;
    }
}