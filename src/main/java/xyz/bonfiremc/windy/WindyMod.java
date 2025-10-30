package xyz.bonfiremc.windy;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.resources.ResourceLocation;

//? if fabric {
public class WindyMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        WindyConfig.HANDLER.load();
        WindyParticles.init();
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath("windy", path);
    }
}
//?}
