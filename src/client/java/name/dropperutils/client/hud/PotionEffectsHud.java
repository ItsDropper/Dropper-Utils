package name.dropperutils.client.hud;

import name.dropperutils.client.feature.PotionEffectsFeature;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;

public class PotionEffectsHud {

    public static void render(GuiGraphics graphics) {

        if (!PotionEffectsFeature.INSTANCE.isEnabled()) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();

        if (mc.player == null || mc.level == null) {
            return;
        }

        int x = 5;
        int y = 20;

        for (MobEffectInstance effect : mc.player.getActiveEffects()) {

            Identifier texture = effect.getEffect()
                    .unwrapKey()
                    .map(key -> key.identifier().withPrefix("mob_effect/"))
                    .orElse(MissingTextureAtlasSprite.getLocation());

            graphics.blitSprite(
                    RenderPipelines.GUI_TEXTURED,
                    texture,
                    x,
                    y,
                    18,
                    18
            );

            String name = effect.getEffect()
                    .value()
                    .getDisplayName()
                    .getString();

            int amplifier = effect.getAmplifier();

            if (amplifier > 0) {
                name += " " + toRoman(amplifier + 1);
            }

            Component duration = MobEffectUtil.formatDuration(
                    effect,
                    1.0F,
                    mc.level.tickRateManager().tickrate()
            );

            graphics.drawString(
                    mc.font,
                    name,
                    x + 22,
                    y + 1,
                    0xFFFFFFFF
            );

            graphics.drawString(
                    mc.font,
                    duration,
                    x + 22,
                    y + 10,
                    0xFFAAAAAA
            );

            y += 24;
        }
    }

    private static String toRoman(int number) {
        return switch (number) {
            case 2 -> "II";
            case 3 -> "III";
            case 4 -> "IV";
            case 5 -> "V";
            case 6 -> "VI";
            case 7 -> "VII";
            case 8 -> "VIII";
            case 9 -> "IX";
            case 10 -> "X";
            default -> String.valueOf(number);
        };
    }
}