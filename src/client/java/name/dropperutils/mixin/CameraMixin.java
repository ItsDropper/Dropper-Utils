package name.dropperutils.mixin;

import name.dropperutils.client.feature.ZoomFeature;
import net.minecraft.client.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Camera.class)
public class CameraMixin {

    @Inject(
            method = "calculateFov",
            at = @At("RETURN"),
            cancellable = true
    )
    private void modifyZoomFov(
            float partialTick,
            CallbackInfoReturnable<Float> cir
    ) {
        float progress = ZoomFeature.getZoomProgress(partialTick);

        if (progress > 0.0F) {
            float multiplier = ZoomFeature.getZoomMultiplier();

            float smoothMultiplier =
                    1.0F + (multiplier - 1.0F) * progress;

            cir.setReturnValue(
                    cir.getReturnValue() * smoothMultiplier
            );
        }
    }
}