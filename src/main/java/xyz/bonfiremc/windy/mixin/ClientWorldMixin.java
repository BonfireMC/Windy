package xyz.bonfiremc.windy.mixin;

import net.minecraft.block.Block;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.bonfiremc.windy.WindyParticles;
import xyz.bonfiremc.windy.config.WindyConfig;

@Mixin(ClientWorld.class)
public abstract class ClientWorldMixin {
    @Inject(method = "randomBlockDisplayTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;getBiome(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/registry/entry/RegistryEntry;"))
    public void windy$spawnWind(int centerX, int centerY, int centerZ, int radius, Random random, Block block, BlockPos.Mutable pos, CallbackInfo ci) {
        ClientWorld world = (ClientWorld) (Object) this;

        WindyConfig config = WindyConfig.HANDLER.instance();

        if (!config.spawnWind || pos.getY() < config.minimumWindHeight) return;
        if (config.windMustSeeSky && !world.isSkyVisible(pos)) return;

        if (random.nextDouble() * 100 <= config.windFrequency * 0.015) {
            world.addParticleClient(
                    WindyParticles.WIND,
                    pos.getX() + random.nextDouble(),
                    pos.getY() + random.nextDouble(),
                    pos.getZ() + random.nextDouble(),
                    0.0D, 0.0D, 0.0D
            );
        }
    }
}
