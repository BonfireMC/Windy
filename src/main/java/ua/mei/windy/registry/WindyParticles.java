package ua.mei.windy.registry;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import ua.mei.windy.particle.WindParticle;

import static ua.mei.windy.WindyMod.id;

public class WindyParticles {
    public static SimpleParticleType WIND;

    public static void init() {
        WIND = Registry.register(Registries.PARTICLE_TYPE, id("wind"), FabricParticleTypes.simple());

        ParticleFactoryRegistry.getInstance().register(WIND, WindParticle.Factory::new);
    }
}
