package name.dropperutils.client.init;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.resources.Identifier;
import name.dropperutils.client.hud.ArmorHud;
import name.dropperutils.client.hud.TotemCounterHud;
import name.dropperutils.client.hud.DebugHud;

public class HudRegistration {

    public static void register() {

        HudElementRegistry.attachElementAfter(
                VanillaHudElements.HOTBAR,
                Identifier.fromNamespaceAndPath("dropperutils", "totem_counter"),
                (graphics, tickCounter) -> TotemCounterHud.render(graphics)
        );

        HudElementRegistry.attachElementAfter(
                VanillaHudElements.HOTBAR,
                Identifier.fromNamespaceAndPath("dropperutils", "armor_hud"),
                (graphics, tickCounter) -> ArmorHud.render(graphics)
        );

        HudElementRegistry.attachElementAfter(
                VanillaHudElements.HOTBAR,
                Identifier.fromNamespaceAndPath("dropperutils", "debug_hud"),
                (graphics, tickCounter) -> DebugHud.render(graphics)
        );

    }
}