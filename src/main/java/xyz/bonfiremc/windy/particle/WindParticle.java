package xyz.bonfiremc.windy.particle;

import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.NotNull;

public class WindParticle extends SingleQuadParticle {
    private final SpriteSet sprites;

    public WindParticle(ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteSet sprites, int lifetime) {
        super(world, x, y, z, velocityX, velocityY, velocityZ, sprites.first());

        this.hasPhysics = true;
        this.lifetime = lifetime;

        this.scale(50.5F);
        this.setAlpha(0.5F);
        this.setPos(x, y, z);
        this.setSpriteFromAge(sprites);

        this.sprites = sprites;
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
    }

    @Override
    protected @NotNull Layer getLayer() {
        return Layer.TRANSLUCENT;
    }

    public record Factory(SpriteSet sprites, int lifetime) implements ParticleProvider<@NotNull SimpleParticleType> {
        public static ParticleProviderRegistry.PendingParticleProvider<@NotNull SimpleParticleType> lifetime(int lifetime) {
            return (sprites) -> new Factory(sprites, lifetime);
        }

        @Override
        public Particle createParticle(@NotNull SimpleParticleType parameters, @NotNull ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, RandomSource random) {

            int distance = random.nextInt(30) + 40;
            double angle = random.nextDouble() * Math.PI * 2;
            double newY = y + random.nextInt(15) + random.nextInt(15);

            return new WindParticle(world, (Math.cos(angle) * distance) + x, newY, (Math.sin(angle) * distance) + z, velocityX, velocityY, velocityZ, this.sprites, lifetime);
        }
    }
}