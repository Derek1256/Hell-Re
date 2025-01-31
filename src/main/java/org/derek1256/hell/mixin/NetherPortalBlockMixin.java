package org.derek1256.hell.mixin;

import net.minecraft.block.NetherPortalBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.derek1256.hell.Hell;

@Mixin(NetherPortalBlock.class)
public abstract class NetherPortalBlockMixin {
    @Inject(method = "getPortalSize", at = @At("HEAD"), cancellable = true)
    private void onGetPortalSize(WorldView world, BlockPos pos, BlockState state, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(Hell.CONFIG.maxPortalSize);
    }
}