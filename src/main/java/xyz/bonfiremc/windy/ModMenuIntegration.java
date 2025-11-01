//? fabric {
package xyz.bonfiremc.windy;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return screen -> WindyConfig.HANDLER.generateGui().generateScreen(screen);
    }
}
//?}
