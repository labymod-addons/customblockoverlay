buildscript {
    repositories {
        var bearerToken = System.getenv("LABYMOD_BEARER_TOKEN")

        if (bearerToken == null && project.hasProperty("net.labymod.distributor.bearer-token")) {
            bearerToken = project.property("net.labymod.distributor.bearer-token").toString()
        }

        maven("https://dist.labymod.net/api/v1/maven/release/") {
            name = "LabyMod Distributor"

            authentication {
                create<HttpHeaderAuthentication>("header")
            }

            credentials(HttpHeaderCredentials::class) {
                name = "Authorization"
                value = "Bearer $bearerToken"
            }
        }


        maven("https://repo.spongepowered.org/repository/maven-public") {
            name = "SpongePowered Repository"
        }
        mavenLocal()
    }

    dependencies {
        classpath("net.labymod.gradle", "addon", "0.2.34")
    }
}

plugins {
    id("java-library")
}

group = "org.example"
version = "1.0.0"

plugins.apply("net.labymod.gradle.addon")

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

subprojects {
    plugins.apply("java-library")
    plugins.apply("net.labymod.gradle.addon")

    repositories {
        maven("https://libraries.minecraft.net/")
        maven("https://repo.spongepowered.org/repository/maven-public/")
        mavenLocal()
    }
}

addon {
    addonInfo {
        namespace("customblockoverlay")
        displayName("CustomBlockOverlay")
        author("LabyMedia GmbH")
        version(System.getenv().getOrDefault("VERSION", "0.0.0"))
    }

    dev {
        releaseChannel = "improvement-addon-api"
        commitReference = "unknown"
    }

    internalRelease()
}