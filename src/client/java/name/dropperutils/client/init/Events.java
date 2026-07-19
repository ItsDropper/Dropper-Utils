package name.dropperutils.client.init;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import name.dropperutils.client.config.DropperUtilsConfig;
import name.dropperutils.client.feature.ArmorHudFeature;
import name.dropperutils.client.feature.FullbrightFeature;
import name.dropperutils.client.feature.TotemCounterFeature;
import name.dropperutils.client.feature.ZoomFeature;
import name.dropperutils.client.feature.DebugHudFeature;
import name.dropperutils.client.gui.ClickGuiScreen;
import name.dropperutils.client.feature.SaturationFeature;
import name.dropperutils.client.util.AnchorOptimizer;
import name.dropperutils.client.util.ExplosionEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;

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

        DebugHudFeature.INSTANCE.setEnabled(
                DropperUtilsConfig.get().debugHud
        );


        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            if (Keybinds.gammaKey.consumeClick()) {
                FullbrightFeature.INSTANCE.toggle();
            }

            FullbrightFeature.INSTANCE.tick();

            if (Keybinds.guiKey.consumeClick()) {
                client.setScreen(new ClickGuiScreen());
            }

            ZoomFeature.INSTANCE.tick(Keybinds.zoomKey);

            if (client.player != null) {

                ItemStack main = client.player.getMainHandItem();
                ItemStack off = client.player.getOffhandItem();

                if (main.is(Items.RESPAWN_ANCHOR)
                        || off.is(Items.RESPAWN_ANCHOR)
                        || main.is(Items.GLOWSTONE)
                        || off.is(Items.GLOWSTONE)) {

                    AnchorOptimizer.wakeUp();

                }

            }

            AnchorOptimizer.tick();
            ExplosionEffects.tick();
        });
    }
}