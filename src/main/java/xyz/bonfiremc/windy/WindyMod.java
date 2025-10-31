package xyz.bonfiremc.windy;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.resources.ResourceLocation;

//? if fabric {
public class WindyMod implements ClientModInitializer {
    public static ResourceLocation asResource(String path) {
        return /*? if <1.21 {*/new /*?}*/ResourceLocation/*? if >=1.21 {*//*.fromNamespaceAndPath*//*?}*/("windy", path);
    }

    @Override
    public void onInitializeClient() {
        WindyConfig.HANDLER.load();
        WindyParticles.init();
    }
}
//?}
