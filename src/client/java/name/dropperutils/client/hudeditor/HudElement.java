package name.dropperutils.client.hudeditor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

public abstract class HudElement {

    protected float xPercent;
    protected float yPercent;

    protected final int defaultX;
    protected final int defaultY;


    public HudElement(float x, float y) {

        this.xPercent = x;
        this.yPercent = y;

        this.defaultX = 0;
        this.defaultY = 0;
    }


    public void move(int mouseX, int mouseY) {

        Minecraft mc = Minecraft.getInstance();

        xPercent =
                (float) mouseX /
                        mc.getWindow().getGuiScaledWidth();

        yPercent =
                (float) mouseY /
                        mc.getWindow().getGuiScaledHeight();
    }


    public boolean isHovered(double mouseX, double mouseY) {

        return mouseX >= getX() &&
                mouseX <= getX() + getWidth() &&
                mouseY >= getY() &&
                mouseY <= getY() + getHeight();
    }


    public int getX() {

        Minecraft mc = Minecraft.getInstance();

        return (int)(
                xPercent *
                        mc.getWindow().getGuiScaledWidth()
        );
    }


    public int getY() {

        Minecraft mc = Minecraft.getInstance();

        return (int)(
                yPercent *
                        mc.getWindow().getGuiScaledHeight()
        );
    }


    public abstract void render(GuiGraphics graphics);

    public abstract int getWidth();

    public abstract int getHeight();

    public void savePosition() {
    }
}