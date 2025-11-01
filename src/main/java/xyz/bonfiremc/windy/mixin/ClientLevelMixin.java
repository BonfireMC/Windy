package xyz.bonfiremc.windy.mixin;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.bonfiremc.windy.WindyConfig;
import xyz.bonfiremc.windy.WindyParticles;

@Mixin(ClientLevel.class)
public abstract class ClientLevelMixin {
    @Shadow
    public abstract void addParticle(ParticleOptions particleData, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed);

    @Inject(method = "doAnimateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;getBiome(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/core/Holder;"))
    public void windy$spawnWind(int posX, int posY, int posZ, int range, RandomSource random, Block block, BlockPos.MutableBlockPos blockPos, CallbackInfo ci) {
        ClientLevel world = (ClientLevel) (Object) this;

        WindyConfig config = WindyConfig.HANDLER.instance();

        if (!config.spawnWind || blockPos.getY() < config.minimumWindHeight) return;
        if (config.windMustSeeSky && !world.canSeeSky(blockPos)) return;

        if (random.nextDouble() * 100 <= config.windFrequency * 0.015) {
            this.addParticle(
                    WindyParticles.WIND/*? neoforge {*//*.get()*//*?}*/,
                    blockPos.getX() + random.nextDouble(),
                    blockPos.getY() + random.nextDouble(),
                    blockPos.getZ() + random.nextDouble(),
                    0.0D, 0.0D, 0.0D
            );
        }
    }
}
