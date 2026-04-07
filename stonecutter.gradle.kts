plugins {
    id("dev.kikugie.stonecutter")
    id("fabric-loom") version "1.15-SNAPSHOT" apply false
}
stonecutter active "26.1-neoforge"

allprojects {
    repositories {
        maven("https://maven.isxander.dev/releases")
        maven("https://maven.terraformersmc.com/")
    }
}
