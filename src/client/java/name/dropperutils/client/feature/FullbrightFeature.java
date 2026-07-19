package name.dropperutils.client.feature;

import name.dropperutils.client.config.DropperUtilsConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class FullbrightFeature extends Feature {

    public static final FullbrightFeature INSTANCE = new FullbrightFeature();

    private FullbrightFeature() {
        super("Fullbright", Category.VISUAL);
    }

    @Override
    public void onEnable() {

        DropperUtilsConfig.get().fullbright = true;
        DropperUtilsConfig.save();

        Minecraft mc = Minecraft.getInstance();

        if (mc.player != null) {
            mc.player.addEffect(
                    new MobEffectInstance(
                            MobEffects.NIGHT_VISION,
                            Integer.MAX_VALUE,
                            0,
                            false,
                            false,
                            false
                    )
            );
        }
    }

    @Override
    public void onDisable() {

        DropperUtilsConfig.get().fullbright = false;
        DropperUtilsConfig.save();

        Minecraft mc = Minecraft.getInstance();

        if (mc.player != null) {
            mc.player.removeEffect(MobEffects.NIGHT_VISION);
        }
    }

    public void tick() {

        if (!isEnabled()) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();

        if (mc.player == null) {
            return;
        }

        if (!mc.player.hasEffect(MobEffects.NIGHT_VISION)) {

            mc.player.addEffect(
                    new MobEffectInstance(
                            MobEffects.NIGHT_VISION,
                            Integer.MAX_VALUE,
                            0,
                            false,
                            false,
                            false
                    )
            );
        }
    }
}