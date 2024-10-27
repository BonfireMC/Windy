package ua.mei.windy.tags;

import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import ua.mei.windy.WindyMod;

public class WindyBiomeTags {
    public static final TagKey<Biome> LESS_WIND = biomeTag("less_wind");
    public static final TagKey<Biome> MORE_WIND = biomeTag("more_wind");
    public static final TagKey<Biome> NO_WIND = biomeTag("no_wind");

    public static TagKey<Biome> biomeTag(String name) {
        return TagKey.of(RegistryKeys.BIOME, Identifier.of(WindyMod.MOD_ID, name));
    }
}
