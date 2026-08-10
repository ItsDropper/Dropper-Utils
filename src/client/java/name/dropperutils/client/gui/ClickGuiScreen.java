package name.dropperutils.client.gui;

import name.dropperutils.client.feature.Category;
import name.dropperutils.client.feature.Feature;
import name.dropperutils.client.gui.components.FeaturePanel;
import name.dropperutils.client.gui.components.GuiInitializer;
import name.dropperutils.client.gui.components.settings.SettingsPanel;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ClickGuiScreen extends Screen {

    private FeaturePanel featurePanel;
    private Category selectedCategory = Category.HUD;

    private SettingsPanel settingsPanel;
    private Feature selectedFeature;

    public ClickGuiScreen() {
        super(Component.literal("DropperUtils"));
    }

    @Override
    protected void init() {
        settingsPanel = new SettingsPanel(this);

        rebuildWidgets();
    }

    public void addButton(Button button) {
        addRenderableWidget(button);
    }

    public void addSlider(AbstractWidget widget) {
        addRenderableWidget(widget);
    }

    @Override
    protected void rebuildWidgets() {
        clearWidgets();

        GuiInitializer.setup(this);

        if (selectedFeature != null) {
        }
    }

    public void openSettings(
            Feature feature,
            int buttonX,
            int buttonY
    ) {
        selectedFeature = feature;
        settingsPanel.open(feature, buttonX, buttonY);
    }

    public void closeSettings() {
        selectedFeature = null;

        settingsPanel.close();

        rebuildWidgets();
    }

    public boolean isSettingsOpen() {
        return selectedFeature != null;
    }

    @Override
    public void tick() {
        super.tick();

        if (settingsPanel != null) {
            settingsPanel.tick();
        }
    }

    @Override
    public void extractRenderState(
            GuiGraphicsExtractor graphics,
            int mouseX,
            int mouseY,
            float delta
    ) {
        graphics.fill(
                0,
                0,
                width,
                height,
                0x99000000
        );

        graphics.centeredText(
                font,
                "DropperUtils",
                width / 2,
                40,
                0xFFFFFF
        );

        if (settingsPanel != null) {
            settingsPanel.render(graphics);
        }

        super.extractRenderState(graphics, mouseX, mouseY, delta);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public int getScreenWidth() {
        return width;
    }

    public void setFeaturePanel(FeaturePanel panel) {
        this.featurePanel = panel;
    }

    public void rebuild() {
        rebuildWidgets();
    }

    public Category getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Category category) {
        this.selectedCategory = category;
    }

    public SettingsPanel getSettingsPanel() {
        return settingsPanel;
    }

    public void refreshWidgets() {
        rebuildWidgets();
    }

    public void removeWidgetPublic(GuiEventListener widget) {
        removeWidget(widget);
    }
}