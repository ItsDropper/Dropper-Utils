package name.dropperutils.client.init;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import name.dropperutils.client.config.DropperUtilsConfig;
import name.dropperutils.client.feature.ArmorHudFeature;
import name.dropperutils.client.feature.FullbrightFeature;
import name.dropperutils.client.feature.TotemCounterFeature;
import name.dropperutils.client.feature.ZoomFeature;
import name.dropperutils.client.gui.ClickGuiScreen;
import name.dropperutils.client.feature.SaturationFeature;

public class Events {

    public static void register() {

        DropperUtilsConfig.load();

        FullbrightFeature.INSTANCE.setEnabled(
                DropperUtilsConfig.get().fullbright
        );

        ZoomFeature.INSTANCE.setEnabled(
                DropperUtilsConfig.get().zoom
        );

        TotemCounterFeature.INSTANCE.setEnabled(
                DropperUtilsConfig.get().totemCounter
        );

        ArmorHudFeature.INSTANCE.setEnabled(
                DropperUtilsConfig.get().armorHud
        );

        SaturationFeature.INSTANCE.setEnabled(
                DropperUtilsConfig.get().saturation
        );


        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            if (Keybinds.gammaKey.consumeClick()) {
                FullbrightFeature.INSTANCE.toggle();
            }

            if (Keybinds.guiKey.consumeClick()) {
                client.setScreen(new ClickGuiScreen());
            }

            ZoomFeature.INSTANCE.tick(Keybinds.zoomKey);
        });
    }
}