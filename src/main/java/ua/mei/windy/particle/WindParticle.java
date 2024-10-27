package ua.mei.windy.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import ua.mei.windy.WindyMod;
import ua.mei.windy.config.WindyConfig;

import java.util.Random;

public class WindParticle extends SpriteBillboardParticle {
    private final SpriteProvider sprites;

    public WindParticle(ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, SpriteProvider sprites) {
        super(world, x, y, z, xSpeed, ySpeed, zSpeed);
        this.sprites = sprites;
        this.scale(50.5F);
        this.maxAge = 50;
        this.collidesWithWorld = true;
        this.setPos(x, y, z);
        this.setSpriteForAge(sprites);
        this.setAlpha(y < world.getSeaLevel() + WindyConfig.HANDLER.instance().minimumWindHeight ? 0 : 0.5F);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteForAge(this.sprites);

        if (this.alpha == 0) this.markDead();
    }

    public record Factory(SpriteProvider sprites) implements ParticleFactory<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType type, ClientWorld world, double x, double y, double z, double velX, double velY, double velZ) {
            Random random = new Random();
            int d = random.nextInt(30) + 40;
            double r = random.nextDouble() * Math.PI * 2;
            double newY = y + random.nextInt(15) + random.nextInt(15);

            return new WindParticle(world, (Math.cos(r) * d) + x, newY, (Math.sin(r) * d) + z, velX, velY, velZ, this.sprites);
        }
    }
}
