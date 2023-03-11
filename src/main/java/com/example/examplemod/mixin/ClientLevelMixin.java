package com.example.examplemod.mixin;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.level.LevelHeightAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientLevel.ClientLevelData.class)
public class ClientLevelMixin {
    // getHorizonHeight(Lnet/minecraft/world/level/LevelHeightAccessor;)D
    @Inject(method = "getHorizonHeight", at = @At("RETURN"), cancellable = true)
    private void noDarkSky(LevelHeightAccessor levelHeightAccessor, CallbackInfoReturnable<Double> cir) {
        cir.setReturnValue(-256d);
    }

    /* @Inject(method = "getClearColorScale", at = @At("RETURN"), cancellable = true)
    private void noDepthFog(CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(0F);
    } */
}
