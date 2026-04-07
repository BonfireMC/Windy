package xyz.bonfiremc.windy;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
//? fabric {
/*import net.fabricmc.fabric.api.client.particle.v1./^? <26.1 {^/ParticleFactoryRegistry/^?} else {^//^ParticleProviderRegistry^//^?}^/;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import xyz.bonfiremc.windy.particle.WindParticle;

import static xyz.bonfiremc.windy.WindyMod.asResource;
*///?}
//? neoforge {
import net.minecraft.core.particles.ParticleType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;
 //?}

public class WindyParticles {
    //? neoforge {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, "windy");
    //?}

    public static /*? neoforge {*/Supplier</*?}*/SimpleParticleType/*? neoforge {*/>/*?}*/ WIND = /*? fabric {*//*Registry.register(BuiltInRegistries.PARTICLE_TYPE, asResource("wind"), FabricParticleTypes.simple())*//*?} else {*/PARTICLE_TYPES.register("wind", () -> new SimpleParticleType(false))/*?}*/;
    public static /*? neoforge {*/Supplier</*?}*/SimpleParticleType/*? neoforge {*/>/*?}*/ STRONG_WIND = /*? fabric {*//*Registry.register(BuiltInRegistries.PARTICLE_TYPE, asResource("strong_wind"), FabricParticleTypes.simple())*//*?} else {*/PARTICLE_TYPES.register("strong_wind", () -> new SimpleParticleType(false))/*?}*/;

    public static void init() {
        //? fabric {
        /*/^? <26.1 {^/ParticleFactoryRegistry/^?} else {^//^ParticleProviderRegistry^//^?}^/.getInstance().register(WIND, WindParticle.Factory.lifetime(50));
        /^? <26.1 {^/ParticleFactoryRegistry/^?} else {^//^ParticleProviderRegistry^//^?}^/.getInstance().register(STRONG_WIND, WindParticle.Factory.lifetime(25));
        *///?}
    }
}
