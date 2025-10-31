package xyz.bonfiremc.windy;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.autogen.AutoGen;
import dev.isxander.yacl3.config.v2.api.autogen.Boolean;
import dev.isxander.yacl3.config.v2.api.autogen.DoubleSlider;
import dev.isxander.yacl3.config.v2.api.autogen.IntField;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;

import static xyz.bonfiremc.windy.WindyMod.asResource;

public class WindyConfig {
    public static ConfigClassHandler<WindyConfig> HANDLER = ConfigClassHandler.createBuilder(WindyConfig.class)
            .id(asResource("config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("windy-config.json"))
                    .build()
            )
            .build();

    @AutoGen(category = "general")
    @Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
    @SerialEntry
    public boolean spawnWind = true;

    @AutoGen(category = "general")
    @IntField(min = -64, max = 320)
    @SerialEntry
    public int minimumWindHeight = 83;

    @AutoGen(category = "general")
    @Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
    @SerialEntry
    public boolean windMustSeeSky = true;

    @AutoGen(category = "general")
    @DoubleSlider(min = 0.5, max = 100, step = 0.5, format = "%.1f%%")
    @SerialEntry
    public double windFrequency = 1;
}
