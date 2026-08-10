package name.dropperutils.client.hudeditor;

import name.dropperutils.client.hud.ArmorHudElement;
import name.dropperutils.client.hud.DebugHudElement;
import name.dropperutils.client.hud.PotionEffectsHudElement;
import name.dropperutils.client.hud.TotemCounterHudElement;
import net.minecraft.client.gui.GuiGraphicsExtractor;

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
    }

    public static void add(HudElement element) {
        ELEMENTS.add(element);
    }

    public static void render(GuiGraphicsExtractor graphics) {

        init();

        for (HudElement element : ELEMENTS) {
            element.renderScaled(graphics);
        }
    }

    public static List<HudElement> getElements() {
        return ELEMENTS;
    }
}