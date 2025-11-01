package xyz.bonfiremc.windy;

import net.minecraft.resources.ResourceLocation;
//? fabric {
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
//?}
//? neoforge {
/*import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

import xyz.bonfiremc.windy.particle.WindParticle;
*///?}

import java.nio.file.Path;

/*? neoforge {*//*@Mod("windy")*//*?}*/
public class WindyMod/*? fabric {*/ implements ClientModInitializer/*?}*/ {
    public static ResourceLocation asResource(String path) {
        return /*? <1.21 {*//*new *//*?}*/ResourceLocation/*? >=1.21 {*/.fromNamespaceAndPath/*?}*/("windy", path);
    }

    /*? fabric {*/@Override/*?}*/
    public /*? fabric {*/void onInitializeClient()/*?} else {*//*WindyMod(IEventBus modBus)*//*?}*/ {
        WindyConfig.HANDLER.load();
        WindyParticles.init();

        //? neoforge {
        /*ModLoadingContext.get().registerExtensionPoint(
                IConfigScreenFactory.class,
                () -> (client, parent) -> WindyConfig.HANDLER.generateGui().generateScreen(parent)
        );

        WindyParticles.PARTICLE_TYPES.register(modBus);
        modBus.addListener(WindyMod::registerParticleProviders);
        *///?}
    }

    public static Path getConfigDir() {
        return /*? fabric {*/FabricLoader.getInstance().getConfigDir()/*?} else {*//*FMLPaths.CONFIGDIR.get()*//*?}*/.resolve("windy-config.json");
    }

    //? neoforge {
    /*public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(WindyParticles.WIND.get(), WindParticle.Factory::new);
    }
    *///?}
}
