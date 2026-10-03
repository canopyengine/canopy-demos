plugins {
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktlint)
    application
}

group = "io.github.canopy.demos"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(libs.canopy.engine)
    implementation(libs.canopy.platforms.terminal)
    runtimeOnly(libs.logback.classic)
    testImplementation(kotlin("test"))
}

kotlin { jvmToolchain(25) }

application {
    mainClass.set("io.github.canopy.demos.ecosystem.EcosystemDemoKt")
}

tasks.test { useJUnitPlatform() }

tasks.withType<JavaExec>().configureEach {
    jvmArgs("--enable-native-access=ALL-UNNAMED")
}
