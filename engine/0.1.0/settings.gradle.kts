pluginManagement {
    repositories {
        providers.gradleProperty("canopyRepository").orNull?.let { repository ->
            maven { url = uri(repository) }
        }
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
rootProject.name = "0.1.0"
