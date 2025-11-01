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

val windyPlatform: String = when {
    modstitch.isLoom -> "fabric"
    modstitch.isModDevGradleRegular -> "neoforge"
    else -> ""
}

modstitch {
    minecraftVersion = minecraft

    parchment {
        prop("parchment.minecraft") { minecraftVersion = it }
        prop("parchment.version") { mappingsVersion = it }
    }

    metadata {
        modId = "windy"
        modName = "Windy"
        modDescription = "Wind particle from Breezy mod"
        modVersion = "$windyVersion+$minecraft-$windyPlatform"
        modGroup = "xyz.bonfiremc"
        modAuthor = "BonfireMC"
        modLicense = "LGPL-3.0"

        replacementProperties.put("pack_format", packFormat)
        replacementProperties.put("github", "https://github.com/BonfireMC/Windy")

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

        defaultRuns()
    }

    mixin {
        addMixinsToModManifest = true

        configs.register("windy")
    }
}

dependencies {
    prop("deps.yacl") {
        val url: String = when {
            minecraft == "1.20" -> "dev.isxander.yacl:yet-another-config-lib-$windyPlatform:$it"
            else -> "dev.isxander:yet-another-config-lib:$it-$windyPlatform"
        }

        modstitchModImplementation(url) {
            if (minecraft == "1.20") {
                // 3.10.0-SNAPSHOT no longer available
                exclude(group = "com.twelvemonkeys.imageio")
                exclude(group = "com.twelvemonkeys.common")
            }

            if (modstitch.isModDevGradleRegular) {
                exclude(group = "thedarkcolour", module = "kotlinforforge-neoforge")
            }
        }
    }

    modstitch.loom {
        prop("deps.fabric") { modstitchModImplementation("net.fabricmc.fabric-api:fabric-api:$it") }
        prop("deps.modmenu") { modstitchModImplementation("com.terraformersmc:modmenu:$it") }
    }
}

stonecutter {
    constants {
        put("fabric", modstitch.isLoom)
        put("neoforge", modstitch.isModDevGradleRegular)
    }
}