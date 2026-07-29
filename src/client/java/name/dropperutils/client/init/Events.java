package name.dropperutils.client.init;

import name.dropperutils.client.feature.*;
import name.dropperutils.client.util.AnchorPrediction;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import name.dropperutils.client.config.DropperUtilsConfig;
import name.dropperutils.client.gui.ClickGuiScreen;
import name.dropperutils.client.util.AnchorOptimizer;
import name.dropperutils.client.util.ExplosionEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import name.dropperutils.client.hudeditor.HudEditorScreen;

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

        PotionEffectsFeature.INSTANCE.setEnabled(
                DropperUtilsConfig.get().potionEffects
        );


        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            if (Keybinds.gammaKey.consumeClick()) {
                FullbrightFeature.INSTANCE.toggle();
            }

            FullbrightFeature.INSTANCE.tick();

            if (Keybinds.guiKey.consumeClick()) {
                client.setScreen(new ClickGuiScreen());
            }


            if (Keybinds.hudEditorKey.consumeClick()) {
                client.setScreen(new HudEditorScreen());
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
            AnchorPrediction.tick();
            ExplosionEffects.tick();
        });
    }
}