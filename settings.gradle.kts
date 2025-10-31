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
    id("dev.kikugie.stonecutter") version "0.7.10"
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

        mc("1.21.9", loaders = listOf("fabric"))
        mc("1.21", loaders = listOf("fabric"))
        mc("1.20", loaders = listOf("fabric"))
    }
}

rootProject.name = "Windy"
