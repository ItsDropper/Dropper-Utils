package name.dropperutils.client.hudeditor;

import name.dropperutils.client.hud.ArmorHudElement;
import name.dropperutils.client.hud.DebugHudElement;
import name.dropperutils.client.hud.PotionEffectsHudElement;
import name.dropperutils.client.hud.TotemCounterHudElement;
import net.minecraft.client.gui.GuiGraphics;

import java.util.ArrayList;
import java.util.List;

public class HudManager {

    private static final List<HudElement> ELEMENTS = new ArrayList<>();

    private static boolean initialized = false;


    public static void init() {

        if (initialized) {
            return;
        }

        initialized = true;


        add(new DebugHudElement());
        add(new ArmorHudElement());
        add(new TotemCounterHudElement());
        add(new PotionEffectsHudElement());

        // future:
        // add(new PotionEffectsHudElement());
        // add(new ArmorHudElement());
        // add(new TotemHudElement());
    }


    public static void add(HudElement element) {
        ELEMENTS.add(element);
    }


    public static void render(GuiGraphics graphics) {

        init();


        for (HudElement element : ELEMENTS) {

            graphics.pose().pushMatrix();

            element.render(graphics);

            graphics.pose().popMatrix();
        }
    }


    public static List<HudElement> getElements() {
        return ELEMENTS;
    }
}