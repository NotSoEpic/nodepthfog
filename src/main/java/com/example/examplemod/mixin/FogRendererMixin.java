package com.example.examplemod.mixin;

import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FogRenderer.class)
public class FogRendererMixin {
    // (camera.y - minBuildHeight) * clearColorScale
    // ->
    // (1 - 0) * 1
    // theres probably a far better way to do it...
    @Redirect(method = "setupColor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;getPosition()Lnet/minecraft/world/phys/Vec3;", ordinal = 3))
    private static Vec3 noDepthFog1(Camera instance) {
        return new Vec3(0, 1, 0);
    }

    @Redirect(method = "setupColor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;getMinBuildHeight()I"))
    private static int noDepthFov2(ClientLevel instance) {
        return 0;
    }

    @Redirect(method = "setupColor", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel$ClientLevelData;getClearColorScale()F"))
    private static float noDepthFox3(ClientLevel.ClientLevelData instance) {
        return 1F;
    }
}
