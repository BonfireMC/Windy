package xyz.bonfiremc.windy;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.util.Identifier;
import xyz.bonfiremc.windy.config.WindyConfig;

public class WindyMod implements ClientModInitializer {
    public static final String MOD_ID = "windy";

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }

    @Override
    public void onInitializeClient() {
        WindyConfig.HANDLER.load();
        WindyParticles.init();
    }
}
