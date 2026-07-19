package name.dropperutils.client.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import name.dropperutils.client.gui.components.GuiInitializer;
import name.dropperutils.client.gui.components.FeaturePanel;
import name.dropperutils.client.feature.Category;


public class ClickGuiScreen extends Screen {

    private FeaturePanel featurePanel;
    private Category selectedCategory = Category.HUD;


    public ClickGuiScreen() {
        super(Component.literal("DropperUtils"));
    }



    public void addButton(Button button) {
        addRenderableWidget(button);
    }


    public void addSlider(AbstractWidget widget) {
        addRenderableWidget(widget);
    }




    @Override
    protected void init() {
        GuiInitializer.setup(this);
    }


    @Override
    public void tick() {
        super.tick();
    }


    @Override
    public void render(
            GuiGraphics graphics,
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


        graphics.drawCenteredString(
                font,
                "DropperUtils",
                width / 2,
                40,
                0xFFFFFF
        );


        super.render(graphics, mouseX, mouseY, delta);
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
        clearWidgets();
        init();
    }

    public Category getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Category category) {
        this.selectedCategory = category;
    }
}