package name.dropperutils.client.gui.components;

import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

import name.dropperutils.client.feature.Category;
import name.dropperutils.client.feature.Feature;
import name.dropperutils.client.feature.FeatureRegistry;
import name.dropperutils.client.gui.ClickGuiScreen;
import name.dropperutils.client.config.DropperUtilsConfig;

public class GuiInitializer {

    public static void setup(ClickGuiScreen screen) {

        int categoryX = 20;
        int categoryY = 60;

        screen.addButton(
                Button.builder(
                        Component.literal("HUD"),
                        b -> {
                            screen.setSelectedCategory(Category.HUD);
                            screen.rebuild();
                        }
                ).bounds(categoryX, categoryY, 80, 20).build()
        );

        screen.addButton(
                Button.builder(
                        Component.literal("Visual"),
                        b -> {
                            screen.setSelectedCategory(Category.VISUAL);
                            screen.rebuild();
                        }
                ).bounds(categoryX, categoryY + 25, 80, 20).build()
        );

        screen.addButton(
                Button.builder(
                        Component.literal("Combat"),
                        b -> {
                            screen.setSelectedCategory(Category.COMBAT);
                            screen.rebuild();
                        }
                ).bounds(categoryX, categoryY + 50, 80, 20).build()
        );

        screen.addButton(
                Button.builder(
                        Component.literal("Misc"),
                        b -> {
                            screen.setSelectedCategory(Category.MISC);
                            screen.rebuild();
                        }
                ).bounds(categoryX, categoryY + 75, 80, 20).build()
        );

        FeaturePanel panel = new FeaturePanel(
                screen.getScreenWidth() / 2 - 75,
                70
        );

        for (Feature feature : FeatureRegistry.FEATURES) {
            panel.addFeature(feature);
        }

        screen.setFeaturePanel(panel);

        int y = panel.getY();

        for (Feature feature : panel.getFeatures()) {

            if (feature.getCategory() != screen.getSelectedCategory()) {
                continue;
            }

            FeatureButton button = new FeatureButton(
                    panel.getX(),
                    y,
                    feature.getName(),
                    () -> {
                        feature.toggle();
                        feature.saveConfig();
                        DropperUtilsConfig.save();
                    },
                    feature::isEnabled
            );

            screen.addButton(button.getButton());

            y += 25;
        }

        SliderPanel.addSliders(
                screen,
                screen.getScreenWidth() / 2 - 75
        );
    }
}