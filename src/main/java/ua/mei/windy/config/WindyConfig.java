package ua.mei.windy.config;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.autogen.*;
import dev.isxander.yacl3.config.v2.api.autogen.Boolean;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;

import static ua.mei.windy.WindyMod.id;

public class WindyConfig {
    public static ConfigClassHandler<WindyConfig> HANDLER = ConfigClassHandler.createBuilder(WindyConfig.class)
            .id(id("config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("windy-config.json"))
                    .setJson5(true)
                    .build()
            )
            .build();

    @AutoGen(category = "general", group = "particles")
    @Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
    @SerialEntry
    public boolean shouldDisplayWind = true;

    @AutoGen(category = "general", group = "particles")
    @IntField(min = -64, max = 320)
    @SerialEntry
    public int minimumWindHeight = 83;

    @AutoGen(category = "general", group = "particles")
    @Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
    @SerialEntry
    public boolean windMustSeeSky = true;

    @AutoGen(category = "general", group = "frequency")
    @FloatField(min = 0, max = 1, format = "%s")
    @SerialEntry
    public float lowWindFrequency = 0.005f;

    @AutoGen(category = "general", group = "frequency")
    @FloatField(min = 0, max = 1, format = "%s")
    @SerialEntry
    public float mediumWindFrequency = 0.010f;

    @AutoGen(category = "general", group = "frequency")
    @FloatField(min = 0, max = 1, format = "%s")
    @SerialEntry
    public float highWindFrequency = 0.015f;
}
