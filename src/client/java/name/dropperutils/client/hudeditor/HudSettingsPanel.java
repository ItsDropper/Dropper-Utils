package name.dropperutils.client.hudeditor;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;
import name.dropperutils.client.hud.ArmorHudElement;
import name.dropperutils.client.hud.TotemCounterHudElement;


public class HudSettingsPanel {

    private static HudElement selected;
    private static boolean open = false;
    private static boolean draggingSlider = false;

    private static final int PANEL_WIDTH = 180;
    private static final int PANEL_HEIGHT = 160;

    private static final int PANEL_BG = 0xDD101010;
    private static final int HEADER_BG = 0xFF181818;
    private static final int BUTTON_BG = 0xFF333333;
    private static final int BUTTON_HOVER = 0xFF555555;
    private static final int TEXT = 0xFFFFFFFF;
    private static final int SUBTEXT = 0xFFAAAAAA;

    private static final int PANEL_BORDER = 0xFF3A3A3A;
    private static final int ACCENT = 0xFF4A90E2;


    private static int getPanelX() {

        Minecraft mc = Minecraft.getInstance();

        if (selected == null) {
            return 10;
        }

        int x = selected.getX() + 100;

        return Math.max(
                5,
                Math.min(
                        x,
                        mc.getWindow().getGuiScaledWidth() - PANEL_WIDTH - 5
                )
        );
    }


    private static int getPanelY() {

        Minecraft mc = Minecraft.getInstance();

        if (selected == null) {
            return 10;
        }

        int y = selected.getY();

        return Math.max(
                5,
                Math.min(
                        y,
                        mc.getWindow().getGuiScaledHeight() - PANEL_HEIGHT - 5
                )
        );
    }


    public static void setSelected(HudElement element) {

        selected = element;
        open = true;
        draggingSlider = false;
    }

    public static void close() {

        open = false;
        selected = null;
    }


    public static void clear() {
        selected = null;
    }


    public static void render(
            GuiGraphics graphics,
            int mouseX,
            int mouseY
    ) {

        if (!open || selected == null) {
            return;
        }


        Minecraft mc = Minecraft.getInstance();


        int panelWidth = PANEL_WIDTH;
        int panelHeight = PANEL_HEIGHT;

        int x = getPanelX();
        int y = getPanelY();


        // main body
        graphics.fill(
                x + 4,
                y,
                x + panelWidth - 4,
                y + panelHeight,
                PANEL_BG
        );

        graphics.fill(
                x,
                y + 4,
                x + panelWidth,
                y + panelHeight - 4,
                PANEL_BG
        );

        // border
        graphics.fill(
                x,
                y,
                x + panelWidth,
                y + 1,
                PANEL_BORDER
        );

        graphics.fill(
                x,
                y + panelHeight - 1,
                x + panelWidth,
                y + panelHeight,
                PANEL_BORDER
        );

        graphics.fill(
                x,
                y,
                x + 1,
                y + panelHeight,
                PANEL_BORDER
        );

        graphics.fill(
                x + panelWidth - 1,
                y,
                x + panelWidth,
                y + panelHeight,
                PANEL_BORDER
        );

        graphics.fill(
                x + 4,
                y,
                x + panelWidth - 4,
                y + 35,
                HEADER_BG
        );

        graphics.fill(
                x,
                y + 4,
                x + panelWidth,
                y + 31,
                HEADER_BG
        );

        graphics.fill(
                x,
                y + 35,
                x + panelWidth,
                y + 37,
                ACCENT
        );


        graphics.drawString(
                mc.font,
                "⚙ " + getDisplayName(selected),
                x + 10,
                y + 12,
                TEXT
        );



        if (selected instanceof ArmorHudElement) {

            graphics.drawString(
                    mc.font,
                    "Layout",
                    x + 10,
                    y + 55,
                    SUBTEXT
            );


            drawButton(
                    graphics,
                    mc,
                    "Vertical",
                    x + 10,
                    y + 70,
                    75,
                    22,
                    mouseX,
                    mouseY
            );


            drawButton(
                    graphics,
                    mc,
                    "Horizontal",
                    x + 95,
                    y + 70,
                    75,
                    22,
                    mouseX,
                    mouseY
            );
        }


        if (selected instanceof TotemCounterHudElement totem) {

            graphics.drawString(
                    mc.font,
                    "Size: " + String.format("%.1fx", totem.getScale()),
                    x + 10,
                    y + 55,
                    0xFFFFFFFF
            );


            int sliderX = x + 10;
            int sliderY = y + 80;

            graphics.fill(
                    sliderX,
                    sliderY,
                    sliderX + 120,
                    sliderY + 5,
                    0xFF555555
            );


            int knobX = sliderX +
                    (int)((totem.getScale() - 0.5f) / 1.5f * 120);


            graphics.fill(
                    knobX - 3,
                    sliderY - 3,
                    knobX + 3,
                    sliderY + 8,
                    0xFFFFFFFF
            );
        }

        drawButton(
                graphics,
                mc,
                "✕ Close",
                x + 10,
                y + panelHeight - 35,
                80,
                24,
                mouseX,
                mouseY
        );


    }



