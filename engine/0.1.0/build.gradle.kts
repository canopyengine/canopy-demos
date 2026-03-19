import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.jvm)
}

group = "io.github.canopy.demos"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    // Canopy
    implementation(libs.canopy.engine)
    implementation(libs.canopy.platforms.terminal)

    // Logging
    runtimeOnly(libs.logback.classic)

    // Testing
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain{
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType(JavaExec::class).configureEach {
    jvmArgs("--enable-native-access=ALL-UNNAMED")
}
