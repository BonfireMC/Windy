package xyz.bonfiremc.windy;

//? fabric {
/*import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import xyz.bonfiremc.windy.particle.WindParticle;
*///?}
import net.minecraft.core.particles.SimpleParticleType;
//? forgelike {
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;

import java.util.function.Supplier;
//?}
//? forge {
import net.minecraftforge.registries.DeferredRegister;
//?}
//? neoforge {
/*import net.neoforged.neoforge.registries.DeferredRegister;
 *///?}

//? fabric {
/*import static xyz.bonfiremc.windy.WindyMod.asResource;
 *///?}

public class WindyParticles {
    //? forgelike {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, "windy");
    //?}

    public static /*? forgelike {*/Supplier</*?}*/SimpleParticleType/*? forgelike {*/>/*?}*/ WIND = /*? fabric {*//*Registry.register(BuiltInRegistries.PARTICLE_TYPE, asResource("wind"), FabricParticleTypes.simple())*//*?} else {*/PARTICLE_TYPES.register("wind", () -> new SimpleParticleType(false))/*?}*/;

    public static void init() {
        //? fabric {
        /*ParticleFactoryRegistry.getInstance().register(WIND, WindParticle.Factory::new);
        *///?}
    }
}
