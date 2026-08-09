pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()

        maven("https://maven.fabricmc.net/")
        maven("https://maven.neoforged.net/releases/")
        maven("https://maven.kikugie.dev/releases")
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.8.2"
}

stonecutter {
    kotlinController = true
    centralScript = "build.gradle.kts"

    create(rootProject) {
        fun mc(mcVersion: String, name: String = mcVersion, loaders: Iterable<String>) {
            for (loader in loaders) {
                version("$name-$loader", mcVersion)
            }
        }

        mc("26.1", loaders = listOf("fabric", "neoforge"))
        mc("1.21.11", loaders = listOf("fabric", "neoforge"))
        mc("1.21.9", loaders = listOf("fabric", "neoforge"))
        mc("1.21", loaders = listOf("fabric", "neoforge"))
        mc("1.20", loaders = listOf("fabric", "forge"))
    }
}

rootProject.name = "Windy"
