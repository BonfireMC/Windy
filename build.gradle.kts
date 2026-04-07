plugins {
    id("net.fabricmc.fabric-loom") version "1.16-SNAPSHOT"
}

val modVersion: String by project

version = "$modVersion+${libs.versions.minecraft.get()}"
group = "xyz.bonfiremc"

repositories {
    maven("https://maven.isxander.dev/releases")
    maven("https://maven.terraformersmc.com/")
}

dependencies {
    minecraft(libs.minecraft)

    implementation(libs.fabric.loader)
    implementation(libs.fabric.api)

    implementation(libs.yacl)
    implementation(libs.modmenu)
}

java {
    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25
}

tasks {
    processResources {
        val minecraftVersion: String = libs.versions.minecraft.get()

        inputs.property("version", version)
        inputs.property("minecraft_version", minecraftVersion)

        filesMatching("fabric.mod.json") {
            expand(
                "version" to version,
                "minecraft_version" to minecraftVersion
            )
        }
    }

    jar {
        from("LICENSE")
    }
}
