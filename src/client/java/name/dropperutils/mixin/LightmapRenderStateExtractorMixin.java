package name.dropperutils.mixin;

import name.dropperutils.client.feature.FullbrightFeature;
import net.minecraft.client.renderer.LightmapRenderStateExtractor;
import net.minecraft.client.renderer.state.LightmapRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightmapRenderStateExtractor.class)
public class LightmapRenderStateExtractorMixin {

    @Inject(
            method = "extract",
            at = @At("TAIL")
    )
    private void dropperUtils$fullbright(
            LightmapRenderState state,
            float tickDelta,
            CallbackInfo ci
    ) {
        if (!FullbrightFeature.INSTANCE.isEnabled()) {
            return;
        }

        state.brightness = FullbrightFeature.getBrightness() / 10.0F;
    }
}