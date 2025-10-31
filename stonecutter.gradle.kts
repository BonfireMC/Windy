plugins {
    id("dev.kikugie.stonecutter")
}
stonecutter active "1.20-fabric"

allprojects {
    repositories {
        maven("https://maven.isxander.dev/releases")
        maven("https://maven.terraformersmc.com/")
    }
}
