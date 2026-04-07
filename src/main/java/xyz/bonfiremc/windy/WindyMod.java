package xyz.bonfiremc.windy;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.resources.Identifier;

public class WindyMod implements ClientModInitializer {
    public static final String MOD_ID = "windy";

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    @Override
    public void onInitializeClient() {
        WindyConfig.HANDLER.load();
        WindyParticles.init();
    }
}
