package name.dropperutils.client.gui.components.settings;

import name.dropperutils.client.feature.Feature;
import name.dropperutils.client.gui.ClickGuiScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;
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

    private int startX;
    private int startY;

    private Button closeButton;

    private final List<Button> buttons = new ArrayList<>();
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

        closeButton = Button.builder(
                Component.literal("X"),
                button -> close()
        ).bounds(
                x + width - 30,
                y + 10,
                20,
                20
        ).build();

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

        for (Button button : buttons) {
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

            animation -= 0.15f;

            if (animation <= 0.0f) {

                animation = 0.0f;

                clearWidgets();

                feature = null;
                closing = false;

                return;
            }

        } else {

            animation += (1.0f - animation) * 0.25f;

            if (animation > 0.999f) {
                animation = 1.0f;
            }
        }

        updateWidgets();
    }

    private int getX() {

        return (int) (
                startX +
                        (screen.width - startX - width) * animation
        );
    }

    private void updateWidgets() {

        int x = getX();
        int y = 70;

        if (closeButton != null) {
            closeButton.setX(x + width - 30);
            closeButton.setY(y + 10);
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

    public void render(GuiGraphicsExtractor graphics) {

        if (feature == null) {
            return;
        }

        int x = getX();
        int y = 70;

        graphics.fill(
                x - 4,
                y - 4,
                x + width + 4,
                y + height + 4,
                0x44000000
        );

        graphics.fill(
                x - 1,
                y - 1,
                x + width + 1,
                y + height + 1,
                0xFF444444
        );

        graphics.fill(
                x,
                y,
                x + width,
                y + height,
                0xEE18181C
        );

        graphics.fill(
                x,
                y,
                x + width,
                y + 2,
                0xFF7777FF
        );

        graphics.text(
                screen.getFont(),
                feature.getName() + " Settings",
                x + 15,
                y + 15,
                0xFFFFFFFF,
                false
        );

        graphics.fill(
                x + 15,
                y + 35,
                x + width - 15,
                y + 36,
                0xFF38383F
        );
    }
}

