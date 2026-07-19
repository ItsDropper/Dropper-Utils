package name.dropperutils.mixin;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.OutlineBufferSource;
import net.minecraft.client.renderer.SubmitNodeCollection;
import net.minecraft.client.renderer.SubmitNodeStorage;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.feature.BlockFeatureRenderer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockFeatureRenderer.class)
public class BlockFeatureRendererMixin {

    @Inject(
            method = "render",
            at = @At("HEAD")
    )
    private void test(
            SubmitNodeCollection submitNodeCollection,
            MultiBufferSource.BufferSource bufferSource,
            BlockRenderDispatcher blockRenderDispatcher,
            OutlineBufferSource outlineBufferSource,
            CallbackInfo ci
    ) {


    }
}