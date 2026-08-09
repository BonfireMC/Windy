package xyz.bonfiremc.windy;

//? <=1.21.9 {
/*import net.minecraft.resources.ResourceLocation;
*///?} else {
import net.minecraft.resources.Identifier;
//?}
//? fabric {
/*import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
*///?}
//? neoforge {
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
//?}
//? forge {
import net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
//?}
//? neoforge || forge {
import xyz.bonfiremc.windy.particle.WindParticle;
//?}

import java.nio.file.Path;

/*? neoforge || forge {*/@Mod("windy")/*?}*/
public class WindyMod/*? fabric {*/ /*implements ClientModInitializer*//*?}*/ {
    public static /*? <=1.21.9 {*//*ResourceLocation*//*?} else {*/Identifier/*?}*/ asResource(String path) {
        return /*? <1.21 {*//*new *//*?}*//*? <=1.21.9 {*//*ResourceLocation*//*?} else {*/Identifier/*?}*//*? >=1.21 {*/.fromNamespaceAndPath/*?}*/("windy", path);
    }

    /*? fabric {*//*@Override*//*?}*/
    public /*? fabric {*//*void onInitializeClient()*//*?} else {*/WindyMod(/*? neoforge {*/IEventBus modBus/*?}*/)/*?}*/ {
        WindyConfig.HANDLER.load();
        WindyParticles.init();

        //? neoforge {
        ModLoadingContext.get().registerExtensionPoint(
                IConfigScreenFactory.class,
                () -> (client, parent) -> WindyConfig.HANDLER.generateGui().generateScreen(parent)
        );

        WindyParticles.PARTICLE_TYPES.register(modBus);
        modBus.addListener(WindyMod::registerParticleProviders);
        //?}

        //? forge {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        WindyParticles.PARTICLE_TYPES.register(modBus);
        modBus.addListener(WindyMod::registerParticleProviders);

        ModLoadingContext.get().registerExtensionPoint(
                ConfigScreenFactory.class,
                () -> new ConfigScreenFactory((client, parent) -> WindyConfig.HANDLER.generateGui().generateScreen(parent))
        );
        //?}
    }

    public static Path getConfigDir() {
        return /*? fabric {*//*FabricLoader.getInstance().getConfigDir()*//*?} else {*/FMLPaths.CONFIGDIR.get()/*?}*/.resolve("windy-config.json");
    }

    //? neoforge || forge {
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(WindyParticles.WIND.get(), WindParticle.Factory.lifetime(50));
        event.registerSpriteSet(WindyParticles.STRONG_WIND.get(), WindParticle.Factory.lifetime(25));
    }
    //?}
}
