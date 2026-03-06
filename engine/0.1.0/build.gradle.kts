
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
    implementation(libs.canopy.app.headless)
    implementation(libs.canopy.input)
    implementation(libs.canopy.data.core)

    implementation(libs.logback.classic)

    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(24)
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType(JavaExec::class).configureEach {
    jvmArgs("--enable-native-access=ALL-UNNAMED")
}
