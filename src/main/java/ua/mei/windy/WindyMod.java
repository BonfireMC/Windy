package ua.mei.windy;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.util.Identifier;
import ua.mei.windy.config.WindyConfig;
import ua.mei.windy.registry.WindyParticles;

public class WindyMod implements ClientModInitializer {
    public static final String MOD_ID = "windy";

    @Override
    public void onInitializeClient() {
        WindyConfig.HANDLER.load();
        WindyParticles.init();
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}
