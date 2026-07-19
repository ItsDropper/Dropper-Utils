package name.dropperutils.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;

import name.dropperutils.client.feature.NoExplosionEffectsFeature;

public class ExplosionEffects {

    public static void tick() {

        if (!NoExplosionEffectsFeature.INSTANCE.isEnabled()) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();

        if (mc.level == null) {
            return;
        }

    }
}