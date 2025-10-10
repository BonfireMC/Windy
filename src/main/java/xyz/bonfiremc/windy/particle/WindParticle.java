package xyz.bonfiremc.windy.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

import java.util.Random;

public class WindParticle extends SpriteBillboardParticle {
    private final SpriteProvider sprites;

    public WindParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider sprites) {
        super(world, x, y, z, velocityX, velocityY, velocityZ);

        this.collidesWithWorld = true;
        this.maxAge = 50;

        this.scale(50.5F);
        this.setAlpha(0.5F);
        this.setPos(x, y, z);
        this.setSpriteForAge(sprites);

        this.sprites = sprites;
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteForAge(this.sprites);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    public record Factory(SpriteProvider sprites) implements ParticleFactory<SimpleParticleType> {
        @Override
        public Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            Random random = new Random();

            int distance = random.nextInt(30) + 40;
            double angle = random.nextDouble() * Math.PI * 2;
            double newY = y + random.nextInt(15) + random.nextInt(15);

            return new WindParticle(world, (Math.cos(angle) * distance) + x, newY, (Math.sin(angle) * distance) + z, velocityX, velocityY, velocityZ, this.sprites);
        }
    }
}
