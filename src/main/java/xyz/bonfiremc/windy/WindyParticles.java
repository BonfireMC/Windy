package xyz.bonfiremc.windy;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import xyz.bonfiremc.windy.particle.WindParticle;

import static xyz.bonfiremc.windy.WindyMod.id;

public class WindyParticles {
    public static SimpleParticleType WIND = Registry.register(Registries.PARTICLE_TYPE, id("wind"), FabricParticleTypes.simple());

    public static void init() {
        ParticleFactoryRegistry.getInstance().register(WIND, WindParticle.Factory::new);
    }
}
