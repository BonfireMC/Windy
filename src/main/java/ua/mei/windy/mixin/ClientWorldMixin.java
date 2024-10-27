package ua.mei.windy.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.Block;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.mei.windy.util.ClientWorldMixinUtil;

@Mixin(ClientWorld.class)
public abstract class ClientWorldMixin {
    @Inject(method = "randomBlockDisplayTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;getBiome(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/registry/entry/RegistryEntry;"))
    public void animateWind(int centerX, int centerY, int centerZ, int radius, Random random, Block block, BlockPos.Mutable pos, CallbackInfo ci, @Local(ordinal = 4) int i, @Local(ordinal = 5) int j, @Local(ordinal = 6) int k) {
        ClientWorld world = (ClientWorld) (Object) this;
        ClientWorldMixinUtil.tryAddWindParticle(world, new BlockPos(i, j, k), random);
    }
}
