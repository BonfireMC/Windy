package xyz.bonfiremc.windy.particle;

//? fabric {
 /*import net.fabricmc.fabric.api.client.particle.v1./^? <26.1 {^/ParticleFactoryRegistry/^?} else {^//^ParticleProviderRegistry^//^?}^/;
*///?}
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.NotNull;

public class WindParticle extends /*? >=1.21.9 {*/SingleQuadParticle /*?} else {*//*TextureSheetParticle*//*?}*/ {
    private final SpriteSet sprites;

    public WindParticle(ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteSet sprites, int lifetime) {
        super(world, x, y, z, velocityX, velocityY, velocityZ/*? >=1.21.9 {*/, sprites.first()/*?}*/);

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
    //? >=1.21.9 {
    protected @NotNull Layer getLayer() {
        return Layer.TRANSLUCENT;
    }
    //?} else {
    /*public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
    *///?}

    public record Factory(SpriteSet sprites, int lifetime) implements ParticleProvider<SimpleParticleType> {
        public static /*? fabric {*//*/^? <26.1 {^/ParticleFactoryRegistry.PendingParticleFactory/^?} else {^//^ParticleProviderRegistry.PendingParticleProvider^//^?}^/*//*?} else {*//*? >=1.21.9 {*/ParticleResources/*?} else {*//*ParticleEngine*//*?}*/.SpriteParticleRegistration/*?}*/<SimpleParticleType> lifetime(int lifetime) {
            return (sprites) -> new Factory(sprites, lifetime);
        }

        @Override
        public Particle createParticle(@NotNull SimpleParticleType parameters, @NotNull ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ/*? >=1.21.9 {*/, RandomSource random /*?}*/) {
            //? <1.21.9 {
            /*RandomSource random = world.random;
            *///?}

            int distance = random.nextInt(30) + 40;
            double angle = random.nextDouble() * Math.PI * 2;
            double newY = y + random.nextInt(15) + random.nextInt(15);

            return new WindParticle(world, (Math.cos(angle) * distance) + x, newY, (Math.sin(angle) * distance) + z, velocityX, velocityY, velocityZ, this.sprites, lifetime);
        }
    }
}