    private static String getDisplayName(HudElement element) {

        return switch (element.getClass().getSimpleName()) {

            case "ArmorHudElement" -> "Armor HUD";
            case "DebugHudElement" -> "Debug HUD";
            case "TotemCounterHudElement" -> "Totem Counter";
            case "PotionEffectsHudElement" -> "Potion Effects";

            default -> element.getClass().getSimpleName();
        };
    }

    public static boolean mouseClicked(double mouseX, double mouseY) {

        if (!open) {
            return false;
        }


        int panelWidth = PANEL_WIDTH;
        int panelHeight = PANEL_HEIGHT;

        int x = getPanelX();
        int y = getPanelY();

        if (selected instanceof ArmorHudElement armor) {

            if (mouseX >= x + 10 &&
                    mouseX <= x + 85 &&
                    mouseY >= y + 70 &&
                    mouseY <= y + 92) {

                armor.setHorizontal(false);
                return true;
            }


            if (mouseX >= x + 95 &&
                    mouseX <= x + 170 &&
                    mouseY >= y + 70 &&
                    mouseY <= y + 92) {

                armor.setHorizontal(true);
                return true;
            }
        }

        if (selected instanceof TotemCounterHudElement totem) {

            int sliderX = x + 10;
            int sliderY = y + 80;


            if (mouseX >= sliderX &&
                    mouseX <= sliderX + 120 &&
                    mouseY >= sliderY - 5 &&
                    mouseY <= sliderY + 10) {


                draggingSlider = true;


                updateTotemSlider(
                        totem,
                        mouseX,
                        sliderX
                );


                return true;
            }
        }


        if (mouseX >= x + 10 &&
                mouseX <= x + 90 &&
                mouseY >= y + panelHeight - 35 &&
                mouseY <= y + panelHeight - 11) {

            close();
            return true;
        }


        return false;
    }

    public static void mouseReleased() {
        draggingSlider = false;
    }

    public static boolean mouseDragged(double mouseX, double mouseY) {

        if (!draggingSlider) {
            return false;
        }


        if (selected instanceof TotemCounterHudElement totem) {

            int sliderX = getPanelX() + 10;


            updateTotemSlider(
                    totem,
                    mouseX,
                    sliderX
            );

            return true;
        }


        return false;
    }

    private static void updateTotemSlider(
            TotemCounterHudElement totem,
            double mouseX,
            int sliderX
    ) {

        float percent =
                (float)((mouseX - sliderX) / 120.0);


        percent = Math.max(0, Math.min(1, percent));


        float value =
                0.5f + percent * 1.5f;


        totem.setScale(value);

    }

    private static void drawButton(
            GuiGraphics graphics,
            Minecraft mc,
            String text,
            int x,
            int y,
            int width,
            int height,
            int mouseX,
            int mouseY
    ) {

        boolean hovered =
                mouseX >= x &&
                        mouseX <= x + width &&
                        mouseY >= y &&
                        mouseY <= y + height;


        int color = hovered ? BUTTON_HOVER : BUTTON_BG;

// middle parts
        graphics.fill(
                x + 3,
                y,
                x + width - 3,
                y + height,
                color
        );

        graphics.fill(
                x,
                y + 3,
                x + width,
                y + height - 3,
                color
        );


        graphics.drawString(
                mc.font,
                text,
                x + 8,
                y + 6,
                TEXT
        );
    }
}