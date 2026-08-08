package xyz.bonfiremc.windy;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class WindyParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, "windy");

    public static final Supplier<SimpleParticleType> WIND = PARTICLE_TYPES.register("wind", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> STRONG_WIND = PARTICLE_TYPES.register("strong_wind", () -> new SimpleParticleType(false));

    public static void init() {
    }
}
