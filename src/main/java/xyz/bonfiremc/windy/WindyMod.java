package xyz.bonfiremc.windy;

//? fabric {
/*import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
*///?}
import net.minecraft.resources.ResourceLocation;
//? forge {
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;
//?}
//? neoforge {
/*import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.common.NeoForge;
*///?}
//? forgelike {
import xyz.bonfiremc.windy.particle.WindParticle;
//?}

import java.nio.file.Path;

/*? forgelike {*/@Mod("windy")/*?}*/
/*? forge {*/@Mod.EventBusSubscriber(modid = "windy", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)/*?}*/
public class WindyMod/*? fabric {*/ /*implements ClientModInitializer*//*?}*/ {
    public static ResourceLocation asResource(String path) {
        return /*? <1.21 {*/new /*?}*/ResourceLocation/*? >=1.21 {*//*.fromNamespaceAndPath*//*?}*/("windy", path);
    }

    /*? fabric {*//*@Override*//*?}*/
    public /*? fabric {*//*void onInitializeClient()*//*?} else {*/WindyMod()/*?}*/ {
        WindyConfig.HANDLER.load();
        WindyParticles.init();

        //? neoforge {
        /*NeoForge.EVENT_BUS.addListener(WindyMod::registerParticleProviders);
        *///?}
    }

    public static Path getConfigDir() {
        return /*? fabric {*//*FabricLoader.getInstance().getConfigDir()*//*?} else {*/FMLPaths.CONFIGDIR.get()/*?}*/.resolve("windy-config.json");
    }

    //? forgelike {
    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(WindyParticles.WIND.get(), WindParticle.Factory::new);
    }
    //?}
}
