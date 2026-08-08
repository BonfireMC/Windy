package xyz.bonfiremc.windy;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import xyz.bonfiremc.windy.particle.WindParticle;

import java.nio.file.Path;

@Mod("windy")
public class WindyMod {
    public static ResourceLocation asResource(String path) {
        return new ResourceLocation("windy", path);
    }

    public WindyMod() {
        WindyConfig.HANDLER.load();
        WindyParticles.init();

        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        WindyParticles.PARTICLE_TYPES.register(modBus);
        modBus.addListener(WindyMod::registerParticleProviders);

        ModLoadingContext.get().registerExtensionPoint(
                ConfigScreenFactory.class,
                () -> new ConfigScreenFactory((client, parent) -> WindyConfig.HANDLER.generateGui().generateScreen(parent))
        );
    }

    public static Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get().resolve("windy-config.json");
    }

    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(WindyParticles.WIND.get(), WindParticle.Factory.lifetime(50));
        event.registerSpriteSet(WindyParticles.STRONG_WIND.get(), WindParticle.Factory.lifetime(25));
    }
}
