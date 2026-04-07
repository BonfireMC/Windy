package xyz.bonfiremc.windy;

import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import xyz.bonfiremc.windy.particle.WindParticle;

import static xyz.bonfiremc.windy.WindyMod.id;

public class WindyParticles {
    public static SimpleParticleType WIND = Registry.register(BuiltInRegistries.PARTICLE_TYPE, id("wind"), FabricParticleTypes.simple());
    public static SimpleParticleType STRONG_WIND = Registry.register(BuiltInRegistries.PARTICLE_TYPE, id("strong_wind"), FabricParticleTypes.simple());

    public static void init() {
        ParticleProviderRegistry.getInstance().register(WIND, WindParticle.Factory.lifetime(50));
        ParticleProviderRegistry.getInstance().register(STRONG_WIND, WindParticle.Factory.lifetime(25));
    }
}
