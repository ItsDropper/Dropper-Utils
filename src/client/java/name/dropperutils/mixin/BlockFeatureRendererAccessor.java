package name.dropperutils.mixin;

import com.mojang.blaze3d.vertex.PoseStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.renderer.feature.BlockFeatureRenderer;

@Mixin(BlockFeatureRenderer.class)
public interface BlockFeatureRendererAccessor {

    @Accessor("poseStack")
    PoseStack getPoseStack();
}