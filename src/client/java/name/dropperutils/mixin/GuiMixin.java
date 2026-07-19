package name.dropperutils.mixin;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;

import name.dropperutils.client.feature.SaturationFeature;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.RenderPipelines;

@Mixin(Gui.class)
public class GuiMixin {

    @Inject(
            method = "renderFood",
            at = @At("TAIL")
    )
    private void renderSaturation(
            GuiGraphics guiGraphics,
            Player player,
            int i,
            int j,
            CallbackInfo ci
    ) {

        if (!SaturationFeature.INSTANCE.isEnabled()) {
            return;
        }

        FoodData food = player.getFoodData();

        int saturation = (int) Math.ceil(food.getSaturationLevel() / 2.0f);

        for (int l = 0; l < saturation; l++) {

            int iconX = j - l * 8 - 9;
            int iconY = i;

            guiGraphics.blit(
                    RenderPipelines.GUI_TEXTURED,
                    Identifier.fromNamespaceAndPath(
                            "dropperutils",
                            "textures/gui/saturation.png"
                    ),
                    iconX,
                    iconY,
                    0,
                    0,
                    9,
                    9,
                    9,
                    9
            );
        }
    }
}