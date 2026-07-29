package name.dropperutils.client.hudeditor;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;
import name.dropperutils.client.hud.ArmorHudElement;
import name.dropperutils.client.hud.TotemCounterHudElement;


public class HudSettingsPanel {

    private static HudElement selected;
    private static boolean open = false;
    private static boolean draggingSlider = false;

    private static int getPanelX() {
        Minecraft mc = Minecraft.getInstance();
        return (mc.getWindow().getGuiScaledWidth() - 150) / 2;
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


    public static void render(GuiGraphics graphics) {

        if (!open || selected == null) {
            return;
        }


        Minecraft mc = Minecraft.getInstance();


        int panelWidth = 150;
        int panelHeight = 140;

        int x = (mc.getWindow().getGuiScaledWidth() - panelWidth) / 2;
        int y = (mc.getWindow().getGuiScaledHeight() - panelHeight) / 2;


        graphics.fill(
                x,
                y,
                x + panelWidth,
                y + panelHeight,
                0xAA000000
        );


        graphics.drawString(
                mc.font,
                "HUD Settings",
                x + 10,
                y + 10,
                0xFFFFFFFF
        );


        graphics.drawString(
                mc.font,
                getDisplayName(selected),
                x + 10,
                y + 30,
                0xFFAAAAAA
        );

        if (selected instanceof ArmorHudElement) {

            graphics.drawString(
                    mc.font,
                    "Layout:",
                    x + 10,
                    y + 55,
                    0xFFFFFFFF
            );


            graphics.fill(
                    x + 10,
                    y + 70,
                    x + 70,
                    y + 90,
                    0xFF555555
            );


            graphics.drawString(
                    mc.font,
                    "Vertical",
                    x + 14,
                    y + 76,
                    0xFFFFFFFF
            );


            graphics.fill(
                    x + 80,
                    y + 70,
                    x + 145,
                    y + 90,
                    0xFF555555
            );


            graphics.drawString(
                    mc.font,
                    "Horizontal",
                    x + 84,
                    y + 76,
                    0xFFFFFFFF
            );
        }

        graphics.fill(
                x + 10,
                y + 92,
                x + 60,
                y + 108,
                0xFF555555
        );

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

        graphics.drawString(
                mc.font,
                "Close",
                x + 20,
                y + 96,
                0xFFFFFFFF
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


        int panelWidth = 150;
        int panelHeight = 140;

        Minecraft mc = Minecraft.getInstance();

        int x = (mc.getWindow().getGuiScaledWidth() - panelWidth) / 2;
        int y = (mc.getWindow().getGuiScaledHeight() - panelHeight) / 2;

        if (selected instanceof ArmorHudElement armor) {

            if (mouseX >= x + 10 &&
                    mouseX <= x + 70 &&
                    mouseY >= y + 70 &&
                    mouseY <= y + 90) {

                armor.setHorizontal(false);
                return true;
            }


            if (mouseX >= x + 80 &&
                    mouseX <= x + 145 &&
                    mouseY >= y + 70 &&
                    mouseY <= y + 90) {

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
                mouseX <= x + 60 &&
                mouseY >= y + 92 &&
                mouseY <= y + 108) {

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
}