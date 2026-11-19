pluginManagement {
    repositories {
        providers.gradleProperty("canopyRepository").orNull?.let { maven { url = uri(it) } }
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        providers.gradleProperty("canopyRepository").orNull?.let { maven { url = uri(it) } }
        mavenLocal()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") { from(files("gradle/version.toml")) }
    }
}

rootProject.name = "pocket-aquarium"
