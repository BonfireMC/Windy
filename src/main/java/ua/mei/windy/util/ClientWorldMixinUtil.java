package ua.mei.windy.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.biome.Biome;
import ua.mei.windy.config.WindyConfig;
import ua.mei.windy.registry.WindyParticles;
import ua.mei.windy.tags.WindyBiomeTags;

public class ClientWorldMixinUtil {
    @Environment(EnvType.CLIENT)
    public static void tryAddWindParticle(ClientWorld world, BlockPos pos, Random random) {
        WindyConfig config = WindyConfig.HANDLER.instance();

        if (!config.shouldDisplayWind || pos.getY() < config.minimumWindHeight) return;
        if (config.windMustSeeSky && !world.isSkyVisible(pos)) return;
        RegistryEntry<Biome> biomeEntry = world.getBiome(pos);
        if (biomeEntry.isIn(WindyBiomeTags.NO_WIND)) return;

        double chance;
        if (biomeEntry.isIn(WindyBiomeTags.LESS_WIND)) {
            chance = config.lowWindFrequency;
        } else if (biomeEntry.isIn(WindyBiomeTags.MORE_WIND)) {
            chance = config.highWindFrequency;
        } else {
            chance = config.mediumWindFrequency;
        }

        if (random.nextFloat() <= chance * 0.015) {
            world.addParticle(WindyParticles.WIND,
                    pos.getX() + random.nextDouble(),
                    pos.getY() + random.nextDouble(),
                    pos.getZ() + random.nextDouble(),
                    0.0D, 0.0D, 0.0D);
        }
    }
}
