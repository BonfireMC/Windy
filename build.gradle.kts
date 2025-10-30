plugins {
    id("dev.isxander.modstitch.base") version "0.7.1-unstable"
}

fun prop(name: String, consumer: (prop: String) -> Unit) {
    (findProperty(name) as? String?)
        ?.let(consumer)
}

val windyVersion = property("mod_version") as String
val minecraft = property("deps.minecraft") as String
val packFormat = property("pack.format") as String

modstitch {
    minecraftVersion = minecraft
    javaVersion = 21

    parchment {
        prop("deps.parchment") { mappingsVersion = it }
    }

    metadata {
        modId = "windy"
        modName = "Windy"
        modVersion = "$windyVersion+$minecraft"
        modGroup = "xyz.bonfiremc"
        modAuthor = "BonfireMC"
        modLicense = "LGPL-3.0"

        replacementProperties.put("pack_format", packFormat)
        replacementProperties.put("mod_version_short", windyVersion)

        prop("meta.mc") { replacementProperties.put("mc", it) }
    }

    loom {
        fabricLoaderVersion = "0.17.3"

        configureLoom {
            runs {
                register("modClient") {
                    client()
                    name = "Mod Client"
                    source(sourceSets.main.name)
                    ideConfigGenerated(true)
                    runDir("../../run")
                }
            }
        }
    }

    moddevgradle {
        prop("deps.neoforge") { neoForgeVersion = it }
        prop("deps.forge") { forgeVersion = it }

        defaultRuns()
    }

    mixin {
        addMixinsToModManifest = true

        configs.register("windy")
    }
}

dependencies {
    val platform: String = when {
        modstitch.isLoom -> "fabric"
        modstitch.isModDevGradleRegular -> "neoforge"
        modstitch.isModDevGradleLegacy -> "forge"
        else -> ""
    }

    prop("deps.yacl") { modstitchModImplementation("dev.isxander:yet-another-config-lib:$it-$platform") }

    modstitch.loom {
        prop("deps.fabric") { modstitchModImplementation("net.fabricmc.fabric-api:fabric-api:$it") }
        prop("deps.modmenu") { modstitchModImplementation("com.terraformersmc:modmenu:$it") }
    }
}

stonecutter {
    constants {
        put("fabric", modstitch.isLoom)
        put("neoforge", modstitch.isModDevGradleRegular)
        put("forge", modstitch.isModDevGradleLegacy)
        put("forgelike", modstitch.isModDevGradle)
    }
}