package name.dropperutils.client.hudeditor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

public abstract class HudElement {

    protected int x;
    protected int y;

    protected final int defaultX;
    protected final int defaultY;


    public HudElement(int x, int y) {
        this.x = x;
        this.y = y;

        this.defaultX = x;
        this.defaultY = y;
    }


    public void move(int mouseX, int mouseY) {

        this.x = mouseX;
        this.y = mouseY;

    }


    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX >= x &&
                mouseX <= x + getWidth() &&
                mouseY >= y &&
                mouseY <= y + getHeight();
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public abstract void render(GuiGraphics graphics);

    public abstract int getWidth();

    public abstract int getHeight();

    public void savePosition() {
    }
}